# GiG Technical Test
 
## Initial Steps

- I first analysed the problem and identified a possible solution.
- I then researched for the ideal packages to use to create the RESTful API along with a connection to the DB (PSQL).
- Started off by generating a template Maven project using [Spring Initializr](https://start.spring.io/).
- From this project, I spent a few hours getting to grips with Java and understanding how the Spring framework interacts with the PSQL DB and handles API calls.
- From this point onwards, I felt confident in implementing the required business logic to create the solution required.

## Architecture

I opted for an N-Tier Architecture, splitting the layers into the following:

Controller -> Service -> Repository -> Database

This structure separates logic and reduces dependability in the code, promoting maintainability and reusability.

## Running Application

In the Application directory in this repository, are two files, the JAR file representing the WebAPI and a Docker-Compose file representing the database.

Assuming Docker and Java are installed on the system, you may follow the below steps to run the application:

1. Run the JAR file by opening a terminal within the same directory as the JAR file and run "java -jar app.jar".
2. Within another terminal in the same directory, run "docker-compose up".

## Testing API

The API contains 2 endpoints:

GET localhost:8080/api/ids | This endpoint is functional ONLY for testing, so that the tester can know the available IDs in the database to make valid requests.
POST localhost:8080/api/transfer | This endpoint handles the transferring of balance from one account to another. This endpoint requires a JSON body of the following format:

{ 
    senderId: Integer,
    receiverId: Integer,
    transferAmount: double
}

Curl request examples:

- curl -X POST http://localhost:8080/api/transfer -H "Content-Type: application/json" -d "{\"senderId\": 1, \"receiverId\": 2, \"transferAmount\": 100.50}"
- curl -X POST http://localhost:8080/api/transfer -H "Content-Type: application/json" -d "{\"senderId\": 3, \"receiverId\": 1, \"transferAmount\": 360.20}"

## Thoughts During Development

- I thought about implementing a PATCH endpoint to handle the balance updates between accounts, but I opted against it as it would be far more secure to handle the update logic in an unexposed manner.
- Since this is a simple API, not much business logic was required, but I ensured that the /api/transfer endpoint was fully functional by implementing the following validations: <br>
 i. Ensure transferAmount is greater than 0. <br>
 ii. Ensure senderId is not null. <br>
 iii. Ensure receiverId is not null. <br>
 iv. Ensure that the senderId and receiverId are not the same. <br>
 v. Attempt to find the sender and receiver in the database. <br>
 vi. Ensure that the sender has sufficient balance to carry out the transaction. <br>

- To keep the solution simple, the Account table contains an id, first_name, last_name, and balance.

## Solution Approach
- I first researched the ideal way to implement an API in Java.
- Secondly, I analysed the problem in the test document and drafted a diagram of what the structure of the solution should look like (image in repository named Diagram.png).
- Using the diagram, I started building the solution section by section.
- To ensure that my desired solution will be viable, I first created a PSQL DB using a docker-compose file and attempted to communicate with it through a simple Spring Application.
- Once that was done, I was confident in building the required layers to complete the test.
- I created the Controller to handle the incoming requests and set up the GET and POST endpoints.
- I then created the Service to contain the business logic required, to then be called by the corresponding endpoints.
- Throughout this, I created an Account Model to represent the Account table in the database.
- Using this Model, I was then able to create an AccountsRepository and extend the JPARepository, this simplified database queries by providing standard methods.
- After each layer was implemented, I conducted a series of tests to ensure that all connections are in place and all layers are communicating effectively.
- Once I was confident in the solution, I used the Maven Assembly plugin to generate the JAR file required so that the API Application can run with a simple Java command.
- Lastly, I tested the application by running the docker-compose file for the DB, and then the JAR file for the application.
- Both the DB and the Application worked in coherence and could handle logical endpoint calls.
