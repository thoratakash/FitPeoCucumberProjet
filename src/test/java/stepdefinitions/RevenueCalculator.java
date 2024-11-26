package stepdefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.RevenueCalculatorPage;

public class RevenueCalculator {

	public WebDriver driver;
	private HomePage homePage;
	private RevenueCalculatorPage revenueCalculatorPage;

	@Given("User opens the web browser")
	public void user_opens_the_web_browser() {
		driver = DriverFactory.getDriver();
	}

	@Given("User navigates to the FitPeo Homepage")
	public void user_navigates_to_the_fit_peo_homepage() {
		homePage = new HomePage(driver);
		homePage.verifyHomePage();
	}

	@Given("User navigates to the Revenue Calculator page")
	public void user_navigates_to_the_revenue_calculator_page() {
		homePage.clickOnRevenueCalculatorBtn();
		revenueCalculatorPage = new RevenueCalculatorPage(driver);
	}

	@Then("User should see the Revenue Calculator page loaded successfully")
	public void user_should_see_the_revenue_calculator_page_loaded_successfully() {
		revenueCalculatorPage.verifyRevenueCalculatorPage();
	}

	@When("User scrolls down to the slider section")
	public void user_scrolls_down_to_the_slider_section() {
		revenueCalculatorPage.userScrollDownPage();
	}

	@When("User adjusts the slider to set its value to {int}")
	public void user_adjusts_the_slider_to_set_its_value_to(int SliderValue) {
		revenueCalculatorPage.setSliderBarValueOnPage(SliderValue);
	}

	@Then("The bottom text field value should update to {string}")
	public void the_bottom_text_field_value_should_update_to(String textFieldValue) {
		revenueCalculatorPage.verifyBottomTextFieldUpdatedOrNot(textFieldValue);
	}

	@When("User clicks on the text field associated with the slider")
	public void user_clicks_on_the_text_field_associated_with_the_slider() {
		revenueCalculatorPage.clickOnInputTextField();
	}

	@When("User enters the value {int} in the text field")
	public void user_enters_the_value_in_the_text_field(int inputValue) {
		revenueCalculatorPage.typeTextIntoField(inputValue);
	}

	@Then("The slider position should update to reflect the value {int}")
	public void the_slider_position_should_update_to_reflect_the_value(int expValue) {
		revenueCalculatorPage.validateInputTextField(expValue);
	}

	@When("User selects the checkboxes for:")
	public void user_selects_the_checkboxes_for(DataTable dataTable) {
		Map<String, String> dataSet = dataTable.asMap(String.class, String.class);

		revenueCalculatorPage.selectCheckBoxForCPT_99091(dataSet.get("CheckBox01"));
		revenueCalculatorPage.selectCheckBoxForCPT_99453(dataSet.get("CheckBox02"));
		revenueCalculatorPage.selectCheckBoxForCPT_99454(dataSet.get("CheckBox03"));
		revenueCalculatorPage.selectCheckBoxForCPT_99474(dataSet.get("CheckBox04"));
	}

	@Then("The header displaying Total Recurring Reimbursement for all Patients Per Month {string}")
	public void the_header_displaying_total_recurring_reimbursement_for_all_patients_per_month(String totalReim) {
		revenueCalculatorPage.verifyTotalReimbursement(totalReim);
	}

}// class
