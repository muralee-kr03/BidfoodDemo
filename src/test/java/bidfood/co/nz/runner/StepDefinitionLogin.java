package bidfood.co.nz.runner;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import bidfood.co.nz.helper.BaseClass;
import bidfood.co.nz.helper.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class StepDefinitionLogin extends BaseClass 
{

	public WebDriver driver;
	
	

	public StepDefinitionLogin() throws Exception 
	{

		driver = RunSetup.driver;

	}
	
	@Given("user is at login page")
	public void user_is_at_login_page()
	{
		driver.get(RunSetup.URL);
	}
	
	@Given("^login as \"([^\"]*)\" user$")
	public void login_as_user(String userType) throws Throwable 
	{
		ArrayList<String> userNamePassword = new ArrayList<String>();
		userNamePassword = Helper.getUserNameAndPassword(userType);
		objLogin.loginWithValidCreditials(userNamePassword.get(0), userNamePassword.get(1));
	}
	
	

	
	
	
	



}
