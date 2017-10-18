import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;


public class ChromeSetUp {

    protected WebDriver driver;

    @BeforeTest
    protected void startChrome()  throws Exception {
        System.setProperty("webdriver.chrome.driver","bin/chromedriver_l");

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}
