package com.dazhi.authdemo.modules.auth.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class RegeDto {
    /**
     * username 用户名
     */
    private String account;

    private String username;
    /**
     * password 密码
     */
    private String password;

    // 角色
    private Integer roleId;
    /**
     * token 登陆凭证
     */
    private String token;
    /**
     * token 过期时间
     */
    private LocalDateTime expireTime;
    /**
     *  登录时间
     */
    private LocalDateTime loginTime;


    private String birthday;

    private String address;

    // 地址
    private String telephone;

    private LocalDateTime createTime;

    // 中心id
    private Integer centerId;

    private String centerName;

    // 经销商名称
    private String jxs;

    // 经销商Id
    private Long jxsId;

    // 积分
    private BigDecimal score;

    // 成交额
    private BigDecimal money;


    // 经销商 数量
    private BigInteger jxsNum;
    // 合伙人数量
    private BigInteger hsrNum;
    // 带单数量
    private BigInteger orderNum;
}
