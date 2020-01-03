package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryService {
    Collection<Category> queryCategoryListByPid(Long pid);

    public List<Category> queryCategoryByIds(List<Long> ids);

    List<Category> queryAllByCid3(Long id);
}
