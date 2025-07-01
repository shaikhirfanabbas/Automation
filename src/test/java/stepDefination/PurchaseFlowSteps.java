package stepDefination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.Pages.CartPage;
import com.Pages.CheckoutPage;
import com.Pages.LoginPage;
import com.Pages.ProductPage;
import com.Pages.ScreenshotUtil;
import com.Pages.WebDriverFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchaseFlowSteps {
	
	WebDriver driver;
	ProductPage productPage;
	CartPage cartPage;
	 List<Double> selectedPrices = new ArrayList<>();

	

	@Given("I launch the saucedemo website")
	public void i_launch_the_saucedemo_website() {
		driver = WebDriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
	}

	@When("I log in with valid credentials {string} and {string}")
	public void i_log_in_with_valid_credentials_and(String username, String password) {
		 new LoginPage(driver).login(username, password);
	}

	@And("I apply {string} filter")
	public void i_apply_filter(String filterOption) {
		productPage = new ProductPage(driver);
        productPage.selectSortOptions(filterOption);
	}

	@And("I add two most expensive items in cart")
	public void i_add_two_most_expensive_items_in_cart() {
		selectedPrices = productPage.addTwoMostExpensiveItems();
	}

	@Then("I should see {int} items in the cart")
	public void i_should_see_items_in_the_cart(int expectedCount) {
		cartPage = productPage.goToCart();
        Assert.assertEquals(cartPage.getItemCount(), expectedCount);
	}

	@And("I validate total price equals the sum of individual item prices")
	public void i_validate_total_price_equals_the_sum_of_individual_item_prices() {
		double expectedTotal = selectedPrices.stream().mapToDouble(Double::doubleValue).sum();
        double actualTotal = cartPage.getTotalPrice();
        Assert.assertEquals(actualTotal, expectedTotal, 0.01);
	}

	@When("I proceed to checkout with {string}, {string}, and {string}")
	public void i_proceed_to_checkout_with_and(String firstName, String lastName, String postalCode) {
		cartPage.checkout();
        new CheckoutPage(driver).fillDetailsAndContinue(firstName, lastName, postalCode);
	}

	@Then("I should see the message {string}")
	public void i_should_see_the_message(String expectedMsg) {
		String actualMsg = new CheckoutPage(driver).getConfirmationMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg));
	}

	@And("I take a screenshot named {string}")
	public void i_take_a_screenshot_named(String filename) throws IOException {
		ScreenshotUtil.captureScreenshot(driver, filename);
        driver.quit();
	}
	
}
