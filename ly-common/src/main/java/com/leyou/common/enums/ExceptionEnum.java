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
    GOODS_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "商品详情不存在"),
    GOODS_SKU_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "商品sku不存在"),
    GOODS_STOCK_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "商品库存不存在"),
    GOODS_ID_CANNOT_NULL(HttpStatus.NOT_FOUND.value(), "商品id不能为null"),
    ADD_GOODS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商品添加失败"),
    UPDATE_GOODS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商品更新失败"),
    DELETE_GOODS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商品更新失败"),
    ;
    private int code;
    private String msg;
}
