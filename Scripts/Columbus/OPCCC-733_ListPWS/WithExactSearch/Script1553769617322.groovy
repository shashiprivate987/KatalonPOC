import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.ArrayList

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

String str=WebUI.callTestCase(findTestCase('test-upstream/Columbus/OPCCC-733_ListPWS/GetAuthToken'), [:], FailureHandling.STOP_ON_FAILURE)
JsonSlurper slurper = new JsonSlurper();
int i=0
ArrayList result
while(i<10){
	GlobalVariable.search_pattern="{'Owner':'cnoll'}"
	println("Trying again")
	response = WS.sendRequest(findTestObject('Object Repository/OPCCC-733_TestListPWS_Objects/GetPWSList'))
	println (response.getResponseText())
	i++
	if (response.getStatusCode() == 200) {
		result = slurper.parseText(response.getResponseText())
		break
	}
}
assertOwner(result, 'cnoll')
private void assertOwner(ArrayList withTrue, String expectedOwner) {
	for (int m = 0; m < withTrue.size; m++) {
		Map map = withTrue[m]
		WS.verifyMatch(map.get('Owner'), expectedOwner, false, FailureHandling.STOP_ON_FAILURE)
	}
}
