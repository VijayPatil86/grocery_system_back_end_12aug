package com.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.SupplierBean;
import com.exception.SupplierExistsException;
import com.exception.SuppliersNotFoundException;
import com.service.SupplierService;

@RestController
@RequestMapping("/api")
public class SupplierController {

	private static final Logger logger = LogManager.getLogger(SupplierController.class);

	@Autowired
	private SupplierService supplierService;

	@GetMapping("/suppliers")
	public ResponseEntity<List<SupplierBean>> getAllSuppliers() {
		logger.debug("GET /api/suppliers - getAllSuppliers() - START");
		List<SupplierBean> allSuppliers = supplierService.getAllSuppliers();
		logger.debug("GET /api/suppliers - getAllSuppliers() - allSuppliers.size " + allSuppliers.size());
		if(allSuppliers.size() == 0) {
			logger.debug("GET /api/suppliers - getAllSuppliers() - allSuppliers.size is 0, throwing SuppliersNotFoundException");
			throw new SuppliersNotFoundException();
		}
		logger.debug("GET /api/suppliers - getAllSuppliers() - END");
		logger.debug("GET /api/suppliers - getAllSuppliers() - Returning 200 OK");
		return ResponseEntity.ok().body(allSuppliers);
	}

	@PostMapping(value = "/suppliers")
	public ResponseEntity<String> registerSupplier(@RequestBody SupplierBean supplierBean) {
		logger.debug("POST /api/suppliers - registerSupplier() - START");
		logger.debug("POST /api/suppliers - registerSupplier() - Supplier to Register: " + supplierBean);
		supplierService.registerSupplier(supplierBean);
		logger.info("POST /api/suppliers - registerSupplier() - Supplier Registered");
		logger.debug("POST /api/suppliers - registerSupplier() - END");
		logger.debug("POST /api/suppliers - registerSupplier() - Returning 200 OK");
		return ResponseEntity.ok().body("Supplier registered");
	}

	@ExceptionHandler(value = {SuppliersNotFoundException.class})
	public ResponseEntity<Object> handleSuppliersNotFoundException() {
		logger.debug("SuppliersNotFoundException - Returning 204 No Content");
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(value = {SupplierExistsException.class})
	public ResponseEntity<String> handleSupplierExistsException(SupplierExistsException supplierExistsException) {
		logger.debug("SupplierExistsException - Returning 409 Conflict");
		return new ResponseEntity<>(supplierExistsException.getMessage(), HttpStatus.CONFLICT);
	}
}
