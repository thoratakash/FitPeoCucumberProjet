package utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

	public WebDriver driver;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void getTitleOfPage() {
		driver.getTitle();
	}

	public WebElement waitForWebElement(WebElement element, long durationOfSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationOfSeconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void clickOnWebElements(WebElement element, long durationOfSeconds) {
		WebElement webElement = waitForWebElement(element, durationOfSeconds);
		webElement.click();
	}

	public void typeTextInField(WebElement element, long durationOfSeconds, String textToBeType) {

		WebElement webElement = waitForWebElement(element, durationOfSeconds);
		webElement.sendKeys(textToBeType);
	}

	public String getTextFromWebElement(WebElement element, long durationOfSeconds) {
		WebElement webElement = waitForWebElement(element, durationOfSeconds);
		return webElement.getText();
	}

	public void javaScriptClick(WebElement element, long durationOfSeconds) {
		WebElement webElement = waitForWebElement(element, durationOfSeconds);
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].click();", webElement);
	}

	public void scrollToSpecificElement(WebElement element, long durationOfSeconds) {
		WebElement webElement = waitForWebElement(element, durationOfSeconds);
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		// jse.executeScript("arguments[0].scrollIntoView(true);", webElement);
		jse.executeScript("arguments[0].scrollIntoView();", webElement);
	}

	public void dragToElement(WebElement element, long durationOfSeconds) {
		WebElement webElement = waitForWebElement(element, durationOfSeconds);
		Actions a = new Actions(driver);
		a.moveToElement(webElement).build().perform();
	}

//	public void scrollToBottomOfPage(WebElement element,long durationOfSeconds) {
//    waitForWebElement(element, durationOfSeconds);
//	JavascriptExecutor jse=(JavascriptExecutor) driver;
//	jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");		
//}
//
//public void scrollBackToTopOFPage(WebElement element,long durationOfSeconds) {
//    waitForWebElement(element, durationOfSeconds);
//	JavascriptExecutor jse=(JavascriptExecutor) driver;
//	jse.executeScript("window.scrollTo(0, 0)");
//}

}// class
