package seleniumTutorial.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumTutorial.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest {

	@Test
	public void submitEcommerce() throws IOException
	{
		String emailId = "johndoe12@gmail.com";
		String password = "Johndoe123"; // wrong password

		page.loginApplication(emailId, password);
		page.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", page.getErrorMessage());

	}
}

