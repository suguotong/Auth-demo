package com.dazhi.authdemo.modules.auth.service.impl;


import com.dazhi.authdemo.common.utils.EntityUtils;
import com.dazhi.authdemo.modules.auth.dao.UserRepository;
import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import com.dazhi.authdemo.modules.auth.service.AuthService;
import com.dazhi.authdemo.modules.auth.vo.CenterVO;
import com.dazhi.authdemo.modules.auth.vo.CenterVOSS;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            if(objects[0]!=null){
                vo.setCenterId((objects[0].toString()));
                vo.setCenterName((objects[1].toString()));
                vo.setJxsNum((objects[2].toString()));
                centerVOList.add(vo);
            }

        }

//        List<Object[]> objects = userRepository.getCenterList();
//        List<CenterVOSS> countOfMonthForms = EntityUtils.castEntity(objects, CenterVOSS.class, new CenterVOSS());
//        log.info("countOfMonthForms = {}",countOfMonthForms);


        return centerVOList;
    }

    //转换实体类
    private <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {
        List<T> returnList = new ArrayList<T>();
        if (CollectionUtils.isEmpty(list)) {
            return returnList;
        }
        Object[] co = list.get(0);
        Class[] c2 = new Class[co.length];
        //确定构造方法
        for (int i = 0; i < co.length; i++) {
            if (co[i] != null) {
                c2[i] = co[i].getClass();
            } else {
                c2[i] = String.class;
            }
        }
        for (Object[] o : list) {
            Constructor<T> constructor = clazz.getConstructor(c2);
            returnList.add(constructor.newInstance(o));
        }
        return returnList;
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
