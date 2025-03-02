package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.ExtentManager;
import utils.Log;

public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void setupExtentReport() {
        ExtentManager.setupReport();  // Initialize the Extent Report
    }

    @BeforeMethod
    public void setup(){
        Log.info("Setting up WebDriver...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));
        Log.info("Navigated to: " + ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null ) {
            driver.quit();
        }
    }
}
