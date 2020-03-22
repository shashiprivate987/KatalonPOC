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


/**
 * Util Class Name:ExcelTestDataUtil
 * Desc: Static class and methods hence no constructor required for initialization
 * Util Class Name: ExcelToggleParamStoreStrategy
 * Desc: This class accepts params like sheetName, dataTableName and instantiates class
 * 
 * API Name: testData.getTestDataForGivenColumn(toggleColumn);
 * Desc: 
 * 1. Accepts column header as parameter
 * 2. Gets List of values for the given column header
 * 
 * APC Name: register.registerTogglesAndGetActualToEnable(toggleList);
 * Desc:
 *    Essentially this concept is designed keeping the condition that when a set of test cases in a suite start the execution on any environment then the
 * fresh Toggles instance is created to register toggles and all are cleared after the suite completed execution
 * 
 * 1.The list returned from the other api is sent to this as parameter
 * 2. Verifies whether the toggle list in the toggle sheet registry has the value in the format [toggle:set]/[toggle:unset]
 * 3. if [toggle:set] is present then the registring is skipped, similarily for [toggle:unset]
 * 4. If toggle with name is [APC:set] is already part of registry and the new list contains [APC:unset] then the [APC:set] is removed and 
 * 	  [APC:unset] is registered and similarly otherwise also and return the toggle which need to be set
 * 5. Similarly for parameters there is no set unset concept and hence the parameters and verified against the parametr registry, 
 *    the parameter registred if it is not present and returned the same  
 *
 *
 * API Name: register.clearAllToggles();
 * Desc: 
 *  Essentially this concept is designed keeping the condition that when a set of test cases in a suite start the execution on any environment then the
 * fresh Parameters instance is created to register parameters and all are cleared after the suite completed execution
 * 
 * 1. All the toggles which are registered in the Toggles sheet will be removed
 * 2. This api is used during after suite in the listener because if we look at the actual run,
 *    we run all our tests in a suite against any environment, so when a run completes on a perticular suite then
 *    the registry has to be cleared
 *   
 * API Name: register.clearAllParameters();
 * Desc:
 * 1.  All the parameters which are registered in the Parameters sheet will be removed  
 * 2. This api is used during after suite in the listener because if we look at the actual run,
 *    we run all our tests in a suite against any environment, so when a run completes on a perticular suite then
 *    the registry has to be cleared
 *
 */

String sheetName = "Shashi_pricingConditions";
String dataTableName ="DataSet8";
String toggleColumn = "Toggles";
String paramColumn = "Parameters";


ExcelTestDataUtil testData = new ExcelTestDataUtil(sheetName, dataTableName);
ExcelToggleParamStoreStrategy register = new ExcelToggleParamStoreStrategy();

List<String> toggleList = testData.getTestDataForGivenColumn(toggleColumn);
List<String> toggledToBeSet = register.registerTogglesAndGetActualToEnable(toggleList);
println "Toggles to be set: \n"+ toggledToBeSet.toString();


List<String> paramList = testData.getTestDataForGivenColumn(paramColumn);
List<String> paramsToBeEnabled = register.registerParametersAndGetActualToEnable(paramList);
println "Parameters to be enabled: \n"+ paramsToBeEnabled.toString();








register.clearAllToggles();

register.clearAllParameters();




