package com.dazhi.authdemo.modules.auth.dao;

import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByAccount(String account);

    UserEntity findByToken(String token);

    List<UserEntity> findAllByRoleIdAndCenterId(Integer roleId,Integer centerId);

    List<UserEntity> findAllByRoleIdAndJxsId(Integer roleId,Long jxsId);

    @Query(value="SELECT center_id as centerId ,center_name as centerName ,count(center_id) as jxsNum from user_entity WHERE  if(?1 !='',center_id=?1,1=1) and role_id =2 GROUP BY center_id,center_name" ,nativeQuery = true)
    List<Object[]> getCenterList(String center_id);

    @Query(value="SELECT center_id,center_name,account,jxs FROM user_entity WHERE if(?1 !='',center_id=?1,1=1) and role_id =2 and if(?2 !='',account like %?2%,1=1)" ,nativeQuery = true)
    List<Object[]> getJxsList(String center_id,String account);

    @Query(value="SELECT center_id,center_name,jxs_id,jxs,account,telephone,create_time,username FROM user_entity WHERE if(?1 !='',jxs_id=?1,1=1) and role_id =3  and if(?2 !='',username like %?2%,1=1)" ,nativeQuery = true)
    List<Object[]> getHhrList(String jxs_id,String username);
}
