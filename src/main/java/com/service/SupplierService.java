package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.SupplierBean;
import com.entity.SupplierEntity;
import com.exception.SupplierExistsException;
import com.repository.SupplierRepository;

@Service
public class SupplierService {

	private static final Logger logger = LogManager.getLogger(SupplierService.class);

	@Autowired
	private SupplierRepository supplierRepository;

	public List<SupplierBean> getAllSuppliers() {
		logger.debug("getAllSuppliers() - START");
		List<SupplierEntity> allSuppliersEntities = supplierRepository.findAll();
		logger.debug("getAllSuppliers() - allSuppliersEntities size " + allSuppliersEntities.size());
		List<SupplierBean> allSuppliersBeans = allSuppliersEntities.stream().map(
				supplierEntity -> SupplierBean.builder()
									.supplierId(supplierEntity.getSupplierId())
									.supplierName(supplierEntity.getSupplierName())
									.supplierCity(supplierEntity.getSupplierCity())
									.build()
			).collect(Collectors.toList());
		logger.debug("getAllSuppliers() - allSuppliersBeans size " + allSuppliersBeans.size());
		logger.debug("getAllSuppliers() - END");
		return allSuppliersBeans;
	}

	public void registerSupplier(SupplierBean supplierBean) {
		logger.debug("registerSupplier() - START");
		logger.debug("registerSupplier() - supplierBean to Register " + supplierBean);
		Optional<Boolean> isSupplierExists = supplierRepository.existsBySupplierNameAndSupplierCity(supplierBean.getSupplierName(), supplierBean.getSupplierCity());
		boolean bSupplierExists = isSupplierExists.isPresent() && isSupplierExists.get();
		logger.debug("registerSupplier() - Supplier Exists " + bSupplierExists);
		if(bSupplierExists) {
			logger.debug("registerSupplier() - Supplier Exists, so, throwing SupplierExistsException");
			throw new SupplierExistsException("this Supplier already exists");
		}
		SupplierEntity supplierEntity = SupplierEntity.builder()
				.supplierName(supplierBean.getSupplierName())
				.supplierCity(supplierBean.getSupplierCity())
				.build();
		logger.debug("registerSupplier() - supplierEntity to Register " + supplierEntity);
		supplierRepository.save(supplierEntity);
		logger.debug("registerSupplier() - Supplier Registered");
	}

}
