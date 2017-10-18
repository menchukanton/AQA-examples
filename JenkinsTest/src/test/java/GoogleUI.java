import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by hillel on 07.07.17.
 */
public class GoogleUI extends RemoteWebDriver {

    WebDriver driver;

    @BeforeTest
    void setUp() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--incognito");
        options.addArguments("--kiosk");
        options.addArguments("--lang=en");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://accounts.google.com/");


        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("1232321321")));
    }


    void stuff() throws AWTException {
        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        driver.close();
        driver.switchTo().defaultContent();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[name='myiframe']")));

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND + "3");


        Actions a = new Actions(driver);

        a.contextClick(driver.findElement(By.cssSelector("123"))).build().perform();


        ((JavascriptExecutor)driver).executeScript("");
    }




    void PerformStep (String inputField, String input, String button) {
        WebElement field = driver.findElement(By.cssSelector(inputField));
        field.clear();
        field.sendKeys(input);

        driver.findElement(By.cssSelector(button))
                .click();

        mySleep(1);
    }

    void FirstStep(String input) {
        PerformStep("input[type='email']", input, "div[role='button']");
    }

    void SecondStep(String input) {
        PerformStep("input[type='password']", input, "div#passwordNext");
    }

    @Test(groups = "positive", priority = 2)
    void SuccessfulLoginFirstStep() {
        FirstStep("rvalek@intersog.com");

        Assert.assertTrue(doesElementExist("input[name='password']"));
    }

    @Test(groups = "positive", priority = 4, dependsOnMethods = {"SuccessfulLoginFirstStep"})
    void SuccessfulLoginSecondStep() {
        SecondStep("Scalash1@");

        Assert.assertFalse(doesElementExist("input[name='password']"));
    }

    @Test(groups = "negative", priority = 1)
    void BadLoginFirstStep () {
        FirstStep("ba124213432423d@gmail.com");

        Assert.assertFalse(doesElementExist("input[name='password']"));
    }

    @Test(groups = "negative", priority = 3, dependsOnMethods = {"SuccessfulLoginFirstStep"})
    void BadLoginSecondStep () {
        SecondStep("1233213");

        Assert.assertTrue(doesElementExist("input[name='password']"));
    }


    boolean doesElementExist(String selector) {
        try {
            driver.findElement(By.cssSelector(selector));
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    void mySleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {}
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }

}
