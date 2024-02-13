package com.vendingmachine.WebConsuming.service;

import com.vendingmachine.WebConsuming.customeexception.ProductIdNotFoundException;
import com.vendingmachine.WebConsuming.model.Inventory;
import com.vendingmachine.WebConsuming.model.InventoryResponse;
import com.vendingmachine.WebConsuming.model.PurchaseRequest;
import com.vendingmachine.WebConsuming.util.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import static com.vendingmachine.WebConsuming.util.FileUtility.GET_PRODUCT_BY_ID;


@Service
public class CustomerWebConsumingService {

    @Autowired
     RestTemplate restTemplate;

    private  Logger log = LoggerFactory.getLogger(CustomerWebConsumingService.class);

    public ResponseEntity<String> getAllInventory() {

        HttpEntity<String> entity = new HttpEntity<>("parameters",   FileUtility.getJwtHeaders());

        //return
        try {
            // Make the POST request using exchange for more control
            ResponseEntity<String> response = restTemplate.exchange(FileUtility.GET_ALL_ITEMS, HttpMethod.GET, entity, String.class);

            // Check if the request was successful (HTTP status code 2xx)
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(response.getBody());
            } else {
                // Handle non-successful response (e.g., log the error, throw an exception)
                return ResponseEntity.status(response.getStatusCode()).body("Request failed");
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

    public Inventory getProductById(int id) {

        HttpEntity<String> entity = new HttpEntity<>( FileUtility.getJwtHeaders());
//        ResponseEntity<Inventory> responseEntity = restTemplate.exchange(
//                GET_PRODUCT_BY_ID, HttpMethod.GET, entity, Inventory.class, id);
        try {
            return restTemplate.exchange(
                            GET_PRODUCT_BY_ID, HttpMethod.GET,entity, Inventory.class, id)
                    .getBody();
        } catch (HttpServerErrorException | HttpClientErrorException.NotFound notFoundException) {
            throw new ProductIdNotFoundException("Product with ID " + id + " not found");
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












