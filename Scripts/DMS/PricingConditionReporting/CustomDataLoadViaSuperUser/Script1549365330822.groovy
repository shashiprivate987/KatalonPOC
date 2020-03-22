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

WebUI.openBrowser('')

// get presentation schema from realm
String service = 'https://svcmachss.ariba.com/'

WebUI.navigateToUrl(service + '/Sourcing/Main/?realm=s4All-18')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/input_User__bt5lrc'), 'customersupportadmin')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

WebUI.scrollToElement(findTestObject('Object Repository/Page_Ariba Spend Management/a_Schema Manager'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/input_User__njzfkc'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/a_Schema Manager'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/a_Advanced'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/a_Presentation Schema'))

String schemaName = WebUI.getText(findTestObject('FetchRealmPresentationSchemaName')).trim()

// trigger scheduled task from the superuser
WebUI.navigateToUrl('https://app184.lab1.ariba.com:7445/Sourcing/Main')

WebUI.setText(findTestObject('Object Repository/Page_Ariba Spend Management/input_User Name_UserName'), 'superuser')

WebUI.setText(findTestObject('Page_Ariba Spend Management/input_Password_Password'), 'ariba')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/input_Password_w-login-form-bt'))

Boolean advancedLink = WebUI.verifyElementClickable(findTestObject('Object Repository/Page_Ariba Spend Management/a_Advanced'))

if (advancedLink) {
    CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/a_Advanced'))
}

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/b_DataLoad Manager'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/a_Custom Data Loads'))

WebUI.comment('Add Schema Choose selector')

WebUI.comment('Get Schema Name from Customer Site')

// ex  awname = Star.Dedicated.Schema03
String awname = ('//div[@awname=\'AdminTaskBasic:schemaList_label_' + schemaName) + '\']'

TestObject to2 = new TestObject('selectSchema')

to2.addProperty('xpath', ConditionType.EQUALS, awname)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/clickSchemaList'))

//WebUI.scrollToElement(to2, 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(to2)

WebUI.setText(findTestObject('Page_Ariba Spend Management/input_Task Name__iuqyp'), 'ASM.EventDimensionLoad')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/button_SearchTask'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/label_Data Load Task_w-chk w-c'))

//WebUI.setText(findTestObject('Object Repository/Page_Ariba Spend Management/input___61sssb'), 'Thu Nov 01 00:00:00 GMT 2018')

//WebUI.setText(findTestObject('Object Repository/Page_Ariba Spend Management/input___nbzbtb'), 'Thu Nov 28 00:00:00 GMT 2018')

def loadStartDate = CustomKeywords.'aribaui.util.AribaUIHelper.getNotificationDueDate'('E, dd MMM yyyy HH:mm:ss z', 0)

WebUI.setText(findTestObject('Object Repository/Page_Ariba Spend Management/input___61sssb'), loadStartDate)

def loadEndDate = CustomKeywords.'aribaui.util.AribaUIHelper.getNotificationDueDate'('E, dd MMM yyyy HH:mm:ss z', 0)

WebUI.setText(findTestObject('Object Repository/Page_Ariba Spend Management/input___nbzbtb'), loadEndDate)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/span_ScheduleLoad'))

WebUI.setText(findTestObject('Object Repository/Page_Ariba Spend Management/input_Task Name__iuqyp'), 'ASM.RfxEventSummaryLoad')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/button_SearchTask'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/label_Data Load Task_w-chk w-c'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/span_ScheduleLoad'))

WebUI.setText(findTestObject('Object Repository/Page_Ariba Spend Management/input_Task Name__iuqyp'), 'ASM.RfxItemSummaryLoad')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/button_SearchTask'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/label_Data Load Task_w-chk w-c'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/span_ScheduleLoad'))

