package utils;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase extends GetScreenshot {

	public WebDriver driver;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public ExtentTest test;

	@BeforeTest
	public void startTest() {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "//target//extentReport.html");
		extent.attachReporter(spark);

		// optionals
		extent.setSystemInfo("machine", "windows");
		extent.setSystemInfo("Application type", "retail application");
		extent.setSystemInfo("browser", "chrome");
	}

	@AfterTest
	public void endTest() {
		extent.flush();
	}

	@BeforeClass
	public void InitiateDriver() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterClass
	public void TerminateDriver() {
		driver.quit();
	}

	@BeforeMethod
	public void Initialize(Method method) {
		driver.get(TestData.getprop("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(TestData.getprop("impicitWait"))));
		test = extent.createTest(method.getName());
	}

	@AfterMethod
	public void GetResults(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			test.log(Status.FAIL, "See the screenshot above!!!"
					+ test.addScreenCaptureFromPath(CaptureScreenshot(driver, "screenshot")));
		}
	}

}
