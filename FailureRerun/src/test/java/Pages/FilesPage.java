package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilesPage extends Tools {

    public FilesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        System.out.println("FilesPage elements are initialized");
    }

    private final String PATH_TO_FILE = "/home/hillel/";
    private final String FILE_NAME = "testFile.txt";

    @FindBy(css = "input[type='file']")
    private WebElement fileInput;
    @FindBy(xpath = "//tr[@data-filename='"+FILE_NAME+"']")
    private WebElement testFile;
    @FindBy(xpath = "//tr[@data-filename='"+FILE_NAME+"']//button[@role='button']")
    private WebElement filePopup;
    @FindBy(xpath = "//div//button[@role='menuitem'][contains(text(),'Delete')]")
    private WebElement deleteButton;
    @FindBy(css = "button.button-primary")
    private WebElement confirmDeleting;

    public void doUpload(){
        fileInput.sendKeys(PATH_TO_FILE + FILE_NAME);
        sleep(3);
        waitForElementDisplayed(testFile);
        waitForElementClickable(testFile);
        driver.navigate().refresh();
    }

    public void doDelete() {
        waitForElementDisplayed(testFile);
        waitForElementClickable(testFile);
        waitForElementDisplayed(filePopup);
        filePopup.click();
        waitForElementDisplayed(deleteButton);
        waitForElementClickable(deleteButton);
        deleteButton.click();
        sleep(2);
        confirmDeleting.click();
        sleep(2);
    }
}
