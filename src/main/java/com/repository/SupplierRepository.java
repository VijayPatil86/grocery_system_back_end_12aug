package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.SupplierEntity;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {
	Optional<Boolean> existsBySupplierNameAndSupplierCity(String supplierName, String supplierCity);
}
