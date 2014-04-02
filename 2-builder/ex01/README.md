Patterns: Builder
=================
Please read through this document completely before starting.

Homework Assignment Description
-------------------------------
This assignment gives you a chance to implement the Builder design pattern.

### Prerequisite
* Spring Framework
* Java enum

### Requirements
You are to implement a Calendar Event framework using the Builder pattern.

#### Existing Classes
1. `Event.java` is the object our builders need to construct.  It contains all the information needed for any type of events within our company.
1. The `com.liferay.smp.event.when.model` package contains models related to date/time of events.  Note that everything in here is either an interface or an enum.
1. The `com.liferay.smp.event.when.model.impl` package contains implementation classes related to date/time of events.  Depending on the type of events, you'll need to construct one of these for your `Event` object.
1. The `com.liferay.smp.event.where.model` package contains location information for events.  Again, everything directly in this package is an interface.  You will likely use a service to construct `Location` objects.
1. The `com.liferay.smp.event.who.model` package contains participant information.  Note that your framework will accept the `User` object as parameter, but will convert them to `Participant` objects to store within the `Event` object.
1. The `com.liferay.smp.service` package contains services that will reserve the corresponding location for the event and return you a `Location` object.  You'll need to use Spring Injection to use these services within your `Builder` classes.

#### Existing Configuration
1. `spring.xml` contains the current bean definition.  You will need to modify this configuration file to accomplish the assignment.
1. `log4j.properties` is available if you need to configure logging for your development.

#### Homework Description
The current calendar system needs a complex `Event` object to work.  Building these `Event` objects via the constructor is not a good option.  You'll need to use the Builder design pattern to help construct these `Event` objects.  Model after this diagram.
![Builder Design Diagram](http://farm4.staticflickr.com/3729/9580436176_06eece55f7_z.jpg)
**Note** that maven will not build without errors (even with the `@Ignore` annotation) until the first exercise is completed correctly.

###### Exercise 1 of 4
1. Create the `com.liferay.smp.event.scheduler` package.  Everything you need to implement will go inside here.
1. Implement the `Scheduler` class described in the diagram above.
1. Create the `EventBuilder` interface and define all the methods found in the diagram.  (Note that you also have the option of implementing the `EventBuilder` as an abstract class.)
1. Update the `spring.xml` and finish the definition for the `scheduler` bean.

###### Exercise 2 of 4
The business often needs to schedule one time webex meetings/events.  Create a builder that can facilitate the construction of this kind of `Event` objects.  Implement the `OnceWebExEventBuilder` found in the diagram.  Note that these events have the following requirements:
* Non-reoccurring.
* If `endDate` is `null`, then it is an all-day event.
* The event is **not** optional for the inviters, but it is for the invitees.
* Reminders should be sent to all participants.

###### Exercise 3 of 4
The IT department is big on their Agile methodologies.  Many teams use daily SCRUM meetings to sync up.  You'll need to implement a builder that helps in constructing this type of events.  Implement the `DailyScrumPhoneEventBuilder` found in the diagram.  Note that these events have the following requirements:
* Reoccurring on weekdays.
* It is never an all-day event.
* If an `endDate` is `null`, there is no end in sight.  If there is an `endDate`, then there is an end.
* All participants must attend, not optional.
* Reminders should be sent to all participants.

###### Exercise 4 of 4
Every quarter, the company schedules an All-Hands meeting to update everyone on the company's performance.  You'll need to implement a builder that helps in constructing this type of events.  Implement the `QuarterlyAllHandsEventBuilder` found in the diagram.  Note that these events have the following requirements:
* Reoccurring once every 3 months.
* It is never an all-day event.
* If an `endDate` is `null`, there is no end in sight.  If there is an `endDate`, then there is an end.
* It is not an optional meeting for the inviters, but optional for the invitees.
* Reminders should be sent to all participants.
* The number of participants must be supplied to the `MeetingRoomSchedulingService` so that it knows how big of a room is needed.

#### Logging
If you need a logging framework to help with development, you can add a `org.slf4j.Logger` to your class and update the `log4j.properties` file in order to use the logging framework.

### Unit Test
In order to complete the assignment, all the unit test methods in `ProxyTest.java` must run successfully.  You cannot modify the test file other than to remove the `@Ignore` annotation.
To run the test, run `mvn clean test` in the directory that contains the `pom.xml` file.

Turning in Homework
-------------------
When you've completed the homework assignment, you may turn in your homework by following the steps below.

1. run `mvn clean`.
1. zip up the *root* folder and rename it to `builder - your_names.zip`.  The *root* folder is the one the `pom.xml` file is directly in.
1. email it to <mailto:wilson.man@liferay.com>
1. CC your partner so I know who he or she is.

Further Reading
---------------
Here are some resources for additional reading:

1. <http://www.javacodegeeks.com/2012/07/builder-design-pattern-in-java.html>