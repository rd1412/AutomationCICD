package seleniumTutorial.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumTutorial.TestComponents.BaseTest;
import seleniumTutorial.pageObjects.CartItems;
import seleniumTutorial.pageObjects.CheckOutPage;
import seleniumTutorial.pageObjects.OrdersPage;
import seleniumTutorial.pageObjects.ProductCatalogue;

public class Ecommerce extends BaseTest {

	String email = "johndoe12@gmail.com";
	String passWord = "Johndoe@123";
	String product = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	// public void submitEcommerce(String emailId, String password, String
	// productName) throws IOException
	public void submitEcommerce(HashMap<String, String> input) throws IOException

	{
		ProductCatalogue catalogue = page.loginApplication(input.get("email"), input.get("passWord"));

		// ProductCatalogue pc = new ProductCatalogue(driver);
		List<WebElement> products = catalogue.getProductList();
		catalogue.addProductToCart(input.get("product"));
		CartItems item = catalogue.goToCart();

		// CartItems item = new CartItems(driver);
		boolean match = item.verifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);
		CheckOutPage checkout = item.checkOut();

		// CheckOutPage checkout = new CheckOutPage(driver);
		String country = "ind";
		checkout.selectCountry(country);
		checkout.placeOrder();
		String orderMessage = checkout.orderConfirmationMessage();

		Assert.assertEquals(orderMessage, "THANKYOU FOR THE ORDER.");
	}

	@Test(dependsOnMethods = "submitEcommerce")
	public void orderHistory() {

		ProductCatalogue catalogue = page.loginApplication(email, passWord);
		OrdersPage order = catalogue.goToOrders();
		boolean match = order.verifyOrdersDisplay(product);
		Assert.assertTrue(match);
	}
	


	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//test//java//seleniumTutorial//data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	// @DataProvider public Object[][] getData() { return new Object[][] { {
	// "johndoe12@gmail.com", "Johndoe@123", "ZARA COAT 3" }, { "anshika@gmail.com",
	// "Iamking@000", "ADIDAS ORIGINAL" }, { "shetty@gmail.com", "Iamking@000",
	// "IPHONE 13 PRO" } }; }

	/*
	 * public Object[][] getData() throws IOException { HashMap<String, String> map
	 * = new HashMap<String, String>();
	 * 
	 * map.put("email", "johndoe12@gmail.com"); map.put("passWord", "Johndoe@123");
	 * map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * 
	 * map1.put("email", "anshika@gmail.com"); map1.put("passWord", "Iamking@000");
	 * map1.put("product", "ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] { { map }, { map1 } };
	 */

}
