import Pages.FilesPage;
import Pages.LoginsPage;
import org.testng.annotations.Test;

public class TestCases extends ChromeConfig {

    @Test
    protected void failureLoginTest(){
        LoginsPage loginsPage = new LoginsPage(driver);
        loginsPage.doFailure();
    }

    @Test
    protected void successLoginTest(){
        LoginsPage loginsPage = new LoginsPage(driver);
        loginsPage.doSuccess();
    }

    @Test
    protected void uploadTest(){
        LoginsPage loginsPage = new LoginsPage(driver);
        FilesPage filesPage = new FilesPage(driver);
        loginsPage.doSuccess();
        filesPage.doUpload();
    }

    @Test
    protected void deleteTest(){
        LoginsPage loginsPage = new LoginsPage(driver);
        FilesPage filesPage = new FilesPage(driver);
        loginsPage.doSuccess();
        filesPage.doUpload();
        filesPage.doDelete();
    }

}
