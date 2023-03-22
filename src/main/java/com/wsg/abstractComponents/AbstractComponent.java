
package com.wsg.abstractComponents;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void waitForElement(String url) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public WebElement waitForElement(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForPresenceOfElement(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementToBeClickable(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public WebElement waitForVisibilityOfElement(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public WebElement waitForElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	public Boolean waitForValueToAppear(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		return wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));

	}

	public Boolean waitForPageToReload(WebElement element, String noOfPositions) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		return wait.until(ExpectedConditions.textToBePresentInElementValue(element, noOfPositions));

	}

	public boolean waitForBudgetToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		return wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(element, "$ 0.00")));
	}

	public void waitForVendorsToAppear(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));

	}

	public void waitForPageToReload(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	public void waitForPageToload(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	
	
    public void switchToWindow(String windowHandle) {
    	
        driver.switchTo().window(windowHandle);
    }
    public void openLinkInNewTab(String url) {
    	
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "', '_blank');");
    }
    
    public void switchToVendorTab(String url) {
    	
    	String currentWindowHandle = driver.getWindowHandle();
    	Set<String> windowHandles = driver.getWindowHandles();
    	for (String windowHandle : windowHandles) {
    	    if (!windowHandle.equals(currentWindowHandle) && url.contains("vendor")) {
    	        driver.switchTo().window(windowHandle);
    	        break;
    	    }
    	}
    }
    public void switchToClientTab(String url) {
    	
    	String currentWindowHandle = driver.getWindowHandle();
    	Set<String> windowHandles = driver.getWindowHandles();
    	for (String windowHandle : windowHandles) {
    	    if (!windowHandle.equals(currentWindowHandle) && url.contains("client")) {
    	        driver.switchTo().window(windowHandle);
    	        break;
    	    }
    	}
    }
    public void refresh() {
    	
    	driver.navigate().refresh();
    }

}
