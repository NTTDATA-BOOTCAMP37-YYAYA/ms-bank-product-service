package com.nttdata.bootcamp.msbankproduct.application.port.incoming;

import com.nttdata.bootcamp.msbankproduct.domain.model.Rule;

import reactor.core.publisher.Flux;

public interface FindBusinessRulesOfProductUseCase {

	
	public  Flux<Rule> findBusinessRulesOfProductUseCase(String productId,String customerTypeId, String customerCategoryId,String actionId);
}
