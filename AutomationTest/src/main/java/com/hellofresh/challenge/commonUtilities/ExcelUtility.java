package com.hellofresh.challenge.commonUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtility {
	File file;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sh;
	String filePath = System.getProperty("user.dir")+"\\TestData\\HelloFreshData.xlsx";
	
	public  void excelread(String sheetName) throws IOException{
		
		 file = new File(filePath);
		 fis = new FileInputStream(file);
		 wb = new XSSFWorkbook(fis);
		 sh = wb.getSheet(sheetName);
		
		}
		
		
	//return row and column count from excel
	public Map<String, Integer> excelRowColCount(String sheetName) throws IOException{
		excelread(sheetName);
	Row header = sh.getRow(0);
	int colNum = header.getLastCellNum();
	int rowNum = sh.getLastRowNum()+1;
	
	GlobalUtil.getLogger().info("Total number of column is:"+colNum);
	GlobalUtil.getLogger().info("Total number of column is:"+rowNum);
	
	Map<String, Integer> count = new HashMap<String, Integer>();
	count.put("rowNum", rowNum);
	count.put("colNum", colNum);
	return count;
    
    /*
     * 
     * 
    String header1 = header.getCell(0).getStringCellValue();
    String header2 = header.getCell(1).getStringCellValue();
     * if (header1.equals("UserName") && header2.equals("Password"))
    		{
        if(sh.getLastRowNum()<1){
            System.out.println("Sheet empty");
            GlobalUtil.getLogger().info("Login sheet is empty");
            
                     return null;
        }   
        else{
        	  }
                    
    		}
	return null;*/
	}
	
	
	public void readUname(String userName, String password, String sheetName, int row, int col) throws IOException{
		excelread(sheetName);
		userName= sh.getRow(row).getCell(col).getStringCellValue();
		password= sh.getRow(row).getCell(col).getStringCellValue();
		
	}
	
	public static void main(String[] args) throws IOException {
		ExcelUtility ex =new ExcelUtility();
		ex.excelRowColCount("Login");
	}
}
