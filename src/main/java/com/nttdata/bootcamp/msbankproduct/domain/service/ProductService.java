package com.nttdata.bootcamp.msbankproduct.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msbankproduct.application.port.incoming.CreateProductUseCase;
import com.nttdata.bootcamp.msbankproduct.application.port.incoming.FindBusinessRulesOfProductUseCase;
import com.nttdata.bootcamp.msbankproduct.application.port.outgoing.CreateProductPort;
import com.nttdata.bootcamp.msbankproduct.application.port.outgoing.FindProductByIdPort;
import com.nttdata.bootcamp.msbankproduct.domain.enums.States;
import com.nttdata.bootcamp.msbankproduct.domain.model.Product;
import com.nttdata.bootcamp.msbankproduct.domain.model.Rule;
import com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.adapter.ProductRepositoryAdapter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService implements CreateProductUseCase,FindBusinessRulesOfProductUseCase{

	final static Logger logger= LoggerFactory.getLogger(ProductRepositoryAdapter.class);
	
	@Autowired
	private CreateProductPort createProductPort;

  @Autowired
  private FindProductByIdPort findProductByIdPort;


	@Override
	public Mono<Product> createProduct(Product product) {
		return createProductPort.createProduct(product);
	}


	@Override
	public Flux<Rule> findBusinessRulesOfProductUseCase(String productId, String customerTypeId, String customerCategoryId,
			String actionId) {
		
		return findProductByIdPort.findProductById(productId)
				.flatMapIterable(p -> p.getClientRules())
				.filter(c->c.getCustomerTypeId().equals(customerTypeId)&&
						c.getCustomerCategoryId().equals(customerCategoryId)&&
						c.getClientRuleState().equals(States.ACTIVE.getValue()))
				.take(1)
				.flatMapIterable(r->r.getBusinessRules())
				.filter(f->f.getActionId().equals(actionId));
	}



	


	
}
