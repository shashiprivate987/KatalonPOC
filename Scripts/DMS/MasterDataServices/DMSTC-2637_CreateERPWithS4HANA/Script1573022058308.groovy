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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

//WebUI.openBrowser('')

String ERPName = 'S4HANA'+System.currentTimeMillis()
String s4HanaIntegrationParam = 'Application.MasterData.EnableS4HANAIntegration=true'
String MDSFeature = 'ariba.base.meta.server.MDSFeatures.SourcingMDSIntegration'

def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
//String appLoginUrl = internal.GlobalVariable.baseURL + '/Sourcing/Main?realm=s4All'
String appLoginUrl = service + '/Sourcing/Main?realm='+realm

//--------------------------------------------------------------------------------------------------------------------------

WebUI.callTestCase(findTestCase('DMS/UtilTests/LoginIntoApp'), [('appLoginUrl') : appLoginUrl, ('userName') : userName],
	FailureHandling.STOP_ON_FAILURE)


// Turn On Toggle

WebUI.callTestCase(findTestCase('DMS/UtilTests/SetFeatureToggle'), [('featureToggleName') : MDSFeature],
	FailureHandling.STOP_ON_FAILURE)

// Turn on parameter

WebUI.callTestCase(findTestCase('DMS/UtilTests/SetRealmParameters'), [('parameterName') : s4HanaIntegrationParam],
	FailureHandling.STOP_ON_FAILURE)

//--------------------------------------------------------------------------------------------------------------------------

// Create contract from stager
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/Test Central ACM'))


WebUI.click(findTestObject('Object Repository/a_admin'))

WebUI.click(findTestObject('Object Repository/a_ARPAdmin Template'))

WebUI.click(findTestObject('Object Repository/a_Advanced'))

WebUI.refresh()

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.click(findTestObject('Object Repository/span_Integration Manager_w-img w-img-awxToggl'))

WebUI.refresh()

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(5)

WebUI.click(findTestObject('Object Repository/a_External System Configuration'))

WebUI.click(findTestObject('Object Repository/span_Create New'))

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.waitForElementPresent(findTestObject('Object Repository/input___cmjigc'), 2, FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('Object Repository/input___cmjigc'), ERPName)

WebUI.setText(findTestObject('Page_Ariba Spend Management/input___6rddsb'), ERPName)

WebUI.click(findTestObject('Object Repository/form_External Systems - Create External SystemSave'))

WebUI.click(findTestObject('Object Repository/span_SAPECC_w-dropdown-pic-ct'))

WebUI.refresh()

WebUI.click(findTestObject('Object Repository/span_Save'))

if(WebUI.verifyElementPresent(findTestObject('Object Repository/span_OK'), 3, FailureHandling.OPTIONAL)){
	WebUI.click(findTestObject('Object Repository/span_OK'))
}

// Verify whether the ERP created

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.setText(findTestObject('Object Repository/input_ID__8wo0tc'), ERPName)

WebUI.click(findTestObject('Object Repository/button_Search_erp'))


WebUI.verifyElementPresent(findTestObject('Object Repository/a_S4HANA_firstrecord'), 4, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/a_S4HANA_firstrecord'))



// Verify total number entities
int count = 0
CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)
WebDriver driver = DriverFactory.getWebDriver()
List<WebElement> listOfMMEntities = driver.findElements(By.xpath("//table[@awname='MasterDataProperties::displayGroup:tableBody']//tr[contains(@class,' tableRow')]"))

println listOfMMEntities.size()

WebUI.verifyMatch(listOfMMEntities.size().toString(), '21', false, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/button_Done'))


