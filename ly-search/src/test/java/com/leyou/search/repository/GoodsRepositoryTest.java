//package com.leyou.search.repository;
//
//import com.leyou.common.enums.ExceptionEnum;
//import com.leyou.common.exception.LyException;
//import com.leyou.common.vo.PageResult;
//import com.leyou.item.pojo.Spu;
//import com.leyou.search.client.GoodsClient;
//import com.leyou.search.pojo.Goods;
//import com.leyou.search.service.SearchService;
//import com.leyou.search.service.impl.SearchServiceImpl;
//import org.aspectj.weaver.ast.Var;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class GoodsRepositoryTest {
//
//    @Autowired
//    GoodsRepository repository;
//
//    @Autowired
//    ElasticsearchTemplate template;
//
//
//    @Autowired
//    GoodsClient goodsClient;
//
//
//    @Autowired
//    SearchServiceImpl searchService;
//    @Test
//
//    public void testCreateIndex() {
//        template.createIndex(Goods.class);
//        template.putMapping(Goods.class);
//    }
//
//    @Test
//    public void loadDate() {
//        int page = 1;
//        int rows = 100;
//        int size = 0;
//
//        do {
//            PageResult<Spu>  result= goodsClient.querySpuByPage(null, true, page, rows);
//            if (CollectionUtils.isEmpty(result.getItems())) {
//                break;
//            }
//            //查询spu 信息
//            List<Spu> spuList = result.getItems();
//            //构建goods
//            List<Goods> goodsList = spuList.stream().map(searchService::buildGoods).collect(Collectors.toList());
//            //存入索引库
//            repository.saveAll(goodsList);
//            page++;
//            size =spuList.size();
//        }while (size==100);
//    }
//}