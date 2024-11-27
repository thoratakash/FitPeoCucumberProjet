package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import factory.DriverFactory;
import utils.ElementUtils;

public class RevenueCalculatorPage {

	public WebDriver driver;
	private ElementUtils elementUtils;

	//Constructor
	public RevenueCalculatorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}

	@FindBy(xpath = "//div[@class='MuiBox-root css-rfiegf']/child::h4[normalize-space(text())='How Many Medicare Patients Would You Include in']")
	private WebElement verifyRevenueCalculatorpage;

	@FindBy(xpath = "//h4[normalize-space(text())='Medicare Eligible Patients']")
	private WebElement elementForPageScrollDown;

	@FindBy(xpath = "//input[@type='number']")
	private WebElement txtInputField;

	@FindBy(xpath = "//input[@type='range']")
	private WebElement slider;

	@FindBy(xpath = "//div[@class='MuiBox-root css-19xu03j'][2]//p[2]")
	private WebElement sliderCurrentValueElement;

	//WebElements for CheckBoxes
	@FindBy(xpath = "//span[text()='57']//preceding-sibling::span")
	private WebElement chkBoxForCPT_99091;

	@FindBy(xpath = "//span[text()='19.19']//preceding-sibling::span")
	private WebElement chkBoxForCPT_99453;

	@FindBy(xpath = "//span[text()='63']//preceding-sibling::span")
	private WebElement chkBoxForCPT_99454;

	@FindBy(xpath = "//span[text()='15']//preceding-sibling::span")
	private WebElement chkBoxForCPT_99474;

	@FindBy(xpath = "//p[text()='CPT-99091']")
	private WebElement CPT_99091;

	@FindBy(xpath = "//p[text()='CPT-99453']")
	private WebElement CPT_99453;

	@FindBy(xpath = "//p[text()='CPT-99454']")
	private WebElement CPT_99454;

	@FindBy(xpath = "//p[text()='CPT-99474']")
	private WebElement CPT_99474;

	public void verifyRevenueCalculatorPage() {
		String expectedText = "Your Remote Patient Monitoring program?";
		String actualText = elementUtils.getTextFromWebElement(verifyRevenueCalculatorpage,DriverFactory.EXPLICIT_TIMEOUT);

		System.out.println("Revenue Page text: " + actualText);

		// Verify if the actual text contains the expected text
		if (actualText != null && !actualText.isEmpty()) {
			if (actualText.contains(expectedText)) {
				Assert.assertTrue(true, "Revenue Calculator page verification passed.");
			} else {
				Assert.fail(
						"Page verification failed. Expected text: '" + expectedText + "', but found: '" + actualText);
			}
		} else {
			Assert.fail("Page verification failed due to null or empty text.");
		}
	}

	public void userScrollDownPage() {
		elementUtils.scrollToSpecificElement(elementForPageScrollDown, DriverFactory.EXPLICIT_TIMEOUT);
	}

	public void setSliderBarValueOnPage(int targetValue) {
		
		Actions actions = new Actions(driver);
		//Using the drag-and-drop method, we are getting the current value as 797 and it moves the slider upto 820.
                actions.dragAndDropBy(slider,93,0) .perform();
		//Without using the drag-and-drop method, we are getting the current value as 200 and it moves the slider up to 820.
                System.out.println("Slider Current Value is : "+slider.getAttribute("value"));
		int currentValue = Integer.parseInt(sliderCurrentValueElement.getText());

		// Adjust Slider Using Keyboard Arrows
		for (int i = 0; i < Math.abs(targetValue - currentValue); i++) {
			if (currentValue < targetValue) {
				slider.sendKeys(Keys.ARROW_RIGHT);
			} else {
				slider.sendKeys(Keys.ARROW_LEFT);
			}
		}
		// Validate that the slider value is updated
		int updatedValue = Integer.parseInt(sliderCurrentValueElement.getText());
		Assert.assertEquals(updatedValue, targetValue, "Slider value did not update correctly!");
	}

	public void verifyBottomTextFieldUpdatedOrNot(String textFieldValue) {
		String sliderValue = slider.getAttribute("value");
		if (sliderValue.equals(textFieldValue)) {
			Assert.assertTrue(true, "Input Text Field value is updated to 820 ");
		} else {
			Assert.fail("Input Text Field value did not update to 820 ");

		}
	}

	public void clickOnInputTextField() {
		elementUtils.clickOnWebElements(txtInputField, DriverFactory.EXPLICIT_TIMEOUT);
	}

	public void typeTextIntoField(int inputValue) {

		try {
			elementUtils.scrollToSpecificElement(elementForPageScrollDown, DriverFactory.EXPLICIT_TIMEOUT);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='" + inputValue + "';", txtInputField);
			js.executeScript("arguments[0].dispatchEvent(new Event('input'));", txtInputField);

			System.out.println("Value set successfully into the input field.");
		} catch (Exception e) {
			System.out.println("Error while typing text into the field: " + e.getMessage());
		}
	}

	public void validateInputTextField(int expValue) {
		try {
			int actualValue = Integer.parseInt(txtInputField.getAttribute("value"));

			if (actualValue == expValue) {
				Assert.assertTrue(true, "Input Text field is updated to the expected value: " + expValue);
			} else {
				Assert.fail("Input Text field did not update. Expected: " + expValue + ", but found: " + actualValue);
			}
		} catch (NumberFormatException e) {
			Assert.fail("Failed to parse the value from the input field. Value might not be an integer.");
		} catch (Exception e) {
			Assert.fail("An unexpected error occurred: " + e.getMessage());
		}
	}

	public void selectCheckBoxForCPT_99091(String chkBox01) {
		elementUtils.dragToElement(CPT_99091, DriverFactory.EXPLICIT_TIMEOUT);

		if (CPT_99091.getText().equals(chkBox01)) {
			if (!chkBoxForCPT_99091.isSelected()) {
				elementUtils.clickOnWebElements(chkBoxForCPT_99091, DriverFactory.EXPLICIT_TIMEOUT);
			}
		} else {
			System.out.println("Invalid checkbox identifier: " + chkBox01);
		}
	}

	public void selectCheckBoxForCPT_99453(String chkBox02) {
		elementUtils.dragToElement(CPT_99453, DriverFactory.EXPLICIT_TIMEOUT);
		if (CPT_99453.getText().equals(chkBox02)) {
			if (!chkBoxForCPT_99453.isSelected()) {
				elementUtils.clickOnWebElements(chkBoxForCPT_99453, DriverFactory.EXPLICIT_TIMEOUT);
			}
		} else {
			System.out.println("Invalid checkbox identifier: " + chkBox02);
		}
	}

	public void selectCheckBoxForCPT_99454(String chkBox03) {
		elementUtils.dragToElement(CPT_99454, DriverFactory.EXPLICIT_TIMEOUT);
		if (CPT_99454.getText().equals(chkBox03)) {
			if (!chkBoxForCPT_99454.isSelected()) {
				elementUtils.clickOnWebElements(chkBoxForCPT_99454, DriverFactory.EXPLICIT_TIMEOUT);
			}
		} else {
			System.out.println("Invalid checkbox identifier: " + chkBox03);
		}
	}

	public void selectCheckBoxForCPT_99474(String chkBox04) {
		elementUtils.dragToElement(CPT_99474, DriverFactory.EXPLICIT_TIMEOUT);
		if (CPT_99474.getText().equals(chkBox04)) {
			if (!chkBoxForCPT_99474.isSelected()) {
				elementUtils.clickOnWebElements(chkBoxForCPT_99474, DriverFactory.EXPLICIT_TIMEOUT);
			}
		} else {
			System.out.println("Invalid checkbox identifier: " + chkBox04);
		}
	}

	@FindBy(xpath = "//p[contains(normalize-space(text()), 'Total Recurring Reimbursement for all Patients Per Month:')]")
	private WebElement total_Reimbursement;

	public void verifyTotalReimbursement(String total) {
		System.out.println("Checking total rem : " + total_Reimbursement);
		if (total_Reimbursement.getText().contains(total)) {
			System.out.println("Got total reimbursement: " + total);
			Assert.assertTrue(true, "Got total reimbursement");
		} else {
			Assert.fail("Did not get total reimbursement. Expected: " + total);
		}
	}

}// class
