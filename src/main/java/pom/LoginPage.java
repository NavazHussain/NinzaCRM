package pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
	 this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="username")
	private WebElement UN;
	
	@FindBy(id="inputPassword")
	 private WebElement PW;

	 @FindBy(xpath="//button[text()='Sign In']")
	 private WebElement loginBtn;
	 public WebDriver getDriver() {
		 return driver;
	 }

	 public WebElement getUN() {
		 return UN;
	 }

	 public WebElement getPW() {
		 return PW;
	 }

	 public WebElement getLoginBtn() {
		 return loginBtn;
	 }

	 public void Login(String url,String username,String password) {

		 driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.get(url);
		 UN.sendKeys(username);
		 PW.sendKeys(password);

		 }


}
