#Sample Feature Definition Template
@Home
Feature: Verify the search functionality in home page
	I want to use this template for the functionalities in the home page

@Regression
Scenario: [HOM-001] - Verify login functionality searching product with product name
Given user is at login page
When login as "STANDARD" user
And search with keyword "cheese"
Then verify "CHEESE BLUE DANISH (APP 3KG)" is displayed in the search result

@Regression
Scenario: [HOM-002] - Verify login functionality searching product with product code
Given user is at login page
When login as "STANDARD" user
And search with keyword "160640"
Then verify "CHEESE BLUE DANISH (APP 3KG)" is displayed in the search result

@Regression
Scenario: [HOM-003] - Verify login functionality adding product to the cart
Given user is at login page
When login as "STANDARD" user
And search with keyword "160640"
And click on the add to card button
And navigate checkout page
Then verify number items in basket is displayed as "1"
Then verify below information is displayed on checkout page
|ProductName                     |Code               |Quantity            |price        |
|CHEESE BLUE DANISH (APP 3KG)    |160640             |1                   |$27.08       |