package factorial;

import java.io.FileInputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class NegativeValidations extends TestBase {
	POMMain obj = new POMMain();
	SoftAssert as = new SoftAssert();

	@Test(description = "Factorial check for negative values-error message and application crash validations")
	public void negativeFactCheck() throws BiffException, IOException, InterruptedException {

		try {
			// creating test to log in extent reporting
			test = extent.createTest("Validation of factorial check of negative values");
			FileInputStream f = new FileInputStream("D:\\AutomationTesting\\Factorialvalues.xls");
			Workbook wb = Workbook.getWorkbook(f);
			Sheet s = wb.getSheet("negativevalues");
			int rowCount = s.getRows();
			for (int i = 0; i < rowCount; i++) {
				// To enter the value in text box
				String inputvalue=s.getCell(0, i).getContents();
				driver.findElement(obj.inputfield).sendKeys(inputvalue);
				// To click on calculate button
				driver.findElement(obj.calculate).click();
				// Wait for result value to be displayed
				waitForElement("visibilityofelementlocated", obj.result);
				Thread.sleep(2000);
				// Extract the result
				String res = driver.findElement(obj.result).getText();
				// System.out.println(res);
				// Validation of error message displayed
				as.assertEquals(res, "Please enter an integer",
						"No error message displayed or incorrect error message");
				test.pass("error message for"+inputvalue+" factorial has been displayed as"+res+"");
				// page crash check
				boolean ch = pageCrashCheck();
				as.assertEquals(ch, false, "Application has been crashed");
				test.pass("application has not been crashed for"+inputvalue+" factorial");
				as.assertAll();
				// Refresh the page before entering the next value in the loop
				driver.navigate().refresh();

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
