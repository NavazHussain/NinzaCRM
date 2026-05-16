package testNG;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Enabled {

	@Test
	public void a()
	{
		Reporter.log("a", true);
	}
	@Test(enabled = false)
	public void b()
	{
		Reporter.log("b", true);
	}
	@Test
	public void c()
	{
		Reporter.log("c", true);
	}
	@Test
	public void d()
	{
		Reporter.log("d", true);
	}
}
