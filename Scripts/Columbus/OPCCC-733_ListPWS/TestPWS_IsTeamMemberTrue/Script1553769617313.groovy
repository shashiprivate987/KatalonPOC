import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder as RestRequestObjectBuilder
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webservice.verification.WSResponseManager as WSResponseManager

String str = WebUI.callTestCase(findTestCase('test-upstream/Columbus/OPCCC-733_ListPWS/GetAuthToken'), [:], FailureHandling.STOP_ON_FAILURE)
println('Access token is:' + GlobalVariable.access_token)
def sluper = new JsonSlurper()
ArrayList withTrue
ArrayList withFalse
int i = 0
while (i < 10) {
	GlobalVariable.isTeamMember = 'true'
	responseWithTrue = WS.sendRequest(findTestObject('Object Repository/OPCCC-733_TestListPWS_Objects/GetPWSList'))
	i++
	if (responseWithTrue.getStatusCode() == 200) {
		println(responseWithTrue.getResponseText())
		withTrue = sluper.parseText(responseWithTrue.getResponseText())
		break
	}
}
assertOwner(withTrue,'cnoll')
int j = 0
while (j < 10) {
	GlobalVariable.isTeamMember = 'false'
	println('Trying again')
	responseWithFalse = WS.sendRequest(findTestObject('Object Repository/OPCCC-733_TestListPWS_Objects/GetPWSList'))
	j++
	if (responseWithFalse.getStatusCode() == 200) {
		withFalse = sluper.parseText(responseWithFalse.getResponseText())
		WS.verifyGreaterThan(withFalse.size, 0) // here just verifying list should not be empty
		break
	}
}
private void assertOwner(ArrayList withTrue, String expectedOwner) {
	for (int m = 0; m < withTrue.size; m++) {
		Map map = withTrue[m]
		WS.verifyMatch(map.get('Owner'), expectedOwner, false, FailureHandling.STOP_ON_FAILURE)
	}
}