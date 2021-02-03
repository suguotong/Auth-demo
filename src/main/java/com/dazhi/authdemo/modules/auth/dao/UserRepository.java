package com.dazhi.authdemo.modules.auth.dao;

import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import com.dazhi.authdemo.modules.auth.vo.CenterVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByAccount(String account);

    UserEntity findByToken(String token);

    @Query(value="SELECT center_id as centerId ,center_name as centerName ,count(center_id) as jxsNum from user_entity WHERE  role_id =2 GROUP BY center_id,center_name" ,nativeQuery = true)
    List<Object> getCenterList();
}
