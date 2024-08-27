# GiG Technical Test
 
## Initial Steps

- I first analysed the problem and identified a possible solution.
- I then researched for the ideal packages to use to create the RESTful API along with a connection to the DB (PSQL).
- Started off by generating a template Maven project using [Spring Initializr](https://start.spring.io/).
- From this project, I spent a few hours getting to grips with Java and understanding how the Spring framework interacts with the PSQL db and handles API calls.
- From this point onwards, I felt confident in implementing the required business logic to create the solution required.

## Architecture

I opted for an N-Tier Architecture, splitting the layers into the following:

Controller -> Service -> Repository -> Database

This structure separates logic and reduces dependability in the code, promoting maintainability and reuseability.

## Running Application

In the Application directory in this repository, are two files, the JAR file representing the WebAPI and a Docker-Compose file repsenting the database.

Assuming Docker and Java are installed on the system, you may follow the below steps to run the application:

1. Run the JAR file by opening a terminal within the same directory as the JAR file, and run "java -jar app.jar".
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

- I thought about implementing a PATCH endpoint to handle the balance updates between accounts, but I opted against as it would be far more secure to handle the update logic in an unexposed manner.
- Since this is a simple API, not much business logic was required, but I ensured that the /api/transfer endpoint was fully functional by implementing the following validations:
 i. Ensure transferAmount is greater than 0. <br>
 ii. Ensure senderId is not null. <br>
 iii. Ensure receiverId is not null. <br>
 iv. Ensure that the senderId and receiverId are not the same. <br>
 v. Attempt to find the sender and receiver in the database. <br>
 vi. Ensure that the sender has sufficient balance to carry out the transaction. <br>

- To keep the solution simple, the Account table contains an id, first_name, last_name, and balance.
