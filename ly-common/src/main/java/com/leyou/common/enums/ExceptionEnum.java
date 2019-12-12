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
    PRICE_CANNOT_BE_NULL1(HttpStatus.NOT_FOUND.value(), "价格不能为空"),
    PRICE_CANNOT_BE_NULL2(HttpStatus.NOT_FOUND.value(), "价格不能为空"),
    PRICE_CANNOT_BE_NULL3(HttpStatus.NOT_FOUND.value(), "价格不能为空");
    private int code;
    private String msg;
}
