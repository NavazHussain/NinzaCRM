package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage {

	public CreateCampaignPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getCampaignName() {
		return CampaignName;
	}

	public WebElement getCampaignStatus() {
		return CampaignStatus;
	}

	public WebElement getTargetSize() {
		return TargetSize;
	}

	public WebElement getExpectedCloseDate() {
		return ExpectedCloseDate;
	}

	public WebElement getDescription() {
		return Description;
	}

	public WebElement getCreateCampaign() {
		return CreateCampaign;
	}
	
	public WebElement getCreateCampaignSubmitBtn() {
		return CreateCampaignSubmitBtn;
	}
	
	@FindBy(name = "campaignName")
	private WebElement CampaignName;
	
	@FindBy(name = "campaignStatus")
	private WebElement CampaignStatus;
	
	@FindBy(name = "targetSize")
	private WebElement TargetSize;
	
	@FindBy(name = "expectedCloseDate")
	private WebElement ExpectedCloseDate;
	
	@FindBy(name = "description")
	private WebElement Description;
	
	@FindBy(xpath = "//span[text()='Create Campaign']")
	private WebElement CreateCampaign;
	
	@FindBy(xpath = "//button[text()='Create Campaign']")
	private WebElement CreateCampaignSubmitBtn;

	

	
}
