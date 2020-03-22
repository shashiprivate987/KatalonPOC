import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import common.util.aribakeywords as Ariba


//--------------------------------------------------------------------------------------------------------------------------

/*
 * LOCATOR CONSTANTS
 */
String OPENCLID_LOC = "//a[contains(@awname,'Open::AbstractDocumentDownloadIcon:itemAction')]"
String UPLOADDOC_LOC = 'ASCContentFrame:excelImportAction'
String EXCELIMPORT_LOC = 'ASPExcelImport:importExcelDocumentAction'
String OPENDOC_LOC = 'Open::AbstractDocumentDownloadIcon:itemAction'
String CONTENTITEMS_LOC = "//table[@awname='displayGroup:tableBody']//tr[contains(@awname,'AWRefreshRegion')]"
String APPENDEDAPCLINK_LOC = "//a[@awname='0.2_PRICEPrice::PRICE:pricingInfoDisplay' and text()='View pricing conditions ']"
String APCLOT_LOT = "//tr[@awname='0.6::AWRefreshRegion']"
String APCLOT_CHILDITEM_LOC = "//tr[@awname='0.6_0.6.1::AWRefreshRegion']"
String APCLINK_LOC = "//a[@awname='0.6_RITASHORTSTRINGIFZ000003Computed Term::RITASHORTSTRINGIFZ000003:pricingInfoDisplay']"
String LOT_TERM_APC_PRICE_LOC = "//input[@awname='_DerivedStringValue::PCCostTermView_DerivedStringValue:fieldValue']"

//--------------------------------------------------------------------------------------------------------------------------
/**
 * Variable declaration
 */
def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
String appLoginUrl = service + '/Sourcing/Main?realm='+realm
def CW = contractName + System.currentTimeMillis()

//--------------------------------------------------------------------------------------------------------------------------
/*
 * App Login
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
 *  Create contract from stager
 */

Ariba.aribaClick(findTestObject('Page_Ariba Spend Management/a_TA..'))

Ariba.aribaClick(findTestObject('Page_TestCentralPage/Test Central ACM'))

WebUI.scrollToElement(findTestObject('Page_Ariba Spend Management/a_Create Workspace'), 3)

Ariba.aribaClick(findTestObject('Page_Ariba Spend Management/a_Create Workspace'))

Ariba.aribaClick(findTestObject('Object Repository/input supplier cw'))

WebUI.setText(findTestObject('Object Repository/input supplier cw'), supplier)

WebUI.setText(findTestObject('Page_Ariba Spend Management/input_Name of Workspace__bdp8j'), CW)

WebUI.setText(findTestObject('Page_Ariba Spend Management/input_Name of Template__x1sbod'), contractTemplate)

Ariba.aribaClick(findTestObject('Object Repository/div_Run Stager'))

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

WebUI.scrollToElement(findTestObject('Page_Ariba Spend Management/View_Project'), 3)

Ariba.aribaClick(findTestObject('Page_Ariba Spend Management/View_Project'))

Ariba.waitForPageToLoad(3)

// Open CLID and Upload File
WebUI.refresh()

Ariba.aribaClick(findTestObject('Object Repository/Page_Ariba Spend Management/span_Documents_w-pml-icon'))

Thread.sleep(2)

WebUI.verifyElementPresent(Ariba.createTestObject(OPENCLID_LOC), 2, FailureHandling.STOP_ON_FAILURE)

WebDriver driver = DriverFactory.getWebDriver()

driver.findElement(By.xpath(OPENCLID_LOC)).click()

Ariba.aribaClick(findTestObject('Object Repository/Page_ASPEditContentDocument/span_Excel Import'))

uploadDocLink = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'equals', UPLOADDOC_LOC, true)

Ariba.aribaClick(uploadDocLink)

WebUI.comment('File Upload with xls file and element and click done once it is uploaded')

CustomKeywords.'common.util.aribakeywords.fileUpload'(findTestObject('Object Repository/Page_Ariba Spend Management/awFileUploadButton'),
	filePath)

excelImportBtn = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'equals', EXCELIMPORT_LOC, true)

Ariba.aribaClick(excelImportBtn)

WebUI.verifyTextPresent('View pricing conditions', false, FailureHandling.STOP_ON_FAILURE)

Ariba.waitForPageToLoad(3)

// Wait for Arches to get the Newly created Contract to be searchable
Ariba.aribaClick(findTestObject('Object Repository/a_TA..'))

Ariba.aribaClick(findTestObject('Object Repository/testCentral'))

//Wait for Arches Work Item Searched Out
WebUI.scrollToElement(findTestObject('Page_TestCentralPage/Wait for Arches Work Item Searched Out'), 3)

