package sourcing.util2

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import common.util.aribakeywords
import common.util.dataGenerationUtil

import com.kms.katalon.core.testobject.ConditionType


import internal.GlobalVariable

public class sourcingKeywords {

	@Keyword
	static def loginToTestCentral(String userName="customersupportadmin"){
		aribakeywords.aribaClick(findTestObject('Object Repository/a_Test Central'))

		WebUI.setText(findTestObject('Object Repository/Page_TestCentralPage/input_User__bt5lrc'), userName)

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_TestCentralPage/input_User__njzfkc'))
	}
	@Keyword
	static String createEventRunStager(String eventName=null,String eventTemplateName=null,
			String eventEndStatus="draft",
			String eventType="RFP",
			int noOfBids=1,
			boolean isFullPrj=false,
			String xlFile2Upload=null,
			boolean addMMItmes=false,
			String suppliers2Invite=null,
			String teamGraders2Invite=null,
			String externalGraders2Invite=null,
			String suppliersWhoDeclined2Bid=null,
			String approvers2Publish=null,
			String addReviewersForAward=null,
			String inputCurrencyRateSet=null,
			String accessControl=null){

		if(eventName==null){
			eventName="event_"+eventType+"_"+dataGenerationUtil.getRandomText(8);
		}
		//WebUI.waitForPageLoad(5)
		WebUI.scrollToElement(findTestObject('Object Repository/Page_TestCentralPage/a_Create Sourcing Event'), 3)
		//WebUI.waitForElementVisible(findTestObject('Object Repository/Page_TestCentralPage/a_Create Sourcing Event'), 3)
		//WebUI.mouseOver(findTestObject('Object Repository/Page_TestCentralPage/a_Create Sourcing Event'))
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_TestCentralPage/a_Create Sourcing Event'))

		//WebUI.waitForPageLoad(6)
		WebUI.delay(2)
		WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Name of event__nzhcgb'), eventName)

		//aribakeywords.aribaClick(findTestObject('Object Repository/Page_TestStagerForm/select_(no selection)RFIRFPRFP'))

		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_TestStagerForm/select_(no selection)RFIRFPRFP'), eventType,true)

		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_TestStagerForm/select_(no selection)draftprev'), eventEndStatus,true)

		if(isFullPrj){
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_TestStagerForm/input_Is Full Project__cf0kz'))
		}
		if(eventTemplateName!=null){
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Event Template Name__kto'), eventTemplateName)
		}

		if(xlFile2Upload!=null){
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Event excel file to uplo'), xlFile2Upload)
		}
		if(addMMItmes){
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_TestStagerForm/input_Add MM Line Items__jhhtq'))
		}

		if(noOfBids!=3){
			WebUI.clearText(findTestObject('Object Repository/Page_TestStagerForm/input_Number Of Bids__auyr'))
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Number Of Bids__auyr'), noOfBids.toString())
		}

		if(suppliers2Invite!=null){
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Suppliers To Invite__mkh'), suppliers2Invite)
		}

		if(teamGraders2Invite!=null){
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Team Graders to Invite__'), teamGraders2Invite)
		}

		if(externalGraders2Invite!=null){
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_External Graders to Invi'), externalGraders2Invite)
		}

		if(suppliersWhoDeclined2Bid!=null){
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Suppliers who decline to'), suppliersWhoDeclined2Bid)
		}

		if(approvers2Publish!=null){
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Approvers for publish__h'), approvers2Publish)
		}

		if(addReviewersForAward!=null){
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Add reviewers for award_'), addReviewersForAward)
		}

		if(inputCurrencyRateSet!=null){
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Currency Rate Set__7gu__'), inputCurrencyRateSet)
		}

		if(accessControl!=null){
			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Access Control__scz69d'), accessControl)
		}

		aribakeywords.aribaClick(findTestObject('Object Repository/div_Run Stager'))

		return eventName
	}

	@Keyword
	static def deleteExistingContentFromTemplate(){
		//Done
		aribakeywords.aribaClick(findTestObject('Page_RFXContentStepName/label_Actions_w-chk w-chk-dsiz'))

		aribakeywords.aribaClick(findTestObject('Page_RFXContentStepName/button_Delete'))

		aribakeywords.aribaClick(findTestObject('Page_RFXContentStepName/button_OK'))
	}

	@Keyword
	static def selectExistingPF(String pfIndex){
		//Done
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/a_Select Existing'))
		String xpath="//label[@awname='AWTSingleSelectColumnRenderer_"+pfIndex+"']"
		clickOnDynamicXpath(xpath)

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ProductionFacilityListRenderer/button_OK'))
	}

