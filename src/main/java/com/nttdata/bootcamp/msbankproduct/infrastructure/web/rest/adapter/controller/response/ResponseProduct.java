package com.nttdata.bootcamp.msbankproduct.infrastructure.web.rest.adapter.controller.response;

import com.nttdata.bootcamp.msbankproduct.domain.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {
	private int httpStatus;
	private Product product;
	private String message;

}