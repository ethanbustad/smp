Patterns: Bridge
================
Please read through this document completely before starting.

Homework Assignment Description
-------------------------------
This assignment gives you a chance to implement the Bridge design pattern.

### Prerequisite
* Spring Framework
* ThreadLocal
* Java enum

### Requirements
You are to implement an auditing framework using the Bridge pattern.

#### Existing Classes
1. `com.liferay.smp.audit` package contains interfaces, models, and enums for auditing purposes.
	1. `AuditContextFactory.java` will return a new `AuditContext.java` object for use.  Inject the *impl* into the classes that need them.
	1. `AuditContext.java` is the value object needed for auditors to audit with.
	1. `Level.java` is an enum to set the priority of an audit.
	1. `Type.java` is an enum to set the type of audit.
1. `com.liferay.smp.bigdata` package contains NoSQL related interfaces and classes.  These are modeled after classes found in the Java MongoDB drivers.
	1. `Mongo.java` is the entry to the MongoDB data store.  Use this via Spring injection to interface with the *mock* MongoDB.  It can return a database for you to store data within.
	1. `MongoClient.java` is the *mock* implementation of Mongo.
	1. `DB.java` and `DBImpl.java` represent the MongoDB database.  Within a database are one or more collections that can store data.
	1. `DBCollection.java` represents a MongoDB collection.  It is analogous to a table in a relational database.
	1. `DBObject.java` represents data units within the MongoDB data store.  Use this to insert or retrieve data.
	1. `DBObjectFactory.java` is the factory that returns you the `DBObject.java`.
1. `com.liferay.smp.io` package contains IO related *mock* interfaces/classes.
	1. `PrintWriter.java` and `PrintWriterImpl.java` are used to write to a file.
1. `com.liferay.smp.model` package contains models needed for the auditing feature.
	1. `UserAction.java` is a model that encapsulates all the needed info related to actions that a user may perform.
	1. `Action.java` is an enum that represents all the actions that users can perform.
	1. `UserActionFactory.java` is a factory that can create a new `UserAction.java` object.
1. `com.liferay.smp.security.auth` package contains security/permission related classes.
	1. `UserThreadLocal.java` is used to pass user credentials.  Use it to know which user is making the request.
1. `com.liferay.smp.service` package contains services that need to use the auditing feature.
1. `com.liferay.smp.service.impl` package is the implementation of the services.
	1. `MessageBoardServiceImpl.java` will need to report user actions.
	1. `RemoteSystemServiceImpl.java` will need to report remote system health into a log file.
1. `com.liferay.smp.util` package contains helper classes.
	1. `MessageConstants.java` has a String constant `REMOTE_SYSTEM_OK` that you can use for your assignment.

#### Existing Configuration
1. `spring.xml` contains the current bean definition.  You may modify this configuration file to accomplish the tasks.
1. `log4j.properties` is available if you need to configure logging for your development.


#### Homework Description
The current system is in need of an auditing feature.  The audit messages from different services have different storage needs.  You'll need to implement the auditing system using the Bridge pattern so that these separate auditors can use different underlying data stores to fit the requirement.  Model after this diagram.
![Bridge Design Diagram](http://farm3.staticflickr.com/2865/9580453104_c74375c6e2_z.jpg)

##### Remote System Service
There is an existing service to check on remote systems to report on their health.  The health checking logic is done inside the `checkSystemHealth()` method (which you do not need to implement).  It calls another method `sendAudit(String message)` to report health status.  The `sendAudit(String message)` is the caller to your auditing system, which you'll need to implement.
**You may modify the `RemoteSystemServiceImpl` to inject the `GeneralAuditor`.**

###### Exercise 1 of 2
1. Implement the `GeneralAuditor` class to handle simple message auditing.
	1. The `GeneralAuditor` should always log messages with `Level.NORMAL`.
	1. The `GeneralAuditor` should use `Type.GENERAL`.
1. You'll also need to implement the `LogFileStore` class to store the audited data to a file called `audit.log`.  The message output should be formatted to the following: `Tue May 21 15:13:25 PDT 2013 - (LOW) GENERAL: Solr System is in good health.`
1. The `LogFileStore` should use the `PrintWriter` internally to write to a file.  Be sure to close the `PrintWriter` after each use.

##### Message Board Service
The `MessageBoardService` is used by site users to interface with the message board.  You'll need to add an auditing feature to this service.  **You may modify the `MessageBoardServiceImpl` to inject the `UserActionAuditor`.**

###### Exercise 2 of 2
1. Implement the `UserActionAuditor` class to handle `UserAction` auditing.
	1. Critical actions should use `Level.HIGH`. Otherwise, use `Level.LOW`.
	1. Use `Type.USER_ACTION`.
	1. The message should have the following format: `37542 DELETE "How to adjust the Aperture?" on Tue May 21 15:13:25 PDT 2013`
	2. The rest of the settings should be obvious.
1. Implement the `NoSQLStore` class to store audit data within MongoDB.  Your `NoSQLStore` class will need to interact with the `MongoClient` class, which is already defined for you in `spring.xml` as a Spring bean.

#### Logging
If you need a logging framework to help with development, you can add a `org.slf4j.Logger` to your class and update the `log4j.properties` file in order to use the logging framework.

### Unit Test
In order to complete the assignment, all the unit test methods in `ProxyTest.java` must run successfully.  You cannot modify the test file other than to remove the `@Ignore` annotation.
To run the test, run `mvn clean test` in the directory that contains the `pom.xml` file.

Turning in Homework
-------------------
When you've completed the homework assignment, you may turn in your homework by following the steps below.

1. run `mvn clean`.
1. zip up the *root* folder and rename it to `bridge - your_names.zip`.  The *root* folder is the one the `pom.xml` file is directly in.
1. email it to <mailto:wilson.man@liferay.com>
1. CC your partner so I know who he or she is.

Further Reading
---------------
Here are some resources for additional reading:

1. <http://www.cs.sjsu.edu/~pearce/modules/patterns/platform/bridge/index.htm>