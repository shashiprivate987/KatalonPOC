import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor as JavaScriptExecutor
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.ExcelData as ExcelData
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.reader.ExcelFactory as ExcelFactory
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import sourcing.util.sourcingKeywords as sourcingKeywords
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import static org.junit.Assert.*
import org.apache.poi.xssf.usermodel.XSSFCell as XSSFCell
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import org.junit.Test as Test
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import common.util.aribakeywords as Ariba
import sourcing.util.sourcingKeywords as Sourcing
import sourcing.util.DmsUtils
import sourcing.util.FileComparatorUtil

//--------------------------------------------------------------------------------------------------------------------------------------
/*
 * Locator Constants
 */
String APCRULE_LOC = '//label[@awname=\'AllowAdvancedPricingConditions_selectedItem::AdvancedPricingConditionRuleView_AllowAdvancedPricingConditions:currChoice\']'

String VALIDITYPERIOD_LOC = '//div[@awname=\'AdvancedPricingConditionRule.ValidityPeriodType::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodType:itemList\']'

String STARTDATE_LOC = '//input[@awname=\'AdvancedPricingConditionRule.ValidityPeriodStartDate::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodStartDate:textFieldAction_1\']'

String PERIOD_LOC = '//input[@awname=\'AdvancedPricingConditionRule.ValidityPeriodDuration::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodDuration:fieldValue\']'

String SCALETYPE_LOC = '//div[@awname=\'AdvancedPricingConditionRule.VolumeScaleType::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.VolumeScaleType:itemList\']'

String SCALE_LOC = '//input[@awname=\'AdvancedPricingConditionRule.Volumes::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.Volumes:newEntry\']'

String APCLOT_LOC = '//tr[@awname=\'0.4::AWRefreshRegion\']//a//b[text()=\'Basket Lot\']'

String LOTCHILDITEM_LOC = '//tr[@awname=\'0.4_0.4.1::AWRefreshRegion\']'

String APCLINK_LOC = '//a[@awname=\'0.4_RITASHORTSTRINGIFZ000002::RITASHORTSTRINGIFZ000002:pricingInfoDisplay\']'

String LOT_TERM_APC_PRICE_LOC = '//input[@awname=\'_DerivedStringValue::PCCostTermView_DerivedStringValue:fieldValue\']'

//--------------------------------------------------------------------------------------------------------------------------------------
/*
 * Local veriables
 */
def service = internal.GlobalVariable.baseURL

def realm = internal.GlobalVariable.realm

String appLoginUrl = (service + '/Sourcing/Main?realm=') + realm

String userName = 'customersupportadmin'

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
/*String volumeScaleFeature = 'ariba.sourcing.util.SourcingEventFeatures.PricingConditionsWithScales'
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
 *  Create Sourcing Event
 */
WebUI.callTestCase(findTestCase('DMS/UtilTests/CreateSourcingEvent'), [('sourcingEvent') : sourcingEvent, ('eventType') : eventType
        , ('rfpEventTemplate') : rfpEventTemplate, ('eventContentFileName') : eventContentFileName, ('eventEndState') : eventEndState
        , ('numberOfBids') : numberOfBids], FailureHandling.STOP_ON_FAILURE)

//--------------------------------------------------------------------------------------------------------------------------
Ariba.aribaClick(findTestObject('Object Repository/a_Buyer Monitoring Page'))

Ariba.waitForPageToLoad(3)

WebUI.refresh()

WebUI.waitForElementPresent(findTestObject('Object Repository/button_action_draft'), 3)

Ariba.aribaClick(findTestObject('Object Repository/button_action_draft'))

Ariba.aribaClick(findTestObject('Object Repository/edit_event_link'))

/*
 *  - Verify Event Header APC Rule Validation
 *  - Check whether the pricing condition rule is set to Yes
 */
WebUI.verifyElementPresent(Ariba.createTestObject(APCRULE_LOC), 3, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(Ariba.createTestObject(VALIDITYPERIOD_LOC), 3, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementPresent(Ariba.createTestObject(STARTDATE_LOC), 3, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(Ariba.createTestObject(PERIOD_LOC), 3, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(Ariba.createTestObject(SCALETYPE_LOC), 3, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(Ariba.createTestObject(SCALE_LOC), 3, FailureHandling.STOP_ON_FAILURE)

Ariba.aribaClick(findTestObject('Object Repository/Content - Draft'))

Ariba.waitForPageToLoad(3)

/*
 *  Verify Lot structure
 */
WebDriver driver = DriverFactory.getWebDriver()

int lotSize = driver.findElements(By.xpath(APCLOT_LOC)).size()

WebUI.verifyMatch(lotSize.toString(), '1', false, FailureHandling.STOP_ON_FAILURE)

int childItem = driver.findElements(By.xpath(LOTCHILDITEM_LOC)).size()

WebUI.verifyMatch(childItem.toString(), '1', false, FailureHandling.STOP_ON_FAILURE)

/*
 *  Verify APC Link
 */
WebUI.verifyTextPresent('View pricing conditions', false, FailureHandling.STOP_ON_FAILURE)

TestObject apcLink = Ariba.createTestObject(APCLINK_LOC)

WebUI.verifyElementPresent(apcLink, 3, FailureHandling.STOP_ON_FAILURE)

Ariba.aribaClick(apcLink)

WebUI.verifyTextPresent('Dec 2019 - Dec 9999', false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent('To 5', false, FailureHandling.STOP_ON_FAILURE)

String termValue = driver.findElement(By.xpath(LOT_TERM_APC_PRICE_LOC)).getAttribute('value')

WebUI.verifyMatch(termValue, '44', false, FailureHandling.STOP_ON_FAILURE)

try {
    Ariba.aribaClick(findTestObject('Page_Ariba Spend Management/span_Done'))
}
catch (Exception e) {
    e.printStackTrace()
} 

/*
 * Excel Validation
 * 
 * 1. Download Excel 
 * 2. Make sure it is placed in the right download location
 * 3. Keep the expected event sheet in the DMS/ExcelValidation dir
 * 3. Call the Excel sheet wise comparison API 
 */
Ariba.aribaClick(Ariba.createTestObject('//button[@awname=\'ASCContentFrame:excelImportAction\']'))

String usrDir = RunConfiguration.getProjectDir()

TestObject fileToBeDwnld = Ariba.createTestObject('//a[@awname=\'ASPExcelImport:exportExcelDocumentAction\']')

String excelName = sourcingEvent.trim() + '.xls'

Ariba.downloadFile(fileToBeDwnld, excelName, 3, 3)

WebUI.comment('Downloaded')

String downloadedFilePath = '/Downloads/' + excelName

/*ExcelData actualSheet = ExcelFactory.getExcelDataWithDefaultSheet((usrDir + '/') + downloadedFilePath, 'Pricing Conditions', 
    false)*/

String expFile = 'EventLotWithAPCTermContent.xls'

//String sourceFilePath = '/Test Files/dms/ExcelValidations/' + fileName

String sheetName = "Pricing Conditions"

boolean match = FileComparatorUtil.validateExcelSheets(expFile, excelName, sheetName)

WebUI.verifyEqual(match, true, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

