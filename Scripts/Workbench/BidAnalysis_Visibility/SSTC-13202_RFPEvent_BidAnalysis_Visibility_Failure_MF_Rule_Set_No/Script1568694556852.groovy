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
 *
 Author : I320059
 */

def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
String appLoginUrl = service + '/Sourcing/Main?realm='+realm

WebUI.callTestCase(findTestCase('Workbench/UtilTests/LoginToApp'), [ ('appLoginUrl') : appLoginUrl, ('UserName') : 'customersupportadmin'],
	FailureHandling.STOP_ON_FAILURE)

//Enable the feature Toggle  OPT-187
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_asm'))

WebUI.callTestCase(findTestCase('Workbench/UtilTests/SetFeatureToggle'), [('toggleName') : 'ariba.sourcing.util.OWBSourcingFetaures.BIDANALYSIS'], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/b_TA'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_Test Central ACM'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_dashboard'))

WebUI.callTestCase(findTestCase('Workbench/UtilTests/CreateNewUser_With_OWBPermission'), [('NewUserName') : 'OWBAutomationUser01'
        , ('Group') : 'Event Administrator,SAP Enable Now User,Optimization Workbench Admin,Optimization Workbench User'], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/b_TA'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_Test Central ACM'))

// End CSA session and start new user session
WebUI.setText(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/input_User__bt5lrc'), 
    NewUserName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/input_User__njzfkc'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_sourcing'))


String templateName = 'RFPTemplate02_MF_Rule_No'
String eventContentExcel = 'Content_file_MFRule_No.xls'

WebUI.callTestCase(findTestCase('Workbench/UtilTests/CreateSourcingEvent'), [('eventName') : 'BidAnalysis_Visibility_Failure_MF_Rule_Set_No', ('templateName') : templateName
        , ('eventContentExcel') : eventContentExcel], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/b_TA'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_Test Central ACM'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_sourcing'))

//view project
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_Rfx Creation Wizard'))

//Verify Bia Analysis tab wont be present.
boolean BA = WebUI.verifyElementNotPresent(findTestObject('Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_Bid Analysis'), 
    3, FailureHandling.OPTIONAL)

if (BA) {
    WebUI.refresh()

    WebUI.comment('Bid Analysis tab is not visible on the event content page, as the Market feedback rule is set to NO')

    CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Optimization_Workbench_Object_Repo/Page_Ariba Spend Management/a_Award'))
}

WebUI.closeBrowser()

