import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import sourcing.util.ExcelTestDataUtil
import java.util.List



/**
 * Util Class Name: ExcelTestDataUtil
 * Desc: Accpets params sheetName and dataTableName through constructor
 * 
 * API Name: testData.getAllDataFromAllRows()
 * Desc: Returns a list of rows having map of cell data which are present inside the Data Table
 * 
 * API Name: testData.getCellValueForKey("rfpEventTemplate");
 * Desc: Returns the value for the key/column header from the default first row
 * 
 * API Name: testData.getCellValuesOfRow(desiredRowNum);
 * Desc: Returns map of key/value pair of the entire row in the data table for the mentioned row number
 * 
 * API Name: testData.getTestDataForGivenColumn(columnHeader);
 * Desc: returns all the values for the mentioned column Header key
 * 
 */


String sheetName = "Shashi_pricingConditions";
String dataTableName ="DataSet9";
String columnHeader = "eventContentFileName";

ExcelTestDataUtil testData = new ExcelTestDataUtil(sheetName, dataTableName);


List<HashMap<String,String>> allRowValues =  testData.getAllDataFromAllRows();
println "All values from each row for the dataset: \n "+ allRowValues;

String cellValue =  testData.getCellValueForKey("rfpEventTemplate");
println "cell values for 'rfpEventTemplate' \n" + cellValue;

int desiredRowNum = 1;
Map<String, String> dataSetForRow = testData.getCellValuesOfRow(desiredRowNum);
println "Data set for the given row :\n" + dataSetForRow;

List<String> columnData = testData.getTestDataForGivenColumn(columnHeader);
println "Values for given column header: " + columnData;
