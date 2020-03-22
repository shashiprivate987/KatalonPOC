package sourcing.util2

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class UtilConstants {
	public static final String TESTDATA_FILE = "TestData.xlsx";
	//public static final String FILEPATH = "/Users/i331477/git/S4MicroServiceKatalon/Test Files/dms/ExcelValidations/" + FILENAME;
	public static final String TOGGLE_SHEET_NAME = "Toggles";
	public static final String PARAMETER_SHEET_NAME = "Parameters";
	public static final String FILEPATH = RunConfiguration.getProjectDir();
	public static final String FILE_RELATIVE_PATH = "/Test Files/dms/ExcelValidations/";
}
