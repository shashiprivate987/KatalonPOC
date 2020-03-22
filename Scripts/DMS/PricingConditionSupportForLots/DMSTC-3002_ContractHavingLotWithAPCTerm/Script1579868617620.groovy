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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import common.util.aribakeywords as Ariba

//--------------------------------------------------------------------------------------------------------------------------
/**
 * Locators
 * 
 */


String LOT_TERM_APC_PRICE_LOC = "//input[@awname='_DerivedStringValue::PCCostTermView_DerivedStringValue:fieldValue']"
String LOTTERMAPCLINK_LOC = "//a[contains(@awname,'CW Term') and text()='View pricing conditions ']"

//--------------------------------------------------------------------------------------------------------------------------
/**
 * Variable declaration
 */

def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
String appLoginUrl = service + '/Sourcing/Main?realm='+realm
def CW = contractName+System.currentTimeMillis()
String userName = 'customersupportadmin'

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


def toggleList = [volumeScaleFeature, fromScaleFeature, appendReplaceContract, prepackMixedCase, itemHierarchy, mixedCasedPrepack]

for(int i=0; i< toggleList.size(); i++){
	WebUI.callTestCase(findTestCase('DMS/UtilTests/SetFeatureToggle'), [('featureToggleName') : toggleList[i]],
		FailureHandling.STOP_ON_FAILURE)
}

//--------------------------------------------------------------------------------------------------------------------------
/*
 * Enabling Realm Parameters
 */
String mixedCaseIntegration = 'Application.AQS.ArticleIntegration.MixedCaseIntegration.Enabled'
String mixedCases = 'Application.AQS.ArticleIntegration.MixedCases.Enabled'

def realmParamList = [mixedCaseIntegration, mixedCases]

for(int i=0; i<realmParamList.size(); i++){

	WebUI.callTestCase(findTestCase('DMS/UtilTests/SetRealmParameters'), [('parameterName') : realmParamList[i]],
		FailureHandling.STOP_ON_FAILURE)
	
}

//--------------------------------------------------------------------------------------------------------------------------

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

WebUI.verifyTextPresent('View pricing conditions' , false, FailureHandling.STOP_ON_FAILURE)

TestObject termApcLink = Ariba.createTestObject(LOTTERMAPCLINK_LOC)

WebUI.waitForElementPresent(termApcLink, 2, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(termApcLink, 2, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(termApcLink)

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebDriver driver = DriverFactory.getWebDriver()
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

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

/*CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Publish'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_OK'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Actions (4)'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_publishContract'))

WebUI.verifyTextPresent('Published', false, FailureHandling.STOP_ON_FAILURE)*/


WebUI.closeBrowser()
