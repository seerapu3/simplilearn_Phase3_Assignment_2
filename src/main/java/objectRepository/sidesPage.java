package objectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class sidesPage {
	
	WebDriver driver; //class variable
	public double sidesPrice;
	public double taxPrice;
	public double restaurantHandlingCharges;
	public double checkoutValue ;

	public sidesPage(WebDriver driver) //local variable for method
	{
		this.driver=driver; //this keyword is referring to the local class variable 
		PageFactory.initElements(driver, this);
	}
	
	@FindAll({@FindBy(xpath = "//span[@class='w-auto ml-3']")})
	public List<WebElement> ListOfitemsPrice;
	
	@FindBy(xpath = "//div[@class='flex justify-between items-start']//span[2]")
	public WebElement taxElement;
	
	@FindBy(xpath = "//div[@class='display-supplement-value']")
	public WebElement rhcElement;
	
	public void selectItem(WebDriver driver, int item) throws InterruptedException
	{
		Thread.sleep(3000);
		ListOfitemsPrice.size();
		System.out.println("Total items : "+ ListOfitemsPrice.size());
		if (item <= ListOfitemsPrice.size())
		{
			String valuePart = ListOfitemsPrice.get(item).getText().substring(1);
			sidesPrice = Float.parseFloat(valuePart);
			System.out.println(sidesPrice);
			ListOfitemsPrice.get(item).click();
			Thread.sleep(2000);
		} else
		{
			System.out.println("Item you are looking does not present");
		}
	}
	
}
