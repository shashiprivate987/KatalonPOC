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

WebUI.openBrowser('')

String service = 'https://svcmachss.ariba.com/'

def eventName = 'APC_Event_' + System.currentTimeMillis()

String eventContent = 'APCEventItemsImports.xls'

String eventTemplate = 'APCSourcingTemplate'

WebUI.navigateToUrl(service + '/Sourcing/Main/?realm=s4All-18')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/input_User__bt5lrc'), 'customersupportadmin')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/input_User__njzfkc'))

//WebUI.scrollToElement(findTestObject('Page_TestCentralPage/a_Create Sourcing Event'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/a_Create Sourcing Event'))

WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Name of event__kmokuc'), eventName)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_TestStagerForm/select_(no selection)RFIRFPRFP'), '1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_TestStagerForm/select_(no selection)draftprev'), '1', true)

WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Event excel file to uplo'), eventContent)

WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Event Template Name__vhy'), eventTemplate)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('div_Run Stager'))
