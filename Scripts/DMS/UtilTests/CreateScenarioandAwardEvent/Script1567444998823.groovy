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

//Clicking on scenario tab and creating manual scenario and awarding supplier
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Scenario (1)'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Create (2)'))

WebUI.waitForElementClickable(findTestObject('Object Repository/a_Manual Scenario'), 10)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Manual Scenario'))

WebUI.clearText(findTestObject('Object Repository/input___FieldManualScenario'))

def scenarioName = 'CleansheetScenario' + System.currentTimeMillis().toString().substring(0, 5)
println scenarioName
WebUI.setText(findTestObject('Object Repository/input___FieldManualScenario'), scenarioName)

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Loading_w-pulldown-icon (1)'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_scenarioDropdown'))
String supplierName='Javatec.com (1)'
//String object="//a[text()="+"'"+supplierName+"']"
//String object=//a[contains(text(),'Javatec.com (1)')]
String object="//a[contains(text(),"+"'"+supplierName+"')]"
CustomKeywords.'sourcing.util2.sourcingKeywords.clickOnDynamicXpath1'(object);
//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/SupplierValueSelectScenarioUI'))
WebUI.delay(3)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Submit for award'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_Done_2'))

