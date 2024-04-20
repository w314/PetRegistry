# Tutorial to Build Pets App

# Project Setup

### Initialize App
- use [Spring Initializer](https://start.spring.io/) to generate app
  - Project: Maven
  - Language: Java
  - Spring Boot: 3.2.5
  - Project Metadata
    - Group: com.wp
    - Artifact: Pets
    - Name: (same as artifact)
    - Package name: (generated)
    - Packaging: Jar
    - Java: 17
  - Dependencies
    - Spring Web
    - Spring Data JPA
    - PostgreSQL Driver
  
- Generate Application
- Extract Downloaded files to appropriate directory
- (application won't run until db connection is setup)
## Setup GIT
- `cd` into project directory
-  run `git init`
-  setup remote repository
- add remore repository `git remote add origin <repoName>`
- make initial commit 

## Setup Database Connection
Add to `src/main/resources/application.properties`:
```bash
# Database Credentials (we need these to connect to our database)------------------------

spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=password

# Spring Data Settings-----------------------

# This allows us to see SQL running in the console - great for debugging
spring.jpa.show-sql=true

# Setting our DDL to update when a change happens (creation/updates)
spring.jpa.hibernate.ddl-auto=update
# We could have set this to "create" to drop and recreate the database each time

# Specify what DB schema we're pointing to
spring.jpa.properties.hibernate.default_schema=public
```