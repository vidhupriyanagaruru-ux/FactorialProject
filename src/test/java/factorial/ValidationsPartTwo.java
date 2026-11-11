package factorial;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidationsPartTwo extends TestBase {

	POMMain obj = new POMMain();

	@SuppressWarnings("deprecation")
	@Test(description = "Validation of placeholder, title and url values")
	public void testValues() {

		try {
			// creating test to log in extent reporting
			test = extent.createTest("Validation of placeholder, title and url values");
			// To validate placeholder of input textbox
			String placeholder = driver.findElement(obj.inputfield).getAttribute("placeholder");
			test.info("placeholder value of the input text box is " + placeholder);
			Assert.assertEquals(placeholder, "Enter an integer", "Mismatch of placeholder value");
			test.pass("Placeholder matched successfully");
			// To validate title of the page
			String title = driver.getTitle();
			test.info("Title of page is " + title);
			if (title.contains("Factorial")) {
				System.out.println("Title contains Factorial");
				test.pass("Title contains Factorial");
			}

			else {
				System.out.println("Title does not contain Factorial");
				test.fail("Title does not contain Factorial");
			}
			// To validate url of the page
			String pageurl = driver.getCurrentUrl();
			test.info("url of the page is " + pageurl);
			if (pageurl.contains("https"))
			{
				System.out.println("url contains https");
				test.pass("url contains https");
			}
			else
			{
				System.out.println("url does not contains https");
				test.fail("url does not contain https");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
