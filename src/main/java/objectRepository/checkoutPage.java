package objectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class checkoutPage {
	
	WebDriver driver; //class variable

	public checkoutPage(WebDriver driver) //local variable for method
	{
		this.driver=driver; //this keyword is referring to the local class variable 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@alt='Website for automation practice']")
	public WebElement logo;
	
	@FindAll({@FindBy(xpath = "//i[@class='mt-4']")})
	public List<WebElement> paymentRadioCheckBoxes;
	
	@FindBy(xpath = "//span[contains(text(),'Cash')]")
	public WebElement cashPayment;
	
	@FindBy(xpath = "//span[contains(text(),'I agree to receive promotional communication.')]")
	public WebElement promotionCheckBox;
	
	@FindBy(xpath = "//a[@href=\"/login\"]") 
	public WebElement signLoginBtn; 
	
	@FindBy(xpath = "//input[@id='checkout__name']") 
	public WebElement nameField; 
	
	@FindBy(xpath = "//input[@id='checkout__phone']") 
	public WebElement phoneField; 
	
	@FindBy(xpath = "//input[@id='checkout__email']") 
	public WebElement emailField; 
	
	@FindBy(xpath = "//span[contains(text(),'Apply Gift Card')]") 
	public WebElement applyGiftCardBtn;
	
	@FindBy(xpath = "//input[@name='giftCardPin']") 
	public WebElement giftCardPin;
	
	@FindBy(xpath = "//input[@name='giftCardNumber']") 
	public WebElement giftCardNumber;
	
	@FindBy(xpath = "//div[@class='sc-fznMnq gdZumo']//span[contains(text(),'Coupon')]") 
	public WebElement couponTab;
	
	@FindBy(xpath = "//input[@name='voucherId']") 
	public WebElement voucherId;
	
	@FindBy(xpath = "//button[@type='submit']") 
	public WebElement applyBtn;
	
	@FindBy(xpath = "//div[@class='sc-fznJRM ciBEcK']") 
	public WebElement errorTxt;
	
	@FindBy(xpath = "//button[contains(text(),'Cancel')]") 
	public WebElement closePopup;
	
	public void clickOnSign(WebDriver driver)
	{
		signLoginBtn.click();
	}
	
	public void homePageVerification(WebDriver driver)
	{	
		//assertEquals(logo.isDisplayed(), true);
	}
	
	public void verifyOnlinePaymentDefaultSelected(WebDriver driver)
	{	
		System.out.println(paymentRadioCheckBoxes.size());
		
		// Check if the radio button is selected by default.
        boolean isSelected = paymentRadioCheckBoxes.get(1).isEnabled();
        
        if (isSelected) {
            System.out.println("The radio button is selected by default.");
        } else {
            System.out.println("The radio button is not selected by default.");
        }
	}
	
	public void verifyPromotionCheckbox(WebDriver driver)
	{	

		// Check if the radio button is selected by default.
        boolean isSelected = promotionCheckBox.isEnabled();
        
        if (isSelected) {
            System.out.println("The receive promotional communication checkbox is selected by default.");
        } else {
            System.out.println("The receive promotional communication checkbox is not selected by default.");
        }
	}
	
	public void selectCashPayment(WebDriver driver)
	{	
		cashPayment.click();
	}
	
	public void enterBasicDetails(WebDriver driver, String name, String phone, String email)
	{	
		nameField.sendKeys(name);
		phoneField.sendKeys(phone);
		emailField.sendKeys(email);
	}
	
	public void validateVoucher(WebDriver driver, String vochurid) throws InterruptedException
	{	
		applyGiftCardBtn.click();
		Thread.sleep(2000);
		couponTab.click();
		Thread.sleep(1000);
		voucherId.sendKeys(vochurid);
		Thread.sleep(1000);
		applyBtn.click();
		Thread.sleep(1000);
		if(errorTxt.getText().contains("currently support that coupon code"))
		{
			System.out.println("The vouchur code is invalid");
		}
		Thread.sleep(1000);
		closePopup.click();
		
	}
}
