
package com.wsg.pageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wsg.abstractComponents.AbstractComponent;

public class VendorDashboardPage extends AbstractComponent {

	private WebDriver driver;
	
	By ReqLeftMenu = By.xpath("//i[@class='menu-icon icon-briefcase']");
	@FindBy(xpath = "//i[@class='menu-icon icon-briefcase']")
	private WebElement requisitionOptionLeftMenu;
	
	By openReq = By.xpath("//*[@id='a_view_req']");
	@FindBy(xpath = "//*[@id='a_view_req']")
	private WebElement openRequisitions;
	
	@FindBy(xpath = "//input[@id='ContentPlaceHolder1_txtReqId']")
	private WebElement reqIdsearchbox;
	
	@FindBy(xpath = "//input[@name='ctl00$ContentPlaceHolder1$Button1']")
	private WebElement searchbtn;
	
	//xpaths for navigation
	By leftMenuCandarrowbutton = By.xpath("//i[@class='menu-icon icon-user']");
	@FindBy(xpath = "//i[@class='menu-icon icon-user']")
	private WebElement candidatesLeftOption;
	
	By leftMenuSubmitCand = By.xpath("//a[@id='a_can_s']");
	@FindBy(xpath = "//a[@id='a_can_s']")
	private WebElement candSubmitOption;
	
	By reqSelectPopup = By.xpath("//*[contains(@id, 'selWorkerDropDown')]");
	@FindBy(xpath = "//*[contains(@id, 'selWorkerDropDown')]")
	private WebElement selectReqForCand;
	
	By addCandbtn = By.xpath("//*[contains(@id, 'btnAdd')]");
	@FindBy(xpath = "//*[contains(@id, 'btnAdd')]")
	private WebElement submitCandbtn;
		
	By addNewCandbtn = By.xpath("//*[contains(@id, 'btnaddexcandidate')]");
	@FindBy(xpath = "//*[contains(@id, 'btnaddexcandidate')]")
	private WebElement submitNewCandbtn;
	
	//li[@id='dCandidates'] = Candidates OR //i[@class='menu-icon icon-user']
	//li[@id='viewcandidates'] = view candidates

	@FindBy(xpath = "//i[@class='menu-icon icon-user']")
	private WebElement candidatesLeftMenu;
	
	public VendorDashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	
	public VendorRequisitionDetailsPage openNewRequisition(String reqId) throws InterruptedException 
	{
		//uses general filter to enter the requisition ID to search the requisition
		waitForPresenceOfElement(ReqLeftMenu);
		waitForElementToBeClickable(ReqLeftMenu);
		requisitionOptionLeftMenu.click();
		
		waitForPresenceOfElement(openReq);
		waitForElementToBeClickable(openReq);
		openRequisitions.click();
		
		waitForElement(reqIdsearchbox);
		reqIdsearchbox.sendKeys(reqId);
		searchbtn.click();
		
		
		
	driver.findElement(By.xpath("//*[text()='" + reqId + "']")).click();
	return new VendorRequisitionDetailsPage(driver);
	}
	
	public AddNewCandidate submitNewCandidateNavigation(String reqId)
	{
		try
		{
			//clicking at Candidates sandwich menu
			waitForPresenceOfElement(leftMenuCandarrowbutton);
			waitForElementToBeClickable(leftMenuCandarrowbutton);
			candidatesLeftOption.click();
			
			//clicking at Submit Candidate option
			waitForPresenceOfElement(leftMenuSubmitCand);
			waitForVisibilityOfElement(leftMenuSubmitCand);
			waitForElementToBeClickable(leftMenuSubmitCand);
			candSubmitOption.click();
			
			//selecting the requisition after pop up appears
			waitForPresenceOfElement(reqSelectPopup);
			waitForElementToBeClickable(reqSelectPopup);
			selectReqForCand.click();
			//add contains text while finding the record in the drop down
			
			Select chooseReq = new Select(selectReqForCand);
			chooseReq.selectByValue(reqId);
			
			
			//
			
			
			//waitForPresenceOfElement(addCandbtn);
			//waitForElementToBeClickable(addCandbtn);
			submitCandbtn.click();
			
			waitForPresenceOfElement(addNewCandbtn);
			waitForElementToBeClickable(addNewCandbtn);
			submitNewCandbtn.click();
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new AddNewCandidate(driver);
	}

	public VendorViewCandidates leftMenuViewCandidatesNavigation(String reqId)
	{
		try
		{
		candidatesLeftMenu.click();
		
		waitForPresenceOfElement(leftMenuSubmitCand);
		waitForElementToBeClickable(leftMenuSubmitCand);
		candSubmitOption.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return new VendorViewCandidates(driver);
	}
	
	
	
	
}
