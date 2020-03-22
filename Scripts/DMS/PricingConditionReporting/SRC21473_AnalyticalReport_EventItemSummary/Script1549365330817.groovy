 /*
 * 
Report showing the Item Level Pricing Condition enabled and event count
 STEPS:
1. Create a Sourcing event using an Advanced Pricing Condition Term Enabled Template
2. Add all Items each having different Validity Type Like viz Monthly, Quarterly, Annually, Buyer defined, supplier defined, Perpetual

3. Perform the surrogate bid for 3 of the Items

4. Run ASM Data Load

5. Now create a report selecting Main Fact as 'Item Level Summery'

6. Measure as Event count and Surrogate bidding

7. Add validity Type as the Pivot Table Row Field and Allow Pricing condition as Page Field

8. Generate the report and view the results

Expected:
8. Event count should be 1 but the surrogate bid is 3 and each validity type has one item each
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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


//---------------------------------- Feature Toggle -------------------------------
WebUI.callTestCase(findTestCase('test-upstream/DMS/UtilTests/SetPricingCondtionFeatureToggle'), [:], 
    FailureHandling.STOP_ON_FAILURE)
//---------------------------------- Create RFP Event -------------------------------
WebUI.callTestCase(findTestCase('test-upstream/DMS/PricingConditionReporting/CreateSourcingEventWithAPC'), [:], FailureHandling.STOP_ON_FAILURE)
//---------------------------------- Custom Data Load -------------------------------
WebUI.callTestCase(findTestCase('test-upstream/DMS/PricingConditionReporting/CustomDataLoadViaSuperUser'), [:], FailureHandling.STOP_ON_FAILURE)
//---------------------------------- Create Sourcing Event Report -------------------------------
WebUI.openBrowser('')

String service = 'https://svcmachss.ariba.com'

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

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/tbody_Main FactEvent Item Summ'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectFact/span_Event Count_w-pml-icon'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Add To Report_w-pmi-item'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectFact/span_Next'))

WebUI.scrollToElement(findTestObject('Object Repository/Page_CreateReportConfigureEdge/span_Allow Pricing Conditions_'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportConfigureEdge/span_Allow Pricing Conditions_'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Add As_w-pmi-item'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectFact/span_Next'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectTimeRange/span_Year(s)_w-dropdown-pic-ct'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Report Time Period_w-dropdown-item'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_CreateReportSelectTimeRange/span_Run Report'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_GenerateReportPivot table/td_Yes'))

WebUI.verifyTextPresent('Yes', false)

WebUI.verifyTextPresent('Allow Pricing Conditions  ', false)

