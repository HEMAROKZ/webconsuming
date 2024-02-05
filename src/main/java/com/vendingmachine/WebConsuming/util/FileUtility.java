package com.vendingmachine.WebConsuming.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static com.vendingmachine.WebConsuming.model.GeneratedToken.getNewToken;
@Component
public class FileUtility {

    public static final String UPDATE_PRODUCT_BY_ID="/productsRest";
    public static final String DELETE_PRODUCT_BY_ID="/deleteRest/{id}";
    public static final String CREATE_PRODUCT="/products";
    public static final String GET_TOKEN="/authenticate";
    public static final String GET_ALL_ITEMS="/getAllInventoryRest";
    public static final String GET_PRODUCT_BY_ID="/productRest/{id}";
    public static final String PURCHASE_PRODUCT_BY_ID="/product";

    public static HttpHeaders getJwtHeaders() {
        String jwtToken = getNewToken();  // Retrieve the token from a secure configuration property

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
