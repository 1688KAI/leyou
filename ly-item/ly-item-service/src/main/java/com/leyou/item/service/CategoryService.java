package com.leyou.item.service;

import com.leyou.service.pojo.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryService {
    Collection<Category> queryCategoryListByPid(Long pid);

    public List<Category> queryCategoryByIds(List<Long> ids);
}
