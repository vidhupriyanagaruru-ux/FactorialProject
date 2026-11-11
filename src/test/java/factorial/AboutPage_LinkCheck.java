package factorial;

import java.io.IOException;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class AboutPage_LinkCheck extends TestBase {

	POMMain obj = new POMMain();

	@Test(description = "Verification of link count in about page and print link name")
	public void aboutLinkCheck() {
		try {
			// creating test to log in extent reporting
			test = extent.createTest("Validation of link count in about page and printing link name");
			// To click on about link
			driver.findElement(obj.about).click();
			// Validate that total no.of links on about page are greater than 5
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait1.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("a"), 5));
			// To get all links
			List<WebElement> links = driver.findElements(By.tagName("a"));
			test.info("Total links on about page are"+links.size());
			System.out.println("The links available on about page are as below");
			test.info("The links available on about page are as below");
			for (WebElement ref : links) {
				System.out.println(ref.getText());
				test.info(ref.getText());
				
			}
			test.pass("Validation of link count in about page and printing link names has been done succesfully");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
