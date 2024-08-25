package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.SupplierEntity;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {

}
