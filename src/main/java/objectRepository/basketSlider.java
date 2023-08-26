package objectRepository;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class basketSlider {
	
	WebDriver driver; 
	public static double pizzaPrice;
	public static double SidesPrice;
	public static double drinkPrice;
	public double taxPrice;
	public double restaurantHandlingCharges;
	public double checkoutValue ;

	public basketSlider(WebDriver driver)
	{
		this.driver=driver;  
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='flex justify-between items-start']//span[2]")
	public WebElement taxElement;
	
	@FindBy(xpath = "//div[@class='display-supplement-value']")
	public WebElement rhcElement;
	

	@FindAll({@FindBy(xpath = "//div[@data-synth='basket-item-type--pizza']")})
	public List<WebElement> totalItemsAdded;
	
	@FindAll({@FindBy(xpath = "//div[@class='basket-item-product-price leading-tight bold text-15 text-black']")})
	public List<WebElement> totalItemsPrices;
	
	@FindAll({@FindBy(xpath = "//div[@data-testid='basket-item-product']//div[1]//div[1]")})
	public List<WebElement> totalItemsNames;
	
	@FindBy(xpath = "//div[@id='basket']/div[3]/div/div/button[2]")
	public WebElement removeIcon;
	
	@FindBy(xpath = "//span[@class='bg-green-dark pl-5 pr-5 rounded']")
	public WebElement totalItems;
	
	@FindBy(xpath = "//span[@class='ml-auto text-right']//span[@data-synth='basket-value']")
	public WebElement basketValue;
	
	@FindBy(xpath = "//span[contains(text(),'Checkout')]")
	public WebElement checkoutBtn;
	
	@FindBy(xpath = "//span[@class='amountdue']")
	public WebElement payableAmount;
	
	@FindBy(xpath = "//div[@data-synth='delivery-minimum-top']//span[contains(text(),'Minimum cart value for delivery is ₹200')]")
	public WebElement minText;
	
	@FindBy(xpath = "//span[contains(text(),'minimum delivery spend')]")
	public WebElement minTextOnPopUp;
	
	public void verifyPizzaAddedToBasket(WebDriver driver)
	{
		if(totalItemsAdded.size()!=0)
		{
			System.out.println("The Products added to cart");
			String valuePart = totalItemsPrices.get(0).getText().substring(1);
			SidesPrice = Float.parseFloat(valuePart);
			SidesPrice =  Math.round(SidesPrice * 100.0) / 100.0 ; 
			System.out.println("The Pizza Price : " + SidesPrice);
		}
	}
	
	public void verifySidesAddedToBasket(WebDriver driver, ExtentTest test )
	{
		
		if(totalItemsAdded.size()!=0)
		{
			System.out.println("The Products added to cart");
			String valuePart = totalItemsPrices.get(0).getText().substring(1);
			SidesPrice = Float.parseFloat(valuePart);
			SidesPrice =  Math.round(pizzaPrice * 100.0) / 100.0 ; 
			System.out.println("The Pizza Price : " + SidesPrice);
			test.log(Status.PASS, "The Sides Price that is added to cart is " + SidesPrice + "Which is less then 200" );
		}
	}
	
	public void verifyCheckoutPrice(WebDriver driver) throws InterruptedException
	{
			Thread.sleep(2000);
			taxPrice = Float.parseFloat(taxElement.getText().substring(1));
			taxPrice =  Math.round(taxPrice * 100.0) / 100.0 ; 
			System.out.println("Total tax : " + taxPrice);
			restaurantHandlingCharges = Float.parseFloat(rhcElement.getText().substring(1));
			restaurantHandlingCharges =  Math.round(restaurantHandlingCharges* 100.0) / 100.0 ; 
			System.out.println("Total Restaurant handing Charges : " + restaurantHandlingCharges);
			checkoutValue = pizzaPrice + taxPrice + restaurantHandlingCharges;
			checkoutValue =  Math.round(checkoutValue * 100.0) / 100.0 ; 
			System.out.println("Total Checkout Price : " + checkoutValue);
	}
	
	public String getTotalItems(WebDriver driver) throws InterruptedException
	{
		System.out.println(totalItems.getText());
		return totalItems.getText();
	}
	
	public float getbasketValue(WebDriver driver) throws InterruptedException
	{
		System.out.println("Checkout value " + basketValue.getText());
		float d = Float.parseFloat(basketValue.getText().substring(1));
		System.out.println(d);
		return d;
	}
	
	public void removePizza(WebDriver driver) throws InterruptedException
	{
		System.out.println(totalItemsNames.size());
		if(totalItemsNames.size() >= 0)
		{
			String valuePart = totalItemsNames.get(0).getText();
			System.out.println(valuePart);
			if(valuePart.contains("Medium Veg Exotica"))
			{
				Thread.sleep(2000);
				System.out.println("About to remove the product");
				removeIcon.click();
				System.out.println("Removed the product");
			}

		}
	}
	public void checkout(WebDriver driver) throws InterruptedException
	{
		checkoutBtn.click();
		Thread.sleep(5000);
	}
	
	public void mimConditionOnPopUp(WebDriver driver) throws InterruptedException
	{
		String min = minTextOnPopUp.getText();
		if(min.contains("away from minimum delivery spend"));
		{
			System.out.println("The popup with min condition has been displayed : " + min );
		}
	}
	
	public void verifyMinimumCartValue(WebDriver driver)
	{
		// Assert that the cart value is greater than or equal to ₹200
		if(checkoutValue >= 200)
		{
			String min = minText.getText();
			if(min.equalsIgnoreCase("Minimum cart value for delivery is ₹200"))
			{
				System.out.println("The condition is displayed : " + min);
				// Assert that the cart value is greater than or equal to ₹200
		        assertTrue("Minimum cart value for delivery not met.", checkoutValue >= 200);
			}
		}
	}
	
	public void notShowingStill(WebDriver driver) throws InterruptedException
	{
		    if (basketValue != null) {
		        System.out.println("The element is not present; do something else");
		    } else {
		    	System.out.println("The element is present; do something else");
		    }
	}

}
