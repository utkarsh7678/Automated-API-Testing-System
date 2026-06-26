# Automated-API-Testing-System

A web-based Automated API Testing System developed using Java, JSP, Servlets, and JSON to automate the execution and validation of REST APIs. The system allows users to define API test cases in JSON format, execute HTTP requests automatically, validate responses, and generate detailed test reports.

## Features

* JSON-based API test case management
* Supports HTTP methods: GET, POST, PUT, and DELETE
* Automated API response validation
* Detailed Pass/Fail report generation
* Lightweight and modular architecture
* User-friendly web interface

## Tech Stack

* Java
* Java Servlets
* JSP (JavaServer Pages)
* JSON
* Apache Tomcat
* HTML/CSS

## Project Workflow

1. Upload or define API test cases in JSON format.
2. Parse the JSON test cases.
3. Dynamically construct and execute HTTP requests.
4. Validate API responses against expected results.
5. Generate detailed test execution reports.

## Project Structure

src/
├── servlet/
├── model/
├── utility/
└── validator/

WebContent/
├── WEB-INF/
├── jsp/
├── css/
└── js/

testcases/
reports/
README.md

## Installation and Setup

1. Clone the repository:
   git clone https://github.com/your-username/Automated-API-Testing-System.git

2. Import the project into Eclipse or IntelliJ IDEA.

3. Configure Apache Tomcat.

4. Build and deploy the project on the Tomcat server.

5. Open the application:
   http://localhost:8080/Automated-API-Testing-System

## Future Enhancements

* AI-based automatic test case generation
* Authentication token support
* Database integration for test history
* PDF and Excel report export
* OpenAPI/Swagger schema validation

## Authors

Utkarsh Kumar
B.Tech Computer Science and Engineering
Galgotias University, India

