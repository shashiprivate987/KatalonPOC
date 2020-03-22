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


String cleanSheetFeature = 'ariba.sourcing.util.SourcingEventFeatures.CleanSheet'
String mixedPack= 'ariba.sourcing.util.RetailSourcingFeatures.PrePackAndMixedCases'
String volumeScaleFeature = 'ariba.sourcing.util.SourcingEventFeatures.PricingConditionsWithScales'
String fromScaleFeature = 'ariba.sourcing.util.SourcingEventFeatures.FromScalesInPricingCondition'
String appendReplaceContract = 'ariba.collaborate.util.CollaborateFeatures.AppendLineItemsInExistingContract'
def rfpEventTemplate = 'SourcingTemplatewithAPC'
def contractTemplate = 'contractTemplateForAPC'
def template = 'Request for Proposal_CleanSheet'
def template1 = 'RequestforProposal_CleanSheet1'
String dfsCheckparameterNameValuePair = 'Application.ACM.DFSCheckEnabled=false'
String clidEnableparameterNameValuePair = 'Application.ACM.ContractLineItemsDocument.Enabled=true'
String hideAppendReplaceCLIDpopup = 'Application.ACM.HideAppendOrReplaceCLIDPopUpWindow=false'
String attributeReportingFeature = 'ariba.collaborate.util.CollaborateFeatures.ItemAttributesReporting'
// Login into App
def service = internal.GlobalVariable.baseURL
def realm = internal.GlobalVariable.realm
//String appLoginUrl = internal.GlobalVariable.baseURL + '/Sourcing/Main?realm=s4All'
String appLoginUrl = service + '/Sourcing/Main?realm='+realm

String userName = 'customersupportadmin'

WebUI.openBrowser('')

WebUI.navigateToUrl(appLoginUrl)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_Test Central'))

WebUI.setText(findTestObject('Object Repository/testcentralpageusername'), userName)

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testcentralsetuserbutton'))

//--------------------------------------------------------------------------------------------------------------------------


CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/a_TA..'))

CustomKeywords.'common.util.aribakeywords.aribaClick'(findTestObject('Object Repository/testCentral'))

//---------------------------------------------------------------------------------------------------------------

//Attribute Reporting feature
WebUI.callTestCase(findTestCase('DMS/UtilTests/SetFeatureToggle'), [('featureToggleName') : attributeReportingFeature],
FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('DMS/UtilTests/SetFeatureToggle'), [('featureToggleName') : volumeScaleFeature],
FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('DMS/UtilTests/SetFeatureToggle'), [('featureToggleName') : fromScaleFeature],
FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('DMS/UtilTests/SetFeatureToggle'), [('featureToggleName') : appendReplaceContract],
FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('DMS/UtilTests/SetFeatureToggle'), [('featureToggleName') : cleanSheetFeature],
FailureHandling.STOP_ON_FAILURE)
	
WebUI.callTestCase(findTestCase('DMS/UtilTests/DisableFeatureToggle'), [('featureToggleName') : mixedPack],
FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('DMS/Stager/LoadTemplate'), [('TemplateName') : template], FailureHandling.STOP_ON_FAILURE)

//WebUI.callTestCase(findTestCase('DMS/Stager/LoadTemplate'), [('TemplateName') : template1], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('DMS/UtilTests/LoadEventTemplateHavingAPC'), [('rfpTemplateName') : rfpEventTemplate],
FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('DMS/UtilTests/LoadContractTemplateHavingAPC'), [('contractTemplateName') : contractTemplate],
FailureHandling.STOP_ON_FAILURE)

//	Disable DFS pop-up
WebUI.callTestCase(findTestCase('DMS/UtilTests/SetRealmParameters'), [('parameterName') : dfsCheckparameterNameValuePair],
FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('DMS/UtilTests/SetRealmParameters'), [('parameterName') : clidEnableparameterNameValuePair],
FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('DMS/UtilTests/SetRealmParameters'), [('parameterName') : hideAppendReplaceCLIDpopup],
	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('DMS/Stager/LoadTemplate'), [('TemplateName') : template1], FailureHandling.STOP_ON_FAILURE)

// Loading templates for Pricing Condition Support for Lots feature

String EVENT_TEMPLATE_LOTAPCTERM = 'BasketLotTemplateforAPC'
WebUI.callTestCase(findTestCase('DMS/Stager/LoadTemplate'), [('TemplateName') : EVENT_TEMPLATE_LOTAPCTERM], FailureHandling.STOP_ON_FAILURE)

String CONTRACT_TEMPLATE_LOTAPCTERM = 'ContractAPCTemplateWithBasketLot'
WebUI.callTestCase(findTestCase('DMS/Stager/LoadTemplate'), [('TemplateName') : CONTRACT_TEMPLATE_LOTAPCTERM], FailureHandling.STOP_ON_FAILURE)




WebUI.closeBrowser()
