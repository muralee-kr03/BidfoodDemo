# Selenium-Java-Cucumber Framework
Framework is developed Java(version 13), WebDriver and Cucumber. 
Maven is used as build tool.
# Getting Started
1. Clone repo into your local machine.
2. Import as Exiting Maven Project (File --> Import --> Maven --> Existing Maven Project).
3. Select the foler "bdd-web-automation" and finish the import.

# Run tests locally
1. Go to package sparksport.co.nz.runner located under src/test/java.
2. Select the RunnerClass.java file, right click and select the option Run as Junit Test.

# Run tests through the commandline
As this project uses Maven, we can invoke the tests using Maven goals which is defined in pom.xml file.
To run the test, use your commandline and issue below command
1. mvn clean install
2. mvn -Dtest=RunnerClass test
