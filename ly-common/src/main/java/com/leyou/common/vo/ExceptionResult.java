package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionResult {
    private Integer status;
    private String message;
    private Long timestamp;
    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message= em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
