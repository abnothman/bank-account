# bank-account

# Description
This project is a bank account solution. It allows to perform :

	Make a deposit to my account.
	Make a withdrawal from my account.
	Print the account statement (date, amount and balance).


# Requirements
	Deposit and Withdrawal from my account.
	Account statement transaction printing.

# User Stories

# US 1:
make a deposit in my account

# US 2:
make a withdrawal from my account

# Used technologies:
Spring Boot : 3.0.8

Java : 17

Maven : 3.10

Lombok : 1.18.30

# Instructions
1. Checkout project

https://github.com/abnothman/bank-account.git

2. Compilate project
Go to project folder and run the following cmd:
cd bank-account
mvn clean install


3. Unit Tests execution
mvn test

3.2 Unit tests for a class
mvn test -DTest=Test classname

4. Run Application
cd infrastructure
mvn spring-boot:run






