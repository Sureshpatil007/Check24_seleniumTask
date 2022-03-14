package stepdefinitions;

import java.util.Properties;
import com.pages.HomePage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps {


	HomePage homePage = new HomePage(DriverFactory.getDriver());
	ConfigReader configReader;
	Properties prop;

	@Given("I click on cookie button")
	public void i_click_on_cookie_button() {
		homePage.acceptCookies();
	}

	@When("I verify cookie")
	public void i_verify_cookie() {
		homePage.verifycookie();
	}

	@When("I click on Further button")
	public void i_click_on_further_button() {
		homePage.clickOnProduct();
	}

	@When("I Enter Email id")
	public void i_enter_email_id() {
		homePage.enterEmailAddress();
	}

	@When("I click on Ich möchte als Gast fortfahren button")
	public void i_click_on_ich_möchte_als_gast_fortfahren_button() {
		homePage.clickOnRadioButton();
	}

	@Then("I Verify all the error messages")
	public void i_verify_all_the_error_messages() {
		homePage.verifyErrors();
	}

	@When("I Enter all details and click on further button")
	public void i_enter_all_details_and_click_on_further_button() {
		homePage.enterDetails();
	}
	@Then("I should see No Error message")
	public void i_should_see_no_error_message() {
		homePage.NoErrorscreen();

	}

}