	@Keyword
	static def createNewPF(String pfName){
		//Done

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/a_Create New'))

		WebUI.waitForElementNotPresent(findTestObject('Object Repository/Page_ASPComposeResponse/div_Questionnaire creation is'),40)

		WebUI.setText(findTestObject('Object Repository/Page_ASPComposeResponse/input___5qtgsb'), pfName)

		WebUI.setText(findTestObject('Object Repository/Page_ASPComposeResponse/input_Street__fecyqc'), 'Central Road')

		WebUI.setText(findTestObject('Object Repository/Page_ASPComposeResponse/input_City__hqoaud'), 'Adamsville')

		WebUI.setText(findTestObject('Object Repository/Page_ASPComposeResponse/input_StateProvinceRegion__pca'), 'AL')

		WebUI.setText(findTestObject('Object Repository/Page_ASPComposeResponse/input_PostalCode__34qknb'), '35005')

		WebUI.setText(findTestObject('Object Repository/Page_ASPComposeResponse/input_Country__gdn_oc'), 'U')

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/button_Submit'))

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/button_OK'))

		WebUI.waitForElementNotPresent(findTestObject('Object Repository/Page_ASPComposeResponse/td_Your response is getting su'),20);

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/button_Done'))

	}

	@Keyword
	static def stopActingAsSupplier(){
		//Done

		clickOnDynamicXpath("//a[@awname = 'AribaAction:undelegate:actionClicked']")
	}

	@Keyword
	static def clickOnSubmitEntireResponse(){
		//Done
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/button_Submit Entire Response'))

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/button_OK'))
	}
	@Keyword
	static def viewProject(String eventName){
		WebUI.scrollToElement(findTestObject('Object Repository/Page_TestCentralPage/a_View Project'), 6)
		WebUI.mouseOver(findTestObject('Object Repository/Page_TestCentralPage/a_View Project'))
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_TestCentralPage/a_View Project'))
		String xpath="//a[@awname='"+eventName+"::AbstractDocumentDownloadIcon:iconAction']"
		clickOnDynamicXpath(xpath);
		//aribakeywords.aribaClick(findTestObject('Object Repository/Page_PIHome/span_Documents_w-img'))
	}
	@Keyword
	static def setDateTimeOfEvent(String date, String time, int duration){
		//Done
		WebUI.setText(findTestObject('Object Repository/Page_RFXRuleStepName/input_date'), date)

		WebUI.setText(findTestObject('Object Repository/Page_RFXRuleStepName/input___dol9xc'), time)
		WebUI.clearText(findTestObject('Object Repository/Page_RFXRuleStepName/input_Duration__4qp5_b'))
		WebUI.setText(findTestObject('Object Repository/Page_RFXRuleStepName/input_Duration__4qp5_b'), duration.toString())

		//aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXRuleStepName/span_Next'))
	}
	@Keyword
	static def selectSupplier(String supplierNamePrefix=null, String[] suppliersList=null){
		//Done
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXRuleStepName/a_Suppliers'))
		if(supplierNamePrefix!=null){
			if(supplierNamePrefix=='any'){
				//select any supplier
				aribakeywords.aribaClick(findTestObject('Page_getContext.getSupplierStepName/anyRandomSupplier'))
			}else{
				//choose specific supplier
				aribakeywords.aribaClick(findTestObject('Page_getContext.getSupplierStepName/button_Invite Another Participan'))
				WebUI.clearText(findTestObject('Object Repository/Page_ASPSupplierSearch/input_Advanced__n4nfnb'))
				WebUI.setText(findTestObject('Object Repository/Page_ASPSupplierSearch/input_Advanced__n4nfnb'), supplierNamePrefix)

				aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPSupplierSearch/button_Search'))
				String xpath
				suppliersList.each {supplierName ->
					println("selecting supplier "+supplierName)
					xpath="//a[contains(text(),'"+supplierName+"')]/../../../../../../..//label[1]"
					TestObject to=getDynamicXpathTestObject(xpath);
					aribakeywords.aribaClick(to);
				}

				aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPSupplierSearch/button_OK'))
			}
		}

	}
	@Keyword
	static def editContentType(){
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_getContext.getSupplierStepName/a_Content'))

	}

	@Keyword
	static def clickOnDynamicXpath(String xpath){
		TestObject to=getDynamicXpathTestObject(xpath)
		aribakeywords.aribaClick(to)
	}

