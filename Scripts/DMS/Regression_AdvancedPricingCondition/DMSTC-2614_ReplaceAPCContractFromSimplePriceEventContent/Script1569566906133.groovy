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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable



def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
//String appLoginUrl = internal.GlobalVariable.baseURL + '/Sourcing/Main?realm=s4All'
String appLoginUrl = service + '/Sourcing/Main?realm='+realm
String clid_Editability_Param = 'Application.ACM.EnableEditingForPricingConditionAtContractContentHeader=true'
def CW = contractName+System.currentTimeMillis()

//--------------------------------------------------------------------------------------------------------------------------

WebUI.callTestCase(findTestCase('DMS/UtilTests/LoginIntoApp'), [('appLoginUrl') : appLoginUrl, ('userName') : userName],
	FailureHandling.STOP_ON_FAILURE)

//--------------------------------------------------------------------------------------------------------------------------

// Set CLID Pricing Condition Editabiltity Parameter
WebUI.callTestCase(findTestCase('DMS/UtilTests/SetRealmParameters'), [('parameterName') : clid_Editability_Param],
	FailureHandling.STOP_ON_FAILURE)
//--------------------------------------------------------------------------------------------------------------------------

// Create contract from stager
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/Test Central ACM'))

WebUI.scrollToElement(findTestObject('Page_Ariba Spend Management/a_Create Workspace'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_Create Workspace'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/input supplier cw'))

WebUI.setText(findTestObject('Object Repository/input supplier cw'), supplier)

WebUI.setText(findTestObject('Page_Ariba Spend Management/input_Name of Workspace__bdp8j'), CW)

WebUI.setText(findTestObject('Page_Ariba Spend Management/input_Name of Template__x1sbod'), contractTemplate)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Run Stager'))

//--------------------------------------------------------------------------------------------------------------------------

// Set project view to Compact
try{
	WebUI.callTestCase(findTestCase('DMS/UtilTests/SetContractProjectCompactView'), [('projectName') : CW],
		FailureHandling.OPTIONAL)
}catch (Exception e) {
	e.printStackTrace()
}

//--------------------------------------------------------------------------------------------------------------------------

WebUI.refresh()

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/View_Project'))


// Actions button for compact view
CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

// Open CLID and Upload File
WebUI.refresh()
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/span_Documents_w-pml-icon'))

String openDoc = 'Open::AbstractDocumentDownloadIcon:itemAction'

openDocLink = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'contains', openDoc, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(openDocLink)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_ASPEditContentDocument/span_Excel Import'))


String uploadDoc = 'ASCContentFrame:excelImportAction'

uploadDocLink = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'equals', uploadDoc, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(uploadDocLink)


WebUI.comment('File Upload with xls file and element and click done once it is uploaded')

CustomKeywords.'common.util.aribakeywords.fileUpload'(findTestObject('Object Repository/Page_Ariba Spend Management/awFileUploadButton'),
	filePath)

String excelImportAwname = 'ASPExcelImport:importExcelDocumentAction'

excelImportBtn = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'equals', excelImportAwname, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(excelImportBtn)


WebUI.verifyElementPresent(findTestObject('Object Repository/a_View pricing conditions'), 3, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_View pricing conditions'))

try {
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Done'))
}
catch (Exception e) {
	e.printStackTrace()
}


CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)


// Wait for Arches to get the Newly created Contract to be searchable
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testCentral'))

