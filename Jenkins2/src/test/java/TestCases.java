import Pages.FilesPage;
import Pages.LoginsPage;
import Reporting.TestRail;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases extends ChromeConfig {

    private LoginsPage loginsPage;
    private FilesPage filesPage;
    private TestRail trReport;

    @BeforeTest
    protected void initDBPages() {
        loginsPage = new LoginsPage(driver);
        filesPage = new FilesPage(driver);
    }

    @BeforeTest
    protected void prepareTestRailRun() throws Exception {
        trReport = new TestRail();
        trReport.startRun(1, "DropBox Automation 1");
    }

    @AfterTest
    protected void closeTestRailRun() throws Exception {
        trReport.endRun();
    }

    @AfterMethod
    protected void reportResult(ITestResult testResult) throws Exception {
        String testDescription = testResult.getMethod().getDescription();
        trReport.setResult(Integer.parseInt(testDescription.substring(0, testDescription.indexOf("."))), testResult.getStatus());
    }

    @Test(description = "2. Failure Login")
    protected void failureLoginTest() {
        loginsPage.doFailure();
    }

    @Test(description = "1. Successful Login")
    protected void successLoginTest() {
        loginsPage.doSuccess();
    }

    @Test(description = "3. Upload File", dependsOnMethods = "successLoginTest")
    protected void uploadTest() {
        filesPage.doUpload();
    }

    @Test(description = "4. Delete File", dependsOnMethods = "uploadTest")
    protected void deleteTest() {
        filesPage.doDelete();
    }
}
