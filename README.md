# The Assignment: Warehouse Management System

The assignment is to implement a warehouse software. This software should hold articles, and the articles should contain an identification number, a name and available stock. It should be possible to load articles into the software from a file, see the attached inventoryDTO.json.

The warehouse software should also have products, products are made of different articles. Products should have a name, price and a list of articles of which they are made from with a quantity. The products should also be loaded from a file, see the attached products.json. 
 
The warehouse should have at least the following functionality;

* Get all products and quantity of each that is an available with the current inventoryDTO
* Remove(Sell) a product and update the inventoryDTO accordingly
* Search for product
* Uploading an already existing article should increase the article stock

Given two sample input files are:

1. `inventory.json`, which holds the articles should contain an identification number, a name and available stock.`src/main/resources/inventory.json`
2. `products.json`, which contains name, price and a list of articles of which they are made from with a quantity. `src/main/resources/products.json`
 

## My Implementation

* It's built for **SpringBoot 2.4.2**

## Requirement
* Java 11
* Spring Boot
* Maven
* H2 Database
* Swagger API Documentation


## How to Run

* Build the project by running `mvn clean package` inside warehouse-ingka target directory.
* Once successfully built, run the service by using the following command:
```
java -jar  warehouse-ingka/target/warehouse-0.0.1-SNAPSHOT.jar

```

## You can run the application using batch file as well on Windows machine
```
Double click on "runApp.bat" file.

```

## To test API by running it locally you can use attached postman collection.
```
https://github.com/vickhny/WarehouseManagementSystem/blob/main/WarehouseServiceAPI.postman_collection.json
```


## REST APIs Endpoints
### Upload articles
```
POST /ingka/uploadArticles
Accept: application/json
Content-Type: application/octet-stream
```
![Alt text](src/main/resources/static/uploadarticles.png?raw=true "Optional Title")


### Upload products
```
POST /ingka/uploadProducts
Accept: application/json
Content-Type: application/octet-stream
```
![Alt text](src/main/resources/static/uploadproducts.png?raw=true "Optional Title")


### Get All products
```
GET /ingka/getAllProducts
Accept: application/json
Content-Type: application/json
```
![Alt text](src/main/resources/static/getallproducts.png?raw=true "Optional Title")


### Sell product
```
POST /ingka/sellProduct
Accept: application/json
Content-Type: application/json
```
![Alt text](src/main/resources/static/sellproduct.png?raw=true "Optional Title")


### Search product
```
POST /ingka/searchProduct
Accept: application/json
Content-Type: application/json
```
![Alt text](src/main/resources/static/searchproduct.png?raw=true "Optional Title")


### Get article available stocks 
```
POST /ingka/getArticleStock
Accept: application/json
Content-Type: application/json
```
![Alt text](src/main/resources/static/getarticlestock.png?raw=true "Optional Title")


### Get information about system health
```
http://localhost:8090/actuator/health

```

### To view Swagger 2 API docs
```
Run the server and browse to - http://localhost:8090/swagger-ui.html#/warehouse-controller
```
![Alt text](src/main/resources/static/swagger.png?raw=true "Optional Title")

