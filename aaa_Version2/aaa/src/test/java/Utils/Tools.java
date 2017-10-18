package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tools {

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

}
