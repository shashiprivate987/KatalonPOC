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

def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
def templateName ='Request for Proposal_CleanSheet'
String appLoginUrl = service + '/Sourcing/Main?realm='+realm

////---------------------------------- Create RFP Event -------------------------------
//WebUI.callTestCase(findTestCase('DMS/CleanSheet/DMSTC-2292_CreateSourcingEvenWithCleanSheet'), [:], FailureHandling.STOP_ON_FAILURE)

////---------------------------------- Create Sourcing Event Report -------------------------------
//WebUI.openBrowser('')




String userName = 'customersupportadmin'

def arrayList = WebUI.callTestCase(findTestCase('DMS/CleanSheet/DMSTC-2292_CreateSourcingEvenWithCleanSheet'), [:], FailureHandling.STOP_ON_FAILURE)
String docId =arrayList[0].split('-')
docId=docId.trim()
String sourcingEvent =arrayList[1]

WebUI.closeBrowser()


//Making sure attributes are loaded into Reporting Manager->
WebUI.openBrowser('')
WebUI.navigateToUrl(appLoginUrl)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))
WebUI.setText(findTestObject('Object Repository/testcentralpageusername'), userName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

WebUI.callTestCase(findTestCase('DMS/UtilTests/AccessManageTemplates'), [('templateName') : templateName],
	FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Request for Proposal'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Template Edit'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Content'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_AmortizationC'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Cost Group Edit'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/tr_Numberofdecimalplaces'))

WebUI.setText(findTestObject('Object Repository/input_Numberofdecimalplaces__6pw92d'), '4')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_OK'))

//Repeating it to put back value2
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_AmortizationC'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Cost Group Edit'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/tr_Numberofdecimalplaces'))
WebUI.setText(findTestObject('Object Repository/input_Numberofdecimalplaces__6pw92d'), '2')
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_OK'))


CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_Next_2'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_Exit'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_return to project'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Actions'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Template publish'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Back Template'))

//WebUI.closeBrowser()
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testCentral'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))


//---------------------------------- Custom Data Load -------------------------------
WebUI.callTestCase(findTestCase('DMS/CleanSheet/CustomDataLoadViaSuperUser'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.closeBrowser()

WebUI.openBrowser('')
WebUI.navigateToUrl(appLoginUrl)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/testcentralpageusername'), userName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))


//Running ItemAttributesReportingTask, ASM Pull via stager
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Run Schedule Task For Realm Node Assignment'))

WebUI.setText(findTestObject('Object Repository/input_Task Name__26unxd'), 'ItemAttributesReportingTask')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Run Stager'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Run ASMPull'))



//WebUI.openBrowser('')
//WebUI.navigateToUrl(appLoginUrl)

//Creating Analytical Report

WebUI.waitForElementPresent(findTestObject('Object Repository/a_Test Central'), 300)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/testcentralpageusername'), userName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

def eventSummaryReport = 'EventSummaryReport_' + System.currentTimeMillis()

WebUI.scrollToElement(findTestObject('Object Repository/Page_TestCentralPage/a_Create Analytical Report'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/input_User__njzfkc'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_TestCentralPage/a_Create Analytical Report'))

WebUI.setText(findTestObject('Object Repository/Page_CreateReportSelectFact/input___y3m7b'), eventSummaryReport)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectFact/span_Contract Clause_w-dropdow'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/tbody_Main ItemAttributes'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_AmortizationC (USD)'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Add To Report_w-pmi-item'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_TotalCost1'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Add To Report_w-pmi-item'))


CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectFact/span_Next'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Amortization_PivotLayout'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_ColumnField'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_AvailableHierarchies'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_AvailableFields'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_EventTitle'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Page Field'))

//WebUI.scrollToElement(findTestObject('Object Repository/a_EventId'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_EventId'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_RowField'))




CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectFact/span_Next'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectTimeRange/span_Year(s)_w-dropdown-pic-ct'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Report Time Period_w-dropdown-item'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Report Most Recent_w-dropdown-item'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/Page_Ariba Spend Management/div_1_RefineData'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectTimeRange/span_Run Report'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Event Title Expand'))

String object="//a[text()="+"'"+sourcingEvent+"']"

CustomKeywords.'sourcing.util2.sourcingKeywords.clickOnDynamicXpath1'(object);

WebUI.verifyElementText(findTestObject('Object Repository/a_AmortizationCostGroup Value'), "16")

//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_GenerateReportPivot table/td_Yes'))

//WebUI.verifyTextPresent('Yes', false)
//
//WebUI.verifyTextPresent('Allow Pricing Conditions  ', false)

