package com.dazhi.authdemo.modules.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ProductModelEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue
    private Integer id;
    private Integer productId;
    private String productName;
    private String model;
}
