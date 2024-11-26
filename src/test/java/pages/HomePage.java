package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import factory.DriverFactory;
import utils.ElementUtils;

public class HomePage {
	
	public WebDriver driver;
	private ElementUtils elementUtils;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils=new ElementUtils(driver);
	}
	
	@FindBy(xpath="//h1[normalize-space(text())='Remote Patient'] ")
	private WebElement verifyHomePage;

		//@FindBy(xpath="//div[text()='Revenue Calculator']")
		@FindBy(xpath="//a[@href=\"/revenue-calculator\"]/child::div[text()='Revenue Calculator']")
		private WebElement btnRevenueCalculator;
		
		
		public void verifyHomePage() {
		    String expectedTitle = "Remote Patient";
		    String text=elementUtils.getTextFromWebElement(verifyHomePage, DriverFactory.EXPLICIT_TIMEOUT);
		    // Fetch the text from the web element
		    System.out.println("Text Value: " + text);

		    // Verify if the actual text contains the expected title
		    if (text != null && !text.isEmpty()) {
		        if (text.contains(expectedTitle)) {		       
		            Assert.assertTrue(true, "FitPeo Home page verification passed.");
		        } else {
		            Assert.fail("Home page verification failed. Expected title: '" + expectedTitle + "', but found: '" + text + "'.");
		        }
		    } else {
		        Assert.fail("Home page text is null or empty. Verification failed.");
		    }
		}

		public void clickOnRevenueCalculatorBtn() {
			//elementUtils.clickOnWebElements(btnRevenueCalculator, DriverFactory.EXPLICIT_TIMEOUT);
			elementUtils.javaScriptClick(btnRevenueCalculator, DriverFactory.EXPLICIT_TIMEOUT);
		}
	
}//class
