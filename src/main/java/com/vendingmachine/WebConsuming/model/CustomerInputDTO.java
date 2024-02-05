package com.vendingmachine.WebConsuming.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;


public class CustomerInputDTO {
    @NotNull
    @JsonIgnore
    private final int productId;
    @NotNull
    @JsonIgnore
    private final int price;

    public CustomerInputDTO(int productId, int price) {
        this.productId = productId;
        this.price = price;
    }

        @JsonCreator
    public static CustomerInputDTO create(@JsonProperty("product_code") int productId, @JsonProperty("giving_amount") int price) {
        return builder().productId(productId).price(price).build();
    }
    public CustomerInputDTO(CustomerInputBuilder customerInputBuilder) {
        this.productId = customerInputBuilder.productId;
        this.price = customerInputBuilder.price;
    }

    public static CustomerInputBuilder builder(){
        return new CustomerInputBuilder();
    }


    public int getProductId() {
        return productId;
    }


    public int getPrice() {
        return price;
    }


    public static class CustomerInputBuilder {

        private int productId;
        private int price;


        public CustomerInputBuilder() {
        }

        public CustomerInputBuilder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public CustomerInputBuilder price(int price) {
            this.price = price;
            return this;
        }

        public CustomerInputDTO build() {
            return new CustomerInputDTO(this);
        }

    }
}