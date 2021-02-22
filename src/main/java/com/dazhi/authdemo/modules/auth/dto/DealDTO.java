package com.dazhi.authdemo.modules.auth.dto;

import lombok.Data;
import java.util.List;

@Data
public class DealDTO {
    // 中心id
    private Integer centerId;
    // 经销商id
    private Long jsxAccountId;
    // 三工合伙人id
    private String sgAccountId;

    private List<ProductDTO> productList;

    // 客户
    private String customerName;
    private String telephone;
    private String address;
}
