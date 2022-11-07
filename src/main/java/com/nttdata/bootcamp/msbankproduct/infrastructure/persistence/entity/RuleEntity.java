package com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.entity;

import org.springframework.beans.BeanUtils;

import com.nttdata.bootcamp.msbankproduct.domain.model.Rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleEntity {


	private String actionId;
	private String ruleId;
	private String ruleName;
	private String ruleValue;
	private String ruleState;
	
	public static Rule toRule(RuleEntity ruleEntity){

		Rule rule = new Rule();
		BeanUtils.copyProperties(ruleEntity, rule);
		return rule;
	}

	public static RuleEntity toRuleEntity(Rule rule){
		RuleEntity ruleEntity = new RuleEntity();
		BeanUtils.copyProperties(rule, ruleEntity);
		return ruleEntity;
	}
	
}
