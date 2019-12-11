package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum{
    PRICE_CANNOT_BE_NULL(400, "价格不能为空"),
    PRICE_CANNOT_BE_NULL1(400, "价格不能为空"),
    PRICE_CANNOT_BE_NULL2(400, "价格不能为空"),
    PRICE_CANNOT_BE_NULL3(400, "价格不能为空");
    private int code;
    private String msg;
}
