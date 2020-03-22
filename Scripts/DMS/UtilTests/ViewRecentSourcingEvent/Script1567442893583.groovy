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

WebUI.delay(3)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Home (2)'))


if(WebUI.verifyElementVisible(findTestObject('Object Repository/Dashboard_sourcing'), FailureHandling.CONTINUE_ON_FAILURE))
{
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Dashboard_sourcing'))
}else
{
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Dashboard_MoreLink'))
	WebUI.delay(4)
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_sourcing'))
   
}

String object1="//a[text()="+"'"+sourcingEvent+"']"
CustomKeywords.'sourcing.util2.sourcingKeywords.clickOnDynamicXpath1'(object1);

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Content1'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Get Sourcing Event'))

//WebUI.setText(findTestObject('Object Repository/input_Event Name__y26fkb'), sourcingEvent)

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Run Stager'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Buyer Monitoring Page'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Content1'))

