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

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/b_TA'))
//
//WebUI.setText(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/input_User__bt5lrc'), 
//    'NewUserName')
//
//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/input_User__njzfkc'))
//
//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_sourcing'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_Create Sourcing Event'))

WebUI.setText(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/input_Name of event__wedmvd'), 
    eventName)

WebUI.setText(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/input_Event Template Name__wtwb_b'), 
    templateName)

WebUI.setText(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/input_Event excel file to upload__mkuj'), 
    eventContentExcel)

WebUI.selectOptionByValue(findTestObject('Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/event_type'), '1', 
    true)

WebUI.selectOptionByValue(findTestObject('Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/event_state'), 
    '4', true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/div_Run Stager'))

