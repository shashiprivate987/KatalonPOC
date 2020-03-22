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
import sourcing.util.ExcelToggleParamStoreStrategy
import sourcing.util.FileComparisonUtil





String sheetName = 'Shashi_pricingConditions'
String dataTableName = 'DataSet10'
String dataTableName1 = 'DataSet9'
//String columnHeader = 'toggles'

ExcelTestDataUtil testData = new ExcelTestDataUtil(sheetName, dataTableName);
ExcelTestDataUtil testData1 = new ExcelTestDataUtil(sheetName, dataTableName1);


//testData1.getAllDataFromAllRows()
println "PPPPPP : "+ testData1.getAllDataFromAllRows()

println "Numeric XXX: "+ testData.getCellValueForKey("sourcingEvent")
println "Date YYY: "+ testData.getCellValueForKey("rfpEventTemplate")

//testData.getCellValuesOfRow(1)
//println "testData.getCellValuesOfRow(1): "+ testData.getCellValuesOfRow(1)

//testData.getTestDataForGivenColumn("eventEndState")
//println "Column Data: "+testData.getTestDataForGivenColumn("eventEndState")


boolean compareSheets = FileComparisonUtil.validateAllSheetsInExcel("EventLotWithAPCTermContent.xls", "EventLotWithAPCTermContentActual.xls");
boolean compareSheet = FileComparisonUtil.validateExcelSheets("EventLotWithAPCTermContent.xls", "EventLotWithAPCTermContentActual.xls", "Pricing");

boolean compareSheet1 = FileComparisonUtil.validateExcelSheets("APCContractLinesWithCustomFormulaExp.xlsx", "APCContractLinesWithCustomFormula.xlsx", "Pricing Conditions");

//WebUI.verifyEqual(compareSheets, true, FailureHandling.STOP_ON_FAILURE)
//WebUI.verifyEqual(compareSheet, true, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyEqual(compareSheet1, true, FailureHandling.STOP_ON_FAILURE)



/*Map<String,String> testVal = testData.getCellValuesOfRow(1);
println "EnableToggles: "+testVal.get("EnableToggles");
println "DisableToggles: "+testVal.get("DisableToggles");
println "NewToggles: "+testVal.get("NewToggles");*/






