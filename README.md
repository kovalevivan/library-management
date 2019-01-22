# Library Management Sample Application
## Project Explanation

[See the presentation here](https://docs.google.com/presentation/d/14zQbTPPNoDg-lROO9TSX-I-optgUjLFZPjHyEdcctGo/edit#slide=id.p)

## Demo on Heroku

[See demo here](http://ikoval-library-management.herokuapp.com/)
 
 ```shell
User:     admin
Password: admin
```

## Run Development Mode
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
