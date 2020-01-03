package com.leyou.search.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Category;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.SearchRequest;
import com.leyou.search.pojo.SearchResult;
import com.leyou.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SearchController {


    @Autowired
    SearchService searchService;

    /**
     *
     * 分页搜索
     * @param request
     * @return
     */
    @PostMapping("page")
    public ResponseEntity<SearchResult> search(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(searchService.search(request));
    }


}
