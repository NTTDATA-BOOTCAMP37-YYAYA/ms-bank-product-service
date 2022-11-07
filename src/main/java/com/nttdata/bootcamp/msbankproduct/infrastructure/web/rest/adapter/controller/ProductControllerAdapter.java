package com.nttdata.bootcamp.msbankproduct.infrastructure.web.rest.adapter.controller;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.msbankproduct.application.port.incoming.CreateProductUseCase;
import com.nttdata.bootcamp.msbankproduct.application.port.incoming.FindBusinessRulesOfProductUseCase;
import com.nttdata.bootcamp.msbankproduct.domain.model.Product;
import com.nttdata.bootcamp.msbankproduct.domain.model.Rule;
import com.nttdata.bootcamp.msbankproduct.infrastructure.web.rest.adapter.controller.response.ResponseProduct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductControllerAdapter {
  
	final static Logger logger= LoggerFactory.getLogger(ProductControllerAdapter.class);

	@Autowired
	private  CreateProductUseCase createProductUseCase;
	
	@Autowired
	private  FindBusinessRulesOfProductUseCase findBusinessRulesOfProductUseCase;

	@PostMapping
	public Mono<ResponseEntity<ResponseProduct>> createProduct( @RequestBody Product product){

		return createProductUseCase.createProduct(product)
		    .flatMap(newProduct -> {
          return Mono.just(new ResponseEntity<ResponseProduct>(
              new ResponseProduct(HttpStatus.SC_OK, newProduct, "Product has beean created"),
              null, HttpStatus.SC_OK));
        })
        .defaultIfEmpty(new ResponseEntity<ResponseProduct>(
            new ResponseProduct(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                                null, "Product not found"),
            null, HttpStatus.SC_INTERNAL_SERVER_ERROR))
        .onErrorResume(e -> Mono.just(new ResponseEntity<ResponseProduct>(
            new ResponseProduct(HttpStatus.SC_INTERNAL_SERVER_ERROR, 
                null, e.getMessage()),
                null, HttpStatus.SC_INTERNAL_SERVER_ERROR)));
	}
	

	@GetMapping("/businessRules/{productId}/{customerTypeId}/{customerCategoryId}/{actionId}")
	public  Flux<Rule> findBusinessRulesOfProduct(
			@PathVariable("productId") String productId,
			@PathVariable("customerTypeId") String customerTypeId,
			@PathVariable("customerCategoryId") String customerCategoryId,
			@PathVariable("actionId") String actionId){

	    return findBusinessRulesOfProductUseCase.findBusinessRulesOfProductUseCase(productId, 
	    																           customerTypeId,
	    																           customerCategoryId,
	    																           actionId);

	}

}
