package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.SupplierBean;
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
			throw new SuppliersNotFoundException("Suppliers not available");
		return ResponseEntity.ok().body(allSuppliers);
	}

	@ExceptionHandler(value = {SuppliersNotFoundException.class})
	public ResponseEntity<Object> handleSuppliersNotFoundException() {
		return ResponseEntity.noContent().build();
	}
}
