package com.vendingmachine.WebConsuming.service;

import com.vendingmachine.WebConsuming.customeexception.CustomIOException;
import com.vendingmachine.WebConsuming.model.Inventory;
import com.vendingmachine.WebConsuming.model.JwtRequest;
import com.vendingmachine.WebConsuming.model.JwtResponse;
import com.vendingmachine.WebConsuming.util.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class AdminWebConsumingService {

    @Autowired
    RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(AdminWebConsumingService.class);

    public ResponseEntity<JwtResponse> getTokenService(JwtRequest auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

    // Create HttpEntity with headers and the authentication request as the request body
    HttpEntity<JwtRequest> entity = new HttpEntity<>(auth, headers);

    // Make the POST request to authenticate and obtain the token
    return restTemplate.exchange(
            FileUtility.GET_TOKEN, HttpMethod.POST, entity, JwtResponse.class);


    }

    public ResponseEntity<String> addProduct(Inventory inventoryDTO) {


        // Create HttpEntity with headers and the inventoryDTO as the request body
        HttpEntity<Inventory> entity = new HttpEntity<>(inventoryDTO,FileUtility.getJwtHeaders());

        try {
            // Make the POST request using exchange for more control
            ResponseEntity<String> response = restTemplate.exchange(
                    FileUtility.CREATE_PRODUCT, HttpMethod.POST, entity, String.class);

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



    public ResponseEntity<String> updateProductById(Inventory inventory) {

        FileUtility.getJwtHeaders();
        // Create HttpEntity with headers and the inventory as the request body
        HttpEntity<Inventory> entity = new HttpEntity<>(inventory, FileUtility.getJwtHeaders());

        try {
            // USING the POST request using exchange for more control
            ResponseEntity<String> response = restTemplate.exchange(
                    FileUtility.UPDATE_PRODUCT_BY_ID, HttpMethod.PUT, entity, String.class);

            // to Check if the request was successful (HTTP status code 2xx)
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(response.getBody());
            } else {
                //to  Handle non-successful response (e.g., log the error, throw an exception)
                return ResponseEntity.status(response.getStatusCode()).body("Request failed");
            }
        } catch (HttpClientErrorException e) {
            // Handle client-side HTTP errors (4xx status codes)
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            // Handle server-side HTTP errors (5xx status codes)
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        }catch (RestClientException e) {
            // Handle RestClientException (e.g., connection issues, timeouts)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }


    public ResponseEntity<String> deleteProductById(int id) {


        HttpEntity<String> entity = new HttpEntity<>( FileUtility.getJwtHeaders());
        try {
        // Send the DELETE request and return the ResponseEntity
            ResponseEntity<String> response= restTemplate.exchange(FileUtility.DELETE_PRODUCT_BY_ID, HttpMethod.DELETE, entity, String.class, id);

            if (response.getStatusCode().is2xxSuccessful()) {

            return ResponseEntity.ok(response.getBody());
        } else {
            //to  Handle non-successful response (e.g., log the error, throw an exception)
            return ResponseEntity.status(response.getStatusCode()).body("Request failed");
        }
    }catch (HttpClientErrorException e) {
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
