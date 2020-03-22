import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor as JavaScriptExecutor
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.ExcelData as ExcelData
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.reader.ExcelFactory as ExcelFactory
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import sourcing.util.sourcingKeywords as sourcingKeywords
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import static org.junit.Assert.*
import org.apache.poi.xssf.usermodel.XSSFCell as XSSFCell
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import org.junit.Test as Test
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver

import common.util.RESTValidator
import common.util.aribakeywords as Ariba
import sourcing.util.sourcingKeywords as Sourcing
import java.util.List
import sourcing.util.ExcelToggleParamStoreStrategy

//--------------------------------------------------------------------------------------------------------------------------------------

/*
 * Excel Validation
 * 
 * 1. Download Excel 
 * 2. Make sure it is placed in the right download location
 * 3. Keep the expected event sheet in the DMS/ExcelValidation dir
 * 3. Call the Excel sheet wise comparison API 
 */

//String usrDir = RunConfiguration.getProjectDir()

//TestObject fileToBeDwnld = Ariba.createTestObject('//a[@awname=\'ASPExcelImport:exportExcelDocumentAction\']')

//String excelName = sourcingEvent.trim() + '.xls'

//Ariba.downloadFile(fileToBeDwnld, excelName, 3, 3)

//WebUI.comment('Downloaded')

//String downloadedFilePath = '/Downloads/' + excelName

/*ExcelData actualSheet = ExcelFactory.getExcelDataWithDefaultSheet((usrDir + '/') + downloadedFilePath, 'Pricing Conditions', 
    false)*/


String expFile = "ExpectedEventLotWithAPCTermContent.xls";
String actFile = "ExpectedEventLotWithAPCTermContent.xls";


String usrDir = RunConfiguration.getProjectDir();
String expFilePath = usrDir + "/Test Files/dms/ExcelValidations/" + expFile
String actFilePath = /*System.getProperty("user.home")*/ usrDir + "/Downloads/" + actFile


def actualMap = CustomKeywords.'common.util.RESTValidator.fileParser'(actFilePath);
def expectedMap = CustomKeywords.'common.util.RESTValidator.fileParser'(expFilePath);

boolean isEqual = RESTValidator.mapDiff(actualMap, expectedMap, "123", null, null);


WebUI.verifyEqual(isEqual, true, FailureHandling.STOP_ON_FAILURE);
//WebUI.closeBrowser()

