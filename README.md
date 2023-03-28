# [Assignment for technical assessment](https://gist.github.com/ongbt/b06720e106a4a02ddf33de7bc5537e19#backend-api-asessment)

### Introduction
The School Admin project is an API service that register teachers to students. It provides a secure and easy-to-use platform for teachers to view their list of students.

### Technology Stack
The School Administration API is built using the following technology stack:

- Java 11
- Spring Boot Framework
- H2 Database (for local development environment)
- PostgreSQL (for production environment)  

The Spring Boot Framework provides a robust and scalable platform for building web applications. H2 Database is used for local development, providing an in-memory database for quick and easy testing. For production, the project is configured to use PostgreSQL, a powerful and reliable relational database management system.  

By utilizing these technologies, the project is able to deliver a fast, reliable, and secure web application for managing assignments and students.  

### Features
The School Administration API provides the following features:

- Student & teacher registration
- Register single/multiple student to teacher
- De-register single student from teacher
- View all teacher with registered students

### Architecture diagram illustration
![Image](./src/main/resources/architecture-diagram2.png){ width="800" height="600" style="display: block; margin: 0 auto" }

### DB diagram illustration
![Image](./src/main/resources/db-diagram.PNG)

  Hostname for dev profile: `http://localhost:8081`  
  Hostname for prod profile: `https://assignment-school-admin-api.herokuapp.com/`  

### Usage

To run the Assignment School Admin project, follow these steps:

1. Simply clone this git, install and run as Spring application. 
2. By default, dev profile is selected and there is no preloaded data.
3. Import the postman collection [**HERE**](/src/main/resources/assigment-school-admin-api.postman_collection.json) and ready to use. 
4. Alternatively you can use api doc in `{hostname}/swagger-ui/index.html#/`
5. As this is secured API, you must enter your credentials under basic auth:  

       username: admin
       password: password

6. If you are using production url, you may skip all steps above and go to the api documentation  

       https://assignment-school-admin-api.herokuapp.com/swagger-ui/index.html

7. For any issues, email me at norulshahlam@gmail.com or whatsapp me at +6592212152

### HAPPY TESTING!