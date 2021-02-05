package com.dazhi.authdemo.modules.auth.dao;

import com.dazhi.authdemo.modules.auth.entity.DealEntity;
import com.dazhi.authdemo.modules.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface DealRepository extends JpaRepository<DealEntity, Integer> {
    List<DealEntity>  findAllByCenterId(Integer centerId);
    List<DealEntity>  findAllByJsxAccountIdAndStatus(Long  jsxAccountId,Integer status);
    List<DealEntity>  findAllBySgAccountId(Long sgAccountId);
    List<DealEntity>  findAllById(Integer sgAccountId);
}
