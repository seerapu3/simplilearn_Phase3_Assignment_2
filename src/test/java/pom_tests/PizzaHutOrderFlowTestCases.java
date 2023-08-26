package pom_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import objectRepository.*;
import utility.XLS_Reader;
import baseSetUp.Base; 

public class PizzaHutOrderFlowTestCases extends Base {
	
	WebDriver driver;
	public deliveryLocationPopUp deliveryPopUp;
	public detailsPage dPage;
	public pizzasPage pPage;
	public drinksPage drPage;
	public sidesPage sPage;
	public basketSlider basket;
	public checkoutPage checkout;
		
	@BeforeClass
	public void initailSetup()
	{
		
		driver=getDriver(); //allocate the driver value from base class
		extentReportsInitialization();
		deliveryPopUp = new deliveryLocationPopUp(driver);
	}

    @Test (priority = 1)
    public void validateDealsPageURL() throws InterruptedException {
    	
    	test = extent.createTest("Validate that the URL has text 'deals'");
    	
    	// User type address as "Hyderabad"  
    	deliveryPopUp = new deliveryLocationPopUp(driver);
    	
    	String location = reader.getCellData("TestData", "Location", 2);
    			
    	deliveryPopUp.enterLocation(driver, location);
    			
    	// User select first auto populate drop down option
		
    	deliveryPopUp.selectFromList(driver);
    	
    	// Log a passed test step
        test.log(Status.PASS, "Able to select "+location+" from the location dropdown");
    			
    	//deliveryPopUp.selectDefaultTime(driver);
    	Thread.sleep(10000);
    	String cURL = driver.getCurrentUrl();
    	System.out.println(cURL);
    	if(cURL.contains("deals"))
    	{
    		System.out.println("The user in Deals Page");
    		test.log(Status.PASS, "Successfully verified, the user in Deals page");
    	}else
    	{
    		System.out.println("User not in deals pag");
    	}
    }

    @Test (priority = 2)
    public void addSideItemToCart() throws InterruptedException {
    	test = extent.createTest("Validate Add Side items to cart");
    	
    	//driver.findElement(By.xpath("//span[contains(text(),OK)]")).click();
        // Go to sides and add any item below 200
    	sPage  = new sidesPage(driver);	
    	
    	dPage = new detailsPage(driver);
    	// Click on Sides menu
    	dPage.seelctSidesMenu(driver);
    	
    	sPage.selectItem(driver, 2);
    	test.log(Status.PASS, "User selected the Sides item which is less than 200");
    	basket = new basketSlider(driver);
    	Thread.sleep(2000);
    	// Validate that the product is added to the basket
    	basket.verifySidesAddedToBasket(driver,test);
    	test.log(Status.PASS, "User successfully verfied the added sides items which is in Basket");
        // Validate that the checkout button price item is still not showing
    	basket.notShowingStill(driver);
    	test.log(Status.PASS, "User successfully verfied the price of the items not showing in checkout button");
    	// Close the test
        extent.flush();
    }

    @Test (priority = 3)
    public void addDrinksToCart() throws InterruptedException {
    	test = extent.createTest("Validate that adding drinks to cart");
    	test.log(Status.PASS, "User successfully navigated to Drinks page");
        // Navigate to the Drinks page
    	dPage.seelctDrinksMenu(driver);
        // Add any two drinks to make the cart total more than 200
    	drPage = new drinksPage(driver);
    	Thread.sleep(3000);
    	drPage.selectItem(driver, 3);
    	test.log(Status.PASS, "User successfully added the drinks item to cart");
    	// Close the test
        extent.flush();
    }

    @Test (priority = 4)
    public void navigateToCheckout() throws InterruptedException {
    	test = extent.createTest("Validate Payment, Promotions and Basic details on checkout");
        // Click on the Checkout button
    	Thread.sleep(2000);
    	basket.checkout(driver);
  
        // Validate that the Online Payment radio button is selected by default
    	Thread.sleep(2000);
    	checkout = new checkoutPage(driver);
    	test.log(Status.PASS, "User successfully click on checkout button and proceeded to checkout page");
    	checkout.verifyOnlinePaymentDefaultSelected(driver);
    	test.log(Status.PASS, "User successfully verified default Online payment default selected");
        // Change the Payment option to Cash
    	Thread.sleep(1000);
    	checkout.selectCashPayment(driver);
    	test.log(Status.PASS, "User successfully selected the payment option to cash");
        // Validate that the "I agree" checkbox is checked by default
    	Thread.sleep(1000);
    	checkout.verifyPromotionCheckbox(driver);
    	test.log(Status.PASS, "The default Promotion checkbox is selected");
        // Enter name, mobile, and email address
    	checkout.enterBasicDetails(driver, "Prasad", "8639313470", "Seeraputesting@nowcare.us");
    	test.log(Status.PASS, "User successfully added Name, Phone number and email");
    	// Close the test
        extent.flush();
    }

    @Test (priority = 5)
    public void applyGiftCard() throws InterruptedException {
    	test = extent.createTest("Validate the Voucher on checkout");
        // Click on the Apply Gift Card link
        // A pop-up should appear
        // Click on the Voucher
        // Give the Voucher code as 12345 and submit
        // Validate if an error is coming that the number is incorrect
    	checkout.validateVoucher(driver, "ABCD12345");
    	test.log(Status.PASS, "User successfully verified voucher invalid error");
        // Close the voucher pop-up
    	// Close the test
        extent.flush();
    }

    @AfterClass
    public void closeBrowser() {
        driver.close();
    }
}