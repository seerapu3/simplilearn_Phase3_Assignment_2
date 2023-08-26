package com.stepDefinitions;
import org.openqa.selenium.WebDriver;
import baseSetUp.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectRepository.basketSlider;
import objectRepository.deliveryLocationPopUp;
import objectRepository.detailsPage;
import objectRepository.drinksPage;
import objectRepository.pizzasPage;
import utility.XLS_Reader;

public class PizzaOrderSteps extends Base {

	WebDriver driver;
	
	public deliveryLocationPopUp deliveryPopUp;
	public detailsPage dPage;
	public pizzasPage pPage;
	public drinksPage drPage;
	public basketSlider basket;
	public float checkout1;
	public double checkout2;

	@Given("User launch Pizzahut application with {string}")
	
	public void user_launch_pizzahut_application(String url) {
		
		// launch Pizzahut application
		
		XLS_Reader reader = new XLS_Reader("D:\\SimpliLearn\\Assignments\\Phase_2\\pizzaHut\\resouces\\pizzaHut.xlsx");
		
		url = reader.getCellData("TestData", "URL", 2);
		
		driver=getDriver(url);
		
	}


	@When("User wait for auto location black pop up screen")
	public void user_wait_for_location_popup() throws InterruptedException {
		// User wait for auto location black pop up screen 
	}

	@Then("User close the pop up screen")
	public void user_close_popup() {
		// User close the pop up screen
	
	}

	@Then("User see pop up for delivery asking for enter location")
	
	public void user_see_delivery_popup() throws InterruptedException {
		
		// User see pop up for delivery asking for enter location  
		// Verify the deliver popup

	}

	@Then("User type address as {string}")
	public void user_type_address(String location) throws InterruptedException {
		
		// User type address as "Hyderabad"  
		deliveryPopUp = new deliveryLocationPopUp(driver);
		
		deliveryPopUp.enterLocation(driver, location);
	}

	@Then("User select first auto populate drop down option")
	
	public void user_select_auto_populate_option() throws InterruptedException {
		
		// User select first auto populate drop down option
		
		deliveryPopUp.selectFromList(driver);
		
		//deliveryPopUp.selectDefaultTime(driver);
	}

	@When("User navigate to deails page")
	public void user_navigate_to_details_page() throws InterruptedException {
		
		dPage = new detailsPage(driver);
		dPage.verifyDealsTabLoaded(driver);
	}

	@Then("User validate vegetarian radio button flag is off")
	public void user_validate_vegetarian_radio_button() {
		
		dPage.checkonVegeratianToggle(driver);
	}

	@Then("User clicks on Pizzas menu bar option")
	public void user_clicks_on_pizzas_menu_bar_option() {
		
		dPage.clickOnPizzasTab(driver);  
	}

	@When("User select add button of any pizza from Recommended")
	public void user_select_add_button() throws InterruptedException {
		
		pPage = new pizzasPage(driver);
		
		pPage.selectItem(driver,22);
		// User select add button of any pizza from Recommended 
	}

	@Then("User see that the pizza is getting added under Your Basket")
	public void user_verify_pizza_in_basket() {
		
		basket = new basketSlider(driver);
		basket.verifyPizzaAddedToBasket(driver);
	}

	@Then("User validate pizza price plus Tax is checkout price")
	public void user_validate_price_with_tax() throws InterruptedException {
		basket.verifyCheckoutPrice(driver);
	}

	@Then("User validate checkout button contains Item count")
	public void user_validate_item_count() throws InterruptedException {

		basket.getTotalItems(driver);
	}

	@Then("User validate checkout button contains total price count")
	public void user_validate_total_price() throws InterruptedException {
		
		checkout1 = basket.getbasketValue(driver);
		System.out.println("Checkout value when Pizza is added : "+ basket.getbasketValue(driver));
		
	}

	@When("User clicks on Drinks option")
	public void user_clicks_on_drinks() throws InterruptedException {

		dPage.seelctDrinksMenu(driver);
	}

	@Then("User select Pepsi option to add into the Basket")
	public void user_select_pepsi() throws InterruptedException {
		drPage = new drinksPage(driver);
		drPage.selectItem(driver,0);
	}

	@Then("User see 2 items are showing under checkout button")
	public void user_validate_items_in_checkout() throws InterruptedException {
		String item = basket.getTotalItems(driver);
		String[] parts = item.split("\\s+", 2);
		String integerPart = parts[0];
		int intValue = Integer.parseInt(integerPart);
		if(intValue == 2 )
		{
			System.out.println("List of Items : " + item);
		} else
		{
			System.out.println("List of items are not 2");
		}
	}

	@Then("User see total price is now more than before")
	public void user_validate_price_increase() throws InterruptedException {
		checkout2 = basket.getbasketValue(driver);
		System.out.println("Checkout value when Pizza is added : "+ checkout2);
		if(checkout2 > checkout1)
		{
			System.out.println("When Pizza is added : " + checkout1);
			System.out.println("When Pizza + Drink is added : " + checkout2);
		}
	}

	@Then("User remove the Pizza item from Basket")
	public void user_remove_pizza_from_basket() throws InterruptedException {
		basket.removePizza(driver);
	}

	@Then("see Price tag got removed from the checkout button")
	public void user_validate_price_tag_removed() {
		// see Price tag got removed from the checkout button 
	}

	@Then("User see 1 item showing in checkout button")
	public void user_validate_single_item_in_checkout() throws InterruptedException {
		String item = basket.getTotalItems(driver);
		String[] parts = item.split("\\s+", 2);
		String integerPart = parts[0];
		int intValue = Integer.parseInt(integerPart);
		if(intValue == 2 )
		{
			System.out.println("List of Items : " + item);
		} else
		{
			System.out.println("List of items are not 2");
		}
	}

	@When("User Clicks on Checkout button")
	public void user_click_checkout_button() throws InterruptedException {
		
		basket.verifyMinimumCartValue(driver);
		Thread.sleep(1000);
		basket.checkout(driver);
	}

	@Then("User see minimum order required pop up is getting displayed")
	public void user_verify_minimum_order_popup() throws InterruptedException {
		basket.mimConditionOnPopUp(driver);
		
		driver.close();
	}

}
