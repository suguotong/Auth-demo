package com.dazhi.authdemo.modules.auth.dao;

import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByAccount(String account);

    UserEntity findByToken(String token);

    List<UserEntity> findAllByRoleIdAndCenterId(Integer roleId,Integer centerId);

    @Query(value="SELECT center_id as centerId ,center_name as centerName ,count(center_id) as jxsNum from user_entity WHERE  if(?1 !='',center_id=?1,1=1) and role_id =2 GROUP BY center_id,center_name" ,nativeQuery = true)
    List<Object[]> getCenterList(String center_id);
}
