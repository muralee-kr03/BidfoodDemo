#Sample Feature Definition Template
@Login
Feature: Verify the login functionality
	I want to use this template for the functionalities in the login page

@Regression
Scenario: [LOG-001] - Verify login functionality with valid username and password
Given user is at login page
When login as "STANDARD" user
Then verify user is navigated to home page and user name "MURALEE KRISHNAN" is displayed on the home page
Then verify the components on home page is displayed
