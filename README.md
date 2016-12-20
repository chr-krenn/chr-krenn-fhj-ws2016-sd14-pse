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
- [Wildfly 10.1!](http://www.wildfly.org/) 
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

Creation / manipulation scripts are available at /database/src/main/resources/sql:
- create-db.sql
- create-ddl.sql
- drop-ddl.sql
- seeddata-dml.sql
- testdata-dml.sql
```
$ root@localhost # mysql -u root -p < create-db.sql
```
Or manually:

```
$ root@localhost # mysql -u root -p 
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
                <login-module code="at.fhj.swd14.pse.security.SecureDatabaseServerLoginModule" flag="required">
                    <module-option name="dsJndiName" value="java:jboss/datasources/SEP"/>
                    <module-option name="principalsQuery" value="SELECT id, password, salt FROM user WHERE username=?"/>
                    <module-option name="rolesQuery" value="select role, rolegroup from user_roles where username=?"/>
                    <module-option name="principalClass" value="at.fhj.swd14.pse.security.DatabasePrincipal"/>
                    <module-option name="unauthenticatedIdentity" value="guest"/>
                </login-module>
        </authentication>
    </security-domain>  
```

Also execute create.sql to setup database (testdata).

### Credentials

| Username        | Password   | Role  |
| ------------- |:-------------:| -----:|
| student1      | x    | user |
| student2      | x    | user |
| student3      | x    | user |
| student4      | x    | user |
| student5      | x    | user |
| admin1        | x    | admin,user |

In order to use the provided authentication config, mysql/mariadb has to be configured to use case-insensitve queries, for this purpose added 
```
lower_case_table_names=1
```
to the client-server section of your my.cnf.
### Deploying the artifacts
Take the following two artifacts and deploy them to Wildfly by copying them to `<wildfly-directory>/standalone/deployments`.
- `backend/backend-assembly/target/backend-assembly-<version>.ear`
- `frontend/frontend-assembly/target/frontend-assembly-<version>.ear`

### Get logged in user

((at.fhj.swd14.pse.security.DatabasePrincipal)FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId()


### URL's

- localhost:8080/swd14-fe/ --> Welcome page (/index.xhtml)
- localhost:8080/swd14-fe/ --> User protected area (automatically forwarded to login if not logged in. after successfull login system redirects you to requested ressource)

## Integration tests

The projects maven file includes integration tests inside the profile integration-tests, which is activated by default. Deactivate it via -P-integration-tests, if you do not want to run integration tests.

###Database tests

The database tests are integration tests and can be based on the class AbstractRepositoryIntegrationTest in backend-impl/src/test/java/repository/. If the entity includes a numeric primary key, use AbstractRepositoryIDIntegrationTest instead. You will have to implement the abstract methods as well as additional tests required to test any repository functionality not included in AbstractRepository. For an example please see the class DepartmentRepositoryIntegrationTest.

The following methods will have to be implemented:
- getRepository: Returns the repository to be used for the test (only instantiate the repository once per test)
- createDummyEntity: Creates a new object of the entity managed by the repository to be tested, the primary key may not be set, if it is automatically created, otherwise a new value will have to be generated for the test (the table is cleared during test setup, however if the cascading between entitys is not setup correctly, using an existing key may cause inconstancies). 
- assertEquals: This will check all fields of both entities for equality
- fetchDummy: refetches the passed dummy from the database (implemented in AbstractRepositoryIDIntegrationTest)
- getDummyId: IDIntegrationTest only, return the primary key from the passed object
- modifyDummy: Modify the object passed, so that an update in the database can be verified
- copyDummyPK: Copy the primary key from the passed source object to the passed destination object

Please note that during test setup a transaction is started, and during teardown it is rolled back. Please do not use any commands that affect the transaction handling or change the database permanently.

## UI tests with Selenium
The UI tests can be found in the **frontend-impl/src/test** package. We are using version 2.53.1 at the moment.
### How to
There's an BaseUITest from which you can derive from. You just need to call the login in your sub testclass in @BeforeClass with a default student or your own user login.
To keep our tests clean we use the PageObjectPattern. You just need to derive from the AbstractPage class. 
* There should be **no asserts** in the PageObject.
* Prefered selector order: by id > name > css > xpath
* Avoid Thread.sleep and implicit waits
* Prefer ExplicitWait or FluentWait
* Use relative URLs

