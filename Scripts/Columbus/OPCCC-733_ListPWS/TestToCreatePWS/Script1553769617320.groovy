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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://svcqass.ariba.com/Buyer/Main/ad/loginPage/SSOActions?awsr=true&realm=accAcwSap&awsso_hpk=true&awsso_ap=ACM&awsso_cc=cmVhbG06WVdOalFXTjNVMkZ3O2F3c3NvX3J1OmFIUjBjSE02THk5emRtTnhZWE56TG1GeWFXSmhMbU52YlM5VGIzVnlZMmx1Wnk5TllXbHVMejl5WldGc2JUMWhZMk5CWTNkVFlYQT07YXdzc29fbHU6YUhSMGNITTZMeTl6ZG1OeFlYTnpMbUZ5YVdKaExtTnZiUzlUYjNWeVkybHVaeTlOWVdsdUwyRmtMMk5zYVdWdWRFeHZaMjkxZEM5VFUwOUJZM1JwYjI1ejthd3Nzb19hcDpRVU5OO2F3c3NvX2FyaWQ6TVRVMU16WTROamM1TVRVek9RPT07YXdzc29fa3U6YUhSMGNITTZMeTl6ZG1OeFlYTnpMbUZ5YVdKaExtTnZiUzlUYjNWeVkybHVaeTlOWVdsdUwyRmtMMk5zYVdWdWRFdGxaWEJCYkdsMlpTOVRVMDlCWTNScGIyNXo7YXdzc29fZmw6TVE9PQ%3D%3D%3AbAwUx0DW02JZbdAEw3Oi84Je5LE%3D')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))
WebUI.setText(findTestObject('Object Repository/Page_Ariba Spend Management/input_User__bt5lrc'), 
    'cnoll')
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_TA..'))
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/body_if (window.ariba) ariba p'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_TestCentralPage/a_Create Workspace'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_TestStagerForm/input_SourcingProject__mfxbcc'))

WebUI.setText(findTestObject('Object Repository/OPCCC-733_TestListPWS_Objects/Page_TestStagerForm/input_Name of Workspace__od8r9'), 
    'TestPWSList')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/OPCCC-733_TestListPWS_Objects/Page_TestStagerForm/input_Term Type__ur5oxb'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/OPCCC-733_TestListPWS_Objects/Page_TestStagerForm/div_Run Stager'))

WebUI.closeBrowser()