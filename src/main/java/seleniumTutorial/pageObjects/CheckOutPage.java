package seleniumTutorial.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumTutorial.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement inputCountry;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement country;
	
	@FindBy(css = "a[class*=submit]")
	WebElement placeOrder;

	@FindBy(css = ".hero-primary")
	WebElement message;

	By waitTime = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(inputCountry, countryName).build().perform();
		waitforElementToAppear(waitTime);
		country.click();
	}

	public void placeOrder() {
		placeOrder.click();
	}

	public String orderConfirmationMessage() {
		return message.getText();
	}
}
