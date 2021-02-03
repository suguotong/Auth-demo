package com.dazhi.authdemo.modules.auth.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class CenterVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public CenterVO() {}

    public CenterVO(Integer centerId, String centerName, Integer jxsNum, Integer hsrNum, Integer orderNum) {
        this.centerId = centerId;
        this.centerName = centerName;
        this.jxsNum = jxsNum;
        this.hsrNum = hsrNum;
        this.orderNum = orderNum;
    }

    // 中心id
    private Integer centerId;
    // 中心 名称
    private String centerName;
    // 经销商 数量
    private Integer jxsNum;
    // 合伙人数量
    private Integer hsrNum;
    // 带单数量
    private Integer orderNum;

}
