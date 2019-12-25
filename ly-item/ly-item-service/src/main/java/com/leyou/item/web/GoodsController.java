package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.service.GoodsService;
import com.leyou.service.pojo.Sku;
import com.leyou.service.pojo.Spu;
import com.leyou.service.pojo.SpuDetail;
import com.leyou.service.vo.SpuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询spu
     *
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


    /**
     * 新增商品
     *
     * @param spu
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody Spu spu) {
        this.goodsService.saveGoods(spu);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /**
     * 更新商品
     *
     * @param spu
     * @return
     */
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu) {
        this.goodsService.updateGoods(spu);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /**
     * 删除商品
     * @param spu
     * @return
     */
    @DeleteMapping("goods")
    public ResponseEntity<Void> deleteGoods(@RequestBody Spu spu) {
        this.goodsService.deleteGoods(spu);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /**
     * 根据spu的ID 查询 detail
     *
     * @param id
     * @return
     */
    @GetMapping("/spu/detail/{id}")
    public ResponseEntity<SpuDetail> querySpuDetailById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.goodsService.querySpuDetailById(id));
    }


    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id")Long id) {
        return ResponseEntity.ok(  goodsService.querySkuBySpuId(id));
    }
}
