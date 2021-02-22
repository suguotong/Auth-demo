package com.dazhi.authdemo.modules.auth.dao;

import com.dazhi.authdemo.modules.auth.entity.DealEntity;
import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface DealRepository extends JpaRepository<DealEntity, Integer> {
    List<DealEntity>  findAllByCenterId(Integer centerId);
    List<DealEntity>  findAllByCenterIdAndStatus(Integer centerId,Integer status);
    List<DealEntity>  findAllByJsxAccountIdAndStatus(Long  jsxAccountId,Integer status);
    List<DealEntity>  findAllBySgAccountIdAndStatus(Long  sgAccountId,Integer status);
    List<DealEntity>  findAllBySgAccountId(Long sgAccountId);
    List<DealEntity>  findAllBySgAccountIdAndProductNameLike(Long sgAccountId,String productName);
    List<DealEntity>  findAllBySgAccountIdAndStatusAndProductNameContains(Long sgAccountId,Integer status,String productName);
    List<DealEntity>  findAllById(Integer sgAccountId);
}
