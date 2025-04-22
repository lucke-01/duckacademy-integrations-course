# Rest API

## Requirements

JDK Java 21 or higher

Maven 3.9.9

## Use and installation

### configure open api generated resources in IDE

1. In your IDE go to target/generated-sources/open-api/src
2. Right click > mark directory as > generates resources root
3. You should be able to use this directory packages in your IDE

## Open API

### Operate with Open API

The open api document is here [Rest API](src/resources/open-api/rest-api.yml)

### REST DOC - Swagger access

Open the following URL

``` browser
http://localhost:8080/swagger-ui
```

## build application:

``` cmd
mvn clean install
```

