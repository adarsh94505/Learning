package com.wsg.pageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wsg.abstractComponents.AbstractComponent;

public class VendorViewCandidates extends AbstractComponent{
	
	private WebDriver driver;
	
	String candidatesID;
	
	By viewCandidateslink = By.xpath("//li[@id='viewcandidates']");
	@FindBy(xpath = "//li[@id='viewcandidates']")
	private WebElement viewCandLeftMenu;
	
	By reqIDSearchBox = By.xpath("//*[@id='ContentPlaceHolder1_txtReqID']");
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_txtReqID']")
	private WebElement requisitionID;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_tbFirstName']")
	private WebElement firstName;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_tbLastName']")
	private WebElement lastName;
	
	@FindBy(xpath = "//*[@id='ContentPlaceHolder1_btnSrch']")
	private WebElement searchBtn;
	
	By resultsTable = By.xpath("//*[@id='DataTables_Table_0']");
	
	@FindBy(xpath="//table[@id='DataTables_Table_0']/descendant::a[2]")
	private WebElement candidateID;
	
	public VendorViewCandidates(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
