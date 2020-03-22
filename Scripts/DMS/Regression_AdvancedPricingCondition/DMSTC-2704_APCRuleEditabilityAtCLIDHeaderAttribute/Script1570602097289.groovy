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
def CW = contractName +System.currentTimeMillis()
String userName = 'customersupportadmin'
String clid_Editability_Param = 'Application.ACM.EnableEditingForPricingConditionAtContractContentHeader=true'




WebUI.openBrowser('')

WebUI.navigateToUrl(appLoginUrl)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/testcentralpageusername'), userName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

//--------------------------------------------------------------------------------------------------------------------------

// Set CLID Pricing Condition Editabiltity Parameter
WebUI.callTestCase(findTestCase('DMS/UtilTests/SetRealmParameters'), [('parameterName') : clid_Editability_Param],
	FailureHandling.STOP_ON_FAILURE)


// Create contract from stager
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/Test Central ACM'))

WebUI.scrollToElement(findTestObject('Page_Ariba Spend Management/a_Create Workspace'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_Create Workspace'))

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

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)


// Open CLID Edit attribute and verify for APC Rule Set

WebUI.refresh()

WebUI.waitForElementVisible(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'), 3, FailureHandling.OPTIONAL)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/clid_edit_attribute'))



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


WebUI.closeBrowser()
