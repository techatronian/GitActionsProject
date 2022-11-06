package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TestData {
	
	public static String getprop(String keyData) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//config.properties");
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}		
		Properties prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop.getProperty(keyData);
	}	
	
	@DataProvider(name = "objectData")
	public Object[][] dataproviderObject(){
		return new Object[][] {
			{"admin@yourstore.com","admin"},
			{"test1@test.com","admin"},
			{"test2@test.com","admin"}
		};
	}	
	
	@DataProvider(name = "excelData")
	public Object[][] dataproviderExcel(){
		Object[][] arrObj = getExcelData(System.getProperty("user.dir")+ "//src//test//resources//ExcelData//loginCredentials.xlsx");
		return arrObj;		
	}
	
    public String[][] getExcelData(String fileName){
    	String[][] data =null;
    	try {
			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet("loginDetails");
			XSSFRow row = sheet.getRow(0);
			Cell cell;			
			//get row and col count
			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = row.getLastCellNum();
			data = new String[rowCount-1][colCount];
			
			for(int i = 1;i<rowCount;i++) {
				for(int j=0;j<colCount;j++) {
					row = sheet.getRow(i);
					cell = row.getCell(j);
					data[i-1][j] = cell.getStringCellValue();
				}
			}
			
			
		} catch (Exception e) {			
			System.out.println(e.getMessage());
		}
    	
    	return data;
    	
    }
	

}
