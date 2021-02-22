package com.dazhi.authdemo.modules.auth.service.impl;

import com.dazhi.authdemo.modules.auth.dao.DealRepository;
import com.dazhi.authdemo.modules.auth.dao.ProductModelRepository;
import com.dazhi.authdemo.modules.auth.dao.ProductRepository;
import com.dazhi.authdemo.modules.auth.dto.DealDTO;
import com.dazhi.authdemo.modules.auth.dto.ProductDTO;
import com.dazhi.authdemo.modules.auth.entity.DealEntity;
import com.dazhi.authdemo.modules.auth.entity.ProductEntity;
import com.dazhi.authdemo.modules.auth.entity.ProductModelEntity;
import com.dazhi.authdemo.modules.auth.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductModelRepository productModelRepository;
    private final DealRepository dealRepository;

    public ProductServiceImpl(ProductRepository productRepository,ProductModelRepository productModelRepository,DealRepository dealRepository) {
        this.productRepository = productRepository;
        this.productModelRepository = productModelRepository;
        this.dealRepository = dealRepository;
    }

    @Override
    public List<ProductEntity> findAll() {
      return   productRepository.findAll();
    }

    @Override
    public List<ProductModelEntity> findAllByProductId(Integer productId,String model) {
        return productModelRepository.findAllByProductIdAndModelContains(productId,model);
    }

    @Override
    public String saveDeal(DealDTO dealDTO){
        try {
            for (ProductDTO productDTO: dealDTO.getProductList()) {
                DealEntity dto = new DealEntity();
                dto.setAddress(dealDTO.getAddress());
                dto.setTelephone(dealDTO.getTelephone());
                dto.setCustomerName(dealDTO.getCustomerName());
                dto.setProductName(productDTO.getProductName());
                dto.setProductModel(productDTO.getModel());
                dto.setNumber(productDTO.getNumber());
                dto.setPrice(productDTO.getPrice());
                dto.setTime(new Date());
                dto.setStatus(0);
                dto.setCenterId(dealDTO.getCenterId());
                dto.setJsxAccountId(dealDTO.getJsxAccountId());
                dto.setSgAccountId(Long.parseLong(dealDTO.getSgAccountId()));
                dealRepository.save(dto);
            }
            return "";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }


    }
}
