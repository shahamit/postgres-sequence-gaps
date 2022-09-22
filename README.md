# Postgres Lost Sequence Tracker

This spring boot app demonstrates the concept of spring's transaction event listener to log gaps in postgres database sequences 
when any transactions rollback

The app contains java classes for fetching and persisting Employees objects

## Transaction Listener

To generalize the problem statement for any model entity we have created an Identifiable interface that every model class
should implement. 

On persisting any model entity its service class should publish an IdentifiableCreatedEvent that helps the TransactionListener
log the model entity's id whenever a transaction rolls back.

## Execution

To run the app start the postgres database container using the start.sh script in the deployment directory before starting
the MainApp

There are a couple of apis to create an employee object, one of them being error-prone (POST /employees/create-employee) 
which simulates runtime failures.
There are executable curl requests scripts in the deployment directory to demonstrate the use-case

## Test Execution

The behavior could also be simulated by executing EmployeeServiceTest that starts a postgres test container before running
the test case. 

The test case confirms that database sequence gaps are indeed created whenever a transaction rolls back

Transaction event listener logs a statement with the jpa entity id whenever a transaction is rolled back