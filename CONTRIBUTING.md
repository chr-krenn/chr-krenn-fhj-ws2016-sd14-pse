# Coding Guidelines
This section defines project-wide coding guidelines.

## §1 Global
- §1.1 The package-prefix for the project is `at.fhj.swd14.pse`
    - Additionally, don't nest classes too deep; as a rule of thumb, don't add more than 2 package-levels
- §1.2 "Never go home on a broken build"
    - Quite a lot of people are sharing the same codebase, at least check your code locally, before pushing it to the repository
    - Mistakes can be made. If someone encounters a failing build or deployment, revert the introducing commit, and inform the committer about it
    - Verify your build (and deployment) on the project's [CI](https://jenkins.almost-a-blog.net)
- §1.3 This project's language is English and English only!
    - Code in other languages will be punished... or at least it should be
- §1.4 Use log4j for logging, instead of logging via System.out.x
    - Add `private static final Logger LOGGER = LogManager.getLogger(YourClass.class);` to your class
    - Log with `LOGGER.warn(...);`, `LOGGER.info(...)`, etc.
        - When logging exceptions, make sure you don't lose the stacktrace!
        - i.e. `LOGGER.warn("Unexpected exception occurred while doing X!", exception)` is better than `LOGGER.warn("Unexpected exception")`
    - Log-Levels rule of thumb... (shamelessly ripped of [this SO question](http://stackoverflow.com/questions/7839565/logging-levels-logback-rule-of-thumb-to-assign-log-levels))
        - **ERROR**: the system is in distress, malfunctioning of huge parts of the system, end-users are directly affected (or will soon be)
            - Apply the "2am rule" - If you're on call, do you want to be woken up at 2AM if this condition happens? If yes, then log it as "ERROR"
        - **WARN**: an unexpected technical or business event happened, customers may be affected, but probably no immediate human intervention is required
            - On call people won't be called immediately, but support personnel will want to review these issues asap to understand what the impact is
            - Basically any issue that needs to be tracked but may not require immediate intervention
        - **INFO**: things we want to see at high volume in case we need to forensically analyze an issue 
            - System lifecycle events (system start, stop) go here
            - "Session" lifecycle events (login, logout, etc.) go here 
            - Significant boundary events should be considered as well (e.g. database calls, remote API calls)
            - Typical business exceptions can go here (e.g. login failed due to bad credentials)
            - Any other event you think you'll need to see in production at high volume goes here
        - **DEBUG**: just about everything that doesn't make the "info" cut... 
            - any message that is helpful in tracking the flow through the system and isolating issues, especially during the development and QA phases
            - use "debug" level logs for entry/exit of most non-trivial methods and marking interesting events and decision points inside methods
        - **TRACE**: don't use this often, this would be for extremely detailed and potentially high volume logs that you don't typically want enabled even during normal development 
            - Examples include dumping a full object hierarchy, logging some state during every iteration of a large loop, etc
    - You can change the log-level in the component's `log4j2.xml`
        - Locate the logger with name `at.fhj` and change it's level to the desired one
- §1.5 Write JUnit Tests for your code!
    - First of all, you get practice writing tests
    - Also tests increase confidence for changes and take away fear of breaking existing stuff
    - Further, other's may see the intention behind someone else's code when looking at their tests
- §1.6 Use [Mockito](http://site.mockito.org/) for mocking/stubbing stuff in your tests
    - Feel free to write mocks yourself, but it's definitely not recommended
    - ... and NO, we will not add another mocking framework!
- §1.7 Use meaning full commit messages
    - If we see one of [these](http://whatthecommit.com/) commit messages, you shall be hunted to depths of hell
    - No, seriously... do yourself and others a favor and write meaningful commit messages to give other's (and your future self) a glimpse of the commit's changes
    - If you are really into it, you might want to read [this](http://chris.beams.io/posts/git-commit/)
- §1.337 The architects are always right!
- §1.338 If the architects are wrong, refer to §1.337!

## §2 Views
- §2.1 Use [Primefaces' components](http://www.primefaces.org/showcase/) where possible
    - Custom styling will only be applied to Primefaces' components

## §3 Frontend-Beans
- §3.1 Use `@Named` instead of `@ManagedBean`
    - See [this SO question](http://stackoverflow.com/questions/4347374/backing-beans-managedbean-or-cdi-beans-named) for explanation
- §3.2 Frontend-Beans must be serializable
    - Add `implements Serializable`
    - Add `private static final long serialVersionUID = 1L;`
        - It does not directly affect the code, but it's good practice to manually add it to serializable classes
        - In theory any long will do, but starting with "1" is a good practice when it comes to versioning

## §4 Backend-Interfaces
- §4.1 POJOs
    - Names should be suffixed with "Dto" as they are Transfer Objects between the backend and the frontend
    - Add `implements Serializable`
      - They must be serializable due to potential binary tranfer via CORBA, RMI, etc.
    - Add `private static final long serialVersionUID = 1L;`
      - It does not directly affect the code, but it's good practice to manually add it
      - In theory any long will do, but starting with "1" is also good practice when it comes to versioning
- §4.2 Services 
    - Packages are named after the corresponding functionality
        - e.g. `UserDto` resides in package `user`
    - Services must only be interfaces
    - Services must be annotated with `@Remote`
    - Services must NOT be serializable
    - Methods (and optionally the interface itself) should have a JavaDoc describing their functionality
        - Refer to `at.fhj.swd14.pse.news.NewsService` for an example
    - Think twice before adding a checked exception to the throws list!
      - Never throw `java.lang.Exception` itself, except you have a very(!) good reason
- §4.3 No other objects must be within this component

## §5 Backend-Implementations
- §5.1 Use JPA for database entities
    - JPA entities must be `Serializable` (§3.2 applies here)
- §5.2 Backend services must be annotated with `@Stateless`
    - Stateful backend services should be reviewed with the architects, as these should usually not be required
- §5.3 Extend `AbstractRepository` for entity specific repositories
- §5.4 Use `@EJB` to inject beans
- §5.5 Create dedicated converter classes in `at.fhj.swd14.pse.converter` for converting between database entities and DTOs
- §5.6 Use `Long` for numeric primary keys

## §6 Database (and SQL)
- §6.1 Use `INNODB` engine for all database entities
- §6.2 Apply `NOT NULL` if possible
- §6.3 Use the `AUTO_INCREMENT` feature for primary keys 
- §6.4 Add `created` and `modified` to all tables
    - It is up to you to expose these columns in the entities or just leave it in the database
    - Use the below syntax to avoid introducing triggers
```
`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
```
- §6.5 Make scripts reexecutable
    - e.g. by adding `IF NOT EXISTS` to DDL instructions
- §6.6 Make sure the database type matches the one used in the entities
    - if the entity defines a `Long` the database type must be `BIGINT`, for `Integer` it is `INT`, and so on

# FAQ
This section contains common problems and their solution.
Feel free to update this section.

- TBD