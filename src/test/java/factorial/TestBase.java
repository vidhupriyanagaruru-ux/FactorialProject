package factorial;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;
import java.nio.file.spi.FileSystemProvider;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

public class TestBase {

	WebDriver driver;
	protected static ExtentReports extent;
	ExtentTest test;
	ITestResult result;
	ConfigReader cr = new ConfigReader();

	// To setup extent report
	@BeforeSuite
	public void setupReport() {
		// creating instance of extent reports
		extent = new ExtentReports();
		// getting date and time to create folder name based on report created date and
		// time
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/Reports/ExtentReport_" + timeStamp + ".html");
		// attaching reporter
		extent.attachReporter(spark);
		// customization of report appearance
		spark.config().setDocumentTitle("Automation Test Report");
		spark.config().setReportName("Regression Suite");
		spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
	}

	@AfterSuite
	public void flushReport() {
		// Essential: Writes test data to the target file
		extent.flush();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail("Test Case Failed: " + result.getThrowable());

			// Capture and attach screenshot dynamically on failure
			try {
				captureScreenshot(result.getName());
			} catch (IOException e) {
				test.info("Failed to attach screenshot due to exception: " + e.getMessage());
			}
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Case Skipped.");
		}
	}

	// to capture screenshot
	public void captureScreenshot(String screenshotName) throws IOException {

		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);// screenshot of entire page
		Files.copy(f,
				new File("C:\\Users\\vidhu\\eclipse-workspace\\FactorialProject\\test-output\\Reports\\Screenshots\\"
						+ screenshotName + ".png"));

	}

	// To launch the application
	@BeforeClass
	@Parameters("browser")
	public void launchApplication(String browser) throws Exception {
		String url = cr.getProperty("url");
		if(browser.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		else if(browser.equalsIgnoreCase("Edge"))
			driver=new EdgeDriver();
		else if(browser.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else
			throw new Exception("Incorrect browser");
		driver.get(url);
		driver.manage().window().maximize();
		// implict wait to wait for element display
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	// To close the browser
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	// Reusable Methods
	// To check presence of element
	public boolean elementPresenceCheck(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	// To check clickability of element
	public boolean elementClickableCheck(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Wait conditions
	public void waitForElement(String condition, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		if (condition.equals("visibilityofelementlocated")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} else if (condition.equals("elementtobeclickable"))
			wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Factorial calculation
	public long factValue(int n) {
		long fact = 1;
		for (int i = n; i > 0; i--) {
			fact = fact * i;
		}
		return fact;
	}

	// To check for app crash
	public boolean pageCrashCheck() {
		String title = driver.getTitle();
		String source = driver.getPageSource();

		if (title.contains("Privacy error") || title.contains("Problem loading page") || source.isEmpty()) {
			return true;
		}
		return false;
	}

}
