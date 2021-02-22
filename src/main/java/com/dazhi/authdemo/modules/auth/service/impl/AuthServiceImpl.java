package com.dazhi.authdemo.modules.auth.service.impl;


import com.dazhi.authdemo.modules.auth.dao.DealRepository;
import com.dazhi.authdemo.modules.auth.dao.UserRepository;
import com.dazhi.authdemo.modules.auth.dto.LoginDTO;
import com.dazhi.authdemo.modules.auth.entity.DealEntity;
import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import com.dazhi.authdemo.modules.auth.service.AuthService;
import com.dazhi.authdemo.modules.auth.vo.CenterVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        user.setScore(BigDecimal.ZERO);
        user.setSyScore(BigDecimal.ZERO);
        UserEntity userInfo = userRepository.save(user);
        return userInfo;
    }


    @Override
    public List<CenterVO> getCenterList(CenterVO centerVO) {
        List<CenterVO> centerVOList = new ArrayList<>();
        List<Object[]> list = userRepository.getCenterList(centerVO.getCenterName());
        for (int i = 0; i < list.size(); i++) {
            CenterVO vo = new CenterVO();
            Object[] objects = list.get(i);
            if (objects[0] != null) {
                vo.setCenterId((objects[0].toString()));
                vo.setCenterName((objects[1].toString()));
                vo.setJxsNum((objects[2].toString()));
                List<UserEntity> hsrNum = userRepository.findAllByRoleIdAndCenterId(3, (Integer) objects[0]);
                vo.setHsrNum("" + hsrNum.size());
                List<DealEntity> orderNum = dealRepository.findAllByCenterIdAndStatus((Integer) objects[0],1);
                vo.setOrderNum("" + orderNum.size());
                centerVOList.add(vo);
            }

        }
        return centerVOList;
    }

    @Override
    public List<CenterVO> getJxsList(CenterVO centerVO) {
        List<CenterVO> centerVOList = new ArrayList<>();
        List<Object[]> list = userRepository.getJxsList(centerVO.getCenterId(), centerVO.getJsxAccount());
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
                List<DealEntity> orderNum = dealRepository.findAllByJsxAccountIdAndStatus(Long.parseLong(objects[2].toString()), 1);
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
        List<Object[]> list = userRepository.getHhrList(centerVO.getJsxAccount(), centerVO.getUserName());
        for (int i = 0; i < list.size(); i++) {
            CenterVO vo = new CenterVO();
            Object[] objects = list.get(i);
            if (objects[0] != null) {
                vo.setCenterId(objects[0].toString());
                vo.setCenterName(objects[1].toString());
                vo.setJsxAccount(objects[2].toString());
                vo.setJsxName(objects[3].toString());
                vo.setHhrAccount(objects[4].toString());
                if(objects[5]!=null){
                    vo.setTelephone(objects[5].toString());
                }else {
                    vo.setTelephone(objects[4].toString());
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (objects[6]!=null) {
                    try {
                        vo.setTime(sdf.format(sdf.parse(objects[6].toString())));
                    } catch (Exception e) {

                    }
                }
                if(objects[7]!=null){
                    vo.setUserName(objects[7].toString());
                }else {
                    vo.setUserName(objects[4].toString());
                }

                List<DealEntity> orderNum = dealRepository.findAllByJsxAccountIdAndStatus(Long.parseLong(objects[2].toString()), 1);
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
    public List<DealEntity> getDealBySgId(CenterVO centerVO) {
        List<DealEntity> list = new ArrayList<>();
        if (StringUtils.isEmpty(centerVO.getProductName())) {
            list = dealRepository.findAllBySgAccountIdAndStatus(Long.parseLong(centerVO.getHhrAccount()),1);
        } else {
            list = dealRepository.findAllBySgAccountIdAndStatusAndProductNameContains(Long.parseLong(centerVO.getHhrAccount()),  1,centerVO.getProductName());
        }

        return list;
    }

    @Override
    public List<DealEntity> getWSHbyJxsId(CenterVO centerVO) {
        List<DealEntity> list = new ArrayList<>();
        list = dealRepository.findAllByJsxAccountIdAndStatus(Long.parseLong(centerVO.getJsxAccount()),0);
        return list;
    }

    @Override
    public List<DealEntity> getDealList(CenterVO centerVO) {
        List<DealEntity> list = new ArrayList<>();
        if (StringUtils.isEmpty(centerVO.getProductName())) {
            list = dealRepository.findAllBySgAccountId(Long.parseLong(centerVO.getHhrAccount()));
        } else {
            list = dealRepository.findAllBySgAccountIdAndProductNameLike(Long.parseLong(centerVO.getHhrAccount()), centerVO.getProductName());
        }

        return list;
    }

    @Override
    public DealEntity getDealById(CenterVO centerVO){
        DealEntity dealEntity = dealRepository.getOne(centerVO.getDealId());
        return dealEntity;
    }

    @Override
    public void checkDeal(CenterVO centerVO) {
        DealEntity dealEntity = dealRepository.getOne(centerVO.getDealId());
        dealEntity.setStatus(Integer.parseInt(centerVO.getStatus()));
        if(centerVO.getStatus().equals("1")){
            UserEntity byAccount = userRepository.findByAccount(dealEntity.getSgAccountId().toString());
            BigDecimal num = new BigDecimal(dealEntity.getNumber() == null ? 0 : dealEntity.getNumber());
            BigDecimal newAddScore = new BigDecimal(String.valueOf(num.multiply(dealEntity.getPrice() == null ? new BigDecimal("0") : dealEntity.getPrice())));
            BigDecimal oldScore = new BigDecimal("0");
            BigDecimal oldSyScore = new BigDecimal("0");
            if (byAccount.getScore() != null) {
                oldScore = byAccount.getScore();
            }
            if (byAccount.getSyScore() != null) {
                oldSyScore = byAccount.getSyScore();
            }
            BigDecimal bignum3 = null;
            BigDecimal syScore = null ;
            bignum3 = oldScore.add(newAddScore);
            syScore = oldSyScore.add(newAddScore);
            byAccount.setScore(bignum3);
            byAccount.setSyScore(syScore);
            userRepository.save(byAccount);
        }
        dealRepository.save(dealEntity);

    }

    @Override
    public UserEntity dhScore(CenterVO centerVO,String token) {
        UserEntity byAccount = userRepository.findByToken(token);
        BigDecimal oldScore = new BigDecimal("0");
        if (byAccount.getScore() != null) {
            oldScore = byAccount.getSyScore();
        }
        BigDecimal bignum3 = null;
        if (oldScore.compareTo(new BigDecimal(centerVO.getScore())) < 0) {
            return null;
        }
        bignum3 = oldScore.subtract(new BigDecimal(centerVO.getScore()));
        byAccount.setSyScore(bignum3);
        userRepository.save(byAccount);
        return byAccount;
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
    public String xgMima(LoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByToken(loginDTO.getToken());
        if(!userEntity.getPassword().equals(loginDTO.getOldPwd())){
            return "0";
        }
        userEntity.setPassword(loginDTO.getPassword());
        userRepository.save(userEntity);
        return "1";
    }


    @Override
    public UserEntity findByToken(String token) {
        //            NO	星级	对应积分
//            1	一星级	5000-20000
//            2	二星级	20000-50000
//            3	三星级	50000-80000
//            4	四星级	80000-100000
//            5	五星级	100000
        UserEntity byToken = userRepository.findByToken(token);
        int   jifen = 0;
        if(byToken.getScore()==null){
            jifen = 0;
            byToken.setScore(BigDecimal.ZERO);
            byToken.setSyScore(BigDecimal.ZERO);
        }else {
            jifen = byToken.getScore().intValue() ;
        }
        if (jifen>=100000){
            byToken.setXingJi(5);
        }else if(jifen>=80000){
            byToken.setXingJi(4);
        }else if(jifen>=50000){
            byToken.setXingJi(3);
        }else if(jifen>=20000){
            byToken.setXingJi(2);
        }
        else if(jifen>=5000){
            byToken.setXingJi(1);
        }else {
            byToken.setXingJi(0);
        }
        return byToken;
    }

    @Override
    public UserEntity findByAccountAndRoleId(String account) {
        return userRepository.findByAccountAndRoleId(account, 2);
    }
}
