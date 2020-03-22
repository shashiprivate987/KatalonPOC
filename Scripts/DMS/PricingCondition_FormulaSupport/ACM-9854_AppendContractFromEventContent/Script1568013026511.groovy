import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable



def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
//String appLoginUrl = internal.GlobalVariable.baseURL + '/Sourcing/Main?realm=s4All'
String appLoginUrl = service + '/Sourcing/Main?realm='+realm

def CW = contractName+System.currentTimeMillis()

//--------------------------------------------------------------------------------------------------------------------------

WebUI.callTestCase(findTestCase('DMS/UtilTests/LoginIntoApp'), [('appLoginUrl') : appLoginUrl, ('userName') : userName],
	FailureHandling.STOP_ON_FAILURE)

//--------------------------------------------------------------------------------------------------------------------------

WebDriver driver = DriverFactory.getWebDriver()

// Create contract from stager
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/Test Central ACM'))

WebUI.scrollToElement(findTestObject('Page_Ariba Spend Management/a_Create Workspace'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_Create Workspace'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/input supplier cw'))

WebUI.setText(findTestObject('Object Repository/input supplier cw'), supplier)

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

WebUI.scrollToElement(findTestObject('Page_Ariba Spend Management/View_Project'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/View_Project'))


// Actions button for compact view
CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

// Open CLID and Upload File
WebUI.refresh()

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/span_Documents_w-pml-icon'))


Thread.sleep(2)
TestObject objOpenCLID = new TestObject('objOpenCLID')
String objOpenCLIDLoc = "//a[contains(@awname,'Open::AbstractDocumentDownloadIcon:itemAction')]"
objOpenCLID.addProperty("xpath", ConditionType.CONTAINS, objOpenCLIDLoc)

WebUI.verifyElementPresent(objOpenCLID, 2, FailureHandling.STOP_ON_FAILURE)
driver.findElement(By.xpath("//a[contains(@awname,'Open::AbstractDocumentDownloadIcon:itemAction')]")).click()

//CustomKeywords.'common.util.aribakeywords.aribaClick'(openDocLink)

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


WebUI.verifyElementPresent(findTestObject('Object Repository/a_View pricing conditions'), 3, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_View pricing conditions'))

try {
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Done'))
}
catch (Exception e) {
	e.printStackTrace()
}


CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

// Wait for Arches to get the Newly created Contract to be searchable
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testCentral'))

//Wait for Arches Work Item Searched Out
WebUI.scrollToElement(findTestObject('Page_TestCentralPage/Wait for Arches Work Item Searched Out'), 3)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/Wait for Arches Work Item Searched Out'))

WebUI.setText(findTestObject('Page_TestCentralPage/SearchParameter_workspaceTitle'), CW)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/div_Run Stager'))


//--------------------------------------------------------------------------------------------------------------------------
// Create sourcing event

String sourcingEvent = sourcingEvent + System.currentTimeMillis()

WebUI.callTestCase(findTestCase('DMS/UtilTests/CreateSourcingEvent'), [('sourcingEvent') : sourcingEvent, ('eventType') : eventType
		, ('rfpEventTemplate') : rfpEventTemplate, ('eventContentFileName') : eventContentFileName, ('eventEndState') : eventEndState
		, ('numberOfBids') : numberOfBids], FailureHandling.STOP_ON_FAILURE)

//--------------------------------------------------------------------------------------------------------------------------

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Buyer Monitoring Page'))

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.refresh()

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Award Tab'))

WebUI.verifyTextPresent('View pricing conditions ', false, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Contract_w-pulldown-icon'))

WebUI.comment('Replace Existing contract')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Add to existing contract'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/label_Submit Initial Bid_w-rdo'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/btn add to existing contract'))


/*println CW
//CW = "CW_1567959142364"
TestObject rdBtnObj = new TestObject("Clicking radio btn")
WebUI.refresh()
String expr = "//label[@awname='"+CW+"::AWTSingleSelectColumnRenderer']"
rdBtnObj.addProperty("xpath", ConditionType.EQUALS, expr)
	
	
boolean visible = WebUI.waitForElementVisible(rdBtnObj, 10)
	
if (!(visible)) {
    int i = 1

	WebUI.setText(findTestObject('Object Repository/text_search_cntr'), CW)
    while (i < 10) {
		
		WebUI.refresh()
		
		CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/contract_Search_btn'))
		
        boolean v = WebUI.waitForElementVisible(rdBtnObj, 5)
        if (v) {
            break
        }
        
        i++
    }
}*/
	

println CW
TestObject rdBtnObj = new TestObject("Clicking radio btn")
WebUI.refresh()
String expr = "//label[@awname='"+CW+"::AWTSingleSelectColumnRenderer']"
rdBtnObj.addProperty("xpath", ConditionType.EQUALS, expr)


WebUI.setText(findTestObject('Object Repository/text_search_cntr'), CW)
CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/contract_Search_btn'))
WebUI.waitForElementVisible(rdBtnObj, 5)

WebUI.verifyElementPresent(rdBtnObj, 3, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(rdBtnObj)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/btn select contract'))


CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/rd append contract'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/btn ok save'))

// open CLID

WebUI.refresh()

WebUI.waitForElementVisible(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'), 3, FailureHandling.OPTIONAL)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Documents_w-pml-icon'))

String openDoc1 = 'Open::AbstractDocumentDownloadIcon:itemAction'

openDocLnk = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'),
	'awname', 'contains', openDoc1, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(openDocLnk)

CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)


int count = 0

count = driver.findElements(By.xpath("//table[@awname='displayGroup:tableBody']//tr[contains(@awname,'AWRefreshRegion')]")).size()


WebUI.verifyMatch(count.toString(), '2', false, FailureHandling.STOP_ON_FAILURE)

driver.findElement(By.xpath("//a[@awname='0.2_PRICEPrice::PRICE:pricingInfoDisplay' and text()='View pricing conditions ']")).click()


CustomKeywords.'common.util.aribakeywords.waitForPageToLoad'(3)

WebUI.verifyTextPresent('Mar 2019', false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent('To 22', false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent('$4,400.00', false, FailureHandling.STOP_ON_FAILURE)

//WebUI.verifyTextPresent('$130.00 USD', false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent('To 44', true, FailureHandling.STOP_ON_FAILURE)

try {
	CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/span_Done'))
}
catch (Exception e) {
	e.printStackTrace()
}

WebUI.closeBrowser()
