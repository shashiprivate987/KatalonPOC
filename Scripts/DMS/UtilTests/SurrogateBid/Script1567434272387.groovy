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

/*
 * SurrogateBid
 *     This test case allows the buyer to perform surrogate bid if the event is in view. Basically clicks on
 *     Supplier tab and perform surrogate bid if submit_bid=1 else it will navigate to Supplier view of event
 */
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/SupplierTabRFPevent'))
WebUI.refresh()
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Javateccom'))
WebUI.waitForElementClickable(findTestObject('Object Repository/SurrogateBidLink_Supplier'), 10)
//WebUI.scrollToElement(findTestObject('Object Repository/SurrogateBidLink_Supplier'), 3)	
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/SurrogateBidLink_Supplier'))

if(WebUI.verifyElementPresent(findTestObject('Object Repository/CustomizeView_OpenEvent'), 3)){
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/CustomizeView_OpenEvent'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/ExpandAll_OpenEvent1'))

String object="//a/span[text()="+"'"+sourcingEvent+"']"

CustomKeywords.'sourcing.util2.sourcingKeywords.clickOnDynamicXpath1'(object);
}else
  println 'false'
WebUI.delay(5)
WebUI.doubleClick(findTestObject('Object Repository/span_Review Prerequisites'))
//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Review Prerequisites'))
WebUI.delay(3)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/label_Survival_w-rdo w-rdo-dsize'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_OK (3)'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_OK'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_OK_Confirm'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/label_Export to Excel_w-chk w-chk-dsize'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Confirm Selected Lots'))

if(submit_bid =='1'){

	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_Excel Import (1)'))
	CustomKeywords.'common.util.aribakeywords.fileUpload'(findTestObject('Object Repository/Page_Ariba Spend Management/awFileUploadButton'),
		filePath)
	
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_Upload (1)'))
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_Done (7)'))
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Submit Entire Response'))
	
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_OK'))
	
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Stop'))
}
