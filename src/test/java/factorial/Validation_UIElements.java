package factorial;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Validation_UIElements extends TestBase {

	POMMain obj = new POMMain();

	@Test(priority = 0, description = "Validation of Presence of Elements")
	public void testPresence() {
		try {
			// creating test to log in extent reporting
			test = extent.createTest("Validation of presence of elements");
			// To validate presence of input textbox
			boolean txtboxdisplay = elementPresenceCheck(obj.inputfield);
			test.info("Validating presence of input text box..");
			Assert.assertTrue(txtboxdisplay, "text box is not displayed");
			test.pass("input text box is present");
			// To validate presence of calculate button
			boolean calbtndisplay = elementPresenceCheck(obj.calculate);
			test.info("Validating presence of calculate button..");
			Assert.assertTrue(calbtndisplay, "calculate button is not displayed");
			test.pass("calculate button is present");
			// To validate presence of about link
			boolean abtdisplay = elementPresenceCheck(obj.about);
			test.info("Validating presence of abount link..");
			Assert.assertTrue(abtdisplay, "about link is not displayed");
			test.pass("about link is present");
			// To validate presence of terms and conditions link
			boolean termsdisplay = elementPresenceCheck(obj.terms);
			test.info("Validating presence of terms link..");
			Assert.assertTrue(termsdisplay, "terms and conditons link is not displayed");
			test.pass("terms link is present");
			// To validate presence of privacy link
			boolean privacydisplay = elementPresenceCheck(obj.privacy);
			test.info("Validating presence of privacy link..");
			Assert.assertTrue(privacydisplay, "privacy link is not displayed");
			test.pass("privacy link is present");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test(priority = 1, description = "Validation of Clickability of Elements ", dependsOnMethods = "testPresence")
	public void testClickable() {
		try {
			// creating test to log in extent reporting
			test = extent.createTest("Validation of clickability of elements");
			// To validate clickability of input textbox
			boolean txtboxclick = elementClickableCheck(obj.inputfield);
			test.info("Validating clickability of input text box..");
			Assert.assertTrue(txtboxclick, "text box is not clickable");
			test.pass("text box is clickable");
			// To validate clickability of calculate button
			boolean calbtnclick = elementClickableCheck(obj.calculate);
			test.info("Validating clickability of calculate button..");
			Assert.assertTrue(calbtnclick, "calculate button is not clickable");
			test.pass("calculate button is clickable");
			// To validate clickability of about link
			boolean abtclick = elementClickableCheck(obj.about);
			test.info("Validating clickability of about link..");
			Assert.assertTrue(abtclick, "about link is not clickable");
			test.pass("about link is clickable");
			// To validate clickability of terms and conditions link
			boolean termsclick = elementClickableCheck(obj.terms);
			test.info("Validating clickability of terms link..");
			Assert.assertTrue(termsclick, "terms and conditons link is not clickable");
			test.pass("terms link is clickable");
			// To validate clickability of privacy link
			boolean privacyclick = elementClickableCheck(obj.privacy);
			test.info("Validating clickability of privacy link..");
			Assert.assertTrue(privacyclick, "privacy link is not clickable");
			test.pass("privacy link is clickable");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
