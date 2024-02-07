package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;


public class extentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    String apiReport;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy:dd:MM:HH:mm:ss").format(new Date());
        apiReport = "Test-Report"+ ".html";

        sparkReporter = new ExtentSparkReporter(".//reports//" + apiReport);
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
        sparkReporter.config().setReportName("Tuneverse Member API");
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application" , "Tuneverse Member API");

    }

    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.PASS,"Test Passed");
    }

    public void onTestFail(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.FAIL,"Test Failed");
        extentTest.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkip(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.SKIP,"Test Failed");
        extentTest.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onTestFinish(ITestContext testContext) {
        extentReports.flush();
    }


}
