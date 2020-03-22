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



CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Manage Templates'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Request for Proposal_CleanSheet'))
String object1="//a[text()="+"'"+templateName+"']"
CustomKeywords.'sourcing.util2.sourcingKeywords.clickOnDynamicXpath1'(object1);

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Open'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('a_NewVersion'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Actions'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_NewVersion'))

WebUI.verifyTextPresent('Draft', false)