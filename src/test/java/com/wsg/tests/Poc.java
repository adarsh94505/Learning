package com.wsg.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wsg.pageObjects.AddNewCandidate;
import com.wsg.pageObjects.ClientDashboardPage;
import com.wsg.pageObjects.ClientLoginPage;
import com.wsg.pageObjects.ClientNewRequisitionPage;
import com.wsg.pageObjects.VendorDashboardPage;
import com.wsg.pageObjects.VendorLoginPage;
import com.wsg.pageObjects.VendorRequisitionDetailsPage;
import com.wsg.pageObjects.VendorViewCandidates;
import com.wsg.testComponents.BaseTest;

public class Poc extends BaseTest 
{
	
	@Test (priority = 1, dataProvider = "getData")
	public void requisitionCycle(Map<String, Map<String, Object>> input) throws IOException, InterruptedException 
	{
		Map<String, Object> testData = input.values().iterator().next();

		ClientLoginPage clientLoginPage = new ClientLoginPage(driver);

		clientLoginPage.goToClientUrl(testData);
		

		ClientDashboardPage clientDashboardPage = clientLoginPage.logInClient(testData);
		Assert.assertTrue(clientLoginPage.verifyLoginSuccess());
		clientLoginPage.verifyLoginSuccess();
		clientDashboardPage.verifyNewRequisitionPopUp();

		ClientNewRequisitionPage clientNewRequisitionPage = clientDashboardPage.requisitionEntry(testData);

		String newReqId = clientNewRequisitionPage.createRequisition(testData);

		VendorLoginPage vendorLoginPage = new VendorLoginPage(driver);

		vendorLoginPage.goToVendorUrl(testData);

	//	VendorDashboardPage vendorDashboardPage = vendorLoginPage.logInVendor(testData);
	//	VendorRequisitionDetailsPage vendorRequisitionDetailsPage = vendorDashboardPage.openNewRequisition(newReqId);
		//vendorRequisitionDetailsPage.submitCandidatefrompool(newReqId);

		//logging into vendor portal and adding a new candidate with the left menu option to submit a candidate
		VendorDashboardPage vendorDashboardPage = vendorLoginPage.logInVendor(testData);
		AddNewCandidate addnewCandidate = vendorDashboardPage.submitNewCandidateNavigation(newReqId);
		addnewCandidate.newCandidateAddition(testData);
		
		//moving to view all candidates screen to extract candidate id
	//	VendorViewCandidates vendorViewCandidates = addnewCandidate.leftMenuViewCandidatesNavigation(newReqId);
		
	//	String candidID= vendorViewCandidates.getCandidateID(testData, newReqId);		
		clientNewRequisitionPage.presentingCandidateToClient(testData);
		VendorRequisitionDetailsPage vendorRequisitionDetailsPage = clientNewRequisitionPage.interviewSchedule(testData);
		
		//switching to Vendor portal and accepting the interview request from requisition detail screen
		vendorRequisitionDetailsPage.acceptInterviewRequest(testData);
		
		
		
		
		
		
		
		//clientNewRequisitionPage.offerCandidate(testData);

			//	vendorRequisitionDetailsPage.acceptOffer(testData);

	/*	ClientAllProfessionalsPage clientAllProfessionalsPage = clientDashboardPage.goToAllProfessionals(testData);

		clientAllProfessionalsPage.markAsCompliant(testData, newReqId);
		//Thread.sleep(10000);
		
		
		//Assert.assertTrue(true);
		System.out.println("test passed successfully"); */
	}



	@DataProvider
	public Object[][] getData() throws IOException 
	{
		List<Map<String, Map<String, Object>>> data = 
		getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//com//wsg//data//clientLogin.json");

		Object[][] dataArray = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) 
		{
			dataArray[i][0] = data.get(i);
		}

		return dataArray;
	}

}