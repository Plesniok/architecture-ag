# Spring Project Name

## Description
Service to handle shopping products


## Table of Contents
2. [Usage](#usage)
3. [Configuration](#configuration)

## Usage

## Configuration
### Application properties
In ```src/main/resources/application.properties``` define env variables.

#### Example variables
```
spring.application.name=products

//for in memory database 

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

//for migrations

spring.jpa.hibernate.ddl-auto=create-drop

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

//logs envs

logging.pattern.correlation=[${spring.application.name:},%X{traceId:-},%X{spanId:-}] 
logging.include-application-name=true
```
# architecture-ag
