 /**
 * @Author: i331477
 * 
 * SRC-21890:Custom formula with cost term support for Pricing Conditions
 * Pre Requisites:
1) Enable below feature for the realm :

Pricing Conditions - Volume Scales/Breaks in Sourcing and Contracts

2) Create new template by Copying RFP template and set "Allow Advanced Pricing Conditions" under bidding rules to Yes and publish template

Buyer should be able to select Allow Advanced pricing conditions or Allow pricing conditions while creating template and should not be able to select both

3) In the template enable 'Can project owner add formula'

------------------------------------------------------------------------------------------------------------------------------------
	
1. Create an event and select Pricing Condition Rule
2. Add Lines from MM data

3. Add Money terms to the item like Discount, Shipping Cost, Tax etc and add them to APC

4. Now create custom Formula and based on arithmetic operations that are seen on the UI add the Money terms and make a formula to it

Ex: TotalCost = Extended Price - Discount + Shipping Cost + Tax
------------------------------------------------------------------------------------------------------------------------------------
Expected Results
4. Should be able to add the formula to the Pricing Conditions and clicking on the APC link should be able to see TotalCost field with the calculations
 */ import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
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
import com.kms.katalon.core.testobject.ConditionType as ConditionType

WebUI.openBrowser('')

String userName = 'customersupportadmin'
def rfpEventTemplate = 'SourcingTemplatewithAPC'
String filePath = '/Test Files/APCEventContentWithFROMScalesSupport.xls'

WebUI.navigateToUrl(internal.GlobalVariable.baseURL + '/Sourcing/Main/aw?awh=r&awssk=aBCugA3V&realm=s4All-3')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/input_User__bt5lrc'), userName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

WebUI.comment('Set Toggle')

WebUI.callTestCase(findTestCase('test-upstream/DMS/UtilTests/SetPricingCondtionFeatureToggle'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/Test Central ACM'))

WebUI.comment('Set APC FROM scale Toggle')

WebUI.callTestCase(findTestCase('test-upstream/DMS/UtilTests/SetPricingCondtionFROMScaleFeatureToggle'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/Test Central ACM'))


WebUI.comment('Create Template with APC and Formula')

WebUI.callTestCase(findTestCase('test-upstream/DMS/UtilTests/LoadSourcingTemplateHavingAPCandFormula'), [('rfpEventTemplate') : rfpEventTemplate], 
    FailureHandling.STOP_ON_FAILURE)

def sourcingproject = 'SourcingProject_' + System.currentTimeMillis()

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_Ariba Spend Management/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Page_TestCentralPage/Test Central ACM'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/a_Create Sourcing Project Page'))

WebUI.setText(findTestObject('Object Repository/Page_Ariba Spend Management/input___ihgt1d'), sourcingproject)

WebUI.comment('Event Type')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/span_No Choice_w-dropdown-pic-'))

String xPath1 = '//div[@awname=\'EventType::CreateView_EventType:itemList_label_RFP\']'

TestObject to1 = new TestObject('objectName')

to1.addProperty('xpath', ConditionType.EQUALS, xPath1)

CustomKeywords.'common.util.aribakeywords.aribaClick'(to1)

String testProjectRdBtn = 'IsTest_selectedItem::CreateView_IsTest:currChoice_1'

testProjectRdBtnClick = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/label_Rahul APC template_w-rdo'), 
    'awname', 'equals', testProjectRdBtn, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(testProjectRdBtnClick)

String awnameTemplate = ('selectedTemplate_' + rfpEventTemplate) + '::CreateProjectDetailsPage:itemTemplate'

templateClick = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/label_Rahul APC template_w-rdo'), 
    'awname', 'equals', awnameTemplate, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(templateClick)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/span_Create'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/label_Yes_w-rdo w-rdo-dsize_1'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/label_AllowPricingConditions_w'))

// Verify Volume Scale type drop down added

WebUI.verifyElementPresent(findTestObject('Object Repository/label_VolumeScaleType'), 2, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementPresent(findTestObject('Object Repository/volume_scale_drpdwn'), 2, FailureHandling.STOP_ON_FAILURE)


CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/Page_Ariba Spend Management/a_Content'))

WebUI.comment('add import excel code')

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/button_Excel Import'))


//UnCheck Buyer terms check box if it is checked
String buyerTerms = 'ASPExcelImport:buyerEnteredTermsSelected_1'

buyerTermsChkBx = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/label_Rahul APC template_w-rdo'),
	'awname', 'equals', buyerTerms, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(buyerTermsChkBx)

try{
	if(WebUI.verifyElementHasAttribute(findTestObject('Object Repository/label_Buyer Terms_w-chk w-chk-'), 'checked', 2)){
		CustomKeywords.'common.util.aribakeywords.aribaClick'(buyerTermsChkBx)
	}
}catch(Exception e){

WebUI.delay(3)
}

CustomKeywords.'common.util.aribakeywords.fileUpload'(findTestObject('Page_Ariba Spend Management/awFileUploadButton'), 
    filePath)

String excelImportAwname = 'ASPExcelImport:importExcelDocumentAction'

excelImportBtn = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Ariba Spend Management/span_Import'), 
    'awname', 'equals', excelImportAwname, true)

CustomKeywords.'common.util.aribakeywords.aribaClick'(excelImportBtn)

String apcLnk = "//a[contains(@awname,'DISCOUNTAMT:pricingInfoDisplay')]"

apcLnkDOM = WebUI.modifyObjectProperty(findTestObject('Object Repository/a_View pricing conditions'),
	'xpath', 'equals', apcLnk, true)

WebUI.verifyElementPresent(apcLnkDOM, 2, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(apcLnkDOM)

// Rows iterate and verify APC Formula Values
WebDriver driver = DriverFactory.getWebDriver()

'Expected value from Table'

'To locate table'
List<WebElement> headerValues = driver.findElements(By.xpath('//table[@awname=\'getValidityPeriods\']//tr//div[@class=\'tableHead\']//tr[2]//th'))

'To locate rows of table it will Capture all the rows available in the table'

'To calculate no of rows In table'
int rows_count = headerValues.size()

'Loop will execute for all the rows of the table'
Loop: for (int row = 0; row < rows_count; row++) {
    // 'To locate columns(cells) of that specific row'
    String val = headerValues.get(row).getText().trim()

    if (val.toLowerCase().contains('formula')) {
        //  println('Header Values are : ' + val)
        println(((('Header Values are : ' + val) + ' and found at ') + row) + 'th location..')

        int formulaRowHeader = row + 1

        println('Formula Value : ' + formulaRowHeader)

        String formularVal = driver.findElement(By.xpath(('//table[@awname=\'getValidityPeriods:tableBody\']//tr[3]//td[' + 
                formulaRowHeader) + ']//td[3]')).getText().trim()

        WebUI.verifyNotEqual(formularVal, null)
    }
}


WebUI.verifyTextPresent("From 0", false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent("From 20", false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent("From 50", false, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/span_Done'))


WebUI.callTestCase(findTestCase('test-upstream/DMS/UtilTests/DeleteTemplate'), [('rfpEventTemplate') : rfpEventTemplate], FailureHandling.STOP_ON_FAILURE)

WebUI.comment('UnSet APC FROM scale Toggle')

WebUI.callTestCase(findTestCase('test-upstream/DMS/UtilTests/UnSetPricingCondtionFROMScaleFeatureToggle'), [:], FailureHandling.STOP_ON_FAILURE)


WebUI.closeBrowser()



