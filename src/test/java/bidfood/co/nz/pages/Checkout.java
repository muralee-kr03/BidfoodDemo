package bidfood.co.nz.pages;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bidfood.co.nz.helper.Helper;
import bidfood.co.nz.runner.RunSetup;
import io.cucumber.datatable.DataTable;

public class Checkout extends Helper {
	
	public WebDriver driver;
	
	By lblProductName = By.xpath("//span[@class='productName ng-binding ng-scope']");
	By lblProductCode = By.cssSelector("span[class='ng-scope'] span[class='productCode ng-binding']");
	///html/body/div[2]/div[5]/div/div/div/div[2]/div/div[3]/div/div[2]/product-list/div/div/table/tbody/tr[3]/td[7]/div/product-list-add-button/div/div[1]/div[1]/input
	By txtQuantity = By.xpath("/html/body/div[2]/div[6]/offcanvas-cart/div[1]/div[4]/div[1]/div/div[1]/div/div/div[5]/form/div/input");
	By lblPrice = By.cssSelector(".item-total.ng-binding.ng-scope");
	By lblNoOfItemsInBasket = By.cssSelector(".trolleyCartCount.ng-binding.warning");
	
	public Checkout(WebDriver driver) throws Exception
	{
		
		this.driver = driver;
		
	}
	
	public void verifyItemsInBasket(String itemsInBasket) throws Exception
	{	
		assertEquals(itemsInBasket.trim(), this.getText(lblNoOfItemsInBasket));
	}
	
	public void verifyDataInCheckoutPage(DataTable checkoutData) throws Exception
	{	
		
		List<List<String>> data = checkoutData.asLists(String.class);	
	
		
		assertEquals(data.get(1).get(0).toUpperCase().trim(), this.getText(lblProductName));
		assertEquals(data.get(1).get(1).toUpperCase().trim(), this.getText(lblProductCode));
		assertEquals(data.get(1).get(3).toUpperCase().trim(), this.getText(lblPrice));

		

	}
	
	
	
	
	 
}


		  	
	