	@Keyword
	static def clickOnDynamicXpath1(String xpath){
		TestObject to=getDynamicXpathTestObject(xpath)
		WebUI.click(to)
	}
	@Keyword
	static def getDynamicXpathTestObject(String xpath){
		println(xpath)
		TestObject to = new TestObject("objectName")
		to.addProperty("xpath", ConditionType.EQUALS, xpath)
		return to;
	}
	@Keyword
	static void publishEvent(){
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXRuleStepName/a_Summary'))
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXSummaryStepName/button_Publish'))

	}
	@Keyword
	static void addLineItem(String lineItemName, String lineItemDesc,String price, String qty){
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXContentStepName/button_Add'))
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXContentStepName/add_line_item'))
		WebUI.setText(findTestObject('Object Repository/Page_ASPEditItemDialog/input___wxv7w'), lineItemName)
		clickOnDynamicXpath("//body[@awname='XinhaBody:Description::EditView_Description:displayValue']")
		WebUI.executeJavaScript('document.getElementById("XinhaIFrame__sdcb3c").contentDocument.body.innerHTML = "LineItem"', null)
		//WebUI.executeJavaScript('document.evaluate('//*[@awname="XinhaBody:Description::TemplateView_Description:displayValue"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerHTML = "RichTextEditorDescription"')
		//WebUI.executeJavaScript('document.getElementById("XinhaIFrame__sdcb3c").contentDocument.body.innerHTML = '+lineItemDesc+"'", null)
		//WebUI.setText(findTestObject('Object Repository/Page_ASPEditItemDialog/iframe_Description_XinhaIFrame/body_'), lineItemDesc)

		WebUI.setText(findTestObject('Object Repository/Page_ASPEditItemDialog/input___gwrold'), qty)

		WebUI.setText(findTestObject('Object Repository/Page_ASPEditItemDialog/input_USD__p62qdd'), price)

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPEditItemDialog/button_Done'))
	}
	@Keyword
	static void addLot(){
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXContentStepName/add_lot'))
	}
	@Keyword
	static void addQuestions(String questionText, String questionType, boolean isRequired=true){
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXContentStepName/span_Add_w-pulldown-icon'))

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXContentStepName/add_question'))
		clickOnDynamicXpath("//body[@awname='XinhaBody:Name::AttributeNameView_Name:displayValue']")

		String script='document.getElementById("XinhaIFrame__9d30kb").contentDocument.body.innerHTML = "Question"'
		WebUI.executeJavaScript(script , null)
		//click on question type drop down
		String xpath="//div[@awname='ValidValues.SingletonValueType::EditView_ValidValues.SingletonValueType:itemList']/span[2]"
		clickOnDynamicXpath(xpath);
		//select question type
		xpath="//div[@awname='ValidValues.SingletonValueType::EditView_ValidValues.SingletonValueType:itemList_label_"+questionType+"']"
		clickOnDynamicXpath(xpath);
		//click on Response required drop down
		xpath="//div[@awname='RequiredType::EditView_RequiredType:itemList']/span[2]"
		clickOnDynamicXpath(xpath);
		if(isRequired){
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPEditQuestionItemDialog/div_Yes Participant Required'))
		}else{
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPEditQuestionItemDialog/div_Not Required'))
		}

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPEditItemDialog/button_Done'))
	}
	@Keyword
	static void gotoDashboard(String userName){
		aribakeywords.aribaClick(findTestObject('Page_SSOMain/a_Test Central'))

		WebUI.setText(findTestObject('Page_TestCentralPage/input_User__bt5lrc'), userName)

		aribakeywords.aribaClick(findTestObject('Page_TestCentralPage/input_User__njzfkc'))

		aribakeywords.aribaClick(findTestObject('Page_TestCentralPage/a_dashboard'))

		aribakeywords.aribaClick(findTestObject('Page_TestCentralPage/a_Dashboard Main'))
	}

