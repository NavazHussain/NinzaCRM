package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest {
  @Test
  public void tc() {
	  System.out.println("TC");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("login");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("logout");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("browser open");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("browser close");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("pre cond");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("post cond");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("DB open");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("DB close");
  }

}
