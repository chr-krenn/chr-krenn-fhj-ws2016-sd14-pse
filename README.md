# SWD14 - PSE Project

[![Build Status](https://jenkins.almost-a-blog.net/buildStatus/icon?job=PSE-build)](https://jenkins.almost-a-blog.net/job/PSE-build) - Build

[![Build Status](https://jenkins.almost-a-blog.net/buildStatus/icon?job=PSE-deploy)](https://jenkins.almost-a-blog.net/job/PSE-deploy) - Deployment

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

### Setting up the database

This is directed at people using the "TeiniVM". First we must create a new database:
```
root@localhost # mysql -u root -p 
Enter password: root66
MariaDB [(none)]> CREATE DATABASE pse;
MariaDB [(none)]> GRANT ALL PRIVILEGES ON pse.* TO student@localhost WITH GRANT OPTION;
```

Then we must perform the configuration that can be found in the project "Wildfly-Configurations" provided by Prof. Teiniker.

And finally we must add the datasource for this project to the standalone.xml of the VMs Wildfly installation:

```
    <datasource jndi-name="java:jboss/datasources/SEP" pool-name="SEP" enabled="true" use-java-context="true" use-ccm="true">
        <connection-url>jdbc:mysql://localhost:3306/pse</connection-url>
        <driver>mysql</driver>
        <security>
            <user-name>student</user-name>
            <password>student</password>
        </security>
    </datasource>
```
```
    <security-domain name="sep-policy" cache-type="default">
        <authentication>
            <login-module code="Database" flag="required">
                <module-option name="dsJndiName" value="java:jboss/datasources/SEP"/>
                <module-option name="principalsQuery" value="SELECT password FROM user WHERE username=?"/>
                <module-option name="rolesQuery" value="SELECT role, 'Roles' FROM user_roles WHERE username=?"/>
                <module-option name="hashAlgorithm" value="SHA-256"/>
                <module-option name="hashEncoding" value="base64"/>
                <module-option name="unauthenticatedIdentity" value="guest"/>
            </login-module>
        </authentication>
    </security-domain>
```

Also execute create.sql to setup database (testdata).
User credentials: student/admin

### Deploying the artifacts
Take the following two artifacts and deploy them to Wildfly by copying them to `<wildfly-directory>/standalone/deployments`.
- `backend/backend-assembly/target/backend-assembly-<version>.ear`
- `frontend/frontend-assembly/target/frontend-assembly-<version>.ear`

### URL's

- localhost:8080/swd14-fe/ --> Welcome page (/index.xhtml)
- localhost:8080/swd14-fe/protected/xxx --> User protected area (automatically forwarded to login if not logged in. after successfull login system redirects you to requested ressource)
- User credentials: student/admin
