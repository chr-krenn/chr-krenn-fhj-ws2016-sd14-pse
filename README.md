# SWD14 - PSE Project

## Project Structure

This project is separated in three modules.

1. Backend
This module contains the backend interfaces and corresponding implementation.
2. Frontend
This module contains the UI packages.
3. Database
This module contains every script needed to setup the database.

### Packages explained
- backend-interface
The interface project containing all remote EJB interfaces as well as all related Domain Transfer Objects.
- backend-impl
The EJB module containing the implementation for all EJB interfaces defined in `backend-interface`.
- backend-assembly
The packaging module assembling the backend EAR artifact.

- frontend-impl
The module containing the JSF/Primefaces-UI. Uses the EJBs defined in `backend-interface` to communicate with the actual implementations.
- frontend-assembly
The packaging module assembling the frontend EAR artifact.

- database
Contains all SQL files needed to setup the database.

## Requirements
- [Wildfly](http://www.wildfly.org/) 
- [Maven](https://maven.apache.org/)
- Database (TBD)

## Installation/Setup
This guide is intended to manually deploy the artifacts. 
For integration in your IDE, please consult the IDE's guides or Dr. Google.

### Building the project
Run following command in the root directory of the project.
```
mvn clean install
```
This will build all module artifacts with Maven.

### Deploying the artifacts
Take the following two artifacts and deploy them to Wildfly by copying them to `<wildfly-directory>/standalone/deployments`.
- `backend/backend-assembly/target/backend-assembly-<version>.ear`
- `frontend/frontend-assembly/target/frontend-assembly-<version>.ear`
