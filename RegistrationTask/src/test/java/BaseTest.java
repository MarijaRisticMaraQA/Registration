import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setUpDriver() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
	}

	protected WebElement getElement(By locator) {

		return driver.findElement(locator);
	}

	protected void typeInput(By locator, String text) {

		getElement(locator).sendKeys(text);
	}

	protected void clickOnElement(By locator) {

		driver.findElement(locator).click();
	}

	@AfterTest
	public void closeDriver() {

		driver.quit();
	}

}
