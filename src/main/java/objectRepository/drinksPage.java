package objectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class drinksPage {
	
	WebDriver driver; //class variable
	public double drPrice;
	public double taxPrice;
	public double restaurantHandlingCharges;
	public double checkoutValue ;

	public drinksPage(WebDriver driver) //local variable for method
	{
		this.driver=driver; //this keyword is referring to the local class variable 
		PageFactory.initElements(driver, this);
	}
	
	@FindAll({@FindBy(xpath = "//span[@class='w-auto ml-3']")})
	public List<WebElement> ListOfDrinksPrice;
	
	@FindBy(xpath = "//div[@class='flex justify-between items-start']//span[2]")
	public WebElement taxElement;
	
	@FindBy(xpath = "//div[@class='display-supplement-value']")
	public WebElement rhcElement;
	
	public void selectItem(WebDriver driver, int item) throws InterruptedException
	{
		ListOfDrinksPrice.size();
		System.out.println(ListOfDrinksPrice.size());
		if ( item <= ListOfDrinksPrice.size())
		{
			String valuePart = ListOfDrinksPrice.get(item).getText().substring(1);
			drPrice = Float.parseFloat(valuePart);
			System.out.println(drPrice);
			ListOfDrinksPrice.get(item).click();
			Thread.sleep(2000);
		} else
		{
			System.out.println("Item you are looking does not present");
		}
	}
	
}
