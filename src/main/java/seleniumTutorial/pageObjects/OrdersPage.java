package seleniumTutorial.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import seleniumTutorial.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orders;

	public List<WebElement> ordersItems() {
		return orders;
	}

	public boolean verifyOrdersDisplay(String productName) {
		boolean match = ordersItems().stream().anyMatch(order -> order.getText().equalsIgnoreCase(productName));
		return match;
	}

}
