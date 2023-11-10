package com.inventorymanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventorymanagement.entity.Sell;

public interface SellRepository extends JpaRepository<Sell, Long> {
}
