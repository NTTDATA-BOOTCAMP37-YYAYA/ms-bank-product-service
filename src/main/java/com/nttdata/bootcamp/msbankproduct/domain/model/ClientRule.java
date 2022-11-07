package com.nttdata.bootcamp.msbankproduct.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRule {

	
	private String customerTypeId;
	private String customerCategoryId;
	private String clientRuleState;
	private List<Rule> businessRules;
	
}
