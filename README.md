# Library Management Sample Application
## Project Explanation

[See the presentation here](https://docs.google.com/presentation/d/14zQbTPPNoDg-lROO9TSX-I-optgUjLFZPjHyEdcctGo/edit#slide=id.p)

## Demo on Heroku

[See demo here](http://ikoval-library-management.herokuapp.com/)
 
 ```shell
User:     admin
Password: admin
```
## Introduction
The idea of the project is to try the following frameworks for educational purposes:

- Server
  - Spring Boot
  - Spring Security
  - Spring Data
  - Hibernate
- Client
  - GWT
  - RestyGWT
- Documentation
  - Swagger UI
  
##  What was implemented
Single-page book management web application with a REST API backend. The application has the following features:
 - Create, Read, Update, and Delete commands
 - Server-side pagination
 - Server-side sorting
 - Server-side filtering by several parameters
 - Security features: login form and storage of user data in the database



## Run for Development
1. Server: Start the WebApp with Spring Boot
   
2. Client: Start GWT SuperDev Mode transpiler
 ```shell
mvn gwt:run-codeserver
```
3. Go to the application URL using a web browser:
 ```http
http://localhost:8080/
```
## Database configuration
In its default configuration, Library Management uses an in-memory database (H2). The database gets populated with data at startup.
