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

/* @author: Sachchida.Singh
 * @feature: Supplier Alternative
 * @description: Verify that supplier can add max no of alternatives as limit set in template
 * @template: SupplierAlt
 * @preRequisites:
 * 	1> Supplier Alt flags should be enabled
 * 	2> Template SupplierAlt should be available.
 */
String templateName = 'SupplierAlt'
WebUI.openBrowser('')
String url="https://svcdev8ss.ariba.com/Sourcing/Main?&realm=s4all-14"
WebUI.navigateToUrl('https://svcmachss.ariba.com/Sourcing/Main?realm=MOLEX&passwordadapter=PasswordAdapter1')
//WebUI.navigateToUrl(url)
CustomKeywords.'sourcing.util2.sourcingKeywords.loginToTestCentral'()
//CustomKeywords.'sourcing.util2.sourcingKeywords.loadTemplate'(templateName)
String eventName = CustomKeywords.'sourcing.util2.sourcingKeywords.createEventRunStager'(null, templateName, 'openWithBidding',"RFP",1)

WebUI.delay(130)

CustomKeywords.'sourcing.util2.sourcingKeywords.viewProject'(eventName)
CustomKeywords.'sourcing.util2.sourcingKeywords.selectSupplierForBidding'(eventName)

CustomKeywords.'sourcing.util2.sourcingKeywords.createSupplierAlt'("pricing", "p1",true)
CustomKeywords.'sourcing.util2.sourcingKeywords.createSupplierAlt'("bundle", "p2")
CustomKeywords.'sourcing.util2.sourcingKeywords.createSupplierAlt'("pricing", "p3",false,true)
CustomKeywords.'sourcing.util2.sourcingKeywords.verifyExeededAltWidgetPresent'()
//CustomKeywords.'sourcing.util2.sourcingKeywords.clickOnDynamicXpath'("//button[@awname='ASPComposeResponse:preSubmitBidAction']")
//ASPComposeResponse:submitBidAction
//WebUI.closeBrowser()

