package factorial;

import org.testng.Assert;

import org.testng.annotations.Test;

public class TermsAndPrivacy_LinkCheck extends TestBase {
	POMMain obj = new POMMain();

	@Test(priority = 0, description = "Verification of terms link")
	public void termsLinkCheck() {
		try {
			// creating test to log in extent reporting
			test = extent.createTest("Validation of terms link");
			// To click on terms and conditions link
			driver.findElement(obj.terms).click();
			// To check the url
			String termsurl = driver.getCurrentUrl();
			System.out.println("terms url is " + termsurl);
			test.info("terms url is" + termsurl);
			Assert.assertTrue(termsurl.contains("terms"), "Incorrect url mapped");
			test.pass("terms url validation is passed");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test(priority = 1, description = "Verification of privacy link")
	public void privacyLinkCheck() {
		try {
			// creating test to log in extent reporting
			test = extent.createTest("Validation of privacy link");
			// To navigate back to main page
			driver.navigate().back();
			// To click on privacy link
			driver.findElement(obj.privacy).click();
			// To check the url
			String privacyurl = driver.getCurrentUrl();
			System.out.println("privacy url is " + privacyurl);
			test.info("privacy url is " + privacyurl);
			Assert.assertTrue(privacyurl.contains("privacy"), "Incorrect url mapped");
			test.pass("privacy url validation is passed");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
