package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.GoodsMapper;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.CategoryService;
import com.leyou.item.service.GoodsService;
import com.leyou.service.pojo.Brand;
import com.leyou.service.pojo.Category;
import com.leyou.service.pojo.Spu;
import com.leyou.service.vo.SpuVo;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;


    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;


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
}
