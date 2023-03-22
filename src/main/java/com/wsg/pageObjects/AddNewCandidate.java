package com.wsg.pageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wsg.abstractComponents.AbstractComponent;

public class AddNewCandidate extends AbstractComponent{
	
	private WebDriver driver;
	
	//*[@id='ContentPlaceHolder1_txtfistname']
	
	//xpaths for filling the form of adding new candidate
	By first_Name = By.xpath("//*[@id='ContentPlaceHolder1_txtfistname']");
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtfistname']")
	private WebElement firstName;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtmiddle']")
	private WebElement middleName;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtlastname']")
	private WebElement lastName;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtemail']")
	private WebElement candEmail;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtphone']")
	private WebElement phoneNumber;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_pay_rate_f']")
	private WebElement candPayRate;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtsuite']")
	private WebElement candSuite;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtaddress1']")
	private WebElement address;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtpostal']")
	private WebElement postalCode;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtcity']")
	private WebElement city;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_ddlprivince']")
	private WebElement province;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtavailable']")
	private WebElement availabilityDate;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtsinnumber']")
	private WebElement ssnNumber;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txttimezone']")
	private WebElement timeZone; 
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtvendoremployeeid']")
	private WebElement vendorEmpID;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_ddlStateOT']")
	private WebElement stateOT;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_lstspeciality']")
	private WebElement specialty;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_documentUpload1']")
	private WebElement docUplaod1;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_documentUpload2']")
	private WebElement docUplaod2;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_documentUpload3']")
	private WebElement docUplaod3;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_btnPreview']")
	private WebElement preview;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_btnsubmit']")
	private WebElement submit;
	
	By previewCandPageTitle = By.xpath("//*[@class='page-title']");
	@FindBy(xpath = "//*[@class='page-title']")
	private WebElement previewPageTitle;
	
	@FindBy(xpath = "//i[@class='menu-icon icon-user']")
	private WebElement candidatesLeftMenu;
	
	By viewCandidateslink = By.xpath("//li[@id='viewcandidates']");
	@FindBy(xpath = "//li[@id='viewcandidates']")
	private WebElement viewCandLeftMenu;
	
	public AddNewCandidate(WebDriver driver) {

		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}
	
	
	
	public void newCandidateAddition (Map<String, Object> testData)
	{
		try
		{
			waitForPresenceOfElement(first_Name);
			waitForElement(first_Name);
			firstName.sendKeys(testData.get("firstName").toString());
			middleName.sendKeys(testData.get("middleName").toString());
			lastName.sendKeys(testData.get("lastName").toString());
			
			candEmail.sendKeys(testData.get("candidateEmail").toString());
			phoneNumber.sendKeys(testData.get("phone").toString());
			candPayRate.sendKeys(testData.get("candidatePayRate").toString());
			
			candSuite.sendKeys(testData.get("suite/apt").toString());
			address.sendKeys(testData.get("address").toString());
			postalCode.sendKeys(testData.get("zip").toString());
			
			city.sendKeys(testData.get("city").toString());
			
			Select provincedropdown = new Select(province);
			provincedropdown.selectByVisibleText(testData.get("state").toString());
			
			availabilityDate.sendKeys(testData.get("availability").toString());
			ssnNumber.sendKeys(testData.get("sSN").toString());
			
			Select timezone = new Select(timeZone);
			timezone.selectByVisibleText(testData.get("timeZone").toString());
			vendorEmpID.sendKeys(testData.get("vendorEmployeeID").toString());
			
			Select stateOTrule = new Select(stateOT);
			stateOTrule.selectByVisibleText(testData.get("stateOT").toString());

			Select specialtyOfCand = new Select(specialty);
			specialtyOfCand.selectByVisibleText(testData.get("specialty").toString());
			
			docUplaod1.sendKeys(System.getProperty("user.dir") + "//src//test//java//com//wsg//data//Medical Assistant.pdf");
			docUplaod2.sendKeys(System.getProperty("user.dir") + "//src//test//java//com//wsg//data//Presentaion.pdf");
			docUplaod3.sendKeys(System.getProperty("user.dir") + "//src//test//java//com//wsg//data//test.pdf");
			
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

	public VendorViewCandidates leftMenuViewCandidatesNavigation(String reqId)
	{
		try
		{
		candidatesLeftMenu.click();
		
		waitForPresenceOfElement(viewCandidateslink);
		waitForElement(viewCandidateslink);
		viewCandLeftMenu.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return new VendorViewCandidates(driver);
	}
}