	@Keyword
	static void reducePreviewTime(){
		String timeText=WebUI.getText(getDynamicXpathTestObject("//span[@id='bidclockTimeString']"))
		def timeValues=timeText.split(":")
		def intMin=timeValues[1] as int

		println("extra time to be reduced "+intMin)

		if(intMin>0){
			clickOnDynamicXpath("//button[@awname='TabSet:refreshCurrentPage']/span/span")
			clickOnDynamicXpath("//a[@awname='ASPMonitorEvent:reduceAction']")
			WebUI.clearText(getDynamicXpathTestObject("//input[@awname='PlannedEndDateComposition_selectedOption::ASCTimeDuration:offset']"))
			WebUI.setText(getDynamicXpathTestObject("//input[@awname='PlannedEndDateComposition_selectedOption::ASCTimeDuration:offset']"), intMin.toString())
			clickOnDynamicXpath("//button[@awname='ModalPageWrapper:okClicked_1']")
		}else{
			println('no need to reduce event time')
		}
		WebUI.delay(30)

	}

	@Keyword
	static void selectSupplierForBidding(String eventName,String supplierName=null){
		//Done
		//pass no arg will select first supplier from list
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPMonitorEventContent/a_Suppliers'))
		String xpath=null;
		if(supplierName!=null){
			xpath="//td[contains(text(),'"+supplierName+"')]/../../../../../../td[1]//label"
			clickOnDynamicXpath(xpath)
		}else{
			//click on first supplier checkbox
			WebUI.delay(1)
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPMonitorEventContent/first_Supplier'))
		}
		aribakeywords.aribaClick(findTestObject('Page_ASPMonitorEventsupplierTabLabe/button_Surrogate Bid'))
		WebUI.delay(4)
		String closeIconClass=WebUI.getAttribute(getDynamicXpathTestObject("(//a[contains(@awname,'::AWTDataTable:linkClicked')])[2]/span"), "class")
		if (closeIconClass=='w-togglebox-icon-off'){
			clickOnDynamicXpath("(//a[contains(@awname,'::AWTDataTable:linkClicked')])[2]")
		}
		String xpathEventForMonitoring="//a[@awname='"+eventName+"_MyTitle::SupplierListView_MyTitle:rfxDetailsAction']"
		clickOnDynamicXpath(xpathEventForMonitoring)
	}

	@Keyword
	static void SurrogateBid(String eventName,String supplierName=null, def generalQuestions=[:]){
		WebUI.waitForElementNotPresent(getDynamicXpathTestObject("//td[contains(text(),'Time remaining in preview')]"),60)
		selectSupplierForBidding(eventName,supplierName)
		//Onbidding PAge
		//Accept terms and conditions
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPSupplierRFXOverview/td_Review and Accept Prerequis'))
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPSupplierAgreement/label_Survival._w-rdo w-rdo-ds'))

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPSupplierAgreement/button_OK'))

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPSupplierAgreement/button_OK_1'))
		//submit line item
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPIntendToBidSelect Lots/label_Export to Excel_w-chk w-'))

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPIntendToBidSelect Lots/span_Submit Selected Lots'))
		//fill general questions
		String index=null
		generalQuestions.each{ key, value ->
			index=key.split('.')
			xpath="//input[contains(@awname,'"+index+"__ptaylor_"+key+"__ptaylor_PRICE::')]"
			WebUI.sendKeys(getDynamicXpathTestObject(xpath), value)
		}
		//fill line items

	}
	@Keyword
	static void goToMonitorEvent(String eventName){
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPRFXPublishExit/a_Monitor'))
		String xpathEventForMonitoring="//a[@awname='"+eventName+"_MyTitle::SupplierListView_MyTitle:rfxDetailsAction']"
		clickOnDynamicXpath(xpathEventForMonitoring)
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPMonitorEventOverview/a_Content'))
		WebUI.delay(4)
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPMonitorEventContent/a_Suppliers'))
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPMonitorEventOverview/a_Content'))
		//WebUI.waitForElementNotPresent('Object Repository/Page_ASPMonitorEventContent/td_Pending Publish Approval', 60)
	}
	@Keyword
	static void openExistingEvent(String eventName){
		String xpath="//a[.='"+eventName+"']"

		TestObject toEvent = new TestObject("objectName")
		toEvent.addProperty("xpath", ConditionType.EQUALS, xpath)
		WebUI.click(toEvent)
	}
	@Keyword
	static void loadTemplate(String templateName, String projectName=null){
		WebUI.scrollToElement(findTestObject('Object Repository/Page_TestCentralPage/a_Load Template'), 3)
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_TestCentralPage/a_Load Template'))
		WebUI.delay(3)
		WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Name of the template__qu'), templateName)

		WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Name of the ZIP file to'), templateName+".zip")
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_TestStagerForm/input_Import new version of te'))

		if(projectName!=null){

			aribakeywords.aribaClick(findTestObject('Object Repository/Page_TestStagerForm/input_Create Project__7fejpc'))

			WebUI.setText(findTestObject('Object Repository/Page_TestStagerForm/input_Name of the Project__mbi'), projectName)
		}

		aribakeywords.aribaClick(findTestObject('div_Run Stager'))
	}
	@Keyword
	static void deleteExistingContent(){
		//Done
		aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXContentStepName/label_Actions_w-chk w-chk-dsiz'))

		aribakeywords.aribaClick(findTestObject('Page_RFXContentStepName/button_Delete'))

		aribakeywords.aribaClick(findTestObject('Object Repository/Page_RFXContentStepName/button_OK'))
	}