Ariba.aribaClick(findTestObject('Page_TestCentralPage/Wait for Arches Work Item Searched Out'))

WebUI.setText(findTestObject('Page_TestCentralPage/SearchParameter_workspaceTitle'), CW)

Ariba.aribaClick(findTestObject('Object Repository/div_Run Stager'))


//--------------------------------------------------------------------------------------------------------------------------
/*
 *  Create sourcing event
 */

String sourcingEvent = sourcingEvent + System.currentTimeMillis()

WebUI.callTestCase(findTestCase('DMS/UtilTests/CreateSourcingEvent'), [('sourcingEvent') : sourcingEvent, ('eventType') : eventType
		, ('rfpEventTemplate') : rfpEventTemplate, ('eventContentFileName') : eventContentFileName, ('eventEndState') : eventEndState
		, ('numberOfBids') : numberOfBids], FailureHandling.STOP_ON_FAILURE)

//--------------------------------------------------------------------------------------------------------------------------

Ariba.aribaClick(findTestObject('Object Repository/a_Buyer Monitoring Page'))

Ariba.waitForPageToLoad(3)

WebUI.refresh()

try{
	Thread.sleep(5)
	Ariba.aribaClick(findTestObject('Object Repository/a_Award Tab'))
}catch(Exception e){
e.printStackTrace()
}


try{
	
	while (WebUI.verifyAlertPresent(2)) {
		WebUI.waitForAlert(2)
	    WebUI.verifyAlertPresent(2)
	    WebUI.acceptAlert()
	    WebUI.delay(1)
	}
}catch(Exception e){
e.printStackTrace()
}

/*
 *  Verify APC Rule Link at award tab
 */
TestObject  expandPlus = Ariba.createTestObject(EXPANDLOTBTN)
WebUI.scrollToElement(expandPlus, 3)
Ariba.aribaClick(expandPlus)
WebUI.verifyTextPresent("View pricing conditions", false, FailureHandling.STOP_ON_FAILURE)

Ariba.aribaClick(findTestObject('Object Repository/span_Contract_w-pulldown-icon'))

WebUI.comment('Replace Existing contract')

Ariba.aribaClick(findTestObject('Object Repository/Add to existing contract'))

Ariba.aribaClick(findTestObject('Object Repository/label_Submit Initial Bid_w-rdo'))

Ariba.aribaClick(findTestObject('Object Repository/btn add to existing contract'))

TestObject rdBtnObj = Ariba.createTestObject("//label[@awname='"+CW+"::AWTSingleSelectColumnRenderer']")

WebUI.refresh()

WebUI.setText(findTestObject('Object Repository/text_search_cntr'), CW)

Ariba.aribaClick(findTestObject('Object Repository/contract_Search_btn'))

WebUI.waitForElementVisible(rdBtnObj, 5)

WebUI.verifyElementPresent(rdBtnObj, 3, FailureHandling.STOP_ON_FAILURE)

Ariba.aribaClick(rdBtnObj)

Ariba.aribaClick(findTestObject('Object Repository/btn select contract'))

Ariba.aribaClick(findTestObject('Object Repository/rd append contract'))

Ariba.aribaClick(findTestObject('Object Repository/btn ok save'))

/*
 *  open CLID
 */

WebUI.refresh()

WebUI.waitForElementVisible(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'), 3, FailureHandling.OPTIONAL)

Ariba.aribaClick(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'))

openDocLnk = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'contains', OPENDOC_LOC, true)

Ariba.aribaClick(openDocLnk)

Ariba.waitForPageToLoad(3)

/*
 *  Verify Lot Structure in CLID
 */
//WebDriver driver = DriverFactory.getWebDriver()

int lotSize = driver.findElements(By.xpath(APCLOT_LOT)).size()
WebUI.verifyMatch(lotSize.toString(), '1', false, FailureHandling.STOP_ON_FAILURE)

int childItem = driver.findElements(By.xpath(APCLOT_CHILDITEM_LOC)).size()
WebUI.verifyMatch(childItem.toString(), '1', false, FailureHandling.STOP_ON_FAILURE)

/*
 * Verify APC Link
 */
WebUI.verifyTextPresent('View pricing conditions', false, FailureHandling.STOP_ON_FAILURE)

List<Object> apcterms = driver.findElements(By.xpath("//a[contains(@awname,'Computed Term::RITASHORTSTRINGIFZ00000')]"))
WebUI.verifyEqual(apcterms.size(), 2, FailureHandling.STOP_ON_FAILURE)

//TestObject apcLink = Ariba.createTestObject(APCLINK_LOC)
TestObject apcLink = Ariba.createTestObject("//a[contains(@awname,'Computed Term::RITASHORTSTRINGIFZ00000')]")
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
