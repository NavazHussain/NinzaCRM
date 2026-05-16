package testNG;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo1 {

	@Test
	public void a10()
	{
		Reporter.log("a10", true);
	}
	@Test
	public void a11()
	{
		Reporter.log("a11", true);
	}
	@Test
	public void a20()
	{
		Reporter.log("a20", true);
	}
	@Test
	public void a9()
	{
		Reporter.log("a9", true);
	}
	
}
