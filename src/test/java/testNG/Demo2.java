package testNG;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo2 {

	@Test
	public void amazon()
	{
		Reporter.log("amazon", true);
	}
	
	@Test
	public void bigbasket()
	{
		Reporter.log("bigbasket", true);
	}
	
	@Test
	public void baskinrobbin()
	{
		Reporter.log("baskinrobbin", true);
	}
	
	@Test
	public void cricbuzz()
	{
		Reporter.log("cricbuzz", true);
	}
}
