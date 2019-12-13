package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.Collection;

public interface CategoryService {
//    List<Category> queryCategoryListByPid(Long pid);
    Collection<Category> queryCategoryListByPid(Long pid);
}
