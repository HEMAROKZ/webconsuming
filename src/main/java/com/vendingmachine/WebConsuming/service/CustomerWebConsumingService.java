package com.vendingmachine.WebConsuming.service;

import com.vendingmachine.WebConsuming.model.CustomerInputDTO;
import com.vendingmachine.WebConsuming.model.Inventory;
import com.vendingmachine.WebConsuming.util.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CustomerWebConsumingService {

    @Autowired
     RestTemplate restTemplate;

    private  Logger log = LoggerFactory.getLogger(CustomerWebConsumingService.class);

    public ResponseEntity<String> getAllInventory() {

        HttpEntity<String> entity = new HttpEntity<>("parameters",   FileUtility.getJwtHeaders());

        return restTemplate.exchange(FileUtility.GET_ALL_ITEMS, HttpMethod.GET, entity, String.class);
    }

    public Inventory getProductById(int id) {

        HttpEntity<String> entity = new HttpEntity<>( FileUtility.getJwtHeaders());
        ResponseEntity<Inventory> responseEntity = restTemplate.exchange(
                FileUtility. GET_PRODUCT_BY_ID, HttpMethod.GET, entity, Inventory.class, id);

            return responseEntity.getBody();

    }
    public  String purchaseProduct(CustomerInputDTO customerInputDTO) {
        int productId = customerInputDTO.getProductId();
        int inputPrice = customerInputDTO.getPrice();

        log.info("product id in purchase product == {} ",productId);
        log.info("inputPrice in purchase product == {} ",inputPrice);

        restTemplate.put(FileUtility.PURCHASE_PRODUCT_BY_ID,customerInputDTO);
        return  "productId "+productId+" item has been successfully purchased";
    }

}












