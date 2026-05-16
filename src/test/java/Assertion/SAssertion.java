package Assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SAssertion {

	@Test
	public void demo()
	{
		 String expectedTitle = "Facebook – log in or sign up";

	        WebDriver driver = new ChromeDriver();
	        driver.get("https://www.facebook.com/");
	        String actualTitle = driver.getTitle();
	        
	        SoftAssert soft = new SoftAssert();
	        soft.assertEquals(actualTitle, expectedTitle);
	        System.out.println("step1");
	        System.out.println("step2");
	        soft.assertAll();
	}
}
