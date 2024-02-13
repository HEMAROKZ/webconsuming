WEBCONSUMING SPRING BOOT APPLICATION 

* This project is a Web Consuming Application that utilizes the RestTemplate library to interact with external web services or APIs.
* It provides a convenient way to make HTTP requests, handle responses, and consume data from remote endpoints.
* For each and every request an header with a JWT token has to be passed along with the request body as the server application contain jwt authentication to access the endpoints .
* Before accessing the endpoints http://localhost:8082/get-token must be hit to generate the authentication jwt token using this endpoint with crediantials in body .
* as for the response JWT token  is stored in  the application  it is used to construct an header with the token and passed along each and every request .
* Logger is used to log the required info for debug when required .
* Custom exception is used to handel HttpClientErrorException and HttpServerErrorException common exception while trying to consume an rest APIs.

  ENDPOINTS PRESENT
  
  part of ADMIN process:
  * http://localhost:8082/get-token -TO GENERATE token wsing crediantials .
  * http://localhost:8082/products/{id}  -to delete an particular product.
  * http://localhost:8082/addProduct -to add an product in inventory.
  * http://localhost:8082/updateProduct -to update an product in inventory.

   part of Customer process:
  * http://localhost:8082/getAllProduct -to fetch all inventry product details
  * http://localhost:8082/product -to purchase the product from vending machine with body of required details
  * http://localhost:8082/getProduct/{id} -to fetch detail od particular product 
