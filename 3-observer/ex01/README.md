Patterns: Observer
==================
Please read through this document completely before starting.

Homework Assignment Description
-------------------------------
This assignment gives you a chance to implement the Observer design pattern.

### Prerequisite
* Java enum
* Spring Framework

### Requirements
You are to implement the order post processing feature that was described in the lesson using the Observer pattern.  The following classes are already in place:

#### Existing Classes
1. `Order.java` interface defines the methods that an `Order` object should have.  It also defines the `Status` Java enum that can be used to set the order status.
1. `OrderImpl.java` class is an implementation of the `Order` interface.  This has already been defined for you in the `spring.xml` file.
1. The other model interfaces and classes are there for use in the future.  You do not need to use them for this homework.
1. The `com.liferay.smp.service` package contains 3 service interfaces.  **Do not modify.**
1. The `com.liferay.smp.service.impl` package contains 3 service implementation classes that implement the interfaces in the parent package.  These classes have no real implementation and you also **do not need to modify them**.  They're defined for you in `spring.xml` as well.  Your code simply need to obtain (via spring) these service beans and call the appropriate methods described in the exercises.

#### Homework Description
When an order is confirmed, you need to implement code (again using Observer pattern) to react to the confirmed order.  ***Please refer to the lesson slides or your gang of four book to design the interfaces and classes that are needed to implement the Observer pattern.***

![Class Diagram](http://farm6.staticflickr.com/5537/9578156217_80fa412e2c_z.jpg)

##### Exercise 1 of 4
When an order is confirmed, the `warehouseService` should be notified to fulfill the order.

##### Exercise 2 of 4
When an order is confirmed, the `customerNotificationService` should **also** be notified to send notification to customer.

##### Exercise 3 of 4
Now, make sure the `accountingService` is also notified to process payment for this order.

##### Exercise 4 of 4
The business has discovered that the company can run more smoothly if the order processing is done in the following order: First, charge the customer.  Second, send them the order confirmation email.  Last, send them the order.  You need to rearrange the processing order of your code to fulfill the business requirement.  (*hint: you should not need to modify code, but only configuration*)

#### Logging
If you need a logging framework to help with development, you can add a `org.slf4j.Logger` to your class and update the `log4j.properties` file in order to use the logging framework.

### Unit Test
In order to complete the assignment, all the unit test methods in `ObserverTest.java` must run successfully.  You cannot modify the test file other than to remove the `@Ignore` annotation.
To run the test, run `mvn clean test` in the directory that contains the `pom.xml` file.

Turning in Homework
-------------------
When you've completed the homework assignment, you may turn in your homework by following the steps below.

1. run `mvn clean`.
1. zip up the *root* folder and rename it to `observer - your_names.zip`.  The *root* folder is the one the `pom.xml` file is directly in.
1. email it to <mailto:wilson.man@liferay.com>
1. CC your partner so I know who he or she is.

Further Reading
---------------
Here are some resources for additional reading:

1. <http://java.dzone.com/articles/design-patterns-uncovered>