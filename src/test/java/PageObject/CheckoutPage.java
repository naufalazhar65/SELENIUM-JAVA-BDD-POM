package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void userLogin() {
		String baseUrl = "https://www.saucedemo.com/";
		driver.get(baseUrl);

		WebElement usernameField = driver.findElement(By.id("user-name"));
		usernameField.sendKeys("standard_user");
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys("secret_sauce");
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
	}

	public void clickAddToCartButton() {
		WebElement addToCartButton = driver.findElement(By.cssSelector(".inventory_item button"));
		addToCartButton.click();
	}

	public void clickCartButton() {
		WebElement cartButton = driver.findElement(By.cssSelector(".shopping_cart_container"));
		cartButton.click();
	}

	public void clickCheckoutButton() {
		WebElement checkoutButton = driver.findElement(By.cssSelector(".checkout_button"));
		checkoutButton.click();
	}

	public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
		WebElement firstNameField = driver.findElement(By.id("first-name"));
		WebElement lastNameField = driver.findElement(By.id("last-name"));
		WebElement postalCodeField = driver.findElement(By.id("postal-code"));

		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		postalCodeField.sendKeys(postalCode);
	}

	public void clickContinueButton() {
		WebElement continueButton = driver.findElement(By.cssSelector(".checkout_buttons .btn_primary.cart_button"));
		continueButton.click();
	}

	public void clickFinishButton() {
		WebElement finishButton = driver.findElement(By.id("finish"));
		finishButton.click();
	}

	public String getCheckoutCompleteMessage() {
		WebElement completeMessage = driver.findElement(By.cssSelector(".complete-header"));
		return completeMessage.getText();
	}

	public String getErrorMessage() {
		WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container"));
		return errorMessage.getText();
	}

}
