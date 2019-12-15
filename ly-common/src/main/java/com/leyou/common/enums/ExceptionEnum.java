package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum{
    BRAND_NOT_FOND(HttpStatus.NOT_FOUND.value(), "品牌不存在"),
    PICTURE_FORMAT_ERROR(HttpStatus.NOT_FOUND.value(), "品牌不存在"),
    CATEGORY_NOT_FOND(HttpStatus.NOT_FOUND.value(), "商品没有找到"),
    SPEC_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "规格参数组不存在"),
    SPEC_PARAM_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "规格参数不存在"),
    GOODS_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "商品不存在"),
    ;

    private int code;
    private String msg;
}
