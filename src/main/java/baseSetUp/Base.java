package baseSetUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utility.*;

public class Base {
	
	public static double pizzaPrice;
	public static XLS_Reader reader = new XLS_Reader("D:\\SimpliLearn\\Assignments\\Phase_2\\pizzaHut\\resouces\\pizzaHut.xlsx");
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
    public static ExtentTest test;

	public WebDriver getDriver()
	{
		String bURL = reader.getCellData("TestData", "URL", 2);
		
		System.out.println(bURL);
		
		// Set the path to the Firefox executable
    	System.setProperty("webdriver.gecko.driver", "D:\\SimpliLearn\\Assignments\\Phase_2\\ecommerce\\driver\\geckodriver.exe");

    	// Initialize the WebDriver
    	FirefoxOptions options = new FirefoxOptions();
        options.addPreference("geo.enabled", false);
        
    	WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();

        // Add steps to open the application URL in the browser
        driver.get(bURL);
        
        return driver;
	}
	
	public WebDriver getDriver(String bUrl)
	{
		// Set the path to the Firefox executable
    	System.setProperty("webdriver.gecko.driver", "D:\\SimpliLearn\\Assignments\\Phase_2\\ecommerce\\driver\\geckodriver.exe");

        // Initialize the WebDriver
    	FirefoxOptions options = new FirefoxOptions();
        options.addPreference("geo.enabled", false);
        
    	WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();

        // Add steps to open the application URL in the browser
        driver.get(bUrl);
        
        return driver;
	}
	
	public void extentReportsInitialization()
	{
		// Initialize ExtentReports and specify the report file location
		spark  = new ExtentSparkReporter("extent-report.html");
		
        // Create ExtentReports instance
        extent = new ExtentReports();
        extent.attachReporter(spark);

	}

}
