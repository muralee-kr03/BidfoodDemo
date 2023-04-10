package bidfood.co.nz.runner;

import org.openqa.selenium.WebDriver;

import bidfood.co.nz.helper.BaseClass;
import io.cucumber.java.en.Then;



public class StepDefinitionCheckout extends BaseClass 
{

	public WebDriver driver;
	
	

	public StepDefinitionCheckout() throws Exception 
	{

		driver = RunSetup.driver;

	}

	@Then("verify number items in basket is displayed as {string}")
	public void verify_number_items_in_basket_is_displayed_as(String itemsInBasket) throws Exception 
	{
		objCheckout.verifyItemsInBasket(itemsInBasket);
	}
	
	@Then("verify below information is displayed on checkout page")
	public void verify_below_information_is_displayed_on_checkout_page(io.cucumber.datatable.DataTable checkoutData) throws Exception 
	{
		objCheckout.verifyDataInCheckoutPage(checkoutData);
	}



}
