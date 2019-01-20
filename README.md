# Library Management Sample Application
## Project explanation

[See the presentation here](https://docs.google.com/presentation/d/14zQbTPPNoDg-lROO9TSX-I-optgUjLFZPjHyEdcctGo/edit#slide=id.p)

## Demo in Heroku

[See demo here](http://ikoval-library-management.herokuapp.com/)
 
 ```shell
User:     admin
Password: admin
```
## Introduction
The idea of the project is to try the following frameworks in educational purpose.

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
Single-page web application with REST API backend for book management with the following features:
 - Create, Read, Update, Delete options
 - Server side pagination
 - Server side sorting
 - Server side filtering by several parameters
 - Security with login form and storing user data in database



## Run for Development
1. Server: Start the WebApp with Spring Boot
   
2. Client: Start GWT SuperDev Mode transpiler
 ```shell
mvn gwt:run-codeserver
```
3. Go to the application URL with a web browser:
 ```http
http://localhost:8080/
```
## Database configuration
In its default configuration, Library Management uses an in-memory database (H2) which gets populated at startup with data.
