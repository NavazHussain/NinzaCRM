package Assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HAssertion {

	@Test
	public void demo()
	{
		 String expectedTitle = "Facebook – log in or sign up";

	        WebDriver driver = new ChromeDriver();
	        driver.get("https://www.facebook.com/");
	        String actualTitle = driver.getTitle();

	        Assert.assertEquals(expectedTitle, actualTitle);
	        System.out.println("step1");
	        System.out.println("step2");
	}
}
