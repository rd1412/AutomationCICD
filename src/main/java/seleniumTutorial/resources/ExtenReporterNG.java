package seleniumTutorial.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtenReporterNG {

	public static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Result");

		ExtentReports reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Raymond");

		return reports;
	}

}
