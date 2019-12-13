package com.leyou.item.web;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 根据父节点ID查询商品分类
     *
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<Collection<Category>> queryCategoryListByPid(@RequestParam("pid") Long pid) {
        return ResponseEntity.ok(categoryService.queryCategoryListByPid(pid));
    }
}
