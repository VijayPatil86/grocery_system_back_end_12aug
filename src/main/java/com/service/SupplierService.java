package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.SupplierBean;
import com.entity.SupplierEntity;
import com.exception.SupplierExistsException;
import com.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public List<SupplierBean> getAllSuppliers() {
		List<SupplierEntity> allSuppliers = supplierRepository.findAll();
		return allSuppliers.stream().map(
				supplierEntity -> SupplierBean.builder()
									.supplierId(supplierEntity.getSupplierId())
									.supplierName(supplierEntity.getSupplierName())
									.supplierCity(supplierEntity.getSupplierCity())
									.build()
			).collect(Collectors.toList());
	}

	public void registerSupplier(SupplierBean supplierBean) {
		Optional<Boolean> isSupplierExists = supplierRepository.existsBySupplierNameAndSupplierCity(supplierBean.getSupplierName(), supplierBean.getSupplierCity());
		if(isSupplierExists.isPresent() && isSupplierExists.get())
			throw new SupplierExistsException("this Supplier already exists");
		SupplierEntity supplierEntity = SupplierEntity.builder()
				.supplierName(supplierBean.getSupplierName())
				.supplierCity(supplierBean.getSupplierCity())
				.build();
		supplierRepository.save(supplierEntity);
	}

}
