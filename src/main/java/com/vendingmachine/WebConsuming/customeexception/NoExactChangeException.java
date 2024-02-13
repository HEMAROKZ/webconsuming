package com.vendingmachine.WebConsuming.customeexception;

public class NoExactChangeException extends RuntimeException{


    public NoExactChangeException(String message) {
        super(message);
    }


}
