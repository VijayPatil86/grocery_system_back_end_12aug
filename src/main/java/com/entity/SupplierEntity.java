package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "SUPPLIER_MASTER")
public class SupplierEntity {
	@Column(name = "supplier_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierId;

	@Column(name = "supplier_name")
	private String supplierName;

	@Column(name="supplier_city")
	private String supplierCity;
}
