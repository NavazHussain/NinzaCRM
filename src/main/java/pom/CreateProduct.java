package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProduct {

	public CreateProduct(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getAddProdBtn() {
		return addProdBtn;
	}

	public WebElement getProdName() {
		return prodName;
	}

	public WebElement getProdCategory() {
		return prodCategory;
	}

	public WebElement getProdquantity() {
		return prodquantity;
	}

	public WebElement getPricePerUnit() {
		return pricePerUnit;
	}

	public WebElement getVendorId() {
		return vendorId;
	}

	public WebElement getAddProdSubmitBtn() {
		return addProdSubmitBtn;
	}
	@FindBy(xpath = "//span[text()='Add Product']")
	private WebElement addProdBtn;
	
	@FindBy(name="productName")
	private WebElement prodName;
	
	@FindBy(name="productCategory")
	private WebElement prodCategory;
	
	@FindBy(name="quantity")
	private WebElement prodquantity;
	
	@FindBy(name="price")
	private WebElement pricePerUnit;
	
	@FindBy(name="vendorId")
	private WebElement vendorId;
	
	@FindBy(xpath ="//button[text()='Add']")
	private WebElement addProdSubmitBtn;
}
