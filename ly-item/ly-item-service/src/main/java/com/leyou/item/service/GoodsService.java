package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.service.pojo.Spu;

public interface GoodsService {
    PageResult<Spu> queryGoodsByPage(String key, Boolean saleable, Integer page, Integer rows);


}
