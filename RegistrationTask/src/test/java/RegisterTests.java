import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegisterTests extends BaseTest {

	String emailText = randomAlphabetic(8) + "@blabla.com";
	By goToRegister = cssSelector(".ico-register");
	By genderFemale = id("gender-female");
	By firstName = id("FirstName");
	By lastName = id("LastName");
	By email = id("Email");
	By password = id("Password");
	By confirmPassword = id("ConfirmPassword");
	By registerButton = id("register-button");
	By successRegistrationText = cssSelector(".page-body");
	By logoutButton = cssSelector(".ico-logout");
	By login = cssSelector(".ico-login");
	By loginButton = cssSelector(".login-button");
	By continueButton = cssSelector(".register-continue-button");

	@Test
	public void registerTest() {

		//Register
		getElement(goToRegister).click();

		clickOnElement(genderFemale);
		typeInput(firstName, "Mara");
		typeInput(lastName, "Maric");
		typeInput(email, emailText);
		typeInput(password, "blabla1");
		typeInput(confirmPassword, "blabla1");

		clickOnElement(registerButton);

		String actualText = getElement(successRegistrationText).getText();
		String expectedText = "Your registration completed";
		assertEquals(actualText, expectedText, "Text is not matching.");

		WebElement continueBtn = getElement(continueButton);
		assertTrue(continueBtn.isDisplayed(), "Continue button is not present");

		WebElement loggedInUser = driver.findElement(By.cssSelector(".header-links .account"));
		String actualLoggedInUser = loggedInUser.getText();
		String expectedLoggedInUser = emailText;
		assertEquals(actualLoggedInUser, expectedLoggedInUser, "Users are not matching");

		clickOnElement(logoutButton);

		// Login
		clickOnElement(login);

		typeInput(email, emailText);
		typeInput(password,"blabla1");

		clickOnElement(loginButton);
		assertEquals(actualLoggedInUser, expectedLoggedInUser, "Users are not matching");
	}
}