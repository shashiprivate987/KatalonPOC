import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.reader.ExcelFactory
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
import static org.junit.Assert.*
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import common.util.aribakeywords as Ariba
import sourcing.util.FileComparatorUtil
import sourcing.util.ExcelTestDataUtil
import sourcing.util.ExcelToggleParamStoreStrategy
import common.util.RESTValidator as Rest


//--------------------------------------------------------------------------------------------------------------------------------------
/*
 * Locator Constants
 */


String APCRULE_LOC = "//label[@awname='AllowAdvancedPricingConditions_selectedItem::AdvancedPricingConditionRuleView_AllowAdvancedPricingConditions:currChoice']"
String VALIDITYPERIOD_LOC = "//div[@awname='AdvancedPricingConditionRule.ValidityPeriodType::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodType:itemList']"
String STARTDATE_LOC = "//input[@awname='AdvancedPricingConditionRule.ValidityPeriodStartDate::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodStartDate:textFieldAction_1']"
String PERIOD_LOC = "//input[@awname='AdvancedPricingConditionRule.ValidityPeriodDuration::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodDuration:fieldValue']"
String SCALETYPE_LOC = "//div[@awname='AdvancedPricingConditionRule.VolumeScaleType::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.VolumeScaleType:itemList']"
String SCALE_LOC = "//input[@awname='AdvancedPricingConditionRule.Volumes::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.Volumes:newEntry']"
String APCLOT_LOC = "//tr[@awname='0.4::AWRefreshRegion']//a//b[text()='Basket Lot']"
String LOTCHILDITEM_LOC = "//tr[@awname='0.4_0.4.1::AWRefreshRegion']"
String APCLINK_LOC = "//a[@awname='0.4_RITASHORTSTRINGIFZ000002::RITASHORTSTRINGIFZ000002:pricingInfoDisplay']"
String LOT_TERM_APC_PRICE_LOC = "//input[@awname='_DerivedStringValue::PCCostTermView_DerivedStringValue:fieldValue']"


//--------------------------------------------------------------------------------------------------------------------------------------
/*
 * Local veriables
 */
def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
String appLoginUrl = service + '/Sourcing/Main?realm='+realm
String userName = 'customersupportadmin'
String sourcingEvent = sourcingEvent + System.currentTimeMillis()


String expRelPath = 'Downloads/EventLotWithAPCTermContent.xls'
String actRelPath = "Downloads/EventLotWithAPCTermContent1.xls"

File file = new File(actRelPath);
//File file = Ariba.downloadFile(xxxx)

//def exp1 = Rest.fileParser(expRelPath)
//def act1 = Rest.fileParser(file.getAbsolutePath())

/*boolean x = Rest.mapDiff(exp1, act1, "DMS123")

println x*/

//--------------------------------------------------------------------------------------------------------------------------
/*
 *  Login into App
 */

/*WebUI.callTestCase(findTestCase('DMS/UtilTests/LoginIntoApp'), [('appLoginUrl') : appLoginUrl, ('userName') : userName],
	FailureHandling.STOP_ON_FAILURE)*/

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
}*/

//--------------------------------------------------
String fileName = 'ContractLotAPCContent.xlsx'
String fileRelPath = '/Test Files/dms/ExcelValidations/'
String sheetName = 'Shashi_pricingConditions'
String dataTableName = 'DataSet8'
String toggles = 'Toggles'
//def toggleList = 'ariba.sourcing.util.SourcingEventFeatures.PricingConditionsWithScales'


ExcelTestDataUtil testData = new ExcelTestDataUtil(sheetName, dataTableName);
ExcelToggleParamStoreStrategy registry = new ExcelToggleParamStoreStrategy();


//List<String> toggleList = registry.registerTogglesAndGetActualToEnable(testData.getTestDataForGivenColumn(toggles));

String toggleList = "XX";

WebUI.callTestCase(findTestCase('DMS/Test_POC/SetFeatureToggle'), [('featureToggleName') : new ArrayList().add("CC")],
	FailureHandling.STOP_ON_FAILURE)



WebUI.closeBrowser()

