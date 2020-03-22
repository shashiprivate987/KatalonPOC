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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_ARPAdmin Template'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/b_Reporting Manager'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Custom attributes report admin'))

def value=WebUI.getAttribute(findTestObject('Object Repository/input_Amortization_Attribute'), 'checked')
println(value)
if(value.equals(null)){
	println('Checking AmortizationC cost group attribute for reporting')
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/label_chk w-chk-dsize'))
	}
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Enable for reporting'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_Yes'))
