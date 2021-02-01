package com.dazhi.authdemo.modules.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class CateTypeEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue
    private Integer id;
    private Integer categoryId;
    private String categoryName;
    private String type;
}
