package com.dazhi.authdemo.modules.auth.dao;

import com.dazhi.authdemo.modules.auth.entity.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<DealEntity, Integer> {

}
