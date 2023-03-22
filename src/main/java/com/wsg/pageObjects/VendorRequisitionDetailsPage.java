
package com.wsg.pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.wsg.abstractComponents.AbstractComponent;

import junit.framework.Assert;

public class VendorRequisitionDetailsPage extends AbstractComponent {

	private WebDriver driver;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_addcandidate']")
	private WebElement submitCandidate;
	
	By candName = By.xpath("(//*[text()='Enrique Robles'])[1]");
	@FindBy(xpath = "(//*[text()='Enrique Robles'])[1]")
	private WebElement candidate;	
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtavailable']")
	private WebElement dateField;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_ddlStateOT']")
	private WebElement stateOtRules;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_documentUpload1']")
	private WebElement docUplaod1;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_documentUpload2']")
	private WebElement docUplaod2;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_btnPreview']")
	private WebElement preview;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_btnsubmit']")
	private WebElement submit;
	
	By poolPopUpLocator = By.xpath("//h4[@id='myModalLabel']");
	@FindBy(xpath = "//h4[@id='myModalLabel']")
	private WebElement poolpopup;
	
	By reqPage = By.id("ContentPlaceHolder1_lbljobid");
	@FindBy(id = "ContentPlaceHolder1_lbljobid")
	private WebElement requisitionPage;
	
	By previewCandPageTitle = By.xpath("//*[@class='page-title']");
	@FindBy(xpath = "//*[@class='page-title']")
	private WebElement previewPageTitle;
	
	By acceptbtn = By.xpath("//td[@style='text-align:right']/a[contains(., 'Accept')]");
	@FindBy(xpath = "//td[@style='text-align:right']/a[contains(., 'Accept')]")
	private WebElement acceptLink;
	
	By Yesbtn = By.xpath("//input[@id='ContentPlaceHolder2_Btnoffconfirm' and @value='Yes']");
	@FindBy(xpath = "//input[@id='ContentPlaceHolder2_Btnoffconfirm' and @value='Yes']")
	private WebElement yesButton;
	
	By submittedCandidatesTable = By.xpath("//*[@id='DataTables_Table_0']");
	
	By radioBtn1 = By.xpath("//*[@id='ContentPlaceHolder2_rb_interview_1']");
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_rb_interview_1']")
	private WebElement dateTimeRadioSelect1;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_rb_interview_2']")
	private WebElement dateTimeRadioSelect2;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_rb_interview_3']")
	private WebElement dateTimeRadioSelect3;
	
	@FindBy(xpath = "//span[@id='ContentPlaceHolder2_lbl_DT_1']")
	private WebElement dateInfo1;
	
	@FindBy(xpath = "//span[@id='ContentPlaceHolder2_lbl_TM_1']")
	private WebElement timeInfo1;
	
	@FindBy(xpath = "//span[@id='ContentPlaceHolder2_lbl_TZ_1']")
	private WebElement timeZoneInfo1;
	
	@FindBy(xpath = "ContentPlaceHolder2_lbl_DT_2")
	private WebElement dateInfo2;
	
	@FindBy(xpath = "//span[@id='ContentPlaceHolder2_lbl_TM_2']")
	private WebElement timeInfo2;
	
	@FindBy(xpath = "//span[@id='ContentPlaceHolder2_lbl_TZ_2']")
	private WebElement timeZoneInfo2;
	
	
	@FindBy(xpath = "//span[@id='ContentPlaceHolder2_lbl_DT_3']")
	private WebElement dateInfo3;
	
	@FindBy(xpath = "//span[@id='ContentPlaceHolder2_lbl_TM_3']")
	private WebElement timeInfo3;
	
	@FindBy(xpath = "//span[@id='ContentPlaceHolder2_lbl_TZ_3']")
	private WebElement timeZoneInfo3;
	
	@FindBy(xpath = "//span[@id='ContentPlaceHolder2_lblmsg']")
	private WebElement candidateNameForInterview;
	
	By confirmInterviewLocator = By.xpath("//*[@id='ContentPlaceHolder2_btnSelectInterview']");
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_btnSelectInterview']")
	private WebElement confirmInterviewBtnPopup;
	
	By yesToConfirmInterviewLocator = By.xpath("//*[@id='ContentPlaceHolder2_Btintconfirm']");
	@FindBy(xpath = "//*[@id='ContentPlaceHolder2_Btintconfirm']")
	private WebElement yesToConfirmInterviewBtn;
	
	
	public VendorRequisitionDetailsPage(WebDriver driver) {

		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	public void submitCandidatefrompool(String reqId) throws InterruptedException {
		
		try {
		submitCandidate.click();

		waitForPresenceOfElement(candName);
		waitForElementToBeClickable(candName);
		candidate.click();

		waitForPresenceOfElement(reqPage);
		

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		String todayAsString = dateFormat.format(today);

		dateField.sendKeys(todayAsString);

		Select stateOt = new Select(stateOtRules);
		stateOt.selectByVisibleText("United States - California");

		docUplaod1.sendKeys(System.getProperty("user.dir") + "//src//test//java//com//wsg//data//Medical Assistant.pdf");

		docUplaod2.sendKeys(System.getProperty("user.dir") + "//src//test//java//com//wsg//data//Presentaion.pdf");

		preview.click();
		
		waitForPresenceOfElement(previewCandPageTitle);
		waitForElementToBeClickable(previewCandPageTitle);
		
		submit.click();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
	
	
	public void acceptOffer(Map<String, Object> testData) throws InterruptedException {
		try
		{
		switchToVendorTab(testData.get("urlVendor").toString());
		driver.navigate().refresh();
		
		waitForPresenceOfElement(acceptbtn);
		waitForElementToBeClickable(acceptbtn);
		
		acceptLink.click();
		
		waitForPresenceOfElement(Yesbtn);
		waitForElementToBeClickable(Yesbtn);
		
		yesButton.click();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
	public void acceptInterviewRequest(Map<String, Object> testData)
	{
		switchToVendorTab(testData.get("urlVendor").toString());
		driver.navigate().refresh();
		WebElement confirmInterviewBtn = 
		driver.findElement(By.xpath("//td[contains(text(), '"+testData.get("candidateEmail").toString()+"')]/following-sibling::td/a[@data-original-title='Confirm Interview']"));
		confirmInterviewBtn.click();
	
		//selecting date-timeslot for scheduled interview by client
		waitForPresenceOfElement(confirmInterviewLocator);
		waitForElementToBeClickable(confirmInterviewLocator);
		dateTimeRadioSelect3.click();
		waitForElementToBeClickable(confirmInterviewLocator);
		confirmInterviewBtnPopup.click();
		
		waitForPresenceOfElement(yesToConfirmInterviewLocator);
		waitForElementToBeClickable(yesToConfirmInterviewLocator);
		yesToConfirmInterviewBtn.click();
		
	}
	
/*	public void checkNameOfCandidate(Map<String, Object> testData)
	{
		String nameOfCand = candidateNameForInterview.getAttribute("text");
		Assert.assertEquals(testData.get("firstName").toString(), nameOfCand);
	} */
}
