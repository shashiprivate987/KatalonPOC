package sourcing.util

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class ExcelInit {

	private String fileFullPath;
	private String excelName;
	private String sheetName;
	private String dataTableName;
	private String columnHeader;

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;


	public ExcelInit(String fileFullPath, String sheetName) {

		this.fileFullPath = fileFullPath;
		this.excelName = excelName;
		this.sheetName = sheetName;

		initExcelDetails(fileFullPath, sheetName);
	}


	private void initExcelDetails(String fileFullPath, String sheetName){
		FileInputStream fis = new FileInputStream(fileFullPath);
		this.workbook = new XSSFWorkbook(fis);
		this.sheet = workbook.getSheet(sheetName);
	}


	public XSSFSheet getSheetObj() {
		return sheet;
	}

	public String getworkbookObj() {
		return workbook;
	}

	public String getFileFullPath() {
		return fileFullPath;
	}


	public String getExcelName() {
		return excelName;
	}


	public String getSheetName() {
		return sheetName;
	}


	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}


	public String getDataTableName() {
		return dataTableName;
	}


	public void setDataTableName(String dataTableName) {
		this.dataTableName = dataTableName;
	}


	public String getColumnHeader() {
		return columnHeader;
	}


	public void setColumnHeader(String columnHeader) {
		this.columnHeader = columnHeader;
	}
}
