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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertiesFileUtility;
import GenericUtilities.WebdriverUtility;
import pom.HomePage;

public class CreateProduct {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		
		PropertiesFileUtility puti = new PropertiesFileUtility();
		ExcelUtility eutil = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebdriverUtility wutil = new WebdriverUtility();
		
		String BROWSER = puti.togetDataFromPropertiesFile("Browser");
		String URL = puti.togetDataFromPropertiesFile("Url");
		String USERNAME = puti.togetDataFromPropertiesFile("UserName");
		String PASSWORD = puti.togetDataFromPropertiesFile("Password");
		
		//Launch browser
		WebDriver driver = null;
		if (BROWSER.equals("Edge")) {
		driver = new EdgeDriver();
		} else if (BROWSER.equals("Chrome")) {
		driver = new ChromeDriver();
		} else if (BROWSER.equals("Firefox")) {
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
		
			String productname = eutil.toreadDatafromExcelFile("product", 1, 0);
			String quantity = eutil.toreadDatafromExcelFile("product", 1, 2);
			String price = eutil.toreadDatafromExcelFile("product", 1, 3);
			
		pom.CreateProduct cp = new pom.CreateProduct(driver);
		
		//Create product
		
		driver.findElement(By.linkText("Products")).click();
		cp.getAddProdBtn().click();
		cp.getProdName().sendKeys(productname);
		
		//drop down category
		/*WebElement categorydd = driver.findElement(By.name("productCategory"));
		Select dd = new Select(categorydd);
		dd.selectByContainsVisibleText("Furniture");
		Thread.sleep(1000);*/
		
		WebElement category = cp.getProdCategory();
		Select dd = new Select(category);
		dd.selectByContainsVisibleText("Furniture");
		Thread.sleep(1000);
		
		//quantity
		/*WebElement quantity = driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys("100");
		Thread.sleep(1000);*/
		
		WebElement qty = cp.getProdquantity();
		qty.clear();
		qty.sendKeys(quantity);
		
		//ppunit
		/*WebElement ppunit = driver.findElement(By.name("price"));
		ppunit.clear();
		ppunit.sendKeys("100");
		Thread.sleep(1000);*/
		
		WebElement ppunit = cp.getPricePerUnit();
		ppunit.clear();
		ppunit.sendKeys(price);
		
		/*WebElement vendor = driver.findElement(By.name("vendorId"));
		Select vd = new Select(vendor);
		vd.selectByIndex(1);
		Thread.sleep(1000);*/
		
		WebElement vendor = cp.getVendorId();
		Select vd = new Select(vendor);
		vd.selectByIndex(2);
		
		//click add button
		/*driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(2500);*/
		
		cp.getAddProdSubmitBtn().submit();
		
		//validate Toast msg
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		
		String msg = toastmsg.getText();
		if(msg.contains(productname))
		{
			System.out.println("product is created");
		}	
		else
		{
			System.out.println("product not created");
		}
		
		//driver.findElement(By.xpath("//button[@aria-label='close']"));
		
		//mouse hover on icon 
		//WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		/*Actions act = new Actions(driver);
		act.moveToElement(icon).click().perform();*/
		
		//click on logout
		//driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
		
		//logout
		HomePage hp = new HomePage(driver);
		WebElement icon = hp.getUserIcon();
		wutil.mouseHoverOnWebElement(driver, icon);
		hp.getLogOutBtn().click();
		
		Thread.sleep(2000);
		driver.quit();
		
		
	}


}
