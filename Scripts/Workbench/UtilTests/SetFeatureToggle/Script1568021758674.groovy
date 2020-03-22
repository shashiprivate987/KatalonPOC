import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_Enable  Or  Disable  Feature  On  Realm'))

WebUI.setText(findTestObject('Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/input_Full Qualified Name of Feature__0iikt'), 
    toggleName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/div_Run Stager'))

