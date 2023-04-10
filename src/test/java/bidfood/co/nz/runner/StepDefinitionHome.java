package bidfood.co.nz.runner;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import bidfood.co.nz.helper.BaseClass;
import bidfood.co.nz.helper.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class StepDefinitionHome extends BaseClass 
{

	public WebDriver driver;
	
	

	public StepDefinitionHome() throws Exception 
	{

		driver = RunSetup.driver;

	}

	
	

	@Then("verify user is navigated to home page and user name {string} is displayed on the home page")
	public void verify_user_is_navigated_to_home_page_and_user_name_is_displayed_on_the_home_page(String loginUserName) throws Exception 
	{
	   objHome.verifyLoginUserNameOnHomePage(loginUserName);
	}
	
	@Then("verify the components on home page is displayed")
	public void verify_the_components_on_home_page_is_displayed() throws Exception
	{
		objHome.verifyComponentsOnHomePage();
	}

	
	@When("search with keyword {string}")
	public void search_with_keyword(String keywordToSearch) throws Exception 
	{
		objHome.searchWithKeyWord(keywordToSearch);
	}

	@Then("verify {string} is displayed in the search result")
	public void verify_is_displayed_in_the_search_result(String productName) throws Exception 
	{
		objHome.verifySearchResult(productName);
	}
	

	@When("click on the add to card button")
	public void click_on_the_add_to_card_button() throws Exception 
	{
		objHome.clickOnAddToCartButton();
	}
	
	@When("navigate checkout page")
	public void navigate_checkout_page() throws Exception 
	{
		objHome.clickOnCheckoutButton();
	}




}
