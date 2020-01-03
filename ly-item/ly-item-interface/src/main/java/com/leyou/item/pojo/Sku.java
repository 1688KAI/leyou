package com.leyou.item.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "tb_sku")
public class Sku implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * sku id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    /**
     * spu id
     */
    @Column(name = "spu_id")
    private Long spuId;
    /**
     * 商品标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 商品的图片，多个图片以‘,’分割
     */
    @Column(name = "images")
    private String images;
    /**
     * 销售价格，单位为分
     */
    @Column(name = "price")
    private Long price;
    /**
     * 特有规格属性在spu属性模板中的对应下标组合
     */
    @Column(name = "indexes")
    private String indexes;
    /**
     * sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序
     */
    @Column(name = "own_spec")
    private String ownSpec;
    /**
     * 是否有效，0无效，1有效
     */
    @Column(name = "`enable`")
    private Boolean enable;
    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 最后修改时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
    /**
     *
     * 库存
     */
    @Transient
    private Integer stock;
}