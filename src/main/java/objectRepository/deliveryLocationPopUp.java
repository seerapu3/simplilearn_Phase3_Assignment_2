package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.XLS_Reader;

public class deliveryLocationPopUp {
	
	XLS_Reader reader = new XLS_Reader("D:\\SimpliLearn\\Assignments\\Phase_2\\pizzaHut\\resouces\\pizzaHut.xlsx");
	
	String location = reader.getCellData("TestData", "Location", 2);
	
	WebDriver driver; //class variable

	public deliveryLocationPopUp(WebDriver driver)
	{
		this.driver=driver; //this keyword is referring to the local class variable 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Enter your location for delivery']")
	public WebElement locationField;
	
	@FindBy(xpath = "//button[2]/div[2]/div") 
	public WebElement autoValue; 
	
	@FindBy(xpath = "//span[contains(text(),'Start your order')]") 
	public WebElement defaultTime;
	
	
	public void enterLocation(WebDriver driver, String location)
	{
		locationField.sendKeys(location);
	}
	
	public void selectFromList(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(2000);
		autoValue.click();
	}
	
	public void selectDefaultTime(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(2000);
		defaultTime.click();
	}
}
