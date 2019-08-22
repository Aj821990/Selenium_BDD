package com.hellofresh.challenge.commonUtilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ReadExcelData {

	XLUtils xlread = new XLUtils();
	GlobalUtil globalutil = GlobalUtil.getInstance();
	String excelPath=System.getProperty("user.dir")+"\\TestData\\TestData.xlsx";
	
	
	public Map<String,String> getDataFromExcel(int rowNum){
		Map<String,String> registerData = new HashMap<String, String>();
		try {
		/*	int rowNum=XLUtils.getRowCount(excelPath, "Sheet1");
		//	int colCount=XLUtils.getCellCount(excelPath,"Sheet1",0);
			
			System.out.println("rowNum is:"+rowNum);
			for(int row =0; row<=rowNum;row++){
			*/
				String password = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 1);
				String title = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 2);
				String firstName = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 3);
				String lastName = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 4);
				String dateOfBirth = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 5);
				String adddress = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 6);
				String city = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 7);
				String state = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 8);
				String postCode = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 9);
				String country = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 10);
				String mobileNum = XLUtils.getCellData(excelPath, "Sheet1", rowNum, 11);
				
				//registerData.add(new Object[]{password,title,firstName,lastName,dob,adddress,city,state,postCode,country,mobileNum});
				
				registerData.put("password", password);
				registerData.put("title", title);
				registerData.put("firstName", firstName);
				registerData.put("lastName", lastName);
				registerData.put("dateOfBirth", dateOfBirth);
				registerData.put("adddress", adddress);
				registerData.put("city", city);
				registerData.put("state", state);
				registerData.put("postCode", postCode);
				registerData.put("country", country);
				registerData.put("mobileNum", mobileNum);
			//}
					}
		catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		System.out.println("excel data is:"+registerData);
		return registerData;
		
	}
	
	
	/*public static void main(String[] args) {
		ReadExcelData generic = new ReadExcelData();
		generic.getDataFromExcel(rownum);
		System.out.println(generic.getDataFromExcel().get("password"));
		System.out.println(generic.getDataFromExcel().get("title"));
		System.out.println(generic.getDataFromExcel().get("firstName"));
		System.out.println(generic.getDataFromExcel().get("lastName"));
		System.out.println(generic.getDataFromExcel().get("adddress"));
		System.out.println(generic.getDataFromExcel().get("city"));
		System.out.println(generic.getDataFromExcel().get("postCode"));
		System.out.println(generic.getDataFromExcel().get("country"));
		System.out.println(generic.getDataFromExcel().get("mobileNum"));
		

	}*/
}
