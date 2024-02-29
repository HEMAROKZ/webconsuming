package com.VendingMachine.testcase

import com.vendingmachine.WebConsuming.model.Inventory
import com.vendingmachine.WebConsuming.service.AdminWebConsumingService
import com.vendingmachine.WebConsuming.util.FileUtility
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject

class AdminWebConsumingServiceSpec extends Specification {

    @Subject
    AdminWebConsumingService adminWebConsumingService=new AdminWebConsumingService();
    // Mock RestTemplate to avoid actual HTTP calls
    def mockRestTemplate = Mock(RestTemplate)

    def setup() {
        adminWebConsumingService.restTemplate = mockRestTemplate
    }


    def "addProduct should return ResponseEntity with body"() {
        given:
        def inventory = new Inventory()
        def expectedResponse = "1 product added successfully"
        HttpEntity<Inventory> entity = new HttpEntity<>(inventory,FileUtility.getJwtHeaders());


        mockRestTemplate.exchange(
               FileUtility.CREATE_PRODUCT, HttpMethod.POST, entity, String.class) >> expectedResponse

        when:
        def result = adminWebConsumingService.addProduct(inventory)

        then:
        1 * mockRestTemplate.exchange(
                 FileUtility.CREATE_PRODUCT, HttpMethod.POST, _, String.class) >> ResponseEntity.ok(expectedResponse)
        result.body == expectedResponse
    }

    def "updateProductById should return ResponseEntity with body"() {
        given:
        def inventory = new Inventory()
        def expectedResponse = "1 Product (s) updated successfully"
        HttpEntity<Inventory> entity = new HttpEntity<>(inventory, FileUtility.getJwtHeaders())

        mockRestTemplate.exchange(
                FileUtility.UPDATE_PRODUCT_BY_ID, HttpMethod.PUT, entity, String.class) >> expectedResponse

        when:
        def result = adminWebConsumingService.updateProductById(inventory)

        then:
        1 * mockRestTemplate.exchange(_, _, _, String) >> ResponseEntity.ok(expectedResponse)
        result.body == expectedResponse
    }

    def "deleteProductById should return ResponseEntity with body"() {
        given:
        def productId =22
        def expectedResponse = "Product deleted successfully"
        HttpEntity<String> entity = new HttpEntity<>(FileUtility.getJwtHeaders())

        mockRestTemplate.exchange(
                FileUtility.DELETE_PRODUCT_BY_ID, HttpMethod.DELETE, entity, String.class, productId) >> expectedResponse

        when:
        def result = adminWebConsumingService.deleteProductById(productId)

        then:
        1 * mockRestTemplate.exchange(_, HttpMethod.DELETE, _, String,productId) >> ResponseEntity.ok(expectedResponse)
        result.body == expectedResponse
    }
}
