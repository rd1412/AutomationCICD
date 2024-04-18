package seleniumTutorial.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartItems {

	WebDriver driver;

	public CartItems(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkout;

	public boolean verifyProductDisplay(String productName) {
		boolean match = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return match;
	}

	public List<WebElement> getCartItems() {
		return cartProducts;
	}

	public CheckOutPage checkOut() {
		checkout.click();
		CheckOutPage checkout = new CheckOutPage(driver);
		return checkout;
	}

}
