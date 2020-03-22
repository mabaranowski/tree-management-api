# tree-management-api 

## Description
Server side of tree-like structure management web app.
For a client side, go to [tree-management-web](https://github.com/mabaranowski/tree-management-web.git)

## Requirements
- Spring Boot 2.2.5
- JDK 8

## Deployment
To deploy full-stack app, start with tree-management-web project.
Make sure both tree-management-api and tree-management-web are in the same directory.
After running ng build in tree-management-web, start server with ./mvnw spring-boot:run.

## Running
```
$ ./mvnw spring-boot:run
```
## Tests
```
$ ./mvnw test
```
