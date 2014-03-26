Patterns: Proxy
===============
Please read through this document completely before starting.

Homework Assignment Description
-------------------------------
This assignment gives you a chance to implement the Proxy design pattern.

### Prerequisite
* Spring Framework

### Requirements
You are to implement two proxies to resolve 2 separate problems.  The first is a performance issue that draws on your understanding of a virtual proxy.  The second is a permission issue that highlights the use case of a security proxy.

#### Existing Classes
1. `WarehouseLegacySystem.java` interface defines an entry to a legacy warehouse system that is external to our system.
1. `ProductImpl.java` class is an implementation of the `Product.java` interface.  This is an existing implementation that you should not touch nor modify.  However, you are free to implement your own class to complete this assignment.
1. `ProductPersistenceImpl.java` class is the existing persistence class for retrieving `Product` information.
1. `SecretProductPersistenceImpl.java` is an existing persistence class that retrieves all the secret products that are in development in the company.
1. `PermissionUtil.java` is like a permission checker that you can call to check whether a user is an administrator.
1. `UserThreadLocal.java` is used to simulate a user making a call for the unit test.  You do not need to do anything to this class.
1. `ProductServiceImpl.java` is a class in the service layer to interact with the persistence layer.  It implements the `ProductService.java` interface.
1. `ProductServiceUtil.java` is a helper class that allow static invocation of the service methods.  You may or may not need to use this class.

#### Existing Configuration
1. `spring.xml` contains the current bean definition.  You may modify this configuration file to accomplish the tasks.
1. `log4j.properties` is available if you need to configure logging for your development.


#### Homework Description
The current system is a product inventory system where users within the company can view the company's products.  This system needs two quick fixes to resolve two issues.

##### Performance Issue
After some digging, your team realized that there's a performance issue coming from the external legacy system tied to the warehouse.  Your team also found out that only a small percentage of users would *click* into a product detail view to check inventory.

###### Exercise 1 of 4
Implement a `ProductProxy` class to have all the information that comes from the internal persistence system but not the warehouse inventory information (i.e. *quantityLeft*).  Note that the corresponding unit test has a timeout of 2 seconds.

###### Exercise 2 of 4
Make sure that the `ProductProxy` can still retrieve the inventory information (*quantityLeft*) if necessary.

##### Security Issue
Currently, anyone within the system can view all the secret products.  The business wants to limit this to just admins for now so they can continue to update the secret products.

###### Exercise 3 of 4
Implement a security proxy that checks whether a user is an admin.  If so, let them access the *secret* products.  If not, return **null** when the method is called.

###### Exercise 4 of 4
Make sure an admin user can access the *secret* products.

#### Logging
If you need a logging framework to help with development, you can add a `org.slf4j.Logger` to your class and update the `log4j.properties` file in order to use the logging framework.

### Unit Test
In order to complete the assignment, all the unit test methods in `ProxyTest.java` must run successfully.  You cannot modify the test file other than to remove the `@Ignore` annotation.
To run the test, run `mvn clean test` in the directory that contains the `pom.xml` file.

Turning in Homework
-------------------
When you've completed the homework assignment, you may turn in your homework by following the steps below.

1. run `mvn clean`.
1. zip up the *root* folder and rename it to `proxy - your_names.zip`.  The *root* folder is the one the `pom.xml` file is directly in.
1. email it to <mailto:wilson.man@liferay.com>
1. CC your partner so I know who he or she is.

Further Reading
---------------
Here are some resources for additional reading:

1. <http://sourcemaking.com/design_patterns/proxy>