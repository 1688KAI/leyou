package com.leyou.page.web;

import com.leyou.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("item")
public class PageController {

    @Autowired
    PageService pageService;

    @GetMapping("test")
    public String test(Model model, @PathVariable("id")Long id){
        return "item";
    }

    /**
     * 跳转到商品详情页
     * @param model
     * @param id
     * @return
     */
    @GetMapping("{id}.html")
    public String toItemPage(Model model, @PathVariable("id")Long id){
        //查询模型数据
        Map<String, Object> attributes = pageService.loadModel(id);
        //准备模型s数据
        model.addAllAttributes(attributes);
        //返回视图
        return "item";
    }
}