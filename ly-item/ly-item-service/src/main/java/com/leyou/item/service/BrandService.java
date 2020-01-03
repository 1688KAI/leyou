package com.leyou.item.service;

import com.leyou.item.pojo.Brand;
import com.leyou.common.vo.PageResult;

import java.util.List;

public interface BrandService {
    PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    void saveBrand(Brand brand, List<Long> cids);

    List<Brand> queryByBrandByCid(Long cid);

    Brand getBrandById(Long id);


    List<Brand> queryBrandByIds(List<Long> ids);
}
