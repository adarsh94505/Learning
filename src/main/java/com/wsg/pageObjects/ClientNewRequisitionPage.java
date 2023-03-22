
package com.wsg.pageObjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.wsg.abstractComponents.AbstractComponent;

public class ClientNewRequisitionPage extends AbstractComponent {

	private WebDriver driver;

	@FindBy(xpath = "//*[contains(@id,'_ddlWeeks')]")
	private WebElement weeks;

	@FindBy(xpath = "//*[contains(@id,'_txtEnddate')]")
	private WebElement endDate;

	@FindBy(xpath = "//*[contains(@id,'_ddlpositions')]")
	private WebElement positions;

	@FindBy(xpath = "//*[contains(@id,'_txtRate')]")
	private WebElement billRate;

	@FindBy(xpath = "//*[contains(@id,'_requisitionBudget')]")
	private WebElement totalEstimatedBudget;

	@FindBy(xpath = "//*[contains(@id,'_ddSTime1')]")
	private WebElement shiftStart;

	@FindBy(xpath = "//*[contains(@id,'_ddETime1')]")
	private WebElement shiftEnd;

	@FindBy(xpath = "//*[contains(@id,'_ddlHiringManager')]")
	private WebElement hiringManager;

	@FindBy(xpath = "//*[contains(@id,'_lboxAvailableVendors')]")
	WebElement availableVendors;

	@FindBy(xpath = "//*[contains(@id,'_btnAddVendorsToClient')]")
	WebElement addVendorsButton;

	By selectedVendors = By.xpath("//select[@id='ContentPlaceHolder1_lboxVendorsWorkingWithClient']/option");

	@FindBy(xpath = "//*[contains(@id,'_ddlReasoncode')]")
	private WebElement reason;

	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_btnPriview']")
	private WebElement previewReqbtn;

	@FindBy(xpath = "//*[@id='SubmitReq']")
	private WebElement submitRequisition;
//	By SubmitBtnPopup = By.cssSelector(".sweet-alert.visible>.sa-button-container>.sa-confirm-button-container>.confirm");

	@FindBy(css = ".sweet-alert.visible>.sa-button-container>.sa-confirm-button-container>.confirm")
	private WebElement confirmationSubmit;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_lbljobid']")
	private WebElement requisitionId;
	
	By offer = By.xpath("//*[@id='ContentPlaceHolder2_btnOfferCandidate' and @value='Offer']");
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_btnOfferCandidate' and @value='Offer']")
	private WebElement OfferElement;
	
	By ele = By.xpath("//a/span[text()='Present to Client']/parent::a");
	
	By yes = By.xpath("//*[@id='ContentPlaceHolder2_Button3' and @value='Yes']");
	@FindBy (xpath = "//*[@id='ContentPlaceHolder2_Button3' and @value='Yes']")
	private WebElement yesbutton;
	
	By ok = By.xpath("//button[@class='confirm btn btn-lg btn-success' and text()='Ok']");
	@FindBy(xpath = "//button[@class='confirm btn btn-lg btn-success' and text()='Ok']")
	private WebElement okButton;
		
	By PresentToClient_YES_btn = By.xpath("//*[@id='ContentPlaceHolder2_btnshortlist']");
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_btnshortlist']")
	private WebElement presentToClientYes;
	
	By submittedCandidatesTable = By.xpath("//*[@id='tblSubmiitedCandidate']");
 
	//scheduling time 1
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_Textdate']")
	private WebElement intDate1;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_ddtime']")
	private WebElement intTime1;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_tzone1']")
	private WebElement intTimeZone1;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_txtsubmitintervew']")
	private WebElement intComment1;
	
	//scheduling time 2
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_Textdate2']")
	private WebElement intDate2;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_ddtime2']")
	private WebElement intTime2;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_tzone2']")
	private WebElement intTimeZone2;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_txtsubmitintervew2']")
	private WebElement intComment2;
	
	//scheduling time 3
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_Textdate3']")
	private WebElement intDate3;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_ddtime3']")
	private WebElement intTime3;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_tzone3']")
	private WebElement intTimeZone3;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_txtsubmitintervew3']")
	private WebElement intComment3;
	
	By sendInterviewRequestBtn = By.xpath("//*[@id='ContentPlaceHolder2_BtnSubmit']");
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_BtnSubmit']")
	private WebElement sendInterviewScheduleButton;
	
	By sendInterviewRequestConfirmBtnlocator = By.xpath("//*[@id='ContentPlaceHolder2_btnIntscheduleCJ']");
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_btnIntscheduleCJ']")
	private WebElement sendInterviewRequestConfirmBtn;
	
