# Project Title: hrSystem

### Description

###  App Links
   Purpose  | App Endpoint | 
   ---------|---------------
   Get Flat Master Data    | http://localhost:1010/maintenance/lookup
   Individual Flat Lookup  | http://localhost:1010/maintenance/flat/{flatNumber}
   Add Maintenance         | http://localhost:1010/maintenance/addMaintenance
   Delete Transaction      | http://localhost:1010/maintenance/deleteTxn
   All Transactions        | http://localhost:1010/maintenance/transactions/all
   Pending Maintenance     | http://localhost:1010/maintenance/pending/{month}/{year}
   Get Floor wise Total    | http://localhost:1010/maintenance/floorTotal/{month}/{year}
  
Dashboard: 


### Project Prerequisite


### Project Setup


### Starting the App


### Running Unit Tests


### Running Integration Tests

    
### Start the App in Debug mode

### Swagger details

- Api-Docs: [http://localhost:1010/v2/api-docs]
- UI: [http://localhost:1010/swagger-ui.html]
  
## Project Requirements
- JDK 1.8 or higher
- Maven 3.0 or higher
- Connectivity to a MY SQL server database with appropriate tables

## GENERAL SETUP

## Build application
`mvn clean install`

## Running Unit And Acceptance Tests
`mvn clean package`

## MY SQL Server Tables Leveraged
   - flat_maintenance_lookup
   - maintenance_txn
