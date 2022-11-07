package com.nttdata.bootcamp.msbankproduct.application.port.outgoing;

import com.nttdata.bootcamp.msbankproduct.domain.model.Product;

import reactor.core.publisher.Mono;

public interface FindProductByIdPort {

	Mono<Product> findProductById(String productId);
}
