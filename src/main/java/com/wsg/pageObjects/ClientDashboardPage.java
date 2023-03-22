
package com.wsg.pageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.wsg.abstractComponents.AbstractComponent;

public class ClientDashboardPage extends AbstractComponent {

	private WebDriver driver;
	
	
	
	@FindBy(xpath = "//*[@id='djob']")
	private WebElement requisitionButton;

	@FindBy(xpath = "//*[@id='newjob']")
	private WebElement newRequisitionButton;
	
	@FindBy(xpath = "//*[@id='myModalLabel1']")
	private WebElement newRequisitionPopUp;
	
	@FindBy(xpath = "//*[@id='ddlClients']")
	private WebElement healthSystemDropdown;

	@FindBy(xpath = "//*[@id='ddlJobLocation']")
	private WebElement facilityDropdown;

	@FindBy(xpath = "//*[@id='ddlUnit']")
	private WebElement hsUnitDropdown;

	@FindBy(xpath = "//*[@id='ddlTypeOfWorker']")
	private WebElement typeOfWorkerDropdown;

	@FindBy(xpath = "//*[@id='ddlSpecialty']")
	private WebElement hsSpecialityDropdown;

	@FindBy(xpath = "//*[@id='ddlReqType']")
	private WebElement requisitionTypeDropdown;
	
	@FindBy(xpath = "//*[@id='loginbtn']")
	private WebElement addRequisitionButton;
	
	By Proflink = By.xpath("(//*[text()='Professionals'])[1]");
	@FindBy(xpath = "(//*[text()='Professionals'])[1]")
	private WebElement professionalsLink;

	By AllProf = By.xpath("//li[@id='viewworker']/a[@id='viewwork']");
	@FindBy(xpath = "//li[@id='viewworker']/a[@id='viewwork']")
	private WebElement allProfessionalsButton;
	
	By requisition = By.xpath("//*[@id='djob']");

	
	public ClientDashboardPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	
	

	public String verifyNewRequisitionPopUp() {
		try
		{
		
		waitForElement(requisition).click();
		newRequisitionButton.click();
		waitForElement(newRequisitionPopUp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return newRequisitionPopUp.getText();
	}
	
	public ClientNewRequisitionPage requisitionEntry(Map<String, Object> testData) {
		try
		{
		Select selectHealthSystem = new Select(waitForElement(healthSystemDropdown));
		selectHealthSystem.selectByVisibleText(testData.get("healthSystem").toString());
		
		Select selectFacility = new Select(waitForElement(facilityDropdown));
		selectFacility.selectByVisibleText(testData.get("facility").toString());

		Select selectHsUnit = new Select(waitForElement(hsUnitDropdown));
		selectHsUnit.selectByVisibleText(testData.get("hsUnit").toString());

		Select selectTypeOfWorker = new Select(waitForElement(typeOfWorkerDropdown));
		selectTypeOfWorker.selectByVisibleText(testData.get("typeOfWorker").toString());

		Select selectHsSpeciality = new Select(waitForElement(hsSpecialityDropdown));
		selectHsSpeciality.selectByVisibleText(testData.get("hsSpeciality").toString());

		Select selectRequisitionType = new Select(waitForElement(requisitionTypeDropdown));
		selectRequisitionType.selectByVisibleText(testData.get("requisitionType").toString());
		
		waitForElement(addRequisitionButton).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ClientNewRequisitionPage(driver);
	}
	
	public ClientAllProfessionalsPage goToAllProfessionals(Map<String, Object> testData) throws InterruptedException {
	try
	{
	
		switchToClientTab(testData.get("urlClient").toString());		
		
		refresh();
				
		waitForPresenceOfElement(Proflink);
		waitForElementToBeClickable(Proflink);
				
		professionalsLink.click();

		waitForPresenceOfElement(AllProf);
		waitForElementToBeClickable(AllProf);
		allProfessionalsButton.click();

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		return new ClientAllProfessionalsPage(driver);
		
	}
	
}
