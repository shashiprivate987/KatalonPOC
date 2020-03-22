import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownTestCase

/**
 * Some methods below are samples for using SetUp/TearDown in a test suite.
 */



/**
 * Setup test suite environment.
 */
@SetUp(skipped = false) // Please change skipped to be false to activate this method.
def setUp() {
	// Put your code here.
	String volumeScaleFeature = 'ariba.sourcing.util.SourcingEventFeatures.PricingConditionsWithScales'
	String fromScaleFeature = 'ariba.sourcing.util.SourcingEventFeatures.FromScalesInPricingCondition'
	def rfpEventTemplate = 'SourcingTemplatewithAPC'
	def contractTemplate = 'contractTemplateForAPC'
	String dfsCheckparameterNameValuePair = 'Application.ACM.DFSCheckEnabled=false'
	String clidEnableparameterNameValuePair = 'Application.ACM.ContractLineItemsDocument.Enabled=true'
	//String appLoginUrl = internal.GlobalVariable.baseURL+'/Sourcing/Main/?realm=s4All-3'
	String appLoginUrl = internal.GlobalVariable.baseURL + '/Sourcing/Main?realm=accAcwSap'
	//String appLoginUrl = 'https://svcdev9ss.ariba.com/Sourcing/Main?realm=s4All-5'
	
	
	
	WebUI.callTestCase(findTestCase('DMS/UtilTests/LoginIntoApp'), [('appLoginUrl') : appLoginUrl], FailureHandling.STOP_ON_FAILURE)
	
	WebUI.callTestCase(findTestCase('DMS/UtilTests/SetFeatureToggle'), [('featureToggleName') : volumeScaleFeature],
	FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('DMS/UtilTests/SetFeatureToggle'), [('featureToggleName') : fromScaleFeature],
	FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('DMS/UtilTests/LoadEventTemplateHavingAPC'), [('rfpTemplateName') : rfpEventTemplate],
	FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('DMS/UtilTests/LoadContractTemplateHavingAPC'), [('contractTemplateName') : contractTemplate],
	FailureHandling.STOP_ON_FAILURE)

	//	Disable DFS pop-up
	WebUI.callTestCase(findTestCase('DMS/UtilTests/SetRealmParameters'), [('parameterName') : dfsCheckparameterNameValuePair],
	FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('DMS/UtilTests/SetRealmParameters'), [('parameterName') : clidEnableparameterNameValuePair],
	FailureHandling.STOP_ON_FAILURE)
}

/**
 * Clean test suites environment.
 */
@TearDown(skipped = false) // Please change skipped to be false to activate this method.
def tearDown() {
	// Put your code here.
	def rfpEventTemplate = 'SourcingTemplatewithAPC'
	def contractTemplate = 'contractTemplateForAPC'
	//String appLoginUrl = internal.GlobalVariable.baseURL+'/Sourcing/Main/?realm=s4All-3'
	String appLoginUrl = internal.GlobalVariable.baseURL + '/Sourcing/Main?realm=accAcwSap'
	//String appLoginUrl = 'https://svcdev9ss.ariba.com/Sourcing/Main?realm=s4All-5'
	
	
	WebUI.callTestCase(findTestCase('DMS/UtilTests/LoginIntoApp'), [('appLoginUrl') : appLoginUrl], FailureHandling.STOP_ON_FAILURE)
	// Delete Template
	WebUI.callTestCase(findTestCase('DMS/UtilTests/DeleteTemplate'), [('templateName') : rfpEventTemplate], FailureHandling.STOP_ON_FAILURE)
	WebUI.callTestCase(findTestCase('DMS/UtilTests/DeleteTemplate'), [('templateName') : contractTemplate], FailureHandling.STOP_ON_FAILURE)
}

/**
 * Run before each test case starts.
 */
@SetupTestCase(skipped = false) // Please change skipped to be false to activate this method.
def setupTestCase() {
	// Put your code here.
	String appLoginUrl = internal.GlobalVariable.baseURL + '/Sourcing/Main?realm=accAcwSap'
	//String appLoginUrl = 'https://svcdev9ss.ariba.com/Sourcing/Main?realm=s4All-5'
	WebUI.callTestCase(findTestCase('DMS/UtilTests/LoginIntoApp'), [('appLoginUrl') : appLoginUrl], FailureHandling.STOP_ON_FAILURE)
}

/**
 * Run after each test case ends.
 */
@TearDownTestCase(skipped = true) // Please change skipped to be false to activate this method.
def tearDownTestCase() {
	// Put your code here.
}

/**
 * References:
 * Groovy tutorial page: http://docs.groovy-lang.org/next/html/documentation/
 */