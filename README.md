# Assigment for Cognizant technical assessment

### Introduction
The Assignment School Admin project is an API that register teachers to students. It provides a secure and easy-to-use platform for teachers to view their list of students.

### Architecture diagram illustration
![Image](./src/main/resources/architecture-diagram2.png)

### Technology Stack
The Assignment School Admin project is built using the following technology stack:

- Java 11
- Spring Boot Framework
- H2 Database (for local development environment)
- PostgreSQL (for production environment)
- The Spring Boot Framework provides a robust and scalable platform for building web applications. H2 Database is used for local development, providing an in-memory database for quick and easy testing. For production, the project is configured to use PostgreSQL, a powerful and reliable relational database management system.  

By utilizing these technologies, the project is able to deliver a fast, reliable, and secure web application for managing assignments and students.

### DB diagram illustration
![Image](./src/main/resources/db-diagram.PNG)


### [View requirement](./src/main/resources/dev-assessment.md)
### [Postman collection (import into Postman)](https://gist.github.com/ongbt/b06720e106a4a02ddf33de7bc5537e19#backend-api-asessment)

There is no preloaded data so you have to add data manually. You can use the above collections for your postman usage or you can use api doc alternatively in
  `http://localhost:8081/swagger-ui/index.html#/`

  Hostname for localhost: `http://localhost:8081`  
  Hostname for production: `https://assignment-school-admin-api.herokuapp.com/`  

### Features
The School Administration System provides the following features:

- Student & teacher registration
- Register single/multiple student to teacher
- De-register single student from teacher
- View all teacher with registered students

### Usage

To use the Assignment School Admin project, follow these steps:

1. Simply clone this git, install and run as Spring application. 
2. By default, dev profile is selected
3. Use the localhost value as your hostname (listed ABOVE). 
4. Import the postman collections and ready to use (listed ABOVE). 
5. As this is secured API, you must enter your credentials under basic auth:  
```
username: admin
password: password
```
8. Or you can simply skip the steps and go to production api docs  

    https://assignment-school-admin-api.herokuapp.com/swagger-ui/index.html

8. For any issues, email me at norulshahlam@gmail.com or whatsapp me at +6592212152

### HAPPY TESTING!