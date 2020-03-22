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



String userName = 'customersupportadmin'
def rfpEventTemplate = 'Request for Proposal_CleanSheet'

String fileName = 'RFP_CleanSheet7.xls'
String eventType='1'      //RFP
String eventEndState='0'  //draft state
String numberOfBids='1'

def Template = 'Request for Proposal_CleanSheet'

def sourcingEvent = 'RFP_CleanSheet' + System.currentTimeMillis()
def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
String appLoginUrl = service + '/Sourcing/Main?realm='+realm

WebUI.openBrowser('')
WebUI.navigateToUrl(appLoginUrl)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/testcentralpageusername'), userName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

WebUI.callTestCase(findTestCase('DMS/UtilTests/CreateEvent'), [('sourcingEvent') : sourcingEvent, ('eventType') : eventType
	, ('rfpEventTemplate') : Template, ('eventContentFileName') : fileName, ('eventEndState') : eventEndState
	, ('numberOfBids') : numberOfBids], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(3)

WebUI.callTestCase(findTestCase('DMS/Stager/ViewEvent'), null)

WebUI.callTestCase(findTestCase('DMS/UtilTests/EditSourcingEvent'), null)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/RespStartonPublish'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Content'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Event_Content'))
WebUI.delay(3)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/RawMaterialCostGroupLink'))

String filePath = 'Test Files/dms/Amortization_Costs.xls'
WebUI.callTestCase(findTestCase('DMS/UtilTests/ImportCostGroupContent'), [('filePath') : filePath], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Object Repository/TotalCostValue'), "21")

def t1=WebUI.getText(findTestObject('Object Repository/TotalCostValue'))
println t1
WebUI.setText(findTestObject('Object Repository/InputText_Money_contentUI'), t1)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/SummaryTabrfpEvent'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/PublishEventButton'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/MonitorEvent'))

/*
 * SurrogateBid 
 *     This test case allows the buyer to perform surrogate bid if the event is in view. Basically clicks on 
 *     Supplier tab and perform surrogate bid if submit_bid=1 else it will navigate to Supplier view of event
 */
WebUI.delay(3)
String submit_bid='0'
String CostGroupfilePath='1'
WebUI.callTestCase(findTestCase('DMS/UtilTests/SurrogateBid'), [('sourcingEvent') : sourcingEvent, ('submit_bid') : submit_bid,
	('filePath') : filePath], FailureHandling.STOP_ON_FAILURE)

/*
 * Updating value for  cost group & submitting supplier response
 */
WebUI.delay(3)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Open_Cost_GroupView'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/tr_Term1'))

WebUI.setText(findTestObject('Object Repository/tr_Term1'), '3')

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Actions'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Actions_CostGroup'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/costgroupUpdateTotalLink'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Done'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Done_CostGroupView'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Update Totals'))
WebUI.delay(3)
def totalCostFormulavalue=WebUI.getText(findTestObject('Object Repository/TotalCost1ValueObject'))
println totalCostFormulavalue
WebUI.verifyElementText(findTestObject('Object Repository/TotalCost1ValueObject'), "17")

WebUI.setText(findTestObject('Object Repository/SupplierView_PriceInputtextBox'), totalCostFormulavalue)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Submit Entire Response'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_OK'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_OK_SurrogateWarn'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Stop'))

//Search for event from sourcing dasboard and opens it

WebUI.callTestCase(findTestCase('DMS/UtilTests/ViewRecentSourcingEvent'), [('sourcingEvent') : sourcingEvent], 
	FailureHandling.STOP_ON_FAILURE)

// Stops RFP event
CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.callTestCase(findTestCase('DMS/UtilTests/StopSourcingEvent'),null)

//Verifying the Supplier values at buyer content page
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/td_1200 USD'))

WebUI.verifyElementText(findTestObject('Object Repository/td_1200 USD'), '$12.00 USD')
WebUI.getText(findTestObject('Object Repository/td_1200 USD'))
WebUI.delay(3)

//Clicking on scenario tab and creating manual scenario and awarding supplier
WebUI.callTestCase(findTestCase('DMS/UtilTests/CreateScenarioandAwardEvent'),null)

def DocumentIDText=WebUI.getText(findTestObject('Object Repository/span_EventName'))
// Return documnt ID to get API response and return event name to approve this task
def arrayList = [DocumentIDText, sourcingEvent]

println(arrayList)

return arrayList

WebUI.closeBrowser()
