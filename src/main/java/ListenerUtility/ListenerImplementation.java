package ListenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseTest.BaseClass;

public class ListenerImplementation implements ITestListener,ISuiteListener{

	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
	
		Date d= new Date();
		String newDate = d.toString().replace(" ","_").replace(":","_");
		
		spark = new ExtentSparkReporter("./AdvReportSS/reportSS_"+newDate+".html");		
		spark.config().setDocumentTitle("CRM result");
		spark.config().setReportName("Ninza");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows11");
		
		Reporter.log("report config", true);
	}

	@Override
	public void onFinish(ISuite suite) {
		
		report.flush();//er
		Reporter.log("report backup", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
	    test = report.createTest(testName);
		test.log(Status.INFO, "==="+testName+"Execution STARTED===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
		String testName = result.getMethod().getMethodName();
		test.log(Status.PASS, "==="+testName+"Execution SUCCESS===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		Date d = new Date();
		String newDate = d.toString().replace(" ","_").replace(":","_");
		
		TakesScreenshot tss = (TakesScreenshot)BaseClass.sdriver;
		String temp = tss.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(temp);
		
		String testName = result.getMethod().getMethodName();
		test.log(Status.FAIL, "==="+testName+"Execution FAIL===");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		String testName = result.getMethod().getMethodName();
		test.log(Status.SKIP, "==="+testName+"Execution SKIPPED===");
	}

	
	
	
	
	
	
	
	
	
	
	
}
