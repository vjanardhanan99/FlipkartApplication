import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * This class implements the ITestListener class and displays the details of the test name OnTestStart, onTestSuccess, onTestFailure and onTestSkipped
 */
public class ListenerClass implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("On Test Start" + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("On Test Success" + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("On Test Failure" + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("On Test Skipped" + result.getName());
    }
}
