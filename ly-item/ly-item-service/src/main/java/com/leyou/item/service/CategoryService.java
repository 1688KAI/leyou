package com.leyou.item.service;

import com.leyou.service.pojo.Category;

import java.util.Collection;

public interface CategoryService {
//    List<Category> queryCategoryListByPid(Long pid);
    Collection<Category> queryCategoryListByPid(Long pid);
}
