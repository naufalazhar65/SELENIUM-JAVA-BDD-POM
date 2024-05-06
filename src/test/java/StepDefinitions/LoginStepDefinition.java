package StepDefinitions;

import PageObject.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition {
	private WebDriver driver;
	private LoginPage loginPage;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/naufalazhar/.cache/selenium/chromedriver/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("disable-gpu");

		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().window().maximize();

		loginPage = new LoginPage(driver);
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	@Given("I am on the SauceDemo login page")
	public void iAmOnTheLoginPage() {
		loginPage.openLoginPage();
	}

	@When("I enter username {string} and password {string}")
	public void iEnterUsernameAndPassword(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@And("I click the login button")
	public void iClickTheLoginButton() {
		loginPage.clickLoginButton();
		loginPage.verifyTitle();
	}

	@Then("I should be on the inventory page")
	public void iShouldBeOnTheInventoryPage() {
		loginPage.getCurrentURL();
	}

	@Then("I should see an error message")
	public void iShouldSeeAnErrorMessage() {
		assertTrue(loginPage.isErrorDisplayed());
	}

	@And("User clicks the logout button")
	public void user_clicks_the_logout_button() {
		loginPage.clickLogoutButton();
	}

	@Then("The user should be logged out successfully")
	public void the_user_should_be_logged_out_successfully() {
		assertTrue(loginPage.isLoginButtonDisplayed());
	}

	@Then("The login page should be displayed")
	public void the_login_page_should_be_displayed() {
		assertTrue(loginPage.areLoginElementsDisplayed());
	}
}
