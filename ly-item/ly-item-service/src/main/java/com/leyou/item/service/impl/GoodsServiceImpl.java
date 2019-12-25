package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.GoodsMapper;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.StockMapper;
import com.leyou.item.service.*;
import com.leyou.service.pojo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;


    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;


    @Autowired
    SkuMapper skuMapper;


    @Autowired

    SpuDetailMapper spuDetailMapper;

    @Autowired
    StockMapper stockMapper;

    @Override
    public PageResult<Spu> queryGoodsByPage(String key, Boolean saleable, Integer page, Integer rows) {
        //分页
        PageHelper.startPage(page, rows);
        //过滤

        Example example = new Example(Spu.class);
        //搜索条件过滤
        Example.Criteria criteria = example.createCriteria();
        if (Strings.isNotBlank(key)) {
            criteria.orLike("title", "%" + key + "%");
        }
        //上下架
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        //排序
        example.setOrderByClause("last_update_time DESC");
        List<Spu> spus = goodsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(spus)) {
            throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
        }
        loadCategoryAndBrandName(spus);
        //解析分页结果
        PageInfo<Spu> info = new PageInfo<>();

        return new PageResult<>(info.getTotal(), spus);
    }

    private void loadCategoryAndBrandName(List<Spu> spus) {
        for (Spu spu : spus) {
            //处理分类名称
            List<String> cnames = categoryService.queryCategoryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join(cnames, "/"));
            //处理品牌名称
            spu.setBname(brandService.getBrandById(spu.getBrandId()).getName());
        }
    }


    @Override
    public SpuDetail querySpuDetailById(Long id) {
        SpuDetail spuDetail = this.spuDetailMapper.selectByPrimaryKey(id);
        if (spuDetail == null) {
            throw new LyException(ExceptionEnum.GOODS_DETAIL_NOT_FOUND);
        }
        return spuDetail;
    }


    @Override
    public List<Sku> querySkuBySpuId(Long id) {
        Sku sku = new Sku();
        sku.setSpuId(id);
        List<Sku> skuList = skuMapper.select(sku);
        if (CollectionUtils.isEmpty(skuList)) {
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOUND);
        }

        List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
        List<Stock> stockList = stockMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(stockList)) {
            throw new LyException(ExceptionEnum.GOODS_STOCK_NOT_FOUND);
        }
        Map<Long, Integer> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
        skuList.forEach(s -> s.setStock(stockMap.get(s.getId())));
        return skuList;
    }

    @Override
    @Transactional
    public void updateGoods(Spu spu) {
        if (null == spu.getId()) {
            throw new LyException(ExceptionEnum.GOODS_ID_CANNOT_NULL);
        }
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        //查询
        List<Sku> skuList = skuMapper.select(sku);
        if (!CollectionUtils.isEmpty(skuList)) {
            //删除sku
            skuMapper.delete(sku);
            List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
            //删除库存
            stockMapper.deleteByIdList(ids);
        }
        //修改spu
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(new Date());
        spu.setSaleable(true);
        spu.setValid(false);
        int row = goodsMapper.updateByPrimaryKeySelective(spu);
        if (row != 1) {
            throw new LyException(ExceptionEnum.UPDATE_GOODS_ERROR);
        }
        //修改detail
        row = this.spuDetailMapper.updateByPrimaryKeySelective(spu.getSpuDetail());
        if (row != 1) {
            throw new LyException(ExceptionEnum.UPDATE_GOODS_ERROR);
        }
        //新增sku和库存
        saveSkuAndStock(spu.getSkus(), spu.getId());
    }

    @Override
    public void deleteGoods(Spu spu) {
        if (null == spu.getId()) {
            throw new LyException(ExceptionEnum.GOODS_ID_CANNOT_NULL);
        }
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        //查询
        List<Sku> skuList = skuMapper.select(sku);
        if (!CollectionUtils.isEmpty(skuList)) {
            //删除sku
            skuMapper.delete(sku);
            List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
            //删除库存
            stockMapper.deleteByIdList(ids);
        }
        int row = goodsMapper.deleteByPrimaryKey(spu);
        if (row != 1) {
            throw new LyException(ExceptionEnum.DELETE_GOODS_ERROR);
        }
        //修改detail
        row = this.spuDetailMapper.deleteByPrimaryKey(spu.getSpuDetail());
        if (row != 1) {
            throw new LyException(ExceptionEnum.DELETE_GOODS_ERROR);
        }
    }


    @Transactional
    @Override
    public void saveGoods(Spu spu) {
        //新增spu
        spu.setId(null);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(new Date());
        spu.setSaleable(true);
        spu.setValid(false);
        int row = goodsMapper.insert(spu);
        if (row != 1) {
            throw new LyException(ExceptionEnum.ADD_GOODS_ERROR);
        }
        //新增detail
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());

        spuDetailMapper.insert(spuDetail);
        // 保存sku和库存信息
        saveSkuAndStock(spu.getSkus(), spu.getId());
    }

    private void saveSkuAndStock(List<Sku> skus, Long spuId) {
        ArrayList<Stock> stockList = new ArrayList<>();
        for (Sku sku : skus) {
            if (!sku.getEnable()) {
                continue;
            }
            // 保存sku
            sku.setSpuId(spuId);
            // 初始化时间
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            this.skuMapper.insert(sku);

            // 保存库存信息
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockList.add(stock);
        }
        this.stockMapper.insertList(stockList);
    }


}
