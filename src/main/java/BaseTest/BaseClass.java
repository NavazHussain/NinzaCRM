package BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import GenericUtilities.PropertiesFileUtility;
import GenericUtilities.WebdriverUtility;
import pom.HomePage;
import pom.LoginPage;

public class BaseClass 
{
	public WebDriver driver=null;
	public static WebDriver sdriver=null; //listener
	public PropertiesFileUtility puti = new PropertiesFileUtility();
	public WebdriverUtility wutil = new WebdriverUtility();
	
 @BeforeSuite(groups = {"smoke","regression"})
 public void beforeSuit()
 {
	 Reporter.log("DB open",true);
 }
 
 @Parameters("BROWSER")
 @BeforeClass(groups = {"smoke","regression"})
 public void beforeClass(String BROWSER) throws IOException
 {
	// String BROWSER = puti.togetDataFromPropertiesFile("Browser");
		
		if(BROWSER.equals("Chrome"))
		{
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(settings);
		}
		else if(BROWSER.equals("Edge"))
		{
			driver = new EdgeDriver();
		}
		else if(BROWSER.equals("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		sdriver=driver;
		 Reporter.log("browser Open",true);
 }
 
 @BeforeMethod(groups = {"smoke","regression"})
 public void beforeMethod() throws IOException
 {
	String URL = puti.togetDataFromPropertiesFile("Url");
	String USERNAME = puti.togetDataFromPropertiesFile("UserName");
	String PASSWORD = puti.togetDataFromPropertiesFile("Password");
	
	driver.manage().window().maximize();
	wutil.waitForPageToLoad(driver);
	driver.get(URL); 
 
	//login
	LoginPage lp = new LoginPage(driver);
	lp.getUN().sendKeys(USERNAME);
	lp.getPW().sendKeys(PASSWORD);
	lp.getLoginBtn().click();
	Reporter.log("login",true);
 }

 @AfterMethod(groups = {"smoke","regression"})
 public void afterMethod()
 {
	HomePage hp = new HomePage(driver);
	WebElement icon = hp.getUserIcon();
	wutil.mouseHoverOnWebElement(driver, icon);
	hp.getLogOutBtn().click();
	Reporter.log("logout",true);
 }
 
 @AfterClass(groups = {"smoke","regression"})
 public void afterClass()
 {
	 driver.quit();
	 Reporter.log("browser Close",true);
 }

 @AfterSuite(groups = {"smoke","regression"})
 public void afterSuite()
 {
	 Reporter.log("DB close",true);
 }
 
}

	
	
	
	
	
	
	
	
	