	public ClientNewRequisitionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@SuppressWarnings("unchecked")
	public String createRequisition(Map<String, Object> testData) throws InterruptedException {
		try
		{
		Select weeksDropdown = new Select(weeks);
		weeksDropdown.selectByVisibleText(testData.get("noOfWeeks").toString());
		waitForValueToAppear(endDate);

		String xpathExpression = String.format("//select[@id='ContentPlaceHolder1_ddlpositions']/option[text()='%s']",
				testData.get("noOfPositions").toString());
		WebElement positionOption = driver.findElement(By.xpath(xpathExpression));
		positionOption.click();
		waitForPageToReload(positions, testData.get("rate").toString());

		billRate.sendKeys(testData.get("rate").toString());

		shiftStart.sendKeys(testData.get("startTime").toString());
		shiftEnd.sendKeys(testData.get("endTime").toString());
		
		waitForBudgetToAppear(totalEstimatedBudget);

		hiringManager.findElement(By.xpath("//option[. = '" + testData.get("managerName").toString() + "']")).click();

		List<String> vList = (List<String>) testData.get("vendorlist");
		List<WebElement> options = availableVendors.findElements(By.tagName("option"));
		for (WebElement option : options) {
			for (Object vendor : vList) {
				if (option.getText().equals(vendor)) {
					option.click();
				}
			}
		}
		addVendorsButton.click();

		waitForVendorsToAppear(selectedVendors);

		Select reasonDropDown = new Select(reason);
		reasonDropDown.selectByVisibleText(testData.get("reqReason").toString());

		previewReqbtn.click();
		
		waitForElement(submitRequisition);
		
		submitRequisition.click();

		waitForElement(confirmationSubmit);
		confirmationSubmit.click();

		waitForElement(requisitionId);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String reqID = requisitionId.getText();
		
		System.out.println(reqID);
		return reqID;
		

	}
	
	public void offerCandidate(Map<String, Object> testData) throws InterruptedException 
	{
		
		switchToClientTab(testData.get("urlClient").toString());
		
		driver.navigate().refresh();
		waitForPresenceOfElement(ele);
		
		WebElement presentToClient = driver.findElement(By.xpath("//a/span[text()='Offer']/parent::a"));
		presentToClient.click();
		
		waitForPresenceOfElement(offer);
		waitForElementToBeClickable(offer);
		OfferElement.click();
		
		waitForPresenceOfElement(yes);
		waitForVisibilityOfElement(yes);
		waitForElementToBeClickable(yes);
		yesbutton.click();
		
		waitForPresenceOfElement(ok);
		waitForElementToBeClickable(ok);
		okButton.click();

	}
	
	public void presentingCandidateToClient(Map<String, Object> testData)
	{
		switchToClientTab(testData.get("urlClient").toString());
		driver.navigate().refresh();
		waitForPresenceOfElement(submittedCandidatesTable);
		waitForVisibilityOfElement(submittedCandidatesTable);
		//td[contains(text(), 'AaryaSareen@yopmail.com')]/following-sibling::td/a[@data-original-title='Confirm Interview']
		WebElement presentToClientButton = driver.findElement
		(By.xpath("//td[contains(text(), '"+testData.get("candidateEmail").toString()+"')]/following-sibling::td[@style='text-align:right;']/a/span[text()='Present to Client']"));
		presentToClientButton.click();
		
		waitForPresenceOfElement(PresentToClient_YES_btn);
		waitForElementToBeClickable(PresentToClient_YES_btn);
		presentToClientYes.click();
		System.out.println("Candidate has been shortlisted successfully");
	
	}
	
	 public VendorRequisitionDetailsPage interviewSchedule(Map<String, Object> testData)
	 {
		waitForPresenceOfElement(submittedCandidatesTable);
		waitForVisibilityOfElement(submittedCandidatesTable); 
		
		By schedInterviewBtn = By.xpath("//td[contains(text(), '"+testData.get("candidateEmail").toString()+"')]/following-sibling::td[@style='text-align:right;']/a/span[text()='Schedule Interview']");
		WebElement scheduleInterviewBtn = driver.findElement
		(By.xpath("//td[contains(text(), '"+testData.get("candidateEmail").toString()+"')]/following-sibling::td[@style='text-align:right;']/a/span[text()='Schedule Interview']"));
		scheduleInterviewBtn.click();
		
		waitForPresenceOfElement(schedInterviewBtn);
		waitForElementToBeClickable(schedInterviewBtn); 
		
		intDate1.sendKeys(testData.get("interviewDate1").toString());
		intTime1.sendKeys(testData.get("interviewTime1").toString());
		
		Select interviewTZ1 = new Select(waitForElement(intTimeZone1));
		interviewTZ1.selectByVisibleText(testData.get("interviewTimeZone1").toString());
		intComment1.sendKeys(testData.get("interviewComment1").toString());
		
		intDate2.sendKeys(testData.get("interviewDate2").toString());
		intTime2.sendKeys(testData.get("interviewTime2").toString());
		Select interviewTZ2 = new Select(waitForElement(intTimeZone2));
		interviewTZ2.selectByVisibleText(testData.get("interviewTimeZone2").toString());
		intComment2.sendKeys(testData.get("interviewComment2").toString());
		
		intDate3.sendKeys(testData.get("interviewDate3").toString());
		intTime3.sendKeys(testData.get("interviewTime3").toString());
		Select interviewTZ3 = new Select(waitForElement(intTimeZone3));
		interviewTZ3.selectByVisibleText(testData.get("interviewTimeZone3").toString());
		intComment3.sendKeys(testData.get("interviewComment3").toString());
		
		waitForPresenceOfElement(sendInterviewRequestBtn);
		waitForElementToBeClickable(sendInterviewRequestBtn); 
		sendInterviewScheduleButton.click();
		
		waitForPresenceOfElement(sendInterviewRequestConfirmBtnlocator);
		waitForElementToBeClickable(sendInterviewRequestConfirmBtnlocator); 
		sendInterviewRequestConfirmBtn.click();
		System.out.println("Interview Request Sent Successfully");
		return new VendorRequisitionDetailsPage(driver);
		
	 }

}
