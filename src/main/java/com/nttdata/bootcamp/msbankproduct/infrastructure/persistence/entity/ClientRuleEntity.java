package com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.entity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import com.nttdata.bootcamp.msbankproduct.domain.model.ClientRule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRuleEntity {

	@Id
	private String clientRuleId;
	private String customerTypeId;
	private String customerCategoryId;
	private String clientRuleState;
	private List<RuleEntity> businessRules;
	
	public static ClientRule toClientRule(ClientRuleEntity clientRuleEntity){

		ClientRule clientRule = new ClientRule();
		BeanUtils.copyProperties(clientRuleEntity, clientRule);
		clientRule.setBusinessRules(clientRuleEntity.getBusinessRules().stream()
				.map(RuleEntity::toRule)
				.collect(Collectors.toList()));
		
		return clientRule;
	}

	public static ClientRuleEntity toClientRuleEntity(ClientRule clientRule){
		ClientRuleEntity clientRuleEntity = new ClientRuleEntity();
		BeanUtils.copyProperties(clientRule, clientRuleEntity);
		clientRuleEntity.setBusinessRules(clientRule.getBusinessRules().stream()
				.map(RuleEntity::toRuleEntity)
                .collect(Collectors.toList()));
		return clientRuleEntity;
	}
}
