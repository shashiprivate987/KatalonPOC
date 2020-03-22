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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import common.util.RESTValidator as RESTValidator

// Run test case to get document ID and event name

def arrayList = WebUI.callTestCase(findTestCase('DMS/CleanSheet/DMSTC-2292_CreateSourcingEvenWithCleanSheet'), [:], FailureHandling.STOP_ON_FAILURE)

//String docId = 'Doc8983946'
//arrayList[0]
String docId =arrayList[0]
docId =docId.split('-')[0]
docId=docId.trim()
print(docId)
//String eventName = 'RFP_CleanSheet1578642816256'
//arrayList[1]
String eventName =arrayList[1]

// Get event content details

ResponseObject getEventContentResponse = WebUI.callTestCase(findTestCase('DMS/CleanSheet/API/GetCleanSheetEvent'), 
    [('docId'):docId], FailureHandling.STOP_ON_FAILURE)
RESTValidator.logInfo(getEventContentResponse)

// Verify if content changes title contains String "Computer suit"

def getContentChangesTitle = CustomKeywords.'common.util.RESTValidator.getPropertyValue'(getEventContentResponse, 
    'title')
def checkTitle = RESTValidator.getPropertyIndex(getEventContentResponse, 'items[x].title', 'Pricing')
def checkTitle2 = RESTValidator.getPropertyIndex(getEventContentResponse, 'items['+checkTitle+'].children[x].itemType', 'Line Item')
def costGroupIndex = RESTValidator.getPropertyIndex(getEventContentResponse, 'items['+checkTitle+'].children['+checkTitle2+'].terms[x].title', 'AmortizationC')

assert costGroupIndex >= 0 : 'Cost group AmortizationC does not exist in response'

// Close window and end the test

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_TA..'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/End Test'))

WebUI.closeBrowser()