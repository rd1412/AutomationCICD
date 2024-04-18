package seleniumTutorial.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumTutorial.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
		
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// PageFactory method is used to perform action using driver
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	@FindBy(id = "userEmail")
	WebElement email;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String emailId, String password) {
		email.sendKeys(emailId);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue catalogue = new ProductCatalogue(driver);
		return catalogue;
	}

	public String getErrorMessage() {
		waitforWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
