package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void openLoginPage() {
		String baseUrl = "https://www.saucedemo.com/";
		driver.get(baseUrl);

	}

	public void verifyTitle() {
		String expectedTitle = "Swag Labs";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch");
	}

	public void enterUsername(String username) {
		WebElement usernameField = driver.findElement(By.id("user-name"));
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys(password);
	}

	public void clickLoginButton() {
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
	}

	public void getCurrentURL() {
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Page url mismatch");
	}

	public boolean isErrorDisplayed() {
		try {
			WebElement errorElement = driver.findElement(By.cssSelector("[data-test='error']"));
			return errorElement.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void clickLogoutButton() {
		WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
		menuButton.click();
		WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
		logoutButton.click();
	}

	public boolean isLoginButtonDisplayed() {
		try {
			WebElement loginButton = driver.findElement(By.id("login-button"));
			return loginButton.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean areLoginElementsDisplayed() {
		try {
			WebElement usernameField = driver.findElement(By.id("user-name"));
			WebElement passwordField = driver.findElement(By.id("password"));
			WebElement loginButton = driver.findElement(By.id("login-button"));

			return usernameField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
}
