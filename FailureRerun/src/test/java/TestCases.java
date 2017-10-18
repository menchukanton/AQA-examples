import Pages.FilesPage;
import Pages.LoginsPage;
import Reporting.TestRail;
import Utils.RerunFailure.Retry;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCases extends ChromeSetUp {

    private LoginsPage loginsPage;
    private FilesPage filesPage;

    private TestRail trReport;

    @BeforeTest
    protected void initDBPages() {
        loginsPage = new LoginsPage(driver);
        filesPage = new FilesPage(driver);
    }

    @BeforeClass
    protected void prepareTestRailRun() throws Exception {
        trReport = new TestRail();
        trReport.startRun(1, "DropBox Automation - " + new SimpleDateFormat("dd/MM/yy HH:mm").format(new Date()));
    }

    @AfterMethod
    protected void reportResult(ITestResult testResult) throws Exception {
        String testDescription = testResult.getMethod().getDescription();
        trReport.setResult(Integer.parseInt(testDescription.substring(0, testDescription.indexOf("."))), testResult.getStatus());
    }


    @AfterClass
    protected void closeTestRailRun() throws Exception {
        trReport.endRun();
    }


    @Test(description = "2. Failure Login")
    protected void failureLoginTest() {
        loginsPage.doFailure();
    }

    @Test(description = "1. Successful Login")
    protected void successLoginTest() {
        loginsPage.doSuccess();
    }

//    @Test(description = "3. Upload File", dependsOnMethods = "successLoginTest")
//    protected void uploadTest() {
//        filesPage.doUpload();
//    }
//
//    @Test(description = "4. Delete File", dependsOnMethods = "uploadTest")
//    protected void deleteTest() {
//        filesPage.doDelete();
//    }
}
