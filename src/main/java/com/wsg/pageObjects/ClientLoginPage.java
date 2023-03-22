package com.wsg.pageObjects;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.wsg.abstractComponents.AbstractComponent;

public class ClientLoginPage extends AbstractComponent {

	private WebDriver driver;

	@FindBy(xpath = "//input[@id='tbEmail']")
	private WebElement usernameField;

	@FindBy(xpath = "//input[@id='tbPassword']")
	private WebElement passwordField;

	@FindBy(xpath = "//*[text()='LOGIN']")
	private WebElement submitBtn;
	
	@FindBy(xpath = "//img[@id='imgDesktop']")
	private WebElement clientLogo;
	
	public ClientLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goToClientUrl(Map<String, Object> testData) {
		
		driver.get(testData.get("urlClient").toString());
		

	}


	public ClientDashboardPage logInClient(Map<String, Object> testData) throws InterruptedException {
		try
		{
		usernameField.sendKeys(testData.get("emailClient").toString());
		passwordField.sendKeys(testData.get("passwordClient").toString());
		submitBtn.click();
		waitForPageToReload(submitBtn);
		
	}
		
	catch(Exception e)
	{
		e.printStackTrace();
	}
		return new ClientDashboardPage(driver);
	

}
	public boolean verifyLoginSuccess()
	{
		boolean loginSuccess = clientLogo.isDisplayed();
		try
		{
			Assert.assertTrue(loginSuccess);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return loginSuccess;
	}
}