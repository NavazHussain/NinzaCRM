package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignWithExpectedDate {

	public static void main(String[] args) throws InterruptedException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.Properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String USERNAME = prop.getProperty("UserName");
		String PASSWORD = prop.getProperty("Password");
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		
		//Login
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//Expected date
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30); // 8-4-2026 + 30
		String datereq = sim.format(cal.getTime());
		
		//Create campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("NinjaThree");
		WebElement tsize = driver.findElement(By.name("targetSize"));
		tsize.clear();
		tsize.sendKeys("100");
		driver.findElement(By.name("campaignStatus")).sendKeys("Active");
		driver.findElement(By.name("expectedCloseDate")).sendKeys(datereq);
		Thread.sleep(2500);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		//validate Toast msg
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		
		String msg = toastmsg.getText();
		if(msg.contains("NinjaThree"))
		{
			System.out.println("campaign is created");
		}	
		else
		{
			System.out.println("campaign not created");
		}
		
		//driver.findElement(By.xpath("//button[@aria-label='close']"));
		
		//mouse hover on icon 
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act = new Actions(driver);
		act.moveToElement(icon).click().perform();
		
		//click on logout
		driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
		Thread.sleep(2000);
		driver.quit();
	}

}

