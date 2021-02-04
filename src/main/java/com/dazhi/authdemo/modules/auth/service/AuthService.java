package com.dazhi.authdemo.modules.auth.service;


import com.dazhi.authdemo.modules.auth.dto.DealDTO;
import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import com.dazhi.authdemo.modules.auth.vo.CenterVO;

import java.util.List;

public interface AuthService {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    UserEntity findByUsername(String username);

    /**
     * 为user生成token
     * @param user
     * @return
     */
    String createToken(UserEntity user);

    UserEntity regester(UserEntity user);

    List<CenterVO> getCenterList(CenterVO centerVO);

    /**
     * 根据token去修改用户token ，使原本token失效
     * @param token
     */
    void logout(String token);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    UserEntity findByToken(String token);

}
