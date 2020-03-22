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

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/ExcelImportButtonCG'))

//CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/UploadFileFromDesktopCG'))

//String filePath = 'Test Files/dms/Amortization_Costs.xls'
CustomKeywords.'common.util.aribakeywords.fileUpload'(findTestObject('Object Repository/Page_Ariba Spend Management/awFileUploadButton'),
	filePath)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/ReplaceRadioButtonInsideCG'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/ImportButtonInsideCG'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/DoneButtoninsideImportCG'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/ActionButtonInsideCG'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/UpdateTotalInsideCG'))