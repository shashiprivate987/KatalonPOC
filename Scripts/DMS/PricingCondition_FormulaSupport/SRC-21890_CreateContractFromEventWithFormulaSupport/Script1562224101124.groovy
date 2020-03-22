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

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Buyer Monitoring Page'))

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.refresh()

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Award Tab'))

WebUI.verifyTextPresent('View pricing conditions ', false, FailureHandling.STOP_ON_FAILURE)

def CW = 'ACPContract' + System.currentTimeMillis()

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Contract_w-pulldown-icon'))

WebUI.comment('create new contract')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/form_Pricing trendsLoading...i'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/label_Submit Initial Bid_w-rdo'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Create New Contract'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Contract Workspace (Procurem'))

WebUI.setText(findTestObject('Object Repository/input___ihgt1d'), CW)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/label_Yes_w-rdo w-rdo-dsize'))

WebUI.setText(findTestObject('Object Repository/input___1ymp9c'), '06/16/2019')

//Set Commodity Code
if (WebUI.verifyElementPresent(findTestObject('Object Repository/a_commodity_code'), 5, FailureHandling.OPTIONAL)) {
    WebUI.setText(findTestObject('Object Repository/a_commodity_code'), 'All Commodities')
}

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/label_SignTemplate154462011136'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Create'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/form_BackAlert ()CustomizeAnal'))

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.verifyElementPresent(findTestObject('Object Repository/a_View pricing conditions'), 3, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_View pricing conditions'))

WebUI.verifyTextPresent('Mar 2019', false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent('To 22', false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent('$4,400.00', false, FailureHandling.STOP_ON_FAILURE)

//WebUI.verifyTextPresent('$130.00 USD', false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent('To 44', true, FailureHandling.STOP_ON_FAILURE)

try {
    CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Done'))
}
catch (Exception e) {
    e.printStackTrace()
} 

// Publish Contract
/*CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Publish'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_OK'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Actions (4)'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_publishContract'))

WebUI.verifyTextPresent('Published', 3, FailureHandling.STOP_ON_FAILURE)*/

WebUI.closeBrowser()

