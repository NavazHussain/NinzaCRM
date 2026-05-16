package testCases;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProductWithMandatoryFields {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		
		//Launch browser
		WebDriver driver=new ChromeDriver(settings);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://49.249.28.218:8098/");
		
		//Login
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//Create product
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("productName")).sendKeys("GameBoyOne");
		Thread.sleep(1000);
		
		//drop down
		WebElement categorydd = driver.findElement(By.name("productCategory"));
		Select dd = new Select(categorydd);
		dd.selectByContainsVisibleText("Furniture");
		Thread.sleep(1000);
		
		WebElement quantity = driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys("100");
		Thread.sleep(1000);
		
		WebElement ppunit = driver.findElement(By.name("price"));
		ppunit.clear();
		ppunit.sendKeys("100");
		Thread.sleep(1000);
		
		WebElement vendor = driver.findElement(By.name("vendorId"));
		Select vd = new Select(vendor);
		vd.selectByIndex(1);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(2500);
		
		//validate Toast msg
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		
		String msg = toastmsg.getText();
		if(msg.contains("GameBoyOne"))
		{
			System.out.println("product is created");
		}	
		else
		{
			System.out.println("product not created");
		}
		
		//driver.findElement(By.xpath("//button[@aria-label='close']"));
		
		//mouse hover on icon 
		/*WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act = new Actions(driver);
		act.moveToElement(icon).click().perform();*/
		
		//click on logout
		/*driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
		Thread.sleep(2000);
		driver.quit();*/
	}

}
