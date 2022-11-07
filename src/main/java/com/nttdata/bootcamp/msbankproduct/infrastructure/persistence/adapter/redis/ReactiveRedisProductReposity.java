package com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.adapter.redis;

import java.util.UUID;
import com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ReactiveRedisProductReposity {

	private final ReactiveRedisOperations<String, ProductEntity> reactiveRedisOperations;


	public Mono<ProductEntity> findById(String productId) {
		return this.reactiveRedisOperations.<String, ProductEntity>opsForHash().get("Product", productId);
	}

	public Mono<ProductEntity> insert(ProductEntity product) {
		if (product.getProductId() == null) {
			String id = UUID.randomUUID().toString();
			product.setProductId(id);
		}
		return this.reactiveRedisOperations.<String, ProductEntity>opsForHash()
				.put("Product", product.getProductId(), product).log().map(p -> product);
	}

}