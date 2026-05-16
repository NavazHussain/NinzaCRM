package testNG;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class DependsOnMethod {

	@Test
	public void create()
	{
		Reporter.log("create", true);
	}
	@Test(dependsOnMethods = "create")
	public void edit()
	{
		Reporter.log("edit", true);
	}
	@Test(dependsOnMethods = {"create" , "edit"})
	public void delete()
	{
		Reporter.log("delete", true);
	}
	
}