//Wait for Arches Work Item Searched Out
WebUI.scrollToElement(findTestObject('Page_TestCentralPage/Wait for Arches Work Item Searched Out'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/Wait for Arches Work Item Searched Out'))

WebUI.setText(findTestObject('Page_TestCentralPage/SearchParameter_workspaceTitle'), CW)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Run Stager'))

//--------------------------------------------------------------------------------------------------------------------------

// Create sourcing event

String sourcingEvent = sourcingEvent + System.currentTimeMillis()

WebUI.callTestCase(findTestCase('DMS/UtilTests/CreateSourcingEvent'), [('sourcingEvent') : sourcingEvent, ('eventType') : eventType
		, ('rfpEventTemplate') : rfpEventTemplate, ('eventContentFileName') : eventContentFileName, ('eventEndState') : eventEndState
		, ('numberOfBids') : numberOfBids], FailureHandling.STOP_ON_FAILURE)

//--------------------------------------------------------------------------------------------------------------------------


CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Buyer Monitoring Page'))

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.refresh()

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Award Tab'))

//WebUI.verifyTextPresent('View pricing conditions ', false, FailureHandling.STOP_ON_FAILURE)


CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Contract_w-pulldown-icon'))

WebUI.comment('Replace Existing contract')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Add to existing contract'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/label_Submit Initial Bid_w-rdo'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/btn add to existing contract'))


println CW
TestObject rdBtnObj = new TestObject("Clicking radio btn")
WebUI.refresh()
String expr = "//label[@awname='"+CW+"::AWTSingleSelectColumnRenderer']"
rdBtnObj.addProperty("xpath", ConditionType.EQUALS, expr)


WebUI.setText(findTestObject('Object Repository/text_search_cntr'), CW)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/contract_Search_btn'))
WebUI.waitForElementVisible(rdBtnObj, 5)

WebUI.verifyElementPresent(rdBtnObj, 3, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(rdBtnObj)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/btn select contract'))


CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/rd replace contract'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/btn ok save'))


// Open CLID Edit attribute and verify for APC Rule Set

WebUI.refresh()

WebUI.waitForElementVisible(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'), 3, FailureHandling.OPTIONAL)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/clid_edit_attribute'))


// Check whether the pricing condition rule is set to Yes

/*String apcRuleLoc = "//tr[@awname='AllowAdvancedPricingConditions::AWRefreshRegion']//../td[3]/table//td[1]"

String apcRuleBoolean = WebUI.getText(findTestObject(apcRuleLoc.trim()))

WebUI.verifyMatch(apcRuleBoolean, 'Yes', false, FailureHandling.STOP_ON_FAILURE)*/

// Check whether the pricing condition rule is set to Yes
TestObject obj1 = new TestObject("apc locator1")
String apcRuleLoc = "//label[@awname='AllowAdvancedPricingConditions_selectedItem::EditView_AllowAdvancedPricingConditions:currChoice']"
obj1.addProperty("xpath", ConditionType.EQUALS, apcRuleLoc)
WebUI.verifyElementPresent(obj1, 3, FailureHandling.STOP_ON_FAILURE)


TestObject obj2 = new TestObject("apc locator2")
String validityPeriodLoc = "//div[@awname='AdvancedPricingConditionRule.ValidityPeriodType::EditView_AdvancedPricingConditionRule.ValidityPeriodType:itemList']"
obj2.addProperty("xpath", ConditionType.EQUALS, validityPeriodLoc)
WebUI.verifyElementPresent(obj2, 3, FailureHandling.CONTINUE_ON_FAILURE)


TestObject obj3 = new TestObject("apc locator3")
String startDateLoc = "//input[@awname='AdvancedPricingConditionRule.ValidityPeriodStartDate::EditView_AdvancedPricingConditionRule.ValidityPeriodStartDate:textFieldAction_1']"
obj3.addProperty("xpath", ConditionType.EQUALS, startDateLoc)
WebUI.verifyElementPresent(obj3, 3, FailureHandling.STOP_ON_FAILURE)


TestObject obj4 = new TestObject("apc locator4")
String periodLoc = "//input[@awname='AdvancedPricingConditionRule.ValidityPeriodDuration::EditView_AdvancedPricingConditionRule.ValidityPeriodDuration:fieldValue']"
obj4.addProperty("xpath", ConditionType.EQUALS, periodLoc)
WebUI.verifyElementPresent(obj4, 3, FailureHandling.STOP_ON_FAILURE)

TestObject obj5 = new TestObject("apc locator5")
String scaleTypeLoc = "//div[@awname='AdvancedPricingConditionRule.VolumeScaleType::EditView_AdvancedPricingConditionRule.VolumeScaleType:itemList']"
obj5.addProperty("xpath", ConditionType.EQUALS, scaleTypeLoc)
WebUI.verifyElementPresent(obj5, 3, FailureHandling.STOP_ON_FAILURE)

TestObject obj6 = new TestObject("apc locator6")
String scaleLoc = "//input[@awname='AdvancedPricingConditionRule.Volumes::EditView_AdvancedPricingConditionRule.Volumes:newEntry']"
obj6.addProperty("xpath", ConditionType.EQUALS, scaleLoc)
WebUI.verifyElementPresent(obj6, 3, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/modalPage_Ok'))


// open CLID

WebUI.refresh()

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'))

String openDoc1 = 'Open::AbstractDocumentDownloadIcon:itemAction'

openDocLnk = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'contains', openDoc1, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(openDocLnk)

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/a_View pricing conditions'), 3, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()
