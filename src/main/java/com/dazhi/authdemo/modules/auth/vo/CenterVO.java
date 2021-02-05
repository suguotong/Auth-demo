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
    // 经销商 88码
    private String jsxAccount;
    // 经销商name
    private String jsxName;
    // 经销商 数量
    private String jxsNum;
    // 合伙人数量
    private String hsrNum;
    // 带单数量
    private String orderNum;
    // 积分
    private String score;
    // 合伙人账号
    private String hhrAccount;
    // 用户姓名
    private String userName;
    // 电话
    private String telephone;
    // 时间
    private String time;

    // 地址
    private String address;
    // 密码
    private String password;

    // 状态 0 未审核 1 已审核 2 未审核
    private String status;

}
