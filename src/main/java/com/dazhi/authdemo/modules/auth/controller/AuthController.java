package com.dazhi.authdemo.modules.auth.controller;

import com.dazhi.authdemo.common.utils.Result;
import com.dazhi.authdemo.common.utils.TokenUtil;
import com.dazhi.authdemo.modules.auth.dto.LoginDTO;
import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import com.dazhi.authdemo.modules.auth.service.AuthService;
import com.dazhi.authdemo.modules.auth.vo.TokenVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录校验
 */
@RestController("/")
public class AuthController   {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 登录
     *
     * @param loginDTO
     * @return token登录凭证
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, bindingResult.getFieldError().getDefaultMessage());
        }
        String username = loginDTO.getAccount();
        String password = loginDTO.getPassword();
        Integer roleId = loginDTO.getRoleId();
        //用户信息
        UserEntity user = authService.findByUsername(username);
        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(password)) {
            return Result.build(400, "用户名或密码错误");
        } else {
            if(!user.getRoleId().equals(roleId)){
                return Result.build(400, "登录账号与角色不匹配");
            }
            //生成token，并保存到数据库
            String token = authService.createToken(user);
            TokenVO tokenVO = new TokenVO();
            tokenVO.setToken(token);
            return Result.ok(tokenVO);
        }
    }



    /**
     * 登出
     *
     * @param
     * @return
     */
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        //从request中取出token
        String token = TokenUtil.getRequestToken(request);
        authService.logout(token);
        return Result.ok();
    }

    @PostMapping("/getUser")
    public Result getUser(HttpServletRequest request) {
        //从request中取出token
        String token = TokenUtil.getRequestToken(request);
        UserEntity user = authService.findByToken(token);
        return Result.ok(user);
    }

    @PostMapping("/regesterUser")
    public Result regesterUser(UserEntity user) {
        UserEntity userInfo = authService.regester(user);
        return Result.ok(userInfo);
    }

    /**
     * 测试
     *
     * @param
     * @return
     */
    @PostMapping("/test")
    public Result test(String token) {
        return Result.ok("恭喜你，验证成功啦，我可以返回数据给你");
    }
}


