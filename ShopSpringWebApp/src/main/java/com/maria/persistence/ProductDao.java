package com.maria.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maria.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{

}
