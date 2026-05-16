package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class InvocationCount {

	@Test(invocationCount = 3)
	public void login() throws InterruptedException
	{
	WebDriver driver = new ChromeDriver();
	Thread.sleep(1250);
	driver.quit();
	}
	
	@Test(invocationCount = 2)
	public void login1() throws InterruptedException
	{
	WebDriver driver = new ChromeDriver();
	Thread.sleep(1250);
	driver.quit();
	}
	
	
}
