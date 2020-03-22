 /*
 * 
 * Event Level Summary report showing details for all the Pricing Condition attributes overridden
 
 STEPS:
1. Go to Create and click on the Analytical Report
2. Provide Title related details at the 'Source Data' Tab

3. Select ' Event Level Summary' under the Main Fact

4. Select 'Event Count' under Measure

5. Click on the 'Pivot Table'

6. Scroll to click on the 'Allow Pricing Conditions' under the Event Information and mention it as Row Field

7. Click on the Refine Data to see "Event Publish Date (Calendar)" and select the option "Fixed date range from" and select the range of dates

8. Click on Run Report

9. Click on the Chart Tab on the top

10. Click on Dashboard Tab on the top

Expected:

1. Should open up an Analytical report creation page
3 & 4. Should be able to select Fact and Measures

6. Allow Pricing Condition should be set to Row Field

7. Range of dates should be selected in which the event is created as preReq data

8. Report should show the Table having Count as Measure of number of events sorted based on how many events are with Allowed Pricing Condition YES and how many are NO

9. Graph should be plot with X-Allow Pricing Condition and Y-Event Count

10. Should show the Graph notation as well as the tabular details of Event Count against the Allowed Pricing Condition boolean
 * 
 */ import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/*WebUI.openBrowser('')

WebUI.callTestCase(findTestCase('test-upstream/DMS/UtilTests/SetPricingCondtionFeatureToggle'), [:],
	FailureHandling.STOP_ON_FAILURE)
*/

//---------------------------------- Feature Toggle -------------------------------
WebUI.callTestCase(findTestCase('test-upstream/DMS/UtilTests/SetPricingCondtionFeatureToggle'), [:],
	FailureHandling.STOP_ON_FAILURE)
//---------------------------------- Create RFP Event -------------------------------
WebUI.callTestCase(findTestCase('test-upstream/DMS/PricingConditionReporting/CreateSourcingEventWithAPC'), [:], FailureHandling.STOP_ON_FAILURE)
//---------------------------------- Custom Data Load -------------------------------
WebUI.callTestCase(findTestCase('test-upstream/DMS/PricingConditionReporting/CustomDataLoadViaSuperUser'), [:], FailureHandling.STOP_ON_FAILURE)
//---------------------------------- Create Sourcing Event Item Report -------------------------------

WebUI.openBrowser('')

String service = 'https://svcmachss.ariba.com/'

def eventSummaryReport = 'EventSummaryReport_' + System.currentTimeMillis()

WebUI.navigateToUrl(service + '/Sourcing/Main/?realm=s4All-18')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/input_User__bt5lrc'), 'customersupportadmin')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

WebUI.scrollToElement(findTestObject('Object Repository/Page_TestCentralPage/a_Create Analytical Report'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/input_User__njzfkc'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_TestCentralPage/a_Create Analytical Report'))

WebUI.setText(findTestObject('Object Repository/Page_CreateReportSelectFact/input___y3m7b'), eventSummaryReport)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectFact/span_Contract Clause_w-dropdow'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_CreateReportSelectFact/span_Event Count_w-pml-icon-for-event'))

String dynamicId = 'Event Count'

String xpath = ('//span[contains(text(),"' + dynamicId) + '")]'

TestObject to = new TestObject('objectName')

to.addProperty('xpath', ConditionType.EQUALS, xpath)

CustomKeywords.'common.util.aribakeywords.aribaClick'(to)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Add To Report_w-pmi-item'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectFact/span_Next'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportConfigureEdge/a_Allow Pricing Conditions'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Add As_w-pmi-item'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectFact/span_Next'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectTimeRange/span_Year(s)_w-dropdown-pic-ct'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Report Time Period_w-dropdown-item'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectTimeRange/span_Run Report'))

WebUI.verifyTextPresent('Yes', false)

WebUI.verifyTextPresent('Allow Pricing Conditions  ', false)