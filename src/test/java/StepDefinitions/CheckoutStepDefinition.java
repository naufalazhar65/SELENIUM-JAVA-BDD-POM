package StepDefinitions;

import PageObject.CheckoutPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutStepDefinition {
	private WebDriver driver;
	private CheckoutPage checkoutPage;

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

		checkoutPage = new CheckoutPage(driver);
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	@Given("User has logged in with valid credentials")
	public void user_has_logged_in_with_valid_credentials() {
		checkoutPage.userLogin();
	}

	@When("User adds items to cart and goes to checkout")
	public void user_adds_items_to_cart_and_goes_to_checkout() {
		checkoutPage.clickAddToCartButton();
		checkoutPage.clickCartButton();
		checkoutPage.clickCheckoutButton();
	}

	@And("User enters checkout information and completes checkout")
	public void user_enters_checkout_information_and_completes_checkout() {
		checkoutPage.enterCheckoutInformation("Naufal", "Azhar", "12345");
		checkoutPage.clickContinueButton();
		checkoutPage.clickFinishButton();
	}

	@When("User enters incomplete checkout information and tries to complete checkout")
	public void user_enters_incomplete_checkout_information_and_tries_to_complete_checkout() {
		checkoutPage.enterCheckoutInformation("John", "Doe", "");
		checkoutPage.clickContinueButton();
	}

	@Then("User should see checkout complete message")
	public void user_should_see_checkout_complete_message() {
		assertEquals("Thank you for your order!", checkoutPage.getCheckoutCompleteMessage());
	}

	@Then("User should see error message indicating missing information")
	public void user_should_see_error_message_indicating_missing_information() {
		assertEquals("Error: Postal Code is required", checkoutPage.getErrorMessage());
	}
}
