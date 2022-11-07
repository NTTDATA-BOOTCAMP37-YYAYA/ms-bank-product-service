package com.nttdata.bootcamp.msbankproduct.domain.enums;

public enum States {

    ACTIVE("A"),
    INACTIVE("I");

    private final String value;

    States(String value) {
        this.value = value;
    }
    
    public String getValue() {
     return value;
  	}

}
