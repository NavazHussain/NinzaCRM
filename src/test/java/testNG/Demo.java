package testNG;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo {

	@Test(priority = 2)
	public void add()
	{
		Reporter.log("add", true);
	}
	
	@Test(priority = 1)
	public void sub()
	{
		Reporter.log("sub", true);
	}
	@Test(priority = -1)
	public void mul()
	{
		Reporter.log("mul", true);
	}
	
}
