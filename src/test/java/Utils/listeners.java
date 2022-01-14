package Utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class listeners implements ITestListener {

    public void onTestSuccess(ITestResult result){

        CommonMethods.takeScreenshot("passed/"+result.getName());
    }
    public void onTestFailure(ITestResult result){
        CommonMethods.takeScreenshot("failed/"+result.getName());
    }
}
