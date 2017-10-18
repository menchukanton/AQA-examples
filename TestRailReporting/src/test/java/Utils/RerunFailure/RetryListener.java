package Utils.RerunFailure;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class RetryListener implements IAnnotationTransformer {
    public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod) {
        if (testAnnotation.getRetryAnalyzer() == null)
            testAnnotation.setRetryAnalyzer(Retry.class);
    }
}