package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.SupplierBean;
import com.entity.SupplierEntity;
import com.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public List<SupplierBean> getAllSuppliers() {
		List<SupplierEntity> allSuppliers = supplierRepository.findAll();
		return allSuppliers.stream().map(
				supplier -> SupplierBean.builder()
					.supplierName(supplier.getSupplierName())
					.supplierCity(supplier.getSupplierCity())
					.build()
			).collect(Collectors.toList());
	}

}
