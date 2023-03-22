
package com.wsg.pageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.wsg.abstractComponents.AbstractComponent;

public class ClientAllProfessionalsPage extends AbstractComponent {

	private WebDriver driver;
	
	By Approve_Link = By.xpath("(//a[starts-with(@href,'C_Worker_Details.aspx?empID')][1])[1]");
	@FindBy(xpath = "(//a[starts-with(@href,'C_Worker_Details.aspx?empID')][1])[1]")
	private WebElement approveLink;
	
	
	By saveBtn = By.xpath("//input[@id='ContentPlaceHolder2_btnAddAction' and @value='Save']");
	@FindBy(xpath = "//input[@id='ContentPlaceHolder2_btnAddAction' and @value='Save']")
	private WebElement saveButton;
	
	By markAsCompliant = By.xpath("//*[text()='Mark as Compliant']");
	@FindBy(xpath = "//*[text()='Mark as Compliant']")
	private WebElement markAsCompliantButton;
	
	By SaveCompliantBtn = By.xpath("//input[@id='ContentPlaceHolder2_SubmitCompliant' and @value='Save']");
	@FindBy(xpath = "//input[@id='ContentPlaceHolder2_SubmitCompliant' and @value='Save']")
	private WebElement save;	
	
	By markcompliantLabel = By.xpath("//span[@id='ContentPlaceHolder1_lblCandidateCompliant']");
	@FindBy(xpath = "//span[@id='ContentPlaceHolder1_lblCandidateCompliant']") 
	private WebElement compliantTag;
	
	public ClientAllProfessionalsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void markAsCompliant(Map<String, Object> testData, String reqId) throws InterruptedException {
		try
		{
		
		// Get the row containing the requisition id
		WebElement row = driver.findElement(By.xpath("(//*[text()='" + reqId + "']/ancestor::tr)[1]"));

		// Get the cell containing the professional id
		WebElement cell = row.findElement(By.xpath("//td[4]/a"));

		// Click on the cell
		cell.click();

		//Thread.sleep(5000); approve doc 1
		
		waitForPresenceOfElement(Approve_Link);
		waitForElementToBeClickable(Approve_Link);
		approveLink.click();

		//Thread.sleep(2000); save doc 1
		
		waitForPresenceOfElement(saveBtn);
		waitForElementToBeClickable(saveBtn);
		saveButton.click();
		
		//Thread.sleep(6000); approve doc 2

		waitForPresenceOfElement(Approve_Link);
		waitForElementToBeClickable(Approve_Link);
		approveLink.click();
		
		//Thread.sleep(2000); save doc 2

		waitForPresenceOfElement(saveBtn);
		waitForElementToBeClickable(saveBtn);
		saveButton.click();

		//Thread.sleep(6000); approve doc 3
		waitForPresenceOfElement(Approve_Link);
		waitForElementToBeClickable(Approve_Link);
		approveLink.click();
		

		//Thread.sleep(2000); save doc 3
		waitForPresenceOfElement(saveBtn);
		waitForElementToBeClickable(saveBtn);
		saveButton.click();
		

	
		waitForPresenceOfElement(markAsCompliant);
		waitForElementToBeClickable(markAsCompliant);
		
		markAsCompliantButton.click();
	
		waitForPresenceOfElement(SaveCompliantBtn);
		waitForElementToBeClickable(SaveCompliantBtn);
		save.click();
			
		waitForElement(compliantTag);
		waitForPresenceOfElement(markcompliantLabel);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(true, compliantTag.isDisplayed());
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}

}
