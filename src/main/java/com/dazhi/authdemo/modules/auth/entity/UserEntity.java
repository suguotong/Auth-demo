package com.dazhi.authdemo.modules.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    @GeneratedValue
    private Integer id;
    /**
     * username 用户名
     */
    private String account;
    /**
     * password 密码
     */
    private String password;

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

    private String telephone;

    private LocalDateTime createTime;

    private String jxs;

    private String jxsId;
}
