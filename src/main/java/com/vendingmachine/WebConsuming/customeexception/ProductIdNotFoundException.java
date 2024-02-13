package com.vendingmachine.WebConsuming.customeexception;

import org.springframework.web.client.HttpClientErrorException;

public class ProductIdNotFoundException extends RuntimeException{

    public ProductIdNotFoundException(String message) {
        super(message);
    }

}
