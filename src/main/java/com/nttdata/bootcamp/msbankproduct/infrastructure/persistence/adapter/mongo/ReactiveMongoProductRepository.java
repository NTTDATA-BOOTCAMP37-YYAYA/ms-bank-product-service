package com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.adapter.mongo;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.entity.ProductEntity;

public interface ReactiveMongoProductRepository extends ReactiveMongoRepository<ProductEntity, String>{


}