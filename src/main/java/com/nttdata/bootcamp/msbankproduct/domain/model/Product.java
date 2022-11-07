package com.nttdata.bootcamp.msbankproduct.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

private String productId;
private String productName;
private String productState;
private List<ClientRule> clientRules;
}
