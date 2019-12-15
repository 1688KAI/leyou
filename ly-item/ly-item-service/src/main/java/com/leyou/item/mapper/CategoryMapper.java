package com.leyou.item.mapper;

import com.leyou.service.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface CategoryMapper extends Mapper<Category>, IdListMapper<Category,Long> {

}
