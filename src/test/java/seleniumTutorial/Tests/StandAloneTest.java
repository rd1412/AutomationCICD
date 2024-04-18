package seleniumTutorial.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("johndoe12@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Johndoe@123");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartItems.stream().anyMatch(p -> p.getText().equalsIgnoreCase("ZARA COAT 3"));

		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		// selecting country and sending input "ind"
		// driver.findElement(By.cssSelector("[placeholder='Select Country']")).click();
		// driver.findElement(By.cssSelector("[placeholder='SelectCountry']")).sendKeys("ind");
		
		// selecting country and sending input "ind" using Actions class
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "ind").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		// Selecting India manually using Regular expression xpath
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

		driver.findElement(By.cssSelector("a[class*=submit]")).click();
		driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
		driver.close();
		}
}
