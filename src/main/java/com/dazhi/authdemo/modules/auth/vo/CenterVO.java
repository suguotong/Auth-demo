package com.dazhi.authdemo.modules.auth.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class CenterVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public CenterVO() {}

    public CenterVO(String centerId, String centerName, String jxsNum, String hsrNum, String orderNum) {
        this.centerId = centerId;
        this.centerName = centerName;
        this.jxsNum = jxsNum;
        this.hsrNum = hsrNum;
        this.orderNum = orderNum;
    }

    // 中心id
    private String centerId;
    // 中心 名称
    private String centerName;
    // 经销商 数量
    private String jxsNum;
    // 合伙人数量
    private String hsrNum;
    // 带单数量
    private String orderNum;

}
