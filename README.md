# bank-account

# Description
This project is a solution to the Bank Account Kata. It allows a bank client to perform :
	Make a deposit to an account.
	Make a withdrawal from an account.
	Print account transaction statement.
	View the account statement (showing date, amount and balance).


# Requirements
	Deposit and Withdrawal functionalities.
	Statement transaction printing.

# User Stories

# US 1:
	In order to save money
	As a bank client
	I want to make a deposit in my account

# US 2:
	In order to retrieve some or all of my savings 
	As a bank client 
	I want to make a withdrawal from my account

# Used technologies:
Spring Boot : 3.0.8

Java : 17

Maven : 3.10

Lombok : 1.18.30

# Instructions
1. Clonage du projet
Checkout project

https://github.com/abnothman/bank-account.git

2. Compilate project
Go to project folder and run the following cmd:
cd bank-account
mvn clean install


3. Unit Tests execution
3.1 Global execution for unit tests
mvn test

3.2 Unit tests for a class
mvn test -DTest=Test classname

4. Run Application
mvn spring-boot:run






