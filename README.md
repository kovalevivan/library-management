# Library Management Sample Application
## Demo in Heroku

[see demo here](http://ikoval-library-management.herokuapp.com/)
 
 ```shell
User: admin
Password: admin
```
## Introduction
This is a single-page web application with REST API backend. The aim was to try the following frameworks in educational purpose.

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
Web application for book management with the following features
  - Presentation of data on the client in the table
  - Create, Read, Update, Delete options
  - Server side pagination
  - Server side sorting
  - Server side filtering by several parameters

## Run for Development
1. Server: Start the WebApp with Spring Boot
   
2. Client: Start GWT SuperDev Mode transpiler
 ```java
mvn gwt:run-codeserver
```
3. Go to the application URL with a web browser:
 ```http
http://localhost:8080/
```
## Database configuration
In its default configuration, Library Management uses an in-memory database (H2) which gets populated at startup with data.
