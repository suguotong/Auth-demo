package com.dazhi.authdemo.modules.auth.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenterVOSS implements Serializable {
    private static final long serialVersionUID = 1L;

    // 中心id
    private String centerId;
    // 中心 名称
    private String centerName;
    // 经销商 数量
    private BigInteger jxsNum;
    // 合伙人数量
    private BigInteger hsrNum;
    // 带单数量
    private BigInteger orderNum;

}
