Patterns: Chain of Responsibility
=================================
Please read through this document completely before starting.

Homework Assignment Description
-------------------------------
This assignment gives you a chance to implement the Chain of Responsibility design pattern.

### Prerequisite
* Spring Framework

### Requirements
You are to implement the email handling software that was described in the lesson using the Chain of Responsibility pattern.

#### Existing Classes
There are a number of classes and interface(s) that are there to get you started.

1. `EmailProcessor.java` interface defines the 2 basic methods to get you started on implementing your own.
1. `Email.java` is the model that contains the email data.
1. `EmailProperties.java` is a utility class that helps you retrieve properties from a properties file.  A list of spam keywords and a list of client domains can be gotten from this class.

#### Existing Configuration
1. `spring.xml` contains the current bean definition.  You may modify this configuration file to accomplish the tasks.
1. `log4j.properties` is available if you need to configure logging for your development.

#### Homework Description
Depending on the content of the email, you would create handlers to process the email according to the requirements.  The requirements for each exercise are below.

![Class Diagram](http://farm3.staticflickr.com/2867/9577572607_ba3c6e1571_z.jpg)

###### Exercise 1 of 5
In order for the first unit tests to pass, the following class and interface must be defined.  (Mind the **note** below regarding compilation errors.)

1. Create an `IncomingEmailProcessor` class that implements the EmailProcessor interface.
1. Create/Define the `EmailHandler` interface.  (*hint: reference the import statement inside `EmailProcessor` to know the package path*)

###### Exercise 2 of 5
Writing your first handler.

1. Create a handler that places an email into the SPAM folder if it detects a phrase "pennies a day" in any case (upper, lower, mixed, etc.)
1. **However**, if the email is marked with priority equal to LOW, HIGH, or URGENT, then do nothing.
1. *Wire* this handler to an email processor via `spring.xml`.

###### Exercise 3 of 5
Write your first handler to chain.

1. Create a handler that marks an email as URGENT if it comes from `liferay.com`.
1. Chain this new handler to the *chain* of handlers.

###### Exercise 4 of 5
1. Create a handler that places an email in the ARCHIVE folder if it detects a `[JIRA]` in the email subject.
1. Chain this new handler to the *chain* of handlers.

###### Exercise 5 of 5
The business realized that some emails from Liferay are going into the SPAM folder.  The reason is that the spam handler is placing them there.

1. Fix this problem without writing any code.  (*hint: only edit the `spring.xml`*)

### Logging
If you need a logging framework to help with development, you can follow the example found in `EmailProperties.java` and `log4j.properties`.

### Unit Test
In order to complete the assignment, all the unit test methods in `HandlerTest.java` must run successfully.  You cannot modify the test file other than to remove the `@Ignore` annotation.
To run the test, run `mvn clean test` in the directory that contains the `pom.xml` file.  (Mind the note below regarding the unit test compilation.)


Turning in Homework
-------------------
When you've completed the homework assignment, you may turn in your homework by following the steps below.

1. run `mvn clean`.
1. zip up the *root* folder and rename it to `cor - your_names.zip`.  The *root* folder is the one the `pom.xml` file is directly in.
1. email it to <mailto:wilson.man@liferay.com>
1. CC your partner so I know who he or she is.

Notes
-----
Note that even with the `@Ignore` annotation, the `mvn clean test` command will show compilation errors until you've successfully implemented the first exercise.

Further Reading
---------------
Here are some resources for additional reading:

1. <http://java.dzone.com/articles/design-patterns-uncovered-chain-of-responsibility>