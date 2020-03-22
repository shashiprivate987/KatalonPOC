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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import common.util.aribakeywords as Ariba


//----------------------------------------------------------------------------------------------------------
/*
 * Locator Constant
 */

String APCLOT_LOT = "//tr[@awname='0.3::AWRefreshRegion']"
String APCLOT_CHILDITEM_LOC = "//tr[@awname='0.3_0.3.1::AWRefreshRegion']"
String APCLINK_LOC = "//a[@awname='0.3_RITASHORTSTRINGIFZ000003Computed Term::RITASHORTSTRINGIFZ000003:pricingInfoDisplay']"
String LOT_TERM_APC_PRICE_LOC = "//input[@awname='_DerivedStringValue::PCCostTermView_DerivedStringValue:fieldValue']"
String EXPANDLOTBTN = "//a[@awname='_Award_8_4_Award::AWTRowAttributeExpandoColumnRenderer:table.pivotState.toggleDetailAttributesExpanded']//div"
//------------------------------------------------------------------------------------------------------------


def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
String appLoginUrl = service + '/Sourcing/Main?realm='+realm
String userName = 'customersupportadmin'
def CW = 'ACPContractWithLot_' + System.currentTimeMillis()
String sourcingEvent = sourcingEvent + System.currentTimeMillis()


//--------------------------------------------------------------------------------------------------------------------------
/*
 *  Login into App
 */

WebUI.callTestCase(findTestCase('DMS/UtilTests/LoginIntoApp'), [('appLoginUrl') : appLoginUrl, ('userName') : userName],
	FailureHandling.STOP_ON_FAILURE)

//--------------------------------------------------------------------------------------------------------------------------
/*
 *  Enabling toggles
 */
String volumeScaleFeature = 'ariba.sourcing.util.SourcingEventFeatures.PricingConditionsWithScales'
String fromScaleFeature = 'ariba.sourcing.util.SourcingEventFeatures.FromScalesInPricingCondition'
String appendReplaceContract = 'ariba.collaborate.util.CollaborateFeatures.AppendLineItemsInExistingContract'
//DMS-9152 - Enable pricing condition for basket lot
String apcForBasketLots = 'ariba.sourcing.util.SourcingEventFeatures.PricingConditionForBasketLot'
//SSR-158 - Sourcing - PrePacks And Mixed Cases
String prepackMixedCase = 'ariba.sourcing.util.RetailSourcingFeatures.PrePackAndMixedCases'
//UP-4293 - Sourcing - Support Item hierarchy
String itemHierarchy = 'ariba.sourcing.util.SourcingEventFeatures.SourcingItemHierarchy'
//SSR-210  - Mixed Cases/PrePack Support for Contracts
String mixedCasedPrepack = 'ariba.sourcing.util.RetailSourcingFeatures.PrePackAndMixedCasesInContract'

/*
def toggleList = [volumeScaleFeature, fromScaleFeature, appendReplaceContract, prepackMixedCase, itemHierarchy, mixedCasedPrepack]

for(int i=0; i< toggleList.size(); i++){
	WebUI.callTestCase(findTestCase('DMS/UtilTests/SetFeatureToggle'), [('featureToggleName') : toggleList[i]],
		FailureHandling.STOP_ON_FAILURE)
}

//--------------------------------------------------------------------------------------------------------------------------

 * Enabling Realm Parameters
 
String mixedCaseIntegration = 'Application.AQS.ArticleIntegration.MixedCaseIntegration.Enabled'
String mixedCases = 'Application.AQS.ArticleIntegration.MixedCases.Enabled'

def realmParamList = [mixedCaseIntegration, mixedCases]

for(int i=0; i<realmParamList.size(); i++){

	WebUI.callTestCase(findTestCase('DMS/UtilTests/SetRealmParameters'), [('parameterName') : realmParamList[i]],
		FailureHandling.STOP_ON_FAILURE)
	
}*/

//--------------------------------------------------------------------------------------------------------------------------

/*
 * Create Sourcing Event
 */

WebUI.callTestCase(findTestCase('DMS/UtilTests/CreateSourcingEvent'), [('sourcingEvent') : sourcingEvent, ('eventType') : eventType
        , ('rfpEventTemplate') : rfpEventTemplate, ('eventContentFileName') : eventContentFileName, ('eventEndState') : eventEndState
        , ('numberOfBids') : numberOfBids], FailureHandling.STOP_ON_FAILURE)

//--------------------------------------------------------------------------------------------------------------------------

Ariba.aribaClick(findTestObject('Object Repository/a_Buyer Monitoring Page'))

Ariba.waitForPageToLoad(3)

WebUI.refresh()

Ariba.aribaClick(findTestObject('Object Repository/a_Award Tab'))

/*
 *  Verify APC Rule Link at award tab
 */
TestObject  expandPlus = Ariba.createTestObject(EXPANDLOTBTN)
WebUI.scrollToElement(expandPlus, 3)
Ariba.aribaClick(expandPlus)
WebUI.verifyTextPresent("View pricing conditions", false, FailureHandling.STOP_ON_FAILURE)


Ariba.aribaClick(findTestObject('Object Repository/span_Contract_w-pulldown-icon'))

/*
 * Create new Contract Workspace
 */

Ariba.aribaClick(findTestObject('Object Repository/form_Pricing trendsLoading...i'))

Ariba.aribaClick(findTestObject('Object Repository/label_Submit Initial Bid_w-rdo'))

Ariba.aribaClick(findTestObject('Object Repository/span_Create New Contract'))

Ariba.aribaClick(findTestObject('Object Repository/a_Contract Workspace (Procurem'))

WebUI.setText(findTestObject('Object Repository/input___ihgt1d'), CW)

Ariba.aribaClick(findTestObject('Object Repository/label_Yes_w-rdo w-rdo-dsize'))

WebUI.setText(findTestObject('Object Repository/input___1ymp9c'), '06/16/2019')

//Set Commodity Code
if (WebUI.verifyElementPresent(findTestObject('Object Repository/a_commodity_code'), 5, FailureHandling.OPTIONAL)) {
    WebUI.setText(findTestObject('Object Repository/a_commodity_code'), 'All Commodities')
}

Ariba.aribaClick(findTestObject('Object Repository/label_SignTemplate154462011136'))

Ariba.aribaClick(findTestObject('Page_Ariba Spend Management/span_Create'))

Ariba.aribaClick(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'))

Ariba.aribaClick(findTestObject('Object Repository/form_BackAlert ()CustomizeAnal'))

Ariba.waitForPageToLoad(3)


/*
 *  Verify Lot Structure in CLID
 */
WebDriver driver = DriverFactory.getWebDriver()

int lotSize = driver.findElements(By.xpath(APCLOT_LOT)).size()
WebUI.verifyMatch(lotSize.toString(), '1', false, FailureHandling.STOP_ON_FAILURE)

int childItem = driver.findElements(By.xpath(APCLOT_CHILDITEM_LOC)).size()
WebUI.verifyMatch(childItem.toString(), '1', false, FailureHandling.STOP_ON_FAILURE)

// Verify APC Link
WebUI.verifyTextPresent('View pricing conditions', false, FailureHandling.STOP_ON_FAILURE)


TestObject apcLink = Ariba.createTestObject(APCLINK_LOC) 
WebUI.verifyElementPresent(apcLink, 3, FailureHandling.STOP_ON_FAILURE)
Ariba.aribaClick(apcLink)


WebUI.verifyTextPresent('Dec 2019 - Dec 9999', false, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyTextPresent('To 5', false, FailureHandling.STOP_ON_FAILURE)
String termValue = driver.findElement(By.xpath(LOT_TERM_APC_PRICE_LOC)).getAttribute("value")
WebUI.verifyMatch(termValue, '44', false, FailureHandling.STOP_ON_FAILURE)

try {
    Ariba.aribaClick(findTestObject('Page_Ariba Spend Management/span_Done'))
}
catch (Exception e) {
    e.printStackTrace()
}

WebUI.closeBrowser()

