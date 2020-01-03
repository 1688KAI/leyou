package com.leyou.item.pojo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_stock")
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 库存对应的商品sku id
     */
    @Id

    private Long skuId;
    /**
     * 可秒杀库存
     */
    @Column(name = "seckill_stock")
    private Integer seckillStock;
    /**
     * 秒杀总数量
     */
    @Column(name = "seckill_total")
    private Integer seckillTotal;
    /**
     * 库存数量
     */
    @Column(name = "stock")
    private Integer stock;
}