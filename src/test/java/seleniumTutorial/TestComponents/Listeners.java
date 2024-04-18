package seleniumTutorial.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumTutorial.resources.ExtenReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports reports = ExtenReporterNG.getReportObject();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "TEST PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}


	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}


}
