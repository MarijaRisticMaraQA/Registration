package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Main {

	static WebDriver driver;

	public static WebElement getElement(By locator) {

		return driver.findElement(locator);
	}

	public static void typeInput(By locator, String text) {

		getElement(locator).sendKeys(text);
	}

	public static void clickOnElement(By locator) {

		driver.findElement(locator).click();
	}

	public static void main(String[] args) {

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

		driver = new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");

		// Registration
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

		WebElement continueButton = driver.findElement(By.cssSelector(".register-continue-button"));
		assertTrue(continueButton.isDisplayed(), "Continue button is not present");

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