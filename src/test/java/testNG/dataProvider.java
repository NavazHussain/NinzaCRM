package testNG;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {

	@Test(dataProvider = "details")
	public void login(String un, String pwd)
	{
	Reporter.log(un+" : "+pwd, true);
	//System.out.println(un+" : "+pwd);
	}
	
	
	@DataProvider
	public Object[][] details()
	{
		Object[][] obj = new Object[3][2];
		obj[0][0]="Dhoni";
		obj[0][1]="D007";
		obj[1][0]="Virat";
		obj[1][1]="V018";
		obj[2][0]="Rohit";
		obj[2][1]="R045";
		
		return obj;
	}
}
