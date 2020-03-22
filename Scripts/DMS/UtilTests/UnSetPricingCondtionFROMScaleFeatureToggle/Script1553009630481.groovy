import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
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



CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testCentral'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_TestCentralPage/input_User__njzfkc'))

WebUI.scrollToElement(findTestObject('Object Repository/Page_TestCentralPage/a_Enable  Or  Disable  Feature'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_TestCentralPage/a_Enable  Or  Disable  Feature'))

WebUI.setText(findTestObject('Object Repository/input_Full Qualified Name of F'), 'ariba.sourcing.util.SourcingEventFeatures.FromScalesInPricingCondition')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_TestStagerForm/input_disable feature__nea9oc'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Run Stager'))

