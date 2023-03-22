package com.wsg.pageObjects;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.wsg.abstractComponents.AbstractComponent;

public class VendorLoginPage extends AbstractComponent {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id='tbEmail']")
	private WebElement usernameField;

	@FindBy(xpath = "//*[@id='tbPassword']")
	private WebElement passwordField;

	@FindBy(xpath = "//*[text()='LOGIN']")
	private WebElement submitBtn;
	
	
	@FindBy(xpath = "//*[@id='hometab']")
	private WebElement homeTab;

	public VendorLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goToVendorUrl(Map<String, Object> testData) {
		try
		{
		openLinkInNewTab(testData.get("urlVendor").toString());
		
		switchToVendorTab(testData.get("urlVendor").toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public VendorDashboardPage logInVendor(Map<String, Object> testData) throws InterruptedException 
	{
		try
		{
		usernameField.sendKeys(testData.get("emailVendor").toString());
		passwordField.sendKeys(testData.get("passwordVendor").toString());
		submitBtn.click();
		waitForElement(homeTab);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new VendorDashboardPage(driver);
	}

}