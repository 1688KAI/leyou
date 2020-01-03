package com.leyou.search.service;

import com.leyou.common.vo.PageResult;
import com.leyou.search.pojo.SearchRequest;
import com.leyou.search.pojo.SearchResult;

public interface SearchService {
    SearchResult search(SearchRequest request);
}
