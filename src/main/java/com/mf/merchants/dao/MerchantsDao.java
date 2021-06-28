package com.mf.merchants.dao;

import com.mf.merchants.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantsDao extends JpaRepository<Merchants, Integer> {
    Merchants findById(int id);

    Merchants findByName(String name);
}