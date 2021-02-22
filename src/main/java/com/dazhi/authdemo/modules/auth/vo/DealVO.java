package com.dazhi.authdemo.modules.auth.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DealVO {
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

    private Date time;

}
