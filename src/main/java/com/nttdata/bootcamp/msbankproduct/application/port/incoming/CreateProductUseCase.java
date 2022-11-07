package com.nttdata.bootcamp.msbankproduct.application.port.incoming;

import com.nttdata.bootcamp.msbankproduct.domain.model.Product;

import reactor.core.publisher.Mono;

public interface CreateProductUseCase {

	Mono<Product> createProduct(Product product);
}
