package seleniumTutorial.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumTutorial.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage page;

	public WebDriver initializeDriver() throws IOException {

		/*
		 * Properties Class -> using properties class we can parse the
		 * GlobalData.properties File and extract all global parameter values
		 */

		Properties prop = new Properties();
		FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//seleniumTutorial//resources//GlobalData.properties");
		prop.load(inputStream);

		// getting browser variable from maven terminal
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			// FireFox
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}

	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}

	public List<HashMap<String, String>> getJsonData(String filpath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filpath), StandardCharsets.UTF_8);

		// String to HashMap - Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		page = new LandingPage(driver);
		page.goTo();
		return page;
	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		driver.close();
	}
}
