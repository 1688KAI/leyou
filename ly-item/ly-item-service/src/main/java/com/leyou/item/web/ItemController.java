package com.leyou.item.web;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.pojo.Item;
import com.leyou.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> saveItem(Item item) {
        if (null == item.getPrice()) {
            throw new LyException(ExceptionEnum.PRICE_CANNOT_BE_NULL);
        }
        item = itemService.saveItem(item);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

}
