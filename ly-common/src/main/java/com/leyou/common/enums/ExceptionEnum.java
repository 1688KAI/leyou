package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum{
    PRICE_CANNOT_BE_NULL(HttpStatus.NOT_FOUND.value(), "价格不能为空"),
    CATEGORY_NOT_FOND(HttpStatus.NOT_FOUND.value(), "商品没有找到");

    private int code;
    private String msg;
}
