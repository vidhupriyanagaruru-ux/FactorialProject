package factorial;

import java.io.FileInputStream;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class BoundaryValueAnalysis extends TestBase {
	POMMain obj = new POMMain();

	@Test(description = "Factorial check for boundary values")
	public void boundaryFactCheck() throws BiffException, IOException, InterruptedException {
		try {
			// creating test to log in extent reporting
			test = extent.createTest("Validation of factorial check of boundary values");
			FileInputStream f = new FileInputStream("D:\\AutomationTesting\\Factorialvalues.xls");
			Workbook wb = Workbook.getWorkbook(f);
			Sheet s = wb.getSheet("boundaryvalues");
			int rowCount = s.getRows();
			for (int i = 0; i < rowCount; i++) {
				String inputvalue=s.getCell(0, i).getContents();
				// To enter the value in text box
				driver.findElement(obj.inputfield).sendKeys(inputvalue);
				// To click on calculate button
				driver.findElement(obj.calculate).click();
				// Wait for result value to be displayed
				waitForElement("visibilityofelementlocated", obj.result);
				Thread.sleep(2000);
				// Extract the result
				String res = driver.findElement(obj.result).getText();
				// System.out.println("The result is"+res);
				// validate the factorial value displayed
				int n = Integer.parseInt(inputvalue);
				String expected_factvalue = "The factorial of " + n + " is: " + factValue(n) + "";
				// System.out.println("Expected factorial value is"+expected_factvalue);
				Assert.assertEquals(res, expected_factvalue, "Incorrect factorial value");
				test.pass("correct factorial for"+inputvalue+" has been displayed which is as"+res+"");
				// Refresh the page before entering the next value in the loop
				driver.navigate().refresh();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
