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
import sourcing.util.FileComparatorUtil


/**
 * Util Class Name: FileComparatorUtil
 * Desc: Static class and methods hence no constructor required for initialization
 * API Name: FileComparatorUtil.validateExcelSheets(expectedFile, actualFile, sheetToBeCompared);
 * Desc:
 * 1. The above api accepts the expected, actual files and sheetToBeCompared as the i/p parameters
 * 2. Does row comparision and returns false for any discripency
 * 3. Does deep compare for each cell in the sheet
 * 4. For any descripency of the cell values then a list stores all the values till end of cell compare for the sheet
 * 5. Logs the error values in a format of "Sheet Name, <Cell Header,Value>" saying what is differen from expected to actual
 * 6. Fails the verification incase of even a single descripency of data
 * 
 * 
 * ACP Name: FileComparatorUtil.validateAllSheetsInFile(expectedFile,actualFile);
 * Desc: 
 * 1. This will get all the sheets for the iteration
 * 2. Compares the sheet count and sheet names from the expected and actual files and returns false for any discripency
 * 3. Does row comparision and returns false for any discripency for each sheet
 * 4. Does deep compare for each cell in the sheet
 * 5. For any descripency of the cell values then a list stores all the values till end of cell compare for the sheet and same is done for all sheets
 * 6. Logs the error values in a format of "Sheet Name, <Cell Header,Value>" saying what is differen from expected to actual
 * 7. Fails the verification incase of even a single descripency of data
 * 
 */




String expectedFile = "ExpectedEventLotWithAPCTermContent.xls";
String actualFile = "ActualEventLotWithAPCTermContent.xls";
String sheetToBeCompared = "Pricing Conditions";



//boolean sheetCompare = FileComparatorUtil.validateExcelSheets(expectedFile, actualFile, sheetToBeCompared);
//WebUI.verifyEqual(sheetCompare, true, FailureHandling.STOP_ON_FAILURE)

boolean fileCompare = FileComparatorUtil.validateAllSheetsInFile(expectedFile,actualFile);
WebUI.verifyEqual(fileCompare, true, FailureHandling.STOP_ON_FAILURE)






