package com.dazhi.authdemo.modules.auth.controller;

import com.dazhi.authdemo.common.utils.Result;
import com.dazhi.authdemo.common.utils.TokenUtil;
import com.dazhi.authdemo.modules.auth.dao.ProductModelRepository;
import com.dazhi.authdemo.modules.auth.dto.DealDTO;
import com.dazhi.authdemo.modules.auth.dto.LoginDTO;
import com.dazhi.authdemo.modules.auth.entity.ProductEntity;
import com.dazhi.authdemo.modules.auth.entity.ProductModelEntity;
import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import com.dazhi.authdemo.modules.auth.service.AuthService;
import com.dazhi.authdemo.modules.auth.service.ProductService;
import com.dazhi.authdemo.modules.auth.vo.CenterVO;
import com.dazhi.authdemo.modules.auth.vo.TokenVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录校验
 */
@RestController("/")
public class AuthController   {

    private final AuthService authService;

    private final ProductService productService;

    public AuthController(AuthService authService,ProductService productService) {
        this.authService = authService;
        this.productService = productService;
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

    @PostMapping("/getProduct")
    public Result getProduct() {
        List<ProductEntity> all = productService.findAll();
        return Result.ok(all);
    }

    @PostMapping("/getProductModel")
    public Result getProduct( @RequestBody ProductModelEntity productModelEntity) {
        List<ProductModelEntity> allByProductId = productService.findAllByProductId(productModelEntity.getId());
        return Result.ok(allByProductId);
    }

    // 保存订单
    @PostMapping("/saveDeal")
    public Result saveDeal( @RequestBody  DealDTO dealDTO) {
        String msg = productService.saveDeal(dealDTO);
        if (StringUtils.isEmpty(msg)){
            return Result.ok();
        }else {
            return Result.build(500,msg);
        }
    }

    // 中心查询（中心）
    @PostMapping("/getCenterList")
    public Result getCenterList(@RequestBody CenterVO centerVO) {
        List<CenterVO> allCenter = authService.getCenterList(centerVO);
        return Result.ok(allCenter);
    }

    // 查询中心的经销商（中心）
    @PostMapping("/getJxsList")
    public Result getJxsList(@RequestBody CenterVO centerVO) {
        List<CenterVO> allCenter = authService.getJxsList(centerVO);
        return Result.ok(allCenter);
    }

    // 查询合伙人列表(中心)
    @PostMapping("/getHhrList")
    public Result getHhrList(@RequestBody CenterVO centerVO) {
        List<CenterVO> allCenter = authService.getHhrList(centerVO);
        return Result.ok(allCenter);
    }

    // 经销商查询


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


