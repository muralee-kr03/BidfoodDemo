package bidfood.co.nz.pages;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bidfood.co.nz.helper.Helper;
import bidfood.co.nz.runner.RunSetup;

public class Home extends Helper {
	
	public WebDriver driver;
	
	By lblLoginUserName = By.cssSelector("span[ng-class=\"{'red-name': phantomUserEnabled}\"]");
	By secondNavigationBar = By.cssSelector(".nav-section.nav-2.flex-container.new-container");
	By thirdNavigationBar = By.cssSelector(".flex-container.justify-center.browse-by.ng-scope");
	By leftPannel = By.cssSelector("div[class='home-panel hidden-sm hidden-xs '] div[class='shortcuts-panel panel-group ng-scope']");
	By centralPannel = By.cssSelector(".col-md-9.col-sm-12.carousel-view-container");
	
	By txtSearch = By.id("search-box");
	By btnSearch = By.id("search-button");
	By firstItemInSearchResult = By.xpath("/html/body/div[2]/div[5]/div/div/div/div[2]/div/div[3]/div/div[2]/product-list/div/div/table/tbody[1]/tr[1]/td/div/span[2]/a");
	By btnAdd = By.xpath("(//button[@type='button'])[12]");
	By btnCheckout = By.id("checkoutState");
	
	public Home(WebDriver driver) throws Exception
	{
		
		this.driver = driver;
		
	}
	
	public void searchWithKeyWord(String keywordToSearch) throws Exception
	{
		this.sendKeys(txtSearch, keywordToSearch);
		this.click(btnSearch);
		Helper.wait(4000);
		
	}
	
	public void clickOnAddToCartButton() throws Exception
	{
		this.click(btnAdd);
		Helper.wait(9000);
	}
	
	public void clickOnCheckoutButton() throws Exception
	{
		this.click(btnCheckout);
		Helper.wait(9000);

	}
	
	public void verifySearchResult(String productName) throws Exception
	{
		assertEquals(this.getText(firstItemInSearchResult), productName);
		
	}

	public void verifyLoginUserNameOnHomePage(String loginUserName) throws Exception
	{
		assertEquals(this.getText(lblLoginUserName), loginUserName);
		
	}
	
	public void verifyComponentsOnHomePage() throws Exception
	{
		assertTrue("Second Naviagation Bar is not displayed", this.isDisplayed(secondNavigationBar));
		assertTrue("Third Naviagation Bar is not displayed", this.isDisplayed(thirdNavigationBar));
		assertTrue("Left Pannel is not displayed", this.isDisplayed(leftPannel));
		assertTrue("Center Pannel is not displayed", this.isDisplayed(centralPannel));
		
	}
	
	
	
	
	 
}


		  	
	