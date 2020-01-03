package com.leyou.search.web;

import com.leyou.item.pojo.Category;
import com.leyou.search.client.CategoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class CategoryController {
    @Autowired
    CategoryClient categoryClient;
    @GetMapping("/all/level")
    ResponseEntity<List<Category>> queryAllByCid3(@RequestParam("id") Long id){
        return ResponseEntity.ok(categoryClient.queryAllByCid3(id));
    }
}
