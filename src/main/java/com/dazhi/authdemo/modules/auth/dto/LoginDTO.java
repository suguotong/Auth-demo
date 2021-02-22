package com.dazhi.authdemo.modules.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新增验证码传输类
 */
@Data
public class LoginDTO {

    @NotBlank(message = "用户名不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "角色不能为空")
    private Integer roleId;

    private String oldPwd;
    private String token;
}
