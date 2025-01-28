package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.CommonUtilities;

public class MyListerers implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		extentReport = CommonUtilities.getExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + "test execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + "test got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.log(Status.FAIL, result.getName() + "test got failed");
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		String screenShotPath = CommonUtilities.takeScreenshotAndReturnPath(driver,
				"\\resources\\Screenshots\\" + result.getName() + ".png");
		extentTest.addScreenCaptureFromPath(screenShotPath);
		extentTest.log(Status.INFO,result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, result.getName() + "test got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		File extentReportFile = new File(System.getProperty("user.dir")+"\\reports\\TNExtentReport.html");
		extentReport.flush();
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
