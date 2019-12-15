package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.service.GoodsService;
import com.leyou.service.pojo.Spu;
import com.leyou.service.vo.SpuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供商品的增删改查接口
 * 操作的表有：
 * tb_spu
 * tb_spu_detail
 * tb_sku
 * tb_stock
 */
@Slf4j
@RestController
@RequestMapping
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     *分页查询spu
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(@RequestParam(value = "key", required = false) String key,
                                                            @RequestParam(value = "saleable", required = false) Boolean saleable,
                                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        return ResponseEntity.ok(goodsService.queryGoodsByPage(key, saleable, page, rows));
    }
}
