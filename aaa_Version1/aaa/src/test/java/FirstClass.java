import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstClass {

    protected WebDriver driver;

    protected void sleep(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
        }
    }

    protected void waitForElementDisplayedXpath(String xPath) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }

    protected void waitForElementDisplayedCss(String cssSelector) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    protected void waitForElementClickableCss(String cssSelector) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable((By.cssSelector(cssSelector))));
    }

    protected void waitForElementClickableXpath(String xPath) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable((By.xpath(xPath))));
    }

    protected void doLogin(String PASS){
        driver.navigate().to("http://dropbox.com/login");
        driver.findElement(By.cssSelector("div>input[name='login_email']")).sendKeys("testusermail2017@gmail.com");
        driver.findElement(By.cssSelector("div>input[name='login_password']")).sendKeys(PASS);
        driver.findElement(By.cssSelector("button[type='submit']>div.sign-in-text")).click();
        sleep(5);
        Assert.assertTrue(driver.getTitle().contains("Dropbox"));
    }

    protected void doUpload(){
        waitForElementDisplayedXpath("//span[contains(text(),'Upload files')]/ancestor::button");
        driver.findElement(By.xpath("//span[contains(text(),'Upload files')]/ancestor::button")).click();
        waitForElementDisplayedCss("button[aria-label='Switch to the basic file uploader']");
        driver.findElement(By.cssSelector("button[aria-label='Switch to the basic file uploader']")).click();
        driver.findElement(By.cssSelector("input[type='file'][aria-label='Upload files']")).sendKeys("/home/hillel/tools.txt");
        waitForElementDisplayedXpath("//span[contains(text(),'tools.txt')]");
    }

    protected void doDelete(){
        driver.get("https://dropbox.com/home");
        waitForElementDisplayedXpath("//span[contains(text(),'tools.txt')]");
        waitForElementDisplayedXpath("//button[@role='button'][contains(@class,'browse-overflow')]");
        driver.findElement(By.xpath("//button[@role='button'][contains(@class,'browse-overflow')]")).click();
        waitForElementDisplayedXpath("//div//button[@role='menuitem'][contains(text(),'Delete')]");
        driver.findElement(By.xpath("//div//button[@role='menuitem'][contains(text(),'Delete')]")).click();
        waitForElementDisplayedCss("button.button-primary");
        driver.findElement(By.cssSelector("button.button-primary")).click();
        sleep(3);
        Assert.assertTrue(driver.findElements(By.xpath("//span[contains(text(),'tools.txt')]")).isEmpty());
    }


    @BeforeTest
    protected void startChrome(){
        System.setProperty("webdriver.chrome.driver","/home/hillel/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    @Test
    protected void doFailureLogin(){
        doLogin("asdsadfsdg");
    }

    @Test
    protected void doSuccessLogin(){
        doLogin("Password11");
    }

    @Test
    protected void uploadTest(){
        doLogin("Password11");
        doUpload();
    }

    @Test
    protected void deleteTest(){
        doLogin("Password11");
        doUpload();
        doDelete();
    }




    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}
