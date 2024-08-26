package com.controller;

import java.util.List;

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

	@Autowired
	private SupplierService supplierService;

	@GetMapping("/suppliers")
	public ResponseEntity<List<SupplierBean>> getAllSuppliers() {
		List<SupplierBean> allSuppliers = supplierService.getAllSuppliers();
		if(allSuppliers.size() == 0)
			throw new SuppliersNotFoundException();
		return ResponseEntity.ok().body(allSuppliers);
	}

	@PostMapping(value = "/suppliers")
	public ResponseEntity<String> registerSupplier(@RequestBody SupplierBean supplierBean) {
		supplierService.registerSupplier(supplierBean);
		return ResponseEntity.ok().body("Supplier registered");
	}

	@ExceptionHandler(value = {SuppliersNotFoundException.class})
	public ResponseEntity<Object> handleSuppliersNotFoundException() {
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(value = {SupplierExistsException.class})
	public ResponseEntity<String> handleSupplierExistsException(SupplierExistsException supplierExistsException) {
		return new ResponseEntity<>(supplierExistsException.getMessage(), HttpStatus.CONFLICT);
	}
}
