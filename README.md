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

Method    | Path                                                                                 | Description
:---------|:-------------------------------------------------------------------------------------|:-----------
**GET**   | `/emergency-auth-code/company/{incorporationNumber}/eligible-officers`               | Calls service to retrieve eligible officers for company number
**GET**   | `/emergency-auth-code/company/{companyNumber}/eligible-officers/{officerId}`         | Calls service to retrieve officer for company number
**GET**   | `/emergency-auth-code/company/{companyNumber}/efiling-status`                        | Calls service to check if company has filed in the past thirty days
**GET**   | `/company/{companyNumber}/action-code`                                               | Calls service to retrieve the current action code set against the company
**GET**   | `/company/{companyNumber}/gaz2-requested`                                            | Calls service to check if a gaz2 is requested for the company
**GET**   | `/company/{companyNumber}/traded-status`                                             | Calls service to retrieve the current traded status for the company
**GET**   | `/officer-search/scottish-bankrupt-officers/{ephemeral_officer_key}`                 | Calls service to view the details for a Scottish bankrupt officer
**POST**  | `/officer-search/scottish-bankrupt-officers`                                         | Calls service to search for a Scottish bankrupt officer
**GET**   | `/company/{companyNumber}/director/active`                                           | Calls service to retrieve the active director details of a company
**GET**   | `/company/{companyNumber}/shareholders/count`                                        | Calls service to retrieve the number of active company shareholders
**GET**   | `/company/{companyNumber}/shareholders`                                              | Calls service to retrieve the list of all active company shareholders
**GET**   | `/company/{companyNumber}/statement-of-capital`                                      | Calls service to retrieve the statement of capital data for the company
**GET**   | `/company/{companyNumber}/filing-history`                                            | Calls service to retrieve the filing history transactions
**GET**   | `/company/{companyNumber}/corporate-body-appointments/persons-of-significant-control`| Calls service to retrieve the psc data for the company
**GET**   | `/company/{companyNumber}/confirmation-statement/paid`                               | Calls service to verify confirmation statement payment has been made for company on due date
**GET**   | `/company/{companyNumber}/register/location`                                         | Calls service to retrieve the registers locations for a company
**GET**   | `/company/{companyNumber}/registered-email-address`                                  | Calls service to retrieve the registered email address for a company
**GET**   | `/overseas-entity/{companyNumber}/managing-officers`                                 | Calls service to retrieve the managing officers for an overseas entity
**GET**   | `/overseas-entity/{companyNumber}/beneficial-owners`                                 | Calls service to retrieve the beneficial owner data for an overseas entity
**GET**   | `/overseas-entity/{companyNumber}/trusts/details`                                    | Calls service to retrieve the trust data for an overseas entity
**GET**   | `/overseas-entity/trusts/{trustId}/individual-trustees`                              | Calls service to retrieve the individual trustee data for a trust
**GET**   | `/overseas-entity/{oeNumber}/trusts/beneficial-owners/links`                         | Calls service to retrieve the trust link data for an overseas entity
 **GET**  | `/overseas-entity/trusts/{trustId}/corporate-trustees`                               | Calls service to retrieve the Trustee Data when given the ID of a Trust 
### Query parameters
Query parameter          | Description
:------------------------|:-----------
`items_per_page`         | Number of items per page returned in this list
`start_index`            | Zero indexed, the offset into the entire list that this page starts at
`payment_period_made_up_to_date`| The confirmation statement payment period made up to date to search for against a specified company when verifying payment for confirmation statement. Date must be ISO format yyyy-MM-dd e.g. 2019-03-31
### Configuration

Key                | Description
-------------------|------------------------------------
`ORACLE_QUERY_API_PORT`|The port of the oracle query api service
`HUMAN_LOG`            |For human readable logs


## Terraform ECS

### What does this code do?

The code present in this repository is used to define and deploy a dockerised container in AWS ECS.
This is done by calling a [module](https://github.com/companieshouse/terraform-modules/tree/main/aws/ecs) from terraform-modules. Application specific attributes are injected and the service is then deployed using Terraform via the CICD platform 'Concourse'.


Application specific attributes | Value                              | Description
:---------|:-----------------------------------------------------------------------------|:-----------
**ECS Cluster**        |utility                                      | ECS cluster (stack) the service belongs to
**Load balancer**      |alb-{env}-oracle-query-api                   | The [load balancer](https://github.com/companieshouse/utility-services-stack/blob/303099e40790b105bc04e310167f26564a0eedec/groups/infrastructure/main.tf#L23) that sits in front of the service
**Concourse pipeline**     |[Pipeline link](https://ci-platform.companieshouse.gov.uk/teams/team-development/pipelines/oracle-query-api) <br> [Pipeline code](https://github.com/companieshouse/ci-pipelines/blob/master/pipelines/ssplatform/team-development/oracle-query-api)                                  | Concourse pipeline link in shared services


### Contributing
- Please refer to the [ECS Development and Infrastructure Documentation](https://companieshouse.atlassian.net/wiki/spaces/DEVOPS/pages/4390649858/Copy+of+ECS+Development+and+Infrastructure+Documentation+Updated) for detailed information on the infrastructure being deployed.

### Testing
- Ensure the terraform runner local plan executes without issues. For information on terraform runners please see the [Terraform Runner Quickstart guide](https://companieshouse.atlassian.net/wiki/spaces/DEVOPS/pages/1694236886/Terraform+Runner+Quickstart).
- If you encounter any issues or have questions, reach out to the team on the **#platform** slack channel.

### Vault Configuration Updates
- Any secrets required for this service will be stored in Vault. For any updates to the Vault configuration, please consult with the **#platform** team and submit a workflow request.

### Useful Links
- [ECS service config dev repository](https://github.com/companieshouse/ecs-service-configs-dev)
- [ECS service config production repository](https://github.com/companieshouse/ecs-service-configs-production)
