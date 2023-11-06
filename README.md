# Restful API for Online eCommerce Application using SpringBoot and Reactjs for frontEnd with Spring Security with JWT Implementation

An eCommerce Application backend with Java Spring Boot with frontend using the Reactjs

- Welcome to the documentation for the Ecommerce API, a powerful RESTful API for an Online eCommerce Application developed using Spring Boot, Spring Security with JWT implementation, and React for the frontend. This API provides comprehensive endpoints to support various features of an Ecommerce Application.

### Deployed link: https://eccomers96.netlify.app/

###  Railway Deployed Apis
https://api-ecom.up.railway.app//swagger-ui/index.html#/

## Tech Stack and Technology Used

- Java
- Spring Framework
- Spring Boot
- JavaScript
- React
- Spring Data JPA
- Hibernate
- MySQL (DataBase)
- Swagger
- Spring Security
- JSON Web Tokens (JWT)
- BCrypt
- Maven
- Axios

## Schema Table
![ER_diagram](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/3bd9f9b8-29a8-42fe-93fd-daa931d46c70)

## Features

- User registration and login with JWT authentication
- Password encryption using BCrypt
- Role-based authorization with Spring Security for user and admin
- Customized access denied handling
- User Module
- Admin Module

## Getting Started

To get started with this project, you will need to have the following installed on your local machine:

- JDK 17+
- Maven 3+

## Installation & Run
To install this application, run the following commands:

### Clone the project repository:
```bash
git clone https://github.com/Vivekgupta96/eCommerce-Application.git
```
### Navigate to the project directory:
```
cd eCommerce-Application

```

This will get a copy of the project installed locally. To configure all of its dependencies and start each app, follow the instructions below.

### Configure Database

Once MySQL is installed you must configure a username and password. By default the user and password should be `root` . If not, you must configure in the file `application.configure` located in the path `src/main/resources/`.

In the file `application.configure` you must edit the parameters `spring.datasource.username` and `spring.datasource.password` with the values you defined.

```
    server.port=8080

    spring.datasource.url=jdbc:mysql://localhost:3306/Ecomdb
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=SQLUsername(i.e=root)
    spring.datasource.password=SQLUserPassword(i.e=root)

```

## Build and run the Spring Boot application using CMD
```
mvnw spring-boot:run
```
```


## API Root Endpoint with Swagger Documentation

```
### https://localhost:8080/

###  Railway Deployed Apis
https://api-ecom.up.railway.app/swagger-ui/index.html#/
### http://localhost:8080/swagger-ui/index.html
```

- To Get Access as admin , you have to use below query for register the admin for the first time in MySQL databse folling are details below

```
step:1
use ecomdb;

step:2

INSERT INTO users (email, password, first_name, last_name, phone_number,user_role,user_account_status)
VALUES ('admin@gmail.com', 'admin@1234', 'Admin', 'Admin', '9999999999',ROLE_ADMIN,ACTIVE);

step:3
Admin Login using credetials ,now you are ready to do all aldmin operation

```

## Admin Access point :- On Footer section rightSide (Admin Access) at Landing / Home PAge

## For Running frontend in Local Machine

- Getting Started

* Before you can run the React frontend, make sure you have the following prerequisites installed on your machine:

* Node.js: Ensure you have Node.js installed.

### Installation

- Once you have the prerequisites in place, follow these steps to install the required packages for the React frontend:

- Open your terminal and navigate to the root directory of the React frontend project. This is the directory where you find the package.json file.

- Run the following command to install all the necessary packages:

```
npm install
```

- This command will download and install all the dependencies listed in the package.json file.

### Running the Application

- To run the React frontend locally, follow these steps:

- Open your terminal and navigate to the root directory of the React frontend project.

- Run the following command to start the development server:

```
npm start
```

Open your web browser and navigate to the following URL:

```
http://localhost:3000


You should now see the React frontend of the Ecommerce Application running locally.

## Below are the website Shot
![register](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/a190a275-8f04-423e-95da-d394c5a035e4)

![login](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/46d7e173-11c6-44fd-a4b1-00e6ae64717e)

![home](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/1f38ba7d-bb1d-43a4-a35d-88fd3746c537)

![product](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/bfa23279-c7a0-4466-998e-4c61942e29a8)

![cart](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/412d1734-dc1f-4f53-bf6a-f598ebc636c2)


![history](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/7b96800a-2b87-4d22-96b6-223964d708c3)

![profile](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/dc3b9dcc-89e1-461a-842c-d14ba6b1b020)

![admin](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/6bdbd598-8130-4e3f-b988-258ae8185f82)

![Allorderdetails](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/4f2236b6-85c4-405a-98e2-b9ca3cd17b8e)

![productadd](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/a37f175f-eebf-41b4-abd2-50b6e9826437)

![alluser](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/0c4710a8-b9db-4827-954c-603285b318d5)


## Below are the endPoint
![endpoint1](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/984456e4-fc64-4c8b-b38e-cb681ec49cb6)

![endpoint2](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/f390583b-d608-4f2e-af85-868441bb3981)

![endpoint3](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/02afbb69-96f6-4cd6-84cc-9baf595e9da9)

![endpoint4](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/743fa282-f9b0-4ad3-a6a7-f064649fb494)

![endpoint5](https://github.com/Vivekgupta96/eCommerce-Application/assets/119284680/27ce182d-05f5-4260-a2ab-a59485a8788a)

## Links

This project uses the following open source libraries:

- [Spring Boot](https://spring.io/projects/spring-boot)
