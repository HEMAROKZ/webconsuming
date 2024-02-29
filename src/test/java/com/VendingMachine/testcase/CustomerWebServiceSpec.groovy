package com.VendingMachine.testcase


import com.vendingmachine.WebConsuming.model.Inventory
import com.vendingmachine.WebConsuming.model.PurchaseRequest
import com.vendingmachine.WebConsuming.service.CustomerWebConsumingService
import com.vendingmachine.WebConsuming.util.FileUtility
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject

class CustomerWebServiceSpec extends Specification {

    @Subject
    CustomerWebConsumingService customerWebConsumingService=new CustomerWebConsumingService();
    // Mock RestTemplate to avoid actual HTTP calls
    def mockRestTemplate = Mock(RestTemplate)



    def setup() {
        customerWebConsumingService.restTemplate = mockRestTemplate
    }



    def "getAllInventory should return ResponseEntity with list of InventoryDTO"() {
        given:
        def expectedResponse = [ new Inventory(productId: 1, name: "Product1", productPrice: 10, productInventoryCount: 5),
                                                   new Inventory(productId: 2, name: "Product2", productPrice: 15, productInventoryCount: 8)]
        HttpEntity<String> entity = new HttpEntity<>( FileUtility.getJwtHeaders())

        mockRestTemplate.exchange(
                FileUtility.GET_ALL_ITEMS, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Inventory>>() {}) >> ResponseEntity.ok(expectedResponse)

        when:
        def result = customerWebConsumingService.getAllInventory()

        then:
        result.body == expectedResponse
    }


    def "getProductById should return ResponseEntity with body of Inventory"() {
        given:
        def id = 1
        def expectedResponse = new Inventory(productId: id, name: "Product1", productPrice: 10, productInventoryCount: 5)
        HttpEntity<String> entity = new HttpEntity<>(FileUtility.getJwtHeaders())

        mockRestTemplate.exchange(
                FileUtility.GET_PRODUCT_BY_ID, HttpMethod.GET, entity, Inventory.class, id) >> ResponseEntity.ok(expectedResponse)

        when:
        def result = customerWebConsumingService.getProductById(id)

        then:
        result == expectedResponse
    }




    def "callAddMultipledDenominationForProductRest should return ResponseEntity with body"() {
        given:
        def purchaseRequest = new PurchaseRequest(
                purchaseDTO: [
                        productId: [611, 899],
                        quantity: [2, 1]
                ],
                denominationConfig: [
                        denomination50: 1,
                        denomination20: 1,
                        denomination10: 1
                ],
                billingCounter: 1
        )
        def expectedResponse = """Bill generated successfully. FileName: Date and Time: 2024-02-27 16:24:19

Total Change: 10.0 Rupees

Denominations:
10 Rupees: 1 notes/coins

Details for Each Product:
Product Name: STRING
Product Price: 32 Rupees
Number Of Quantity Purchased: 1

"""

        HttpEntity<PurchaseRequest> requestEntity = new HttpEntity<>(purchaseRequest, FileUtility.getJwtHeaders())

        mockRestTemplate.postForEntity(
                FileUtility.PURCHASE_PRODUCT_BY_ID, requestEntity, String.class) >> ResponseEntity.ok(expectedResponse)

        when:
        def result = customerWebConsumingService.callAddMultipledDenominationForProductRest(purchaseRequest)

        then:
        result.body == expectedResponse
    }
//////////////////////////////////////////////////////////



}


