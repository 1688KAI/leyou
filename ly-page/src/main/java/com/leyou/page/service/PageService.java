package com.leyou.page.service;

import com.leyou.item.pojo.*;
import com.leyou.page.client.BrandClient;
import com.leyou.page.client.CategoryClient;
import com.leyou.page.client.GoodsClient;
import com.leyou.page.client.SpecParamClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class PageService {

    @Autowired
    BrandClient brandClient;

    @Autowired
    CategoryClient categoryClient;

    @Autowired
    GoodsClient goodsClient;

    @Autowired
    SpecParamClient specParamClient;
    @Autowired
    TemplateEngine templateEngine;

    public Map<String, Object> loadModel(Long id) {

        Map<String, Object> map = new HashMap<>();


        //查询spu
        Spu spu = goodsClient.querySpuById(id);

        //查询skus
        List<Sku> skus = spu.getSkus();
        //查询详情
        SpuDetail detail = spu.getSpuDetail();
        //查询brand
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        //查询商品分类
        List<Category> categories = categoryClient.queryCategoryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        //查询商品规格参数
        List<SpecGroup> specs = specParamClient.queryGroupByCid(spu.getCid3());

        map.put("subTitle", spu.getSubTitle());
        map.put("title", spu.getTitle());
        map.put("spu", spu);
        map.put("skus", skus);
        map.put("brand", brand);
        map.put("categories", categories);
        map.put("specs", specs);
        map.put("detail", detail);
        return map;
    }

    public void createHtml(Long spuId) {
        Context context = new Context();

        context.setVariables(loadModel(spuId));
        File dest = new File("F:\\static",spuId+".html");
        try(PrintWriter writer = new PrintWriter(dest)){
            templateEngine.process("item", context, writer);
        }catch (Exception e){
            log.error("[页面静态化失败]"+e);
        }
    }
}
