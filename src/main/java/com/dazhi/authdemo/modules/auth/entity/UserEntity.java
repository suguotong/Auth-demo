package com.dazhi.authdemo.modules.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 验证码实体类
 */
@Data
@Entity
public class UserEntity {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

    private BigDecimal syScore;

    // 成交额
    private BigDecimal money;


    // 经销商 数量
    private BigInteger jxsNum;
    // 合伙人数量
    private BigInteger hsrNum;
    // 带单数量
    private BigInteger orderNum;

    private Integer xingJi;
}
