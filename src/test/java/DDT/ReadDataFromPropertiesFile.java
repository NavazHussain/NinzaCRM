package DDT;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {
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
	
	
	}

}