	@Keyword
	static def createSupplierAlt(String altType, String altName,boolean firstAlt=false,boolean isLast=false,minQty=null,maxQty=null) {

		//WebUI.click(findTestObject('Object Repository/Page_ASPSupplierHomePage/span_newSuppAlt'))
		if(firstAlt){
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/button_ReviseAlternative Respo'))
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/button_OK_1'))
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/button_Alternative'))
			//			if (WebUI.verifyElementVisible(getDynamicXpathTestObject("//button[@awname='ASPComposeResponse:syncPrimaryBidAction']")){
			//				clickOnDynamicXpath("//button[@awname='ASPComposeResponse:syncPrimaryBidAction']");
			//			}


		}else{
			WebUI.delay(3)
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPComposeResponse/button_Alternative'))
		}

		if(altType=='pricing'){

			clickOnDynamicXpath("//a[@awname = 'ASPComposeResponse:createSupplierPricingHeaderAction']")
			WebUI.setText(findTestObject('Object Repository/Page_ASPSelectLotItems/input_Alternative Name __a1ood'), altName)

			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPSelectLotItems/label_Export to Excel_w-chk w-'))
			WebUI.delay(2)

			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPSelectLotItems/button_OK'))
		}else if (altType=="bundle"){
			clickOnDynamicXpath("//a[@awname='ASPComposeResponse:createSupplierBundleHeaderAction']")
			WebUI.setText(findTestObject('Object Repository/Page_ASPSelectLotItems/input_Alternative Name __kgl6f'), altName)

			WebUI.setText(findTestObject('Object Repository/Page_ASPSelectLotItems/input_Bundle Name __zmr1ub'), altType)
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPSelectLotItems/label_Export to Excel_w-chk w-_1'))
			WebUI.delay(2)
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPSelectLotItems/button_OK'))
		}else if(altType=="tier"){

			clickOnDynamicXpath("//a[@awname='ASPComposeResponse:createSupplierTierHeaderAction']")

			WebUI.setText(findTestObject('Object Repository/Page_ASPEditTier/input_Alternative Name__kgt0b'), altName)

			WebUI.setText(findTestObject('Object Repository/Page_ASPEditTier/input_Tier Name__cbkqtb'), altType)
			aribakeywords.aribaClick('Object Repository/Page_ASPEditTier/label_Export to Excel_w-chk w-');

			if(minQty!=null && maxQty!=null){
				clickOnDynamicXpath("//button[@awname='ASPEditTier:addTierChoiceAction']")
				WebUI.delay(1)
				WebUI.setText(findTestObject('Object Repository/Page_ASPEditTier/input_Max Quantity__xpx4nc'), minQty)
				WebUI.setText(findTestObject('Object Repository/Page_ASPEditTier/input_Max Quantity__3ichcd'), maxQty)
			}

			WebUI.delay(3)
			aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPEditTier/button_Apply'))
			if(!isLast){
				String xpath="//a[@awname='"+altName+"::BaseTabSet:tabClicked']"
				WebUI.verifyElementPresent(getDynamicXpathTestObject(xpath), 3)
			}
		}

	}

	@Keyword
	static void verifyExeededAltWidgetPresent(){
		String errMsg=WebUI.getAttribute(getDynamicXpathTestObject("//div[@id='slidingErrorMsg']//td[@class='msgText']"), "innerText");
		WebUI.verifyElementPresent(getDynamicXpathTestObject("//div[@id='slidingErrorMsg']//td[@class='msgText']"), 3)
		if(!errMsg.contentEquals('Exceeded number of alternatives you can create. Delete previous alternatives to create a new one.')){
			KeywordUtil.markFailedAndStop(" Supplier Alt Error message is not as expected.")
		}
		//aribakeywords.aribaClick(findTestObject('Object Repository/Page_ASPEditTier/span_Cancel'))
	}

}
