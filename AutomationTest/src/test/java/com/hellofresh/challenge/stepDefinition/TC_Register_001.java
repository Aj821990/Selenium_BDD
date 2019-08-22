package com.hellofresh.challenge.stepDefinition;

import java.util.Map;

import com.hellofresh.challenge.commonUtilities.GlobalUtil;
import com.hellofresh.challenge.commonUtilities.ReadExcelData;
import com.hellofresh.challenge.commonUtilities.XLUtils;
import com.hellofresh.challenge.pageObjects.RegisterPage;

import cucumber.api.java.en.Then;

public class TC_Register_001 {

	GlobalUtil globalUtil = GlobalUtil.getInstance();
	XLUtils xlread = new XLUtils();
	ReadExcelData read = new ReadExcelData();
	RegisterPage rp = new RegisterPage(globalUtil.getDriver());
	String excelPath = System.getProperty("user.dir")
			+ "\\TestData\\TestData.xlsx";

	@Then("^Click Sign in button$")
	public void click_Sign_in_button() throws Throwable {
		rp.clickSignIn();
		GlobalUtil.getLogger().info("user clicked on signup button");
		
	}

	@Then("^Fill \"([^\"]*)\" address to create an account$")
	public void fill_address_to_create_an_account(String enterEmail)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions.

		rp.setEmail(enterEmail);
		GlobalUtil.getLogger().info("user entered the Email");
	}

	@Then("^Click Create an account$")
	public void click_Create_an_account() throws Throwable {
		rp.clickCreateAccount();
		GlobalUtil.getLogger().info("user clicked on create account button");

	}

	@Then("^Fill all fields with correct data \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void fill_all_fields_with_correct_data(String password,
			String title, String fName, String lName, String Dob,
			String address, String city, String state, String postCode,
			String country, String mobNum) throws Throwable {
		// int rowNum=XLUtils.getRowCount(ex, "Sheet1");
		int rowNum = XLUtils.getRowCount(excelPath, "Sheet1");
		for (int row = 1; row <= rowNum; row++) {
			Map<String, String> regData = read.getDataFromExcel(row);
			rp.clickTitle(regData.get("title"));
			rp.setFName(regData.get("firstName"));
			rp.setLName(regData.get("lastName"));
			rp.setPassword(regData.get("password"));

			String dob = regData.get("dateOfBirth");

			Map<String, String> mapDob = globalUtil.splitDate(dob);
			System.out.println("before select day"+mapDob.get("date"));
			rp.setDobDay(mapDob.get("date"));
			System.out.println("day is selected");
			System.out.println("month isselected"+mapDob.get("month"));
			rp.setDobMonth(mapDob.get("month"));
			System.out.println("month isselected");
			rp.setDobYear(mapDob.get("year"));
			System.out.println("year is selected");

			rp.setAddress(regData.get("adddress"));
			rp.setCity(regData.get("city"));
			rp.setState(regData.get("state"));
			rp.setPostCode(regData.get("postCode"));
			rp.setCountry(regData.get("country"));

			rp.setMobPhone(regData.get("mobileNum"));
			
			rp.clickRegister();
		}
		

	}

	@Then("^Click Register button$")
	public void click_Register_button() throws Throwable {
rp.clickRegister();
	}

}
