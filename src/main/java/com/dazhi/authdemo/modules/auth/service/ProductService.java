package com.dazhi.authdemo.modules.auth.service;

import com.dazhi.authdemo.modules.auth.dao.ProductModelRepository;
import com.dazhi.authdemo.modules.auth.dto.DealDTO;
import com.dazhi.authdemo.modules.auth.entity.ProductEntity;
import com.dazhi.authdemo.modules.auth.entity.ProductModelEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> findAll();
    List<ProductModelEntity> findAllByProductId(Integer productId);
    String saveDeal(DealDTO dealDTO);
}
