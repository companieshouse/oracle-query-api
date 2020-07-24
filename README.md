# Oracle query API Service
API service wrapper for CHIPS Oracle DB queries that cannot be done in other languages e.g. golang due to driver or environment constraints. This application is written using the [Spring Boot](http://projects.spring.io/spring-boot/) Java framework.

## Requirements
In order to run the API locally you'll need the following installed on your machine:

- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)

## Getting Started
1. Run `make`
2. Run `./start.sh`

### Endpoints

Method    | Path                                                     | Description
:---------|:---------------------------------------------------------|:-----------
**GET**   | `/company/{incorporationNumber}/eligible-officers`       | Calls service to retrieve eligible officers for company number
**GET**   | `/company/{companyNumber}/eligible-officers/{officerId}` | Calls service to retrieve officer for company number
**GET**   | `/company/{companyNumber}/efiling-status`                | Calls service to check if company has filed in the past thirty days

### Configuration

Key                | Description
-------------------|------------------------------------
`ORACLE_QUERY_API_PORT`|The port of the oracle query api service
`HUMAN_LOG`            |For human readable logs