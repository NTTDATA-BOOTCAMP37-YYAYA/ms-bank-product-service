package com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.entity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bootcamp.msbankproduct.domain.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Product")
public class ProductEntity {

	@Id
	private String productId;
	private String productName;
	private String productState;
	private List<ClientRuleEntity> clientRules;
	
	public static Product toProduct(ProductEntity productEntity){

		Product product = new Product();
		BeanUtils.copyProperties(productEntity, product);
		product.setClientRules(productEntity.getClientRules().stream()
				.map(ClientRuleEntity::toClientRule)
                .collect(Collectors.toList()));
		return product;
	}

	public static ProductEntity toProductEntity(Product product){
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(product, productEntity);
		productEntity.setClientRules(product.getClientRules().stream()
				.map(ClientRuleEntity::toClientRuleEntity)
                .collect(Collectors.toList()));
		return productEntity;
	}
}
