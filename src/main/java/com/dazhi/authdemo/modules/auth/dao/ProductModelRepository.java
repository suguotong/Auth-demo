package com.dazhi.authdemo.modules.auth.dao;

import com.dazhi.authdemo.modules.auth.entity.ProductModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductModelRepository extends JpaRepository<ProductModelEntity, Integer> {
    List<ProductModelEntity> findAllByProductId(Integer productId);
}
