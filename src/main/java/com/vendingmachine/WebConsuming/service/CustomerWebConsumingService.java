package com.vendingmachine.WebConsuming.service;

import com.vendingmachine.WebConsuming.customeexception.CustomIOException;
import com.vendingmachine.WebConsuming.customeexception.ProductIdNotFoundException;
import com.vendingmachine.WebConsuming.model.Inventory;
import com.vendingmachine.WebConsuming.model.InventoryResponse;
import com.vendingmachine.WebConsuming.model.PurchaseRequest;
import com.vendingmachine.WebConsuming.util.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.List;

import static com.vendingmachine.WebConsuming.util.FileUtility.GET_ALL_ITEMS;
import static com.vendingmachine.WebConsuming.util.FileUtility.GET_PRODUCT_BY_ID;


@Service
public class CustomerWebConsumingService {

    public   RestTemplate restTemplate;
    @Autowired
    public CustomerWebConsumingService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    private  Logger log = LoggerFactory.getLogger(CustomerWebConsumingService.class);

    public ResponseEntity<List<Inventory>> getAllInventory() {

        HttpEntity<String> entity = new HttpEntity<>( FileUtility.getJwtHeaders());

        //return
        try {
        ParameterizedTypeReference<List<Inventory>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<Inventory>> response    = restTemplate.exchange(
                GET_ALL_ITEMS, HttpMethod.GET, entity, responseType);
            log.info("response ==" +response );
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(response.getBody());
            } else {
                // Handle non-successful response (e.g., log the error, throw an exception)
               throw new CustomIOException("ERROR OCCURRED ");
            }
        } catch (HttpServerErrorException | HttpClientErrorException notFoundException) {
            // Handle client-side HTTP errors (4xx status codes)
        throw new CustomIOException("ERROR OCCURRED EITHER HttpServerErrorException OR HttpClientErrorException :: "+notFoundException);
        }

    }

    public Inventory getProductById(int id) {

        HttpEntity<String> entity = new HttpEntity<>( FileUtility.getJwtHeaders());

        try {
            ResponseEntity<Inventory> result= restTemplate.exchange(
                            GET_PRODUCT_BY_ID, HttpMethod.GET,entity, Inventory.class, id);
            return result.getBody();

        } catch (HttpServerErrorException | HttpClientErrorException notFoundException) {

            throw new ProductIdNotFoundException("Product with ID " + id + " not found");
        } catch (RestClientException e) {
            // Handle RestClientException (e.g., connection issues, timeouts)
            throw new CustomIOException("RestclientException occured may be due to connection issue and timeout");
        }

    }


    public ResponseEntity<String> callAddMultipledDenominationForProductRest(PurchaseRequest purchaseRequest) {

        HttpEntity<PurchaseRequest> requestEntity = new HttpEntity<>(purchaseRequest, FileUtility.getJwtHeaders());
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(FileUtility.PURCHASE_PRODUCT_BY_ID, requestEntity, String.class);

            // Process the response if needed
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(responseEntity.getBody());
                // Handle the response
            } else {
                return ResponseEntity.status(responseEntity.getStatusCode()).body("Request failed");
            }

        } catch (HttpClientErrorException e) {
            // Handle client-side HTTP errors (4xx status codes)
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            // Handle server-side HTTP errors (5xx status codes)
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        }
        catch (RestClientException e) {
            // Handle RestClientException (e.g., connection issues, timeouts)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}












