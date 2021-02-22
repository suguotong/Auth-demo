package com.dazhi.authdemo.modules.auth.service;


import com.dazhi.authdemo.modules.auth.dto.DealDTO;
import com.dazhi.authdemo.modules.auth.dto.LoginDTO;
import com.dazhi.authdemo.modules.auth.entity.DealEntity;
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

    List<CenterVO> getJxsList(CenterVO centerVO);

    List<CenterVO> getHhrList(CenterVO centerVO);

    List<DealEntity> getDealBySgId(CenterVO centerVO);

    List<DealEntity> getWSHbyJxsId(CenterVO centerVO);

    List<DealEntity> getDealList(CenterVO centerVO);

    void checkDeal(CenterVO centerVO);

    DealEntity getDealById(CenterVO centerVO);

    UserEntity dhScore(CenterVO centerVO,String token);


    /**
     * 根据token去修改用户token ，使原本token失效
     * @param token
     */
    void logout(String token);

    String xgMima(LoginDTO loginDTO);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    UserEntity findByToken(String token);

    /**
     * 根据经销商Id获取经销商信息
     * @param jsxId
     * @return
     */
    UserEntity findByAccountAndRoleId(String account);

}
