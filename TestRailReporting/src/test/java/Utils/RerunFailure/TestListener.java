package Utils.RerunFailure;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Set;

public class TestListener implements ITestListener {
//    private ITestContext context;
//
    public void onStart(ITestContext context) {
//        this.context = context;
    }

    public void onFinish(ITestContext context) {
//        Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
//
//        for (ITestResult test : failedTests) {
//            if (context.getPassedTests().getResults(test.getMethod()).size() > 0)
//                failedTests.remove(test);
//        }
    }

    public void onTestStart(ITestResult result) {
        result.getTestContext().getSkippedTests().removeResult(result.getMethod());
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}