package bidfood.co.nz.runner;



import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import bidfood.co.nz.helper.Helper;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)

@CucumberOptions(
		features = {"src/test/resources/FeatureFile/"},
		glue={"bidfood.co.nz.runner"},
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		tags = {"@Regression"}
		)

public class RunnerClass {
	
	 public static int noOfTotalTest, noOfFailedTest, noOfPassedTest;
	 
	 public RunnerClass() throws Exception{
		 	noOfTotalTest = 0;
			noOfFailedTest = 0;
			noOfPassedTest = 0;
	 }
	 
@BeforeClass
public static void setup() throws Throwable{
 
	 if( Helper.GetConfigValue("ClearHistory").toUpperCase().equals("TRUE"))
	 {
		 Helper.clearScreenshotFolder();			// function to delete exiting screenshot folder to clear screenshots files.
	 }	 
	
}

@AfterClass
public static void teardown() throws Throwable {
  
    if( Helper.GetConfigValue("RenameReport").toUpperCase().equals("TRUE"))
    {
    	
    	Helper.renameReport();       // Report file path is stored to variable RunSetup.reportFileName inside the function after renaming the report.
    }
    else              
    {
    	RunSetup.reportFileName = System.getProperty("user.dir")+ "//test-output//HTMLReport//Index.html";
    }
    
   
    if( Helper.GetConfigValue("OnSlack").toUpperCase().equals("TRUE"))
    {
    	Helper.sendTestResultOnSlack();
    }
    
    
}

}
