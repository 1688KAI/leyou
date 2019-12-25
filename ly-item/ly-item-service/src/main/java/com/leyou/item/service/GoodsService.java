package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.service.pojo.Sku;
import com.leyou.service.pojo.Spu;
import com.leyou.service.pojo.SpuDetail;

import java.util.List;

public interface GoodsService {
    PageResult<Spu> queryGoodsByPage(String key, Boolean saleable, Integer page, Integer rows);



    void saveGoods(Spu spu);

    SpuDetail querySpuDetailById(Long id);


    List<Sku> querySkuBySpuId(Long id);

    void updateGoods(Spu spu);

    void deleteGoods(Spu spu);
}
