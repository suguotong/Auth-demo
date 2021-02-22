package com.dazhi.authdemo.modules.auth.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    // 产品
    private String productName;
    private String model;
    private Integer number;
    private BigDecimal price;
}
