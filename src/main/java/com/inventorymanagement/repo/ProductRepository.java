package com.inventorymanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventorymanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
