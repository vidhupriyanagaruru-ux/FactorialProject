package factorial;

import org.openqa.selenium.By;

public class POMMain {
	
	//Locator of input text box
	By inputfield= By.id("number");
	//Locator of calculate button
	By calculate=By.xpath("//button[@type='submit']");
	//Locator of About link
	By about= By.xpath("//a[text()='About']");
	//Locator of terms and conditions link
	By terms=By.xpath("//a[text()='Terms and Conditions']");
	//Locator of privacy link
	By privacy=By.xpath("//a[text()='Privacy']");
	//Locator of result on calculation
	By result=By.id("resultDiv");

}
