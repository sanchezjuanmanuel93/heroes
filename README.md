# Heroes Spring Boot Application

Technical examen for Hiberus Tecnología.
To implement this , I used:
* Spring Boot 2.5.4
* JDK 11
* Docker to package the application
* Swagger 2
* Liquibase
* H2 Database
* Maven

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
git clone https://github.com/sanchezjuanmanuel93/heroes-spring-boot-app.git

mvn package
```

## Usage

```bash
docker build -t heroes-api.jar .

docker run -p 8080:8080 heroes-api.jar
```

## Useful links

* H2 console: [http://localhost:8080/h2](http://localhost:8080/h2)
* Swagger: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)


