package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum{
    BRAND_NOT_FOND(404, "品牌不存在"),
    PICTURE_FORMAT_ERROR(404, "品牌不存在"),
    CATEGORY_NOT_FOND(404, "商品没有找到"),
    SPEC_GROUP_NOT_FOUND(404, "规格参数组不存在"),
    SPEC_PARAM_NOT_FOUND(404, "规格参数不存在"),
    GOODS_NOT_FOUND(404, "商品不存在"),
    GOODS_DETAIL_NOT_FOUND(404, "商品详情不存在"),
    GOODS_SKU_NOT_FOUND(404, "商品sku不存在"),
    GOODS_STOCK_NOT_FOUND(404, "商品库存不存在"),
    GOODS_ID_CANNOT_NULL(404, "商品id不能为null"),
    GOODS_KAY_CANNOT_NULL(404, "商品名称不能为null"),
    ADD_GOODS_ERROR(500, "商品添加失败"),
    UPDATE_GOODS_ERROR(500, "商品更新失败"),
    DELETE_GOODS_ERROR(500, "商品更新失败"),
    ;
    private int code;
    private String msg;
}
