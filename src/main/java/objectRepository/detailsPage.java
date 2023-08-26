package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class detailsPage {
	
	WebDriver driver; //class variable

	public detailsPage(WebDriver driver) //local variable for method
	{
		this.driver=driver; //this keyword is referring to the local class variable 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='side-menu lg:shadow-down-1 lg:border-b lg:border-t']//span[contains(text(),'Deals')]")
	public WebElement dealsMenu;
	
	@FindBy(xpath = "//div[@class='side-menu lg:shadow-down-1 lg:border-b lg:border-t']//span[contains(text(),'Pizzas')]") 
	public WebElement pizzaMenu; 
	
	@FindBy(xpath = "//div[@class='side-menu lg:shadow-down-1 lg:border-b lg:border-t']//span[contains(text(),'Sides')]") 
	public WebElement sidesMenu;
	
	@FindBy(xpath = "//div[@class='side-menu lg:shadow-down-1 lg:border-b lg:border-t']//span[contains(text(),'Drinks')]") 
	public WebElement drinksMenu;
	
	@FindBy(xpath = "//div[@class='side-menu lg:shadow-down-1 lg:border-b lg:border-t']//p[contains(text(),'Vegetarian')]") 
	public WebElement vegeratrianToggle; 
	
	public void verifyDealsTabLoaded(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(3000);
		String txt = "Deals";
		if(txt.equalsIgnoreCase(dealsMenu.getText()))
				{
			
				}
	}
	
	public void clickOnPizzasTab(WebDriver driver)
	{
		pizzaMenu.click();
	}
	public void seelctDrinksMenu(WebDriver driver)
	{
		drinksMenu.click();
	}
	
	public void seelctSidesMenu(WebDriver driver)
	{
		sidesMenu.click();
	}

	public void checkonVegeratianToggle(WebDriver driver)
	{
		vegeratrianToggle.click();
	}
	
}
