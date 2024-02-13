package com.vendingmachine.WebConsuming.controller;

import com.vendingmachine.WebConsuming.model.*;
import com.vendingmachine.WebConsuming.service.AdminWebConsumingService;
import com.vendingmachine.WebConsuming.service.CustomerWebConsumingService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;


@RestController
public class RestTemplateController {

        @Autowired
        private AdminWebConsumingService adminWebConsumingService;

        @Autowired
        private CustomerWebConsumingService customerWebConsumingService;

        private static final Logger log = LoggerFactory.getLogger(RestTemplateController.class);

        @PostMapping("/get-token")
    public ResponseEntity<JwtResponse> getToken(@RequestBody JwtRequest authenticationRequest) {
        try {
            ResponseEntity<JwtResponse> response = adminWebConsumingService.getTokenService(authenticationRequest);

            GeneratedToken.setNewToken(response.getBody().getToken());
                        return ResponseEntity.ok()
                                .body( response.getBody());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/getAllProduct")
    @Operation(summary = "Consuming CUSTOMER PROCESS--Get LIST OF ALL Inventry item")
    public ResponseEntity<String> getAllProduct(){
        return customerWebConsumingService.getAllInventory();
    }

//    @GetMapping("/getProduct/{id}")
//    @Operation(summary = "Consuming CUSTOMER PROCESS--Get Inventry item by id")
//    public InventoryResponse getProductById(@PathVariable int id){
//        return customerWebConsumingService.getProductById(id);
//    }
    @GetMapping("/getProduct/{id}")
    @Operation(summary = "Consuming CUSTOMER PROCESS--Get Inventry item by id")
    public Inventory getProductById(@PathVariable int id){
        return customerWebConsumingService.getProductById(id);

    }



    @PostMapping("/product")
    @Operation(summary = "CUSTOMER PROCESS--purchase Inventry item")
    public ResponseEntity<String> ProductPurchase(@RequestBody PurchaseRequest purchaseRequest) {
        return customerWebConsumingService.callAddMultipledDenominationForProductRest(purchaseRequest);
    }

    @PostMapping("/addProduct")
    @Operation(summary = "Consuming ADMIN PROCESS--Add  Inventory item ")
    public ResponseEntity< String>  addProduct(@RequestBody Inventory inventory){
        return adminWebConsumingService.addProduct(inventory);
    }

    @PutMapping("/updateProduct")
    @Operation(summary = "Consuming ADMIN PROCESS--Update  Inventory item ")
    public ResponseEntity<String> updateProductById(@RequestBody Inventory e){
       return adminWebConsumingService.updateProductById(e);

    }

    @DeleteMapping("/products/{id}")
    @Operation(summary = "Consuming ADMIN PROCESS--DELETE  Inventory item ")
    public  ResponseEntity<String> deleteProductById(@PathVariable int id) {
        return adminWebConsumingService.deleteProductById(id) ;
    }

}
