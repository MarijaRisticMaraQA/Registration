import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Cart extends BaseTest {

	By booksSection = By.cssSelector(".top-menu > li:nth-child(1) > a[href*='books']");
	By notebookInDropdown = By.cssSelector(".top-menu .sublist.firstLevel > li:nth-child(2) > a[href*='notebooks']");
	By addToCartButtonBookOne = By.cssSelector("div[data-productid='13'] > div:nth-child(2) > div:nth-child(4) > div:nth-child(2) > input");
	By addToCartButtonBookTwo = By.cssSelector("div[data-productid='45'] > div:nth-child(2) > div:nth-child(4) > div:nth-child(2) > input");
	By addToCartButtonNotebook = By.cssSelector("div[data-productid='31'] > div:nth-child(2) > div:nth-child(4) > div:nth-child(2) > input");
	By topCart = By.cssSelector("li[id='topcartlink'] .ico-cart");
	By priceOne = By.xpath("//tr[@class='cart-item-row'][1]//td[@class='subtotal nobr end']//span[2]");
	By priceTwo = By.xpath("//tr[@class='cart-item-row'][2]//td[@class='subtotal nobr end']//span[2]");
	By priceThree = By.xpath("//tr[@class='cart-item-row'][3]//td[@class='subtotal nobr end']//span[2]");
	By totalPrice = By.cssSelector(".order-total");

	@Test
	public void shoppingManiaTest() throws InterruptedException {

		addBooksToCart();
		Thread.sleep(1000);
		addNotebookToCart();
		Thread.sleep(1000);
		clickOnElement(topCart);

		double pricesSum = sumPrices();
		double shockTotalPrice = Double.parseDouble(getElement(totalPrice).getText());

		assertEquals(pricesSum, shockTotalPrice, "Price sum is not matching");
	}

	public void addBooksToCart() throws InterruptedException {

		clickOnElement(booksSection);
		Thread.sleep(1000);
		clickOnElement(addToCartButtonBookOne);
		Thread.sleep(1000);
		clickOnElement(addToCartButtonBookTwo);
	}

	public void addNotebookToCart() throws InterruptedException {

		WebElement computerSectionDropdown = driver.findElement(By.cssSelector(".top-menu > li:nth-child(2) > a[href*='computers']"));
		Actions action = new Actions(driver);

		action.moveToElement(computerSectionDropdown).perform();
		clickOnElement(notebookInDropdown);
		Thread.sleep(1000);

		clickOnElement(addToCartButtonNotebook);
	}

	public double sumPrices() {

		double firstPrice = Double.parseDouble(getElement(priceOne).getText());
		double secondPrice = Double.parseDouble(getElement(priceTwo).getText());
		double thirdPrice = Double.parseDouble(getElement(priceThree).getText());

		return firstPrice + secondPrice + thirdPrice;
	}
}
