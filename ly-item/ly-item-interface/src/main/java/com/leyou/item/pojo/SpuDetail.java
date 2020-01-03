package com.leyou.item.pojo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "tb_spu_detail")
public class SpuDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long spuId;
    /**
     * 商品描述信息
     */
    @Column(name = "description")
    private String description;
    /**
     * 通用规格参数数据
     */
    @Column(name = "generic_spec")
    private String genericSpec;
    /**
     * 特有规格参数及可选值信息，json格式
     */
    @Column(name = "special_spec")
    private String specialSpec;
    /**
     * 包装清单
     */
    @Column(name = "packing_list")
    private String packingList;
    /**
     * 售后服务
     */
    @Column(name = "after_service")
    private String afterService;
}