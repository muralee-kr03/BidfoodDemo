package bidfood.co.nz.pages;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bidfood.co.nz.helper.Helper;
import bidfood.co.nz.runner.RunSetup;

public class Login extends Helper {
	
	public WebDriver driver;
	String loginPageURL;
	String UATLoginURL;
	
    By sportNav = By.xpath("//div[@class='jss268']");
	By txtUserName = By.id("UserName");
	By txtPassword = By.id("Password");
	By btnLogin = By.id("submitBtn");	

	
    

	
	public Login(WebDriver driver) throws Exception
	{
		
		this.driver = driver;
		
	}

	

	public void loginWithValidCreditials(String userName, String password) throws Exception  
	{

		this.sendKeys(txtUserName, userName);
		this.sendKeys(txtPassword, password);
		this.click(btnLogin);
		Thread.sleep(5000);
	
	}
	
	public void verifyValidationMessageForInvlaidCreditials(String validationMessageExpected) throws Exception
	{
		assertEquals(validationMessageExpected, "Expected");
		
	}
	
	
	
	
	public void verifyValidationMessageForForgetPasswordPage(String validationMessageExpected) throws Exception
	{
		assertEquals(validationMessageExpected, "Expected");
		
	}
	
	
	
	 
}


		  	
	