package seleniumTutorial.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumTutorial.TestComponents.BaseTest;
import seleniumTutorial.pageObjects.CartItems;
import seleniumTutorial.pageObjects.CheckOutPage;
import seleniumTutorial.pageObjects.LandingPage;
import seleniumTutorial.pageObjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingpage;
	public ProductCatalogue catalogue;
	public CheckOutPage checkout;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingpage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_usename_and_password(String username, String password) {

		catalogue = page.loginApplication(username, password);
	}

	@When("^Select the product (.+) and add to cart$")
	public void Select_the_product_and_add_to_cart(String product) {
		List<WebElement> products = catalogue.getProductList();
		catalogue.addProductToCart(product);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String product) {
		CartItems item = catalogue.goToCart();

		boolean match = item.verifyProductDisplay(product);
		Assert.assertTrue(match);
		checkout = item.checkOut();

		checkout.selectCountry("ind");
		checkout.placeOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String orderMessage = checkout.orderConfirmationMessage();
		Assert.assertEquals(orderMessage, string);
		driver.close();
	}

	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string) {

		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.close();
	}


}
