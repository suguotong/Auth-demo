package com.dazhi.authdemo.modules.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class DealEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    // 中心id
    private Integer centerId;
    // 经销商id
    private Long jsxAccountId;
    // 三工合伙人id
    private Long sgAccountId;

    // 产品
    private String productName;
    private String productModel;
    private Integer number;
    private BigDecimal price;

    // 客户
    private String customerName;
    private String telephone;
    private String address;

    private Integer status;

}
