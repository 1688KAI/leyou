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
    CATEGORY_NOT_FOND(HttpStatus.NOT_FOUND.value(), "商品没有找到"),;

    private int code;
    private String msg;
}
