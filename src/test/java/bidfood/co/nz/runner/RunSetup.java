package bidfood.co.nz.runner;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import bidfood.co.nz.helper.Helper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
//import atu.testrecorder.ATUTestRecorder;
//import cucumber.api.Scenario;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class RunSetup {
	
	
	public static RemoteWebDriver driver;  
    public static java.net.URL SauceLabURL;

	
	String strConfigRecording, strConfigBrowser, screenShotPath;
	public static String reportFileName, strTakeScreenshot, strExecutionEnvironment, strTakeScreenshotOnFail, URL;

	public RunSetup() throws Exception{
		
		
	
	}
	
	@Before
	public void openBrowser(Scenario scenario) throws Exception {
		
		
		
	//--------------------------------------------Get path from config.properties file------------------------------
		
		strTakeScreenshot = Helper.GetConfigValue("EnableScreenshot").trim();
		strTakeScreenshotOnFail = Helper.GetConfigValue("ScreenshotOnFailed").trim();
		strConfigBrowser = Helper.GetConfigValue("Browser").toUpperCase().trim();
		strExecutionEnvironment = Helper.GetConfigValue("Execution").trim();
		
		switch (Helper.GetConfigValue("Environment").toUpperCase()) 
		{
			case "UAT":
				URL = Helper.GetConfigValue("URL").trim();
			case "STAGING":
				URL = Helper.GetConfigValue("URL").trim();
			default:
				URL = Helper.GetConfigValue("URL").trim();
		
		}
		
		switch (strConfigBrowser.toUpperCase().trim()) 
		{
			case "CHROME":
				if(strExecutionEnvironment.toUpperCase().equals("SAUCELAB"))
				{
					ChromeOptions browserOptions = new ChromeOptions();
					browserOptions.setPlatformName(Helper.GetConfigValue("Platform").trim());
					browserOptions.setBrowserVersion(Helper.GetConfigValue("BrowserVersion").trim());
					Map<String, Object> sauceOptions = new HashMap<>();
					browserOptions.setCapability("sauce:options", sauceOptions);

					SauceLabURL = new java.net.URL("https://" + System.getenv("SaucelabUserName") + ":" + System.getenv("SaucelabPassword") + "@ondemand.us-west-1.saucelabs.com:443/wd/hub");
				    driver = new RemoteWebDriver(SauceLabURL, browserOptions);
				}
				else
				{
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--remote-allow-origins=*");
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(options);
					
				}
				break;
				
			case "EDGE":
				if(strExecutionEnvironment.toUpperCase().equals("SAUCELAB"))
				{
					EdgeOptions browserOptions = new EdgeOptions();
					browserOptions.setPlatformName(Helper.GetConfigValue("Platform").trim());
					browserOptions.setBrowserVersion(Helper.GetConfigValue("BrowserVersion").trim());
					Map<String, Object> sauceOptions = new HashMap<>();
					browserOptions.setCapability("sauce:options", sauceOptions);
		
					SauceLabURL = new java.net.URL("https://" + System.getenv("SaucelabUserName") + ":" + System.getenv("SaucelabPassword") + "@ondemand.us-west-1.saucelabs.com:443/wd/hub");
					driver = new RemoteWebDriver(SauceLabURL, browserOptions);
				}
				else
				{
					EdgeOptions options = new EdgeOptions();
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver(options);
				}
				break;
				
			default:
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
		
		}

		
		if(strExecutionEnvironment.toUpperCase().equals("SAUCELAB"))
		{
			JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
			javascriptexecutor.executeScript("sauce:job-name=Web APP-" + scenario.getName());
		}
		
		
		driver.manage().window().maximize();	
//		Thread. sleep(1000);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		
	
	}
	
	@After
	public void embedScreenshot(Scenario scenario)throws Exception {
		
		Helper.strGlobalTCID = Helper.getTCIDFromScenario(scenario);  // -----------GET THE TC ID FROM THE SCENARIO NAME-----------------

		// -------------UPDATE THE TEST CASE STATUS BASED ON THE SCENARIO STATUS--------
		
		
		//------------------------------------------------------------------------------
		
			 if(strTakeScreenshot.toUpperCase().equals("TRUE"))
			 {
				
				 if(strTakeScreenshotOnFail.toUpperCase().equals("TRUE"))
				 {			
					 if (scenario.isFailed())
					 {	
						 screenShotPath = Helper.getscreenshotUpdate(driver);

					 }
				 }
				 else
				 {
					 screenShotPath = Helper.getscreenshotUpdate(driver);

				 }
				 
				
			 }
	

	
    
		JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
		 
		if (scenario.isFailed())
		{
			RunnerClass.noOfFailedTest = RunnerClass.noOfFailedTest + 1;
			RunnerClass.noOfTotalTest = RunnerClass.noOfTotalTest + 1;
			if(strExecutionEnvironment.toUpperCase().equals("SAUCELAB"))
			{
				javascriptexecutor.executeScript("sauce:job-result=failed");	
			}
		}
		else
		{
			RunnerClass.noOfPassedTest = RunnerClass.noOfPassedTest + 1;
			RunnerClass.noOfTotalTest = RunnerClass.noOfTotalTest + 1;
			if(strExecutionEnvironment.toUpperCase().equals("SAUCELAB"))
			{
				javascriptexecutor.executeScript("sauce:job-result=passed");
			}
		}
	    System.out.print("Not of Test" + RunnerClass.noOfTotalTest);
	    System.out.print("Not of Passed Test" + RunnerClass.noOfPassedTest);
	    System.out.print("Not of Failed Test" + RunnerClass.noOfFailedTest);
   
		driver.close();
		
	}

	
}

