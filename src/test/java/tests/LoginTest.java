package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentManager;
import utils.Log;
import utils.ScreenshotUtil;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        ExtentManager.createTest("Login Test - Valid Credentials");

        Log.info("Starting Login Test...");
        ExtentManager.logInfo("Starting Login Test");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(ConfigReader.getProperty("username"));
        Log.info("Entered Username: " + ConfigReader.getProperty("username"));
        ExtentManager.logInfo("Entered Username");

        loginPage.enterPassword(ConfigReader.getProperty("password"));
        Log.info("Entered Password: ********");
        ExtentManager.logInfo("Entered Password");

        loginPage.clickLogin();
        Log.info("Clicked Login Button.");
        ExtentManager.logInfo("Clicked Login Button");

        String actualTitle = driver.getTitle();
        Log.info("Page Title After Login: " + actualTitle);
        ExtentManager.logInfo("Page Title After Login: " + actualTitle);

        Assert.assertTrue(actualTitle.contains("Just a moment..."), "Login Failed!");
        Log.info("Login Test Passed!");
//        ExtentManager.logPass("Login Test Passed!");
    }

    @AfterMethod
    public void tearDownReport(ITestResult result) {
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
        if (result.getStatus() == ITestResult.FAILURE) {
            Log.error("Test Failed: " + result.getName());
            ExtentManager.logFail("Test Failed: " + result.getThrowable().getMessage(), screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            Log.info("Test Passed. Screenshot Captured.");
            ExtentManager.logPass("Test Passed Successfully", screenshotPath);
        }
        ExtentManager.flushReport();
    }
}
