package testNG;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo3 {

	@Test
	public void Apple()
	{
		Reporter.log("Apple", true);
	}
	@Test
	public void banana()
	{
		Reporter.log("banana", true);
	}
	@Test
	public void Pear()
	{
		Reporter.log("Pear", true);
	}
	@Test
	public void guava()
	{
		Reporter.log("guava", true);
	}
}
