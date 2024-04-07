# k-lagan-challenge

In the company's e-commerce database we have the PRICES table that reflects the final price and the rate that applies to a chain product between certain dates. Below is an example of the table with the relevant fields:

## PRICES

| BRAND_ID | START_DATE             | END_DATE              | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE   | CURR |
|----------|------------------------|-----------------------|------------|------------|----------|---------|------|
| 1        | 2020-06-14-00.00.00    | 2020-12-31-23.59.59   | 1          | 35455      | 0        | 35.50   | EUR  |
| 1        | 2020-06-14-15.00.00    | 2020-06-14-18.30.00   | 2          | 35455      | 1        | 25.45   | EUR  |
| 1        | 2020-06-15-00.00.00    | 2020-06-15-11.00.00   | 3          | 35455      | 1        | 30.50   | EUR  |
| 1        | 2020-06-15-16.00.00    | 2020-12-31-23.59.59   | 4          | 35455      | 1        | 38.95   | EUR  |

## Fields:

- BRAND_ID: foreign key of the group (1 = XYZ). 
- START_DATE, END_DATE: range of dates in which the indicated rate price applies. 
- PRICE_LIST : Identifier of the applicable price list. 
- PRODUCT_ID: Product code identifier. 
- PRIORITY: Price application disambiguator. If two rates coincide in a range of dates, the one with the highest priority (highest numerical value) is applied. 
- PRICE : final sale price. 
- CURR: currency in iso. 


### What is requested: 

Build an application/service in SpringBoot that provides a rest endpoint such that: 

- Accept as input parameters: Application Date, Product Identifier, String Identifier. 
- Return as output data: product identifier, chain identifier, rate to apply, dates of application and final price to apply. 
- An in-memory database (e.g. h2) must be used and initialized with the data from the example (you can change the name of the fields and add new ones if you want, choose the data type that is considered appropriate for them). 


Develop some tests of the rest endpoint that validates the following requests of the service with the example data: 

- Test 1: request at 10:00 a.m. on the 14th for product 35455 for brand 1 (XYZ) 
- Test 2: request at 4:00 p.m. on the 14th for product 35455 for brand 1 (XYZ) 
- Test 3: request at 9:00 p.m. on day 14th for product 35455 for brand 1 (XYZ) 
- Test 4: request at 10:00 a.m. on the 15th for product 35455 for brand 1 (XYZ) 
- Test 5: request at 9:00 p.m. on day 16th for product 35455 for brand 1 (XYZ) 


### What Will be valued: 

- Design and construction of the service. 
- Code Quality. 
- Correct results in the tests. 

# SOLUTION:

Microservicio desarrollado con Springboot + Java 17 + Maven + JPA. Para testear la aplicación se utiliza JUnit y Mockito

Se aplica las siguientes ....

- TDD
- DDD
- Hexagonal Arquitecture
- CQRS 
- Aspect for loging envents 
- Cache
