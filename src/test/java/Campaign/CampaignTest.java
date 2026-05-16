package Campaign;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import BaseTest.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertiesFileUtility;
import GenericUtilities.WebdriverUtility;
import pom.CreateCampaignPage;
import pom.HomePage;
import pom.LoginPage;
import testCases.CreateCampaign;

@Listeners(ListenerUtility.ListenerImplementation.class)
public class CampaignTest extends BaseClass {

	@Test(groups = "smoke")
	public void toCreateCampaignWithExpDateTest() throws EncryptedDocumentException, IOException, InterruptedException
	{

		ExcelUtility eutil = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
	
		HomePage hp = new HomePage(driver);
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		
		
		String campname = eutil.toreadDatafromExcelFile("campaign", 1, 1);
		String target = eutil.toreadDatafromExcelFile("campaign", 1, 3);
		String campstatus = eutil.toreadDatafromExcelFile("campaign", 1, 2);
		
		// open browser	
		//Launch browser	
		//Login	
		//Expected date
		
		String daterequired = jutil.togetRequiredDate(30);
		
		//Create campaign
		hp.getCreatecampaignBtn().click();
		//driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campname);
		WebElement tsize = driver.findElement(By.name("targetSize"));
		tsize.clear();
		tsize.sendKeys(target);
		driver.findElement(By.name("campaignStatus")).sendKeys(campstatus);
		
		WebElement expectCloseDate = driver.findElement(By.name("expectedCloseDate"));
		wutil.passInput(driver, expectCloseDate, daterequired);
		Thread.sleep(2500);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		//validate Toast msg
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname));
		
		/*if(msg.contains(campname))
		{
			System.out.println("campaign is created");
		}	
		else
		{
			System.out.println("campaign not created");
		}*/
			
		//logout
		
		Thread.sleep(2000);
		driver.quit();
	}

	@Test(groups = "regression")
	public void toCreateCampaignWithMandatoryFieldTest() throws InterruptedException, EncryptedDocumentException, IOException
	{
		ExcelUtility eutil = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		
		HomePage hp = new HomePage(driver);
		CreateCampaignPage cpp = new CreateCampaignPage(driver);
		hp.getCreatecampaignBtn().click();
		
		String campname = eutil.toreadDatafromExcelFile("campaign", 1, 1);
		String target = eutil.toreadDatafromExcelFile("campaign", 1, 3);
		
		// launch a browser		
		//Login	
		
		//Create campaign	
		cpp.getCreateCampaign().click();
		cpp.getCampaignName().sendKeys(campname);
		WebElement ts = cpp.getTargetSize();
		ts.clear();
		ts.sendKeys(target);
		
		Thread.sleep(1500);
		//cpp.getDescription().sendKeys(desc);
		cpp.getCreateCampaignSubmitBtn().click();
		
		//validate Toast msg
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		
		String msg = toastmsg.getText();
		if(msg.contains(campname))
		{
			System.out.println("campaign is created");
		}	
		else
		{
			System.out.println("campaign not created");
		}
				
		//logout
		
		Thread.sleep(2000);
		driver.quit();
	}

}

	

