import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.JavascriptExecutor


// Login into App
//String appLoginUrl = internal.GlobalVariable.baseURL + '/Sourcing/Main?realm=accAcwSap'
def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
//String appLoginUrl = internal.GlobalVariable.baseURL + '/Sourcing/Main?realm=s4All'
String appLoginUrl = service + '/Sourcing/Main?realm='+realm

String userName = 'customersupportadmin'

WebUI.openBrowser('')

WebUI.navigateToUrl(appLoginUrl)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/testcentralpageusername'), userName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

//--------------------------------------------------------------------------------------------------------------------------


String sourcingEvent = sourcingEvent + System.currentTimeMillis()

WebUI.callTestCase(findTestCase('DMS/UtilTests/CreateSourcingEvent'), [('sourcingEvent') : sourcingEvent, ('eventType') : eventType
        , ('rfpEventTemplate') : rfpEventTemplate, ('eventContentFileName') : eventContentFileName, ('eventEndState') : eventEndState
        , ('numberOfBids') : numberOfBids], FailureHandling.STOP_ON_FAILURE)

//--------------------------------------------------------------------------------------------------------------------------


WebUI.verifyElementPresent(findTestObject('Object Repository/a_View Project'), 3, FailureHandling.CONTINUE_ON_FAILURE)

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_View Project'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Buyer Monitoring Page'))

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.refresh()

WebUI.waitForElementPresent(findTestObject('Object Repository/button_action_draft'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_action_draft'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/edit_event_link'))

//Verify Event Header APC Rule

// Check whether the pricing condition rule is set to Yes
TestObject obj1 = new TestObject("apc locator1")
String apcRuleLoc = "//label[@awname='AllowAdvancedPricingConditions_selectedItem::AdvancedPricingConditionRuleView_AllowAdvancedPricingConditions:currChoice']"
obj1.addProperty("xpath", ConditionType.EQUALS, apcRuleLoc)
WebUI.verifyElementPresent(obj1, 3, FailureHandling.STOP_ON_FAILURE)


TestObject obj2 = new TestObject("apc locator2")
String validityPeriodLoc = "//div[@awname='AdvancedPricingConditionRule.ValidityPeriodType::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodType:itemList']"
obj2.addProperty("xpath", ConditionType.EQUALS, validityPeriodLoc)
WebUI.verifyElementPresent(obj2, 3, FailureHandling.CONTINUE_ON_FAILURE)


TestObject obj3 = new TestObject("apc locator3")
String startDateLoc = "//input[@awname='AdvancedPricingConditionRule.ValidityPeriodStartDate::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodStartDate:textFieldAction_1']"
obj3.addProperty("xpath", ConditionType.EQUALS, startDateLoc)
WebUI.verifyElementPresent(obj3, 3, FailureHandling.STOP_ON_FAILURE)


TestObject obj4 = new TestObject("apc locator4")
String periodLoc = "//input[@awname='AdvancedPricingConditionRule.ValidityPeriodDuration::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodDuration:fieldValue']"
obj4.addProperty("xpath", ConditionType.EQUALS, periodLoc)
WebUI.verifyElementPresent(obj4, 3, FailureHandling.STOP_ON_FAILURE)

TestObject obj5 = new TestObject("apc locator5")
String scaleTypeLoc = "//div[@awname='AdvancedPricingConditionRule.VolumeScaleType::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.VolumeScaleType:itemList']"
obj5.addProperty("xpath", ConditionType.EQUALS, scaleTypeLoc)
WebUI.verifyElementPresent(obj5, 3, FailureHandling.STOP_ON_FAILURE)

TestObject obj6 = new TestObject("apc locator6")
String scaleLoc = "//input[@awname='AdvancedPricingConditionRule.Volumes::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.Volumes:newEntry']"
obj6.addProperty("xpath", ConditionType.EQUALS, scaleLoc)
WebUI.verifyElementPresent(obj6, 3, FailureHandling.STOP_ON_FAILURE)



CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Content - Draft'))

// Verify Line Level APC Rule

TestObject obj7 = new TestObject("apc locator7")
String apcLink = "//a[@awname='0.1_PRICEPrice::PRICE:pricingInfoDisplay']"
obj7.addProperty("xpath", ConditionType.EQUALS, apcLink)
WebUI.verifyElementPresent(obj7, 3, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(obj7)


try {
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Done'))
}
catch (Exception e) {
	e.printStackTrace()
}

WebUI.closeBrowser()

