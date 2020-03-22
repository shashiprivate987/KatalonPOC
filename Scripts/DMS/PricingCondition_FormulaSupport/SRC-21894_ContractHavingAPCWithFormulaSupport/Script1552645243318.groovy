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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
//String appLoginUrl = internal.GlobalVariable.baseURL + '/Sourcing/Main?realm=s4All'
String appLoginUrl = service + '/Sourcing/Main?realm='+realm

def CW = contractName+System.currentTimeMillis()

String userName = 'customersupportadmin'

WebUI.openBrowser('')

WebUI.navigateToUrl(appLoginUrl)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/testcentralpageusername'), userName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

//--------------------------------------------------------------------------------------------------------------------------

// Create contract from stager
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/Test Central ACM'))

WebUI.scrollToElement(findTestObject('Page_Ariba Spend Management/a_Create Workspace'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_Create Workspace'))

WebUI.setText(findTestObject('Page_Ariba Spend Management/input_Name of Workspace__bdp8j'), CW)

WebUI.setText(findTestObject('Page_Ariba Spend Management/input_Name of Template__x1sbod'), contractTemplate)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Run Stager'))

//--------------------------------------------------------------------------------------------------------------------------

// Set project view to Compact
try{
	WebUI.callTestCase(findTestCase('DMS/UtilTests/SetContractProjectCompactView'), [('projectName') : CW],
		FailureHandling.OPTIONAL)
}catch (Exception e) {
	e.printStackTrace()
}

//--------------------------------------------------------------------------------------------------------------------------

WebUI.refresh()

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/View_Project'))

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

// Open CLID and Upload File

WebUI.refresh()

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/span_Documents_w-pml-icon'))

String openDoc = 'Open::AbstractDocumentDownloadIcon:itemAction'

openDocLink = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'contains', openDoc, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(openDocLink)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_ASPEditContentDocument/span_Excel Import'))


String uploadDoc = 'ASCContentFrame:excelImportAction'

uploadDocLink = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'equals', uploadDoc, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(uploadDocLink)


WebUI.comment('File Upload with xls file and element and click done once it is uploaded')

CustomKeywords.'common.util.aribakeywords.fileUpload'(findTestObject('Object Repository/Page_Ariba Spend Management/awFileUploadButton'),
	filePath)

String excelImportAwname = 'ASPExcelImport:importExcelDocumentAction'

excelImportBtn = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'equals', excelImportAwname, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(excelImportBtn)

apcLink = findTestObject('Object Repository/a_View pricing conditions')

WebUI.waitForElementPresent(apcLink, 2, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(apcLink, 2, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(apcLink)

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.verifyTextPresent("TotalCost_CustomFormula", false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent("To 22", false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent("To 44", false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent("Feb 2019 - Jan 2020", false, FailureHandling.STOP_ON_FAILURE)



try {
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Done'))
}
catch (Exception e) {
	e.printStackTrace()
}


CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

/*CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Publish'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_OK'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Actions (4)'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_publishContract'))


WebUI.verifyTextPresent('Published', false, FailureHandling.STOP_ON_FAILURE)*/


WebUI.closeBrowser()
