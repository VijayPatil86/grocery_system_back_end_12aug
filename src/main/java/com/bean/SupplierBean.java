package com.bean;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@Builder
public class SupplierBean {
	private int supplierId;
	@NotBlank
	private String supplierName;
	@NotBlank
	private String supplierCity;
}
