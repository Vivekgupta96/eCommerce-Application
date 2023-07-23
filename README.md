# Restful API for Online eCommerce Application using SpringBoot with Spring Security with JWT Implementation

This Applicationp is a eCommerce Application with Spring Boot API with All endPoint for Ecommerse Application

*  Developed this REST API for an Online eCommerce Applicationn. 
* This API performs all the fundamental CRUD operations of any Online eCommerce Applicationn with user validation at every step.


## Tech Stack and Technology Used

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Swagger
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven
  

  
## Schema Table 
![Master](https://github.com/Vivekgupta96/eCommerce-Application/blob/main/ER_diagram.png)


## Features
* User  and Admin registration and login with JWT authentication
* Password encryption using BCrypt
* Role-based authorization with Spring Security
* Customized access denied handling
* User Module
* Admin Module

## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+

To install this application, run the following commands:

```bash
git clone https://github.com/Vivekgupta96/eCommerce-Application.git 
cd eCommerse Application
```

This will get a copy of the project installed locally. To configure all of its dependencies and start each app, follow the instructions below.

### Configure Database

Once MySQL is installed you must configure a username and password. By default the user and password should be `root` . If not, you must configure in the file `application.configure` located in the path `src/main/resources/`.

In the file `application.configure` you must edit the parameters `spring.datasource.username` and `spring.datasource.password` with the values you defined.

## Installation & Run

```
    server.port=8080

    spring.datasource.url=jdbc:mysql://localhost:3306/Ecomdb
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=SQLUsername(i.e=root)
    spring.datasource.password=SQLUserPassword(i.e=root)

```

## API Root Endpoint with Swagger Documentation

```
https://localhost:8080/
http://localhost:8080/swagger-ui/index.html
```

## Links

This project uses the following open source libraries:

- [Spring Boot](https://spring.io/projects/spring-boot)


