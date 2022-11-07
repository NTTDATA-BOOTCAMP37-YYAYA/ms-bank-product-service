package com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbankproduct.application.port.outgoing.CreateProductPort;
import com.nttdata.bootcamp.msbankproduct.application.port.outgoing.FindProductByIdPort;
import com.nttdata.bootcamp.msbankproduct.domain.model.Product;
import com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.adapter.redis.ReactiveRedisProductReposity;
import com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.entity.ProductEntity;

import reactor.core.publisher.Mono;

@Component
public class ProductRepositoryAdapter implements CreateProductPort,FindProductByIdPort{

  /**For MongoDB
   * @Autowired
   * private ReactiveMongoProductRepository reactiveMongoDB;
   * */

  @Autowired
  private ReactiveRedisProductReposity reactiveRedisDB;
	 

	@Override
	public Mono<Product> createProduct(Product product) {
		
		return reactiveRedisDB.insert(ProductEntity.toProductEntity(product))
				.map(ProductEntity::toProduct);
	}

	@Override
	public Mono<Product> findProductById(String id) {
		return reactiveRedisDB.findById(id)
				.map(ProductEntity::toProduct);
	}

}
