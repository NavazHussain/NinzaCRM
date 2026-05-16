package Implementation;


import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertiesFileUtility;
import GenericUtilities.WebdriverUtility;
import pom.CreateCampaignPage;
import pom.HomePage;
import pom.LoginPage;

public class CreateCampaigni {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		PropertiesFileUtility puti = new PropertiesFileUtility();
		ExcelUtility eutil = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebdriverUtility wutil = new WebdriverUtility();
		
		String BROWSER = puti.togetDataFromPropertiesFile("Browser");
		String URL = puti.togetDataFromPropertiesFile("Url");
		String USERNAME = puti.togetDataFromPropertiesFile("UserName");
		String PASSWORD = puti.togetDataFromPropertiesFile("Password");
		
		String campname = eutil.toreadDatafromExcelFile("campaign", 1, 1);
		String target = eutil.toreadDatafromExcelFile("campaign", 1, 3);
		
		// cross browsing testing
		WebDriver driver=null;
		
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
		
		//Launch browser
		driver.manage().window().maximize();
		wutil.waitForPageToLoad(driver);
		driver.get(URL);
		
		//Login
		LoginPage lp = new LoginPage(driver);
		/*driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();*/
		
		lp.getUN().sendKeys(USERNAME);
		lp.getPW().sendKeys(PASSWORD);
		lp.getLoginBtn().click();
		
		//Create campaign
		
		/*driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campname);
		WebElement tsize = driver.findElement(By.name("targetSize"));
		tsize.clear();
		tsize.sendKeys(target);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();*/
		
		CreateCampaignPage cpp = new CreateCampaignPage(driver);
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
		
		//driver.findElement(By.xpath("//button[@aria-label='close']"));
		
		//mouse hover on icon 
		//WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		/*Actions act = new Actions(driver);
		act.moveToElement(icon).click().perform();*/
		
		//wutil.mouseHoverOnWebElement(driver, icon);
		
		//click on logout
		/*WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		wutil.clickOnWebElement(driver,logout);*/
		
		//logout
		HomePage hp = new HomePage(driver);
		WebElement icon = hp.getUserIcon();
		wutil.mouseHoverOnWebElement(driver, icon);
		hp.getLogOutBtn().click();
			
		Thread.sleep(2000);
		driver.quit();
	}

}
