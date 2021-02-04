package com.dazhi.authdemo.modules.auth.service.impl;


import com.dazhi.authdemo.modules.auth.dao.DealRepository;
import com.dazhi.authdemo.modules.auth.dao.UserRepository;
import com.dazhi.authdemo.modules.auth.entity.DealEntity;
import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import com.dazhi.authdemo.modules.auth.service.AuthService;
import com.dazhi.authdemo.modules.auth.vo.CenterVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final DealRepository dealRepository;

    public AuthServiceImpl(UserRepository userRepository, DealRepository dealRepository) {

        this.userRepository = userRepository;
        this.dealRepository = dealRepository;
    }


    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByAccount(username);

    }

    //12小时后失效
    private final static int EXPIRE = 100;

    @Override
    public String createToken(UserEntity user) {
        //用UUID生成token
        String token = UUID.randomUUID().toString();
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        //保存到数据库
        user.setLoginTime(now);
        user.setExpireTime(expireTime);
        user.setToken(token);
        userRepository.save(user);
        return token;
    }

    @Override
    public UserEntity regester(UserEntity user) {
        UserEntity userInfo = userRepository.save(user);
        return userInfo;
    }


    @Override
    public List<CenterVO> getCenterList(CenterVO centerVO) {
        List<CenterVO> centerVOList = new ArrayList<>();
        List<Object[]> list = userRepository.getCenterList(centerVO.getCenterId());
        for (int i = 0; i < list.size(); i++) {
            CenterVO vo = new CenterVO();
            Object[] objects = list.get(i);
            if (objects[0] != null) {
                vo.setCenterId((objects[0].toString()));
                vo.setCenterName((objects[1].toString()));
                vo.setJxsNum((objects[2].toString()));
                List<UserEntity> hsrNum = userRepository.findAllByRoleIdAndCenterId(3, (Integer) objects[0]);
                vo.setHsrNum("" + hsrNum.size());
                List<DealEntity> orderNum = dealRepository.findAllByCenterId((Integer) objects[0]);
                vo.setOrderNum("" + orderNum.size());
                centerVOList.add(vo);
            }

        }
        return centerVOList;
    }

    @Override
    public List<CenterVO> getJxsList(CenterVO centerVO) {
        List<CenterVO> centerVOList = new ArrayList<>();
        List<Object[]> list = userRepository.getJxsList(centerVO.getCenterId(),centerVO.getJsxAccount());
        for (int i = 0; i < list.size(); i++) {
            CenterVO vo = new CenterVO();
            Object[] objects = list.get(i);
            if (objects[0] != null) {
                vo.setCenterId(objects[0].toString());
                vo.setCenterName(objects[1].toString());
                vo.setJsxAccount(objects[2].toString());
                vo.setJsxName(objects[3].toString());
                List<UserEntity> hsrNum = userRepository.findAllByRoleIdAndJxsId(3, Long.parseLong(objects[2].toString()));
                vo.setHsrNum("" + hsrNum.size());
                List<DealEntity> orderNum = dealRepository.findAllByJsxAccountId(Long.parseLong(objects[2].toString()));
                vo.setOrderNum("" + orderNum.size());
                BigDecimal score = new BigDecimal("0");
                for (int j = 0; j < orderNum.size(); j++) {
                    BigDecimal m = orderNum.get(j).getPrice().multiply(new BigDecimal(orderNum.get(j).getNumber()));
                    score = score.add(m);
                }
                vo.setScore(score.toString());
                centerVOList.add(vo);
            }

        }
        return centerVOList;
    }

    @Override
    public List<CenterVO> getHhrList(CenterVO centerVO) {
        List<CenterVO> centerVOList = new ArrayList<>();
        List<Object[]> list = userRepository.getHhrList(centerVO.getJsxAccount(),centerVO.getUserName());
        for (int i = 0; i < list.size(); i++) {
            CenterVO vo = new CenterVO();
            Object[] objects = list.get(i);
            if (objects[0] != null) {
                vo.setCenterId(objects[0].toString());
                vo.setCenterName(objects[1].toString());
                vo.setJsxAccount(objects[2].toString());
                vo.setJsxName(objects[3].toString());
                vo.setHhrAccount(objects[4].toString());
                vo.setTelephone(objects[5].toString());
                vo.setTime(objects[6].toString());
                vo.setUserName(objects[7].toString());
                List<DealEntity> orderNum = dealRepository.findAllByJsxAccountId(Long.parseLong(objects[4].toString()));
                vo.setOrderNum("" + orderNum.size());
                BigDecimal score = new BigDecimal("0");
                for (int j = 0; j < orderNum.size(); j++) {
                    BigDecimal m = orderNum.get(j).getPrice().multiply(new BigDecimal(orderNum.get(j).getNumber()));
                    score = score.add(m);
                }
                vo.setScore(score.toString());
                centerVOList.add(vo);
            }

        }
        return centerVOList;
    }

    @Override
    public void logout(String token) {
        UserEntity userEntity = userRepository.findByToken(token);
        //用UUID生成token
        token = UUID.randomUUID().toString();
        //修改用户的token使原本的token失效，前端需配合将token清除
        userEntity.setToken(token);
        userRepository.save(userEntity);

    }

    @Override
    public UserEntity findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
