package seleniumTutorial.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Ecommerce {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		String emailId = "johndoe12@gmail.com";
		String password = "Johndoe@123";

		LandingPage page = new LandingPage(driver);
		page.goTo();
		// page.loginApplication(emailId, password);
		ProductCatalogue catalogue = page.loginApplication(emailId, password);
		// ProductCatalogue pc = new ProductCatalogue(driver);
		List<WebElement> products = catalogue.getProductList();
		String productName = "ZARA COAT 3";
		catalogue.addProductToCart(productName);
		CartItems item = catalogue.goToCart();

		// CartItems item = new CartItems(driver);
		boolean match = item.verifyProductDisplay(productName);

		Assert.assertTrue(match);
		CheckOutPage checkout = item.checkOut();

		// CheckOutPage checkout = new CheckOutPage(driver);
		String country = "ind";
		checkout.selectCountry(country);
		checkout.placeOrder();
		String orderMessage = checkout.orderConfirmationMessage();

		Assert.assertEquals(orderMessage, "THANKYOU FOR THE ORDER.");
		driver.close();
	}
}
