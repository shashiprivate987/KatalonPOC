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
//def rfpEventTemplate = 'RequestforProposal_CleanSheet1.zip'
//
//String fileName = 'RFP_Content1.xls'
String fileName = 'Content1.xls'
String eventType='1'      //RFP
String eventEndState='0'  //draft state
String numberOfBids='1'
//
def Template = 'RequestforProposal_CleanSheet1'
//def Template = 'Request for Proposal_CleanSheet'
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

WebUI.callTestCase(findTestCase('DMS/Stager/ViewEvent'), null)
WebUI.delay(3)
WebUI.callTestCase(findTestCase('DMS/UtilTests/EditSourcingEvent'), null)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/RespStartonPublish'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Content'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Event_Content'))

WebUI.delay(3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/b_Pricing'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Add_Line Item_Content'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/input___wxv7w'))

WebUI.setText(findTestObject('Object Repository/input___wxv7w'), "Line1")

WebUI.setText(findTestObject('Object Repository/input_USD__j_kc4c'), "50")

WebUI.setText(findTestObject('Object Repository/input_USD__p62qdd'), "50")

WebUI.setText(findTestObject('Object Repository/input___gwrold'), "1")

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Done_content'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/RawMaterialCostGroupLink'))

String filePath2 = 'Test Files/dms/AmortizationC.xls'
WebUI.callTestCase(findTestCase('DMS/UtilTests/ImportCostGroupContent'), [('filePath') : filePath2], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Actions'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/costgroupUpdateTotalLink'))

WebUI.verifyElementText(findTestObject('Object Repository/TotalCostValue'), "6")

def t1=WebUI.getText(findTestObject('Object Repository/TotalCostValue'))
println t1
//WebUI.setText(findTestObject('Object Repository/InputText_Money_contentUI'), t1)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/SummaryTabrfpEvent'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/PublishEventButton'))
WebUI.delay(2)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/MonitorEvent'))


//  SurrogateBid
//     This test case allows the buyer to perform surrogate bid if the event is in view. Basically clicks on
//     Supplier tab and perform surrogate bid if submit_bid=1 else it will navigate to Supplier view of event

WebUI.delay(3)
String submit_bid='0'
String CostGroupfilePath='1'
WebUI.callTestCase(findTestCase('DMS/UtilTests/SurrogateBid'), [('sourcingEvent') : sourcingEvent, ('submit_bid') : submit_bid], FailureHandling.STOP_ON_FAILURE)
	

// Updating value for  cost group & submitting supplier response

WebUI.delay(3)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Open_Cost_GroupView'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/tr_Term1'))

WebUI.setText(findTestObject('Object Repository/tr_Term1'), '3')

//Adding Second Line item at supplier side
//Here Amortization Click need to be added
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/b_AmortizationC'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Add_LineItem_InsideCG'))

WebUI.refresh()

WebUI.setText(findTestObject('Object Repository/input___wxv7w'), "ACLine2")

WebUI.setText(findTestObject('Object Repository/input___gwrold'), "1")

WebUI.setText(findTestObject('Object Repository/input_Loading__6vp6sd'), "2")


CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_Done (6)'))

WebUI.delay(2)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_Done (4)'))


//End of adding second line item at supplier side

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Update Totals'))
WebUI.delay(3)
def totalCostFormulavalue=WebUI.getText(findTestObject('Object Repository/TotalCost1ValueObject'))
println totalCostFormulavalue
WebUI.verifyElementText(findTestObject('Object Repository/TotalCost1ValueObject'), "10")

WebUI.setText(findTestObject('Object Repository/SupplierView_PriceInputtextBox'), totalCostFormulavalue)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Submit Entire Response'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_OK_SurrogateWarn'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Stop'))

//Search for event from sourcing dasboard and opens it

WebUI.callTestCase(findTestCase('DMS/UtilTests/ViewRecentSourcingEvent'), [('sourcingEvent') : sourcingEvent],
	FailureHandling.STOP_ON_FAILURE)

// Stops RFP event
CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.callTestCase(findTestCase('DMS/UtilTests/StopSourcingEvent'),null)

//Verifying the Supplier values at buyer content page
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_SupplierPrice'))

WebUI.verifyElementText(findTestObject('Object Repository/a_SupplierPrice'), '$10.00 USD')

WebUI.delay(3)

//Clicking on scenario tab and creating manual scenario and awarding supplier
WebUI.callTestCase(findTestCase('DMS/UtilTests/CreateScenarioandAwardEvent'),null)

WebUI.closeBrowser()
