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

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testCentral'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Create Sourcing Event'))

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/input_Name of event__vusb0b'))

//String eventName = sourcingEvent + System.currentTimeMillis()

WebUI.setText(findTestObject('Object Repository/input_Name of event__vusb0b'), sourcingEvent)

String eventType = eventType

WebUI.selectOptionByValue(findTestObject('Page_TestStagerForm/select_(no selection)RFIRFPRFP'), eventType, true)

WebUI.setText(findTestObject('Object Repository/input_Event Template Name__qty'), rfpEventTemplate)

WebUI.setText(findTestObject('Object Repository/input_Event excel file to uplo'), eventContentFileName)

//WebUI.selectOptionByValue(findTestObject('Object Repository/select_(no selection)draftprev'), '6', true)

//WebUI.selectOptionByValue(findTestObject('Object Repository/select_(no selection)draftprev'), eventEndState, true)

String eventEndState = eventEndState

WebUI.selectOptionByValue(findTestObject('Object Repository/select_(no selection)draftprev'), eventEndState, true)

WebUI.setText(findTestObject('Object Repository/input_Number Of Bids__yf32cb'), numberOfBids)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Run Stager'))