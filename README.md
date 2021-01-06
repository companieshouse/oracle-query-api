# Oracle query API Service
API service wrapper for CHIPS Oracle DB queries. This API should be used for all queries from CHS to the CHIPS database. This application is written using the [Spring Boot](http://projects.spring.io/spring-boot/) Java framework.

## Requirements
In order to run the API locally you'll need the following installed on your machine:

- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)

## Getting Started
1. Run `make`
2. Run `./start.sh`

### Endpoints

Method    | Path                                                                         | Description
:---------|:-----------------------------------------------------------------------------|:-----------
**GET**   | `/emergency-auth-code/company/{incorporationNumber}/eligible-officers`       | Calls service to retrieve eligible officers for company number
**GET**   | `/emergency-auth-code/company/{companyNumber}/eligible-officers/{officerId}` | Calls service to retrieve officer for company number
**GET**   | `/emergency-auth-code/company/{companyNumber}/efiling-status`                | Calls service to check if company has filed in the past thirty days
**GET**   | `/company/{companyNumber}/action-code`                                       | Calls service to retrieve the current action code set against the company

### Query parameters
Query parameter  | Description
:----------------|:-----------
`items_per_page` | Number of items per page returned in this list
`start_index`    | Zero indexed, the offset into the entire list that this page starts at


### Configuration

Key                | Description
-------------------|------------------------------------
`ORACLE_QUERY_API_PORT`|The port of the oracle query api service
`HUMAN_LOG`            |For human readable logs
