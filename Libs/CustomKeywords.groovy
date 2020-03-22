
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import com.kms.katalon.core.testobject.TestObject

import java.lang.String

import com.kms.katalon.core.testobject.ResponseObject

import java.util.Map

import com.kms.katalon.core.testobject.RequestObject

import java.lang.Object

import java.util.List

import java.io.File

import java.util.HashMap

import java.util.ArrayList


def static "common.util.fileHandling.getPrefix"() {
    (new common.util.fileHandling()).getPrefix()
}

def static "common.util.fileHandling.downloadFile"(
    	TestObject to	
     , 	String fileName	
     , 	int retry	
     , 	int delay	) {
    (new common.util.fileHandling()).downloadFile(
        	to
         , 	fileName
         , 	retry
         , 	delay)
}

def static "common.util.fileHandling.dragAndDropFile"(
    	TestObject to	
     , 	String fileName	) {
    (new common.util.fileHandling()).dragAndDropFile(
        	to
         , 	fileName)
}

def static "common.util.fileHandling.downloadFile"(
    	TestObject to	
     , 	String fileName	
     , 	int retry	) {
    (new common.util.fileHandling()).downloadFile(
        	to
         , 	fileName
         , 	retry)
}

def static "common.util.fileHandling.downloadFile"(
    	TestObject to	
     , 	String fileName	) {
    (new common.util.fileHandling()).downloadFile(
        	to
         , 	fileName)
}

def static "sourcing.util2.DMSTestDataUtil.getCellValuesOfRow"(
    	String sheet	
     , 	String dataTableName	
     , 	int rowNum	) {
    (new sourcing.util2.DMSTestDataUtil()).getCellValuesOfRow(
        	sheet
         , 	dataTableName
         , 	rowNum)
}

def static "sourcing.util2.DMSTestDataUtil.getTestDataForGivenColumn"(
    	String sheetName	
     , 	String dataTableName	
     , 	String columnHeader	) {
    (new sourcing.util2.DMSTestDataUtil()).getTestDataForGivenColumn(
        	sheetName
         , 	dataTableName
         , 	columnHeader)
}

def static "sourcing.util.ExcelTestDataUtil.getCellValuesOfRow"(
    	int rowNum	) {
    (new sourcing.util.ExcelTestDataUtil()).getCellValuesOfRow(
        	rowNum)
}

def static "sourcing.util.ExcelTestDataUtil.getTestDataForGivenColumn"(
    	String columnHeader	) {
    (new sourcing.util.ExcelTestDataUtil()).getTestDataForGivenColumn(
        	columnHeader)
}

def static "aribaui.util.Test.getTimeStamp"() {
    (new aribaui.util.Test()).getTimeStamp()
}

def static "sourcing.util2.DmsUtilsDummy.getTestDataForGivenColumn"(
    	String fileRelPath	
     , 	String fileName	
     , 	String sheetName	
     , 	String dataTableName	
     , 	String columnHeader	) {
    (new sourcing.util2.DmsUtilsDummy()).getTestDataForGivenColumn(
        	fileRelPath
         , 	fileName
         , 	sheetName
         , 	dataTableName
         , 	columnHeader)
}

def static "sourcing.util2.DmsUtilsDummy.getTestDataForGivenColumn1"(
    	String fileRelPath	
     , 	String fileName	
     , 	String sheetName	
     , 	String dataTableName	
     , 	String columnHeader	) {
    (new sourcing.util2.DmsUtilsDummy()).getTestDataForGivenColumn1(
        	fileRelPath
         , 	fileName
         , 	sheetName
         , 	dataTableName
         , 	columnHeader)
}

def static "aribaui.util.AribaUIHelper.getNotificationDueDate"(
    	Object format	
     , 	int advance	) {
    (new aribaui.util.AribaUIHelper()).getNotificationDueDate(
        	format
         , 	advance)
}

def static "aribaui.util.AribaUIHelper.aribaUIClick"(
    	Object format	
     , 	int advance	) {
    (new aribaui.util.AribaUIHelper()).aribaUIClick(
        	format
         , 	advance)
}

def static "sourcing.util.FileComparatorUtil.validateExcelSheets"(
    	String expFile	
     , 	String actFile	
     , 	String sheetName	) {
    (new sourcing.util.FileComparatorUtil()).validateExcelSheets(
        	expFile
         , 	actFile
         , 	sheetName)
}

def static "sourcing.util.FileComparatorUtil.getTimeStamp"() {
    (new sourcing.util.FileComparatorUtil()).getTimeStamp()
}

def static "sourcing.util2.sourcingKeywords.loginToTestCentral"(
    	String userName	) {
    (new sourcing.util2.sourcingKeywords()).loginToTestCentral(
        	userName)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	
     , 	String suppliersWhoDeclined2Bid	
     , 	String approvers2Publish	
     , 	String addReviewersForAward	
     , 	String inputCurrencyRateSet	
     , 	String accessControl	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite
         , 	suppliersWhoDeclined2Bid
         , 	approvers2Publish
         , 	addReviewersForAward
         , 	inputCurrencyRateSet
         , 	accessControl)
}

def static "sourcing.util2.sourcingKeywords.deleteExistingContentFromTemplate"() {
    (new sourcing.util2.sourcingKeywords()).deleteExistingContentFromTemplate()
}

def static "sourcing.util2.sourcingKeywords.selectExistingPF"(
    	String pfIndex	) {
    (new sourcing.util2.sourcingKeywords()).selectExistingPF(
        	pfIndex)
}

def static "sourcing.util2.sourcingKeywords.createNewPF"(
    	String pfName	) {
    (new sourcing.util2.sourcingKeywords()).createNewPF(
        	pfName)
}

def static "sourcing.util2.sourcingKeywords.stopActingAsSupplier"() {
    (new sourcing.util2.sourcingKeywords()).stopActingAsSupplier()
}

def static "sourcing.util2.sourcingKeywords.clickOnSubmitEntireResponse"() {
    (new sourcing.util2.sourcingKeywords()).clickOnSubmitEntireResponse()
}

def static "sourcing.util2.sourcingKeywords.viewProject"(
    	String eventName	) {
    (new sourcing.util2.sourcingKeywords()).viewProject(
        	eventName)
}

def static "sourcing.util2.sourcingKeywords.setDateTimeOfEvent"(
    	String date	
     , 	String time	
     , 	int duration	) {
    (new sourcing.util2.sourcingKeywords()).setDateTimeOfEvent(
        	date
         , 	time
         , 	duration)
}

def static "sourcing.util2.sourcingKeywords.selectSupplier"(
    	String supplierNamePrefix	
     , 	String[] suppliersList	) {
    (new sourcing.util2.sourcingKeywords()).selectSupplier(
        	supplierNamePrefix
         , 	suppliersList)
}

def static "sourcing.util2.sourcingKeywords.editContentType"() {
    (new sourcing.util2.sourcingKeywords()).editContentType()
}

def static "sourcing.util2.sourcingKeywords.clickOnDynamicXpath"(
    	String xpath	) {
    (new sourcing.util2.sourcingKeywords()).clickOnDynamicXpath(
        	xpath)
}

def static "sourcing.util2.sourcingKeywords.clickOnDynamicXpath1"(
    	String xpath	) {
    (new sourcing.util2.sourcingKeywords()).clickOnDynamicXpath1(
        	xpath)
}

def static "sourcing.util2.sourcingKeywords.getDynamicXpathTestObject"(
    	String xpath	) {
    (new sourcing.util2.sourcingKeywords()).getDynamicXpathTestObject(
        	xpath)
}

def static "sourcing.util2.sourcingKeywords.publishEvent"() {
    (new sourcing.util2.sourcingKeywords()).publishEvent()
}

def static "sourcing.util2.sourcingKeywords.addLineItem"(
    	String lineItemName	
     , 	String lineItemDesc	
     , 	String price	
     , 	String qty	) {
    (new sourcing.util2.sourcingKeywords()).addLineItem(
        	lineItemName
         , 	lineItemDesc
         , 	price
         , 	qty)
}

def static "sourcing.util2.sourcingKeywords.addLot"() {
    (new sourcing.util2.sourcingKeywords()).addLot()
}

def static "sourcing.util2.sourcingKeywords.addQuestions"(
    	String questionText	
     , 	String questionType	
     , 	boolean isRequired	) {
    (new sourcing.util2.sourcingKeywords()).addQuestions(
        	questionText
         , 	questionType
         , 	isRequired)
}

def static "sourcing.util2.sourcingKeywords.gotoDashboard"(
    	String userName	) {
    (new sourcing.util2.sourcingKeywords()).gotoDashboard(
        	userName)
}

def static "sourcing.util2.sourcingKeywords.reducePreviewTime"() {
    (new sourcing.util2.sourcingKeywords()).reducePreviewTime()
}

def static "sourcing.util2.sourcingKeywords.selectSupplierForBidding"(
    	String eventName	
     , 	String supplierName	) {
    (new sourcing.util2.sourcingKeywords()).selectSupplierForBidding(
        	eventName
         , 	supplierName)
}

def static "sourcing.util2.sourcingKeywords.SurrogateBid"(
    	String eventName	
     , 	String supplierName	
     , 	Object generalQuestions	) {
    (new sourcing.util2.sourcingKeywords()).SurrogateBid(
        	eventName
         , 	supplierName
         , 	generalQuestions)
}

def static "sourcing.util2.sourcingKeywords.goToMonitorEvent"(
    	String eventName	) {
    (new sourcing.util2.sourcingKeywords()).goToMonitorEvent(
        	eventName)
}

def static "sourcing.util2.sourcingKeywords.openExistingEvent"(
    	String eventName	) {
    (new sourcing.util2.sourcingKeywords()).openExistingEvent(
        	eventName)
}

def static "sourcing.util2.sourcingKeywords.loadTemplate"(
    	String templateName	
     , 	String projectName	) {
    (new sourcing.util2.sourcingKeywords()).loadTemplate(
        	templateName
         , 	projectName)
}

def static "sourcing.util2.sourcingKeywords.deleteExistingContent"() {
    (new sourcing.util2.sourcingKeywords()).deleteExistingContent()
}

def static "sourcing.util2.sourcingKeywords.createSupplierAlt"(
    	String altType	
     , 	String altName	
     , 	boolean firstAlt	
     , 	boolean isLast	
     , 	Object minQty	
     , 	Object maxQty	) {
    (new sourcing.util2.sourcingKeywords()).createSupplierAlt(
        	altType
         , 	altName
         , 	firstAlt
         , 	isLast
         , 	minQty
         , 	maxQty)
}

def static "sourcing.util2.sourcingKeywords.verifyExeededAltWidgetPresent"() {
    (new sourcing.util2.sourcingKeywords()).verifyExeededAltWidgetPresent()
}

def static "sourcing.util2.sourcingKeywords.loginToTestCentral"() {
    (new sourcing.util2.sourcingKeywords()).loginToTestCentral()
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	
     , 	String suppliersWhoDeclined2Bid	
     , 	String approvers2Publish	
     , 	String addReviewersForAward	
     , 	String inputCurrencyRateSet	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite
         , 	suppliersWhoDeclined2Bid
         , 	approvers2Publish
         , 	addReviewersForAward
         , 	inputCurrencyRateSet)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	
     , 	String suppliersWhoDeclined2Bid	
     , 	String approvers2Publish	
     , 	String addReviewersForAward	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite
         , 	suppliersWhoDeclined2Bid
         , 	approvers2Publish
         , 	addReviewersForAward)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	
     , 	String suppliersWhoDeclined2Bid	
     , 	String approvers2Publish	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite
         , 	suppliersWhoDeclined2Bid
         , 	approvers2Publish)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	
     , 	String suppliersWhoDeclined2Bid	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite
         , 	suppliersWhoDeclined2Bid)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"(
    	String eventName	) {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager(
        	eventName)
}

def static "sourcing.util2.sourcingKeywords.createEventRunStager"() {
    (new sourcing.util2.sourcingKeywords()).createEventRunStager()
}

def static "sourcing.util2.sourcingKeywords.selectSupplier"(
    	String supplierNamePrefix	) {
    (new sourcing.util2.sourcingKeywords()).selectSupplier(
        	supplierNamePrefix)
}

def static "sourcing.util2.sourcingKeywords.selectSupplier"() {
    (new sourcing.util2.sourcingKeywords()).selectSupplier()
}

def static "sourcing.util2.sourcingKeywords.addQuestions"(
    	String questionText	
     , 	String questionType	) {
    (new sourcing.util2.sourcingKeywords()).addQuestions(
        	questionText
         , 	questionType)
}

def static "sourcing.util2.sourcingKeywords.selectSupplierForBidding"(
    	String eventName	) {
    (new sourcing.util2.sourcingKeywords()).selectSupplierForBidding(
        	eventName)
}

def static "sourcing.util2.sourcingKeywords.SurrogateBid"(
    	String eventName	
     , 	String supplierName	) {
    (new sourcing.util2.sourcingKeywords()).SurrogateBid(
        	eventName
         , 	supplierName)
}

def static "sourcing.util2.sourcingKeywords.SurrogateBid"(
    	String eventName	) {
    (new sourcing.util2.sourcingKeywords()).SurrogateBid(
        	eventName)
}

def static "sourcing.util2.sourcingKeywords.loadTemplate"(
    	String templateName	) {
    (new sourcing.util2.sourcingKeywords()).loadTemplate(
        	templateName)
}

def static "sourcing.util2.sourcingKeywords.createSupplierAlt"(
    	String altType	
     , 	String altName	
     , 	boolean firstAlt	
     , 	boolean isLast	
     , 	Object minQty	) {
    (new sourcing.util2.sourcingKeywords()).createSupplierAlt(
        	altType
         , 	altName
         , 	firstAlt
         , 	isLast
         , 	minQty)
}

def static "sourcing.util2.sourcingKeywords.createSupplierAlt"(
    	String altType	
     , 	String altName	
     , 	boolean firstAlt	
     , 	boolean isLast	) {
    (new sourcing.util2.sourcingKeywords()).createSupplierAlt(
        	altType
         , 	altName
         , 	firstAlt
         , 	isLast)
}

def static "sourcing.util2.sourcingKeywords.createSupplierAlt"(
    	String altType	
     , 	String altName	
     , 	boolean firstAlt	) {
    (new sourcing.util2.sourcingKeywords()).createSupplierAlt(
        	altType
         , 	altName
         , 	firstAlt)
}

def static "sourcing.util2.sourcingKeywords.createSupplierAlt"(
    	String altType	
     , 	String altName	) {
    (new sourcing.util2.sourcingKeywords()).createSupplierAlt(
        	altType
         , 	altName)
}

def static "sourcing.util.sourcingKeywords.loginToTestCentral"(
    	String userName	) {
    (new sourcing.util.sourcingKeywords()).loginToTestCentral(
        	userName)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	
     , 	String suppliersWhoDeclined2Bid	
     , 	String approvers2Publish	
     , 	String addReviewersForAward	
     , 	String inputCurrencyRateSet	
     , 	String accessControl	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite
         , 	suppliersWhoDeclined2Bid
         , 	approvers2Publish
         , 	addReviewersForAward
         , 	inputCurrencyRateSet
         , 	accessControl)
}

def static "sourcing.util.sourcingKeywords.deleteExistingContentFromTemplate"() {
    (new sourcing.util.sourcingKeywords()).deleteExistingContentFromTemplate()
}

def static "sourcing.util.sourcingKeywords.selectExistingPF"(
    	String pfIndex	) {
    (new sourcing.util.sourcingKeywords()).selectExistingPF(
        	pfIndex)
}

def static "sourcing.util.sourcingKeywords.createNewPF"(
    	String pfName	) {
    (new sourcing.util.sourcingKeywords()).createNewPF(
        	pfName)
}

def static "sourcing.util.sourcingKeywords.stopActingAsSupplier"() {
    (new sourcing.util.sourcingKeywords()).stopActingAsSupplier()
}

def static "sourcing.util.sourcingKeywords.clickOnSubmitEntireResponse"() {
    (new sourcing.util.sourcingKeywords()).clickOnSubmitEntireResponse()
}

def static "sourcing.util.sourcingKeywords.viewProject"(
    	String eventName	) {
    (new sourcing.util.sourcingKeywords()).viewProject(
        	eventName)
}

def static "sourcing.util.sourcingKeywords.setDateTimeOfEvent"(
    	String date	
     , 	String time	
     , 	int duration	) {
    (new sourcing.util.sourcingKeywords()).setDateTimeOfEvent(
        	date
         , 	time
         , 	duration)
}

def static "sourcing.util.sourcingKeywords.selectSupplier"(
    	String supplierNamePrefix	
     , 	String[] suppliersList	) {
    (new sourcing.util.sourcingKeywords()).selectSupplier(
        	supplierNamePrefix
         , 	suppliersList)
}

def static "sourcing.util.sourcingKeywords.editContentType"() {
    (new sourcing.util.sourcingKeywords()).editContentType()
}

def static "sourcing.util.sourcingKeywords.clickOnDynamicXpath"(
    	String xpath	) {
    (new sourcing.util.sourcingKeywords()).clickOnDynamicXpath(
        	xpath)
}

def static "sourcing.util.sourcingKeywords.clickOnDynamicXpath1"(
    	String xpath	) {
    (new sourcing.util.sourcingKeywords()).clickOnDynamicXpath1(
        	xpath)
}

def static "sourcing.util.sourcingKeywords.getDynamicXpathTestObject"(
    	String xpath	) {
    (new sourcing.util.sourcingKeywords()).getDynamicXpathTestObject(
        	xpath)
}

def static "sourcing.util.sourcingKeywords.publishEvent"() {
    (new sourcing.util.sourcingKeywords()).publishEvent()
}

def static "sourcing.util.sourcingKeywords.addLineItem"(
    	String lineItemName	
     , 	String lineItemDesc	
     , 	String price	
     , 	String qty	) {
    (new sourcing.util.sourcingKeywords()).addLineItem(
        	lineItemName
         , 	lineItemDesc
         , 	price
         , 	qty)
}

def static "sourcing.util.sourcingKeywords.addLot"() {
    (new sourcing.util.sourcingKeywords()).addLot()
}

def static "sourcing.util.sourcingKeywords.addQuestions"(
    	String questionText	
     , 	String questionType	
     , 	boolean isRequired	) {
    (new sourcing.util.sourcingKeywords()).addQuestions(
        	questionText
         , 	questionType
         , 	isRequired)
}

def static "sourcing.util.sourcingKeywords.gotoDashboard"(
    	String userName	) {
    (new sourcing.util.sourcingKeywords()).gotoDashboard(
        	userName)
}

def static "sourcing.util.sourcingKeywords.reducePreviewTime"() {
    (new sourcing.util.sourcingKeywords()).reducePreviewTime()
}

def static "sourcing.util.sourcingKeywords.selectSupplierForBidding"(
    	String eventName	
     , 	String supplierName	) {
    (new sourcing.util.sourcingKeywords()).selectSupplierForBidding(
        	eventName
         , 	supplierName)
}

def static "sourcing.util.sourcingKeywords.SurrogateBid"(
    	String eventName	
     , 	String supplierName	
     , 	Object generalQuestions	) {
    (new sourcing.util.sourcingKeywords()).SurrogateBid(
        	eventName
         , 	supplierName
         , 	generalQuestions)
}

def static "sourcing.util.sourcingKeywords.goToMonitorEvent"(
    	String eventName	) {
    (new sourcing.util.sourcingKeywords()).goToMonitorEvent(
        	eventName)
}

def static "sourcing.util.sourcingKeywords.openExistingEvent"(
    	String eventName	) {
    (new sourcing.util.sourcingKeywords()).openExistingEvent(
        	eventName)
}

def static "sourcing.util.sourcingKeywords.loadTemplate"(
    	String templateName	
     , 	String projectName	) {
    (new sourcing.util.sourcingKeywords()).loadTemplate(
        	templateName
         , 	projectName)
}

def static "sourcing.util.sourcingKeywords.deleteExistingContent"() {
    (new sourcing.util.sourcingKeywords()).deleteExistingContent()
}

def static "sourcing.util.sourcingKeywords.createSupplierAlt"(
    	String altType	
     , 	String altName	
     , 	boolean firstAlt	
     , 	boolean isLast	
     , 	Object minQty	
     , 	Object maxQty	) {
    (new sourcing.util.sourcingKeywords()).createSupplierAlt(
        	altType
         , 	altName
         , 	firstAlt
         , 	isLast
         , 	minQty
         , 	maxQty)
}

def static "sourcing.util.sourcingKeywords.verifyExeededAltWidgetPresent"() {
    (new sourcing.util.sourcingKeywords()).verifyExeededAltWidgetPresent()
}

def static "sourcing.util.sourcingKeywords.loginToTestCentral"() {
    (new sourcing.util.sourcingKeywords()).loginToTestCentral()
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	
     , 	String suppliersWhoDeclined2Bid	
     , 	String approvers2Publish	
     , 	String addReviewersForAward	
     , 	String inputCurrencyRateSet	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite
         , 	suppliersWhoDeclined2Bid
         , 	approvers2Publish
         , 	addReviewersForAward
         , 	inputCurrencyRateSet)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	
     , 	String suppliersWhoDeclined2Bid	
     , 	String approvers2Publish	
     , 	String addReviewersForAward	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite
         , 	suppliersWhoDeclined2Bid
         , 	approvers2Publish
         , 	addReviewersForAward)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	
     , 	String suppliersWhoDeclined2Bid	
     , 	String approvers2Publish	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite
         , 	suppliersWhoDeclined2Bid
         , 	approvers2Publish)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	
     , 	String suppliersWhoDeclined2Bid	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite
         , 	suppliersWhoDeclined2Bid)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	
     , 	String externalGraders2Invite	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite
         , 	externalGraders2Invite)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	
     , 	String teamGraders2Invite	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite
         , 	teamGraders2Invite)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	
     , 	String suppliers2Invite	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes
         , 	suppliers2Invite)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	
     , 	boolean addMMItmes	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload
         , 	addMMItmes)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	
     , 	String xlFile2Upload	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj
         , 	xlFile2Upload)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	
     , 	boolean isFullPrj	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids
         , 	isFullPrj)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	
     , 	int noOfBids	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType
         , 	noOfBids)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	
     , 	String eventType	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus
         , 	eventType)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	
     , 	String eventEndStatus	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName
         , 	eventEndStatus)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	
     , 	String eventTemplateName	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName
         , 	eventTemplateName)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"(
    	String eventName	) {
    (new sourcing.util.sourcingKeywords()).createEventRunStager(
        	eventName)
}

def static "sourcing.util.sourcingKeywords.createEventRunStager"() {
    (new sourcing.util.sourcingKeywords()).createEventRunStager()
}

def static "sourcing.util.sourcingKeywords.selectSupplier"(
    	String supplierNamePrefix	) {
    (new sourcing.util.sourcingKeywords()).selectSupplier(
        	supplierNamePrefix)
}

def static "sourcing.util.sourcingKeywords.selectSupplier"() {
    (new sourcing.util.sourcingKeywords()).selectSupplier()
}

def static "sourcing.util.sourcingKeywords.addQuestions"(
    	String questionText	
     , 	String questionType	) {
    (new sourcing.util.sourcingKeywords()).addQuestions(
        	questionText
         , 	questionType)
}

def static "sourcing.util.sourcingKeywords.selectSupplierForBidding"(
    	String eventName	) {
    (new sourcing.util.sourcingKeywords()).selectSupplierForBidding(
        	eventName)
}

def static "sourcing.util.sourcingKeywords.SurrogateBid"(
    	String eventName	
     , 	String supplierName	) {
    (new sourcing.util.sourcingKeywords()).SurrogateBid(
        	eventName
         , 	supplierName)
}

def static "sourcing.util.sourcingKeywords.SurrogateBid"(
    	String eventName	) {
    (new sourcing.util.sourcingKeywords()).SurrogateBid(
        	eventName)
}

def static "sourcing.util.sourcingKeywords.loadTemplate"(
    	String templateName	) {
    (new sourcing.util.sourcingKeywords()).loadTemplate(
        	templateName)
}

def static "sourcing.util.sourcingKeywords.createSupplierAlt"(
    	String altType	
     , 	String altName	
     , 	boolean firstAlt	
     , 	boolean isLast	
     , 	Object minQty	) {
    (new sourcing.util.sourcingKeywords()).createSupplierAlt(
        	altType
         , 	altName
         , 	firstAlt
         , 	isLast
         , 	minQty)
}

def static "sourcing.util.sourcingKeywords.createSupplierAlt"(
    	String altType	
     , 	String altName	
     , 	boolean firstAlt	
     , 	boolean isLast	) {
    (new sourcing.util.sourcingKeywords()).createSupplierAlt(
        	altType
         , 	altName
         , 	firstAlt
         , 	isLast)
}

def static "sourcing.util.sourcingKeywords.createSupplierAlt"(
    	String altType	
     , 	String altName	
     , 	boolean firstAlt	) {
    (new sourcing.util.sourcingKeywords()).createSupplierAlt(
        	altType
         , 	altName
         , 	firstAlt)
}

def static "sourcing.util.sourcingKeywords.createSupplierAlt"(
    	String altType	
     , 	String altName	) {
    (new sourcing.util.sourcingKeywords()).createSupplierAlt(
        	altType
         , 	altName)
}

def static "common.util.aribakeywords.refreshBrowser"() {
    (new common.util.aribakeywords()).refreshBrowser()
}

def static "common.util.aribakeywords.aribaClick"(
    	TestObject to	) {
    (new common.util.aribakeywords()).aribaClick(
        	to)
}

def static "common.util.aribakeywords.getHtmlTableRows"(
    	TestObject table	
     , 	String outerTagName	) {
    (new common.util.aribakeywords()).getHtmlTableRows(
        	table
         , 	outerTagName)
}

def static "common.util.aribakeywords.waitForPageToLoad"(
    	int timeout	) {
    (new common.util.aribakeywords()).waitForPageToLoad(
        	timeout)
}

def static "common.util.aribakeywords.fileUpload"(
    	TestObject to	
     , 	String filepath	) {
    (new common.util.aribakeywords()).fileUpload(
        	to
         , 	filepath)
}

def static "common.util.aribakeywords.navigateToUrl"(
    	String rawURL	) {
    (new common.util.aribakeywords()).navigateToUrl(
        	rawURL)
}

def static "common.util.aribakeywords.switchIframe"(
    	TestObject to	) {
    (new common.util.aribakeywords()).switchIframe(
        	to)
}

def static "common.util.aribakeywords.switchToDefaultFrame"() {
    (new common.util.aribakeywords()).switchToDefaultFrame()
}

def static "common.util.aribakeywords.aribaSetText"(
    	TestObject to	
     , 	String textToEnter	) {
    (new common.util.aribakeywords()).aribaSetText(
        	to
         , 	textToEnter)
}

def static "common.util.aribakeywords.dragAndDropFile"(
    	TestObject to	
     , 	String fileName	) {
    (new common.util.aribakeywords()).dragAndDropFile(
        	to
         , 	fileName)
}

def static "common.util.aribakeywords.downloadFile"(
    	TestObject to	
     , 	String fileName	
     , 	int retry	
     , 	int delay	) {
    (new common.util.aribakeywords()).downloadFile(
        	to
         , 	fileName
         , 	retry
         , 	delay)
}

def static "common.util.aribakeywords.createTestObject"(
    	String xpath	) {
    (new common.util.aribakeywords()).createTestObject(
        	xpath)
}

def static "common.util.aribakeywords.dragAndDropElement"(
    	TestObject sTO	
     , 	TestObject tTO	) {
    (new common.util.aribakeywords()).dragAndDropElement(
        	sTO
         , 	tTO)
}

def static "common.util.aribakeywords.xinhaSetText"(
    	TestObject to	
     , 	String text	) {
    (new common.util.aribakeywords()).xinhaSetText(
        	to
         , 	text)
}

def static "common.util.aribakeywords.downloadFile"(
    	TestObject to	
     , 	String fileName	
     , 	int retry	) {
    (new common.util.aribakeywords()).downloadFile(
        	to
         , 	fileName
         , 	retry)
}

def static "common.util.aribakeywords.downloadFile"(
    	TestObject to	
     , 	String fileName	) {
    (new common.util.aribakeywords()).downloadFile(
        	to
         , 	fileName)
}

def static "common.util.RESTValidator.verifyStatusCode"(
    	ResponseObject response	
     , 	String expectedStatusCode	) {
    (new common.util.RESTValidator()).verifyStatusCode(
        	response
         , 	expectedStatusCode)
}

def static "common.util.RESTValidator.addBasicAuthorizationProperty"(
    	TestObject request	
     , 	String username	
     , 	String password	) {
    (new common.util.RESTValidator()).addBasicAuthorizationProperty(
        	request
         , 	username
         , 	password)
}

def static "common.util.RESTValidator.createRestRequest"(
    	String requestType	
     , 	Map properties	
     , 	boolean bypassObjRepo	) {
    (new common.util.RESTValidator()).createRestRequest(
        	requestType
         , 	properties
         , 	bypassObjRepo)
}

def static "common.util.RESTValidator.getContentType"(
    	RequestObject request	) {
    (new common.util.RESTValidator()).getContentType(
        	request)
}

def static "common.util.RESTValidator.getParsedContent"(
    	String text	
     , 	boolean jsonArray	) {
    (new common.util.RESTValidator()).getParsedContent(
        	text
         , 	jsonArray)
}

def static "common.util.RESTValidator.updateJSONContent"(
    	Object parsedContent	
     , 	Map properties	) {
    (new common.util.RESTValidator()).updateJSONContent(
        	parsedContent
         , 	properties)
}

def static "common.util.RESTValidator.updateText"(
    	String str	
     , 	Map properties	) {
    (new common.util.RESTValidator()).updateText(
        	str
         , 	properties)
}

def static "common.util.RESTValidator.setSortKey"(
    	String SortKey	
     , 	boolean reset	) {
    (new common.util.RESTValidator()).setSortKey(
        	SortKey
         , 	reset)
}

def static "common.util.RESTValidator.setIgnoreKey"(
    	String IgnoreKey	
     , 	boolean reset	) {
    (new common.util.RESTValidator()).setIgnoreKey(
        	IgnoreKey
         , 	reset)
}

def static "common.util.RESTValidator.validateAsyncKey"(
    	Object parsedContent	
     , 	String AsyncKey	) {
    (new common.util.RESTValidator()).validateAsyncKey(
        	parsedContent
         , 	AsyncKey)
}

def static "common.util.RESTValidator.validateProperty"(
    	Object parsedContent	
     , 	Map properties	) {
    (new common.util.RESTValidator()).validateProperty(
        	parsedContent
         , 	properties)
}

def static "common.util.RESTValidator.validateKey"(
    	Object parsedContent	
     , 	List properties	) {
    (new common.util.RESTValidator()).validateKey(
        	parsedContent
         , 	properties)
}

def static "common.util.RESTValidator.getPropertyValue"(
    	Object parsedContent	
     , 	String property	) {
    (new common.util.RESTValidator()).getPropertyValue(
        	parsedContent
         , 	property)
}

def static "common.util.RESTValidator.getPropertyValues"(
    	Object content	
     , 	String key	) {
    (new common.util.RESTValidator()).getPropertyValues(
        	content
         , 	key)
}

def static "common.util.RESTValidator.getPropertyIndex"(
    	Object content	
     , 	String key	
     , 	Object value	) {
    (new common.util.RESTValidator()).getPropertyIndex(
        	content
         , 	key
         , 	value)
}

def static "common.util.RESTValidator.getPropertyIndexes"(
    	Object content	
     , 	String key	
     , 	Object value	) {
    (new common.util.RESTValidator()).getPropertyIndexes(
        	content
         , 	key
         , 	value)
}

def static "common.util.RESTValidator.compareJson"(
    	ResponseObject response	
     , 	String expectedResponse	
     , 	String TestCaseID	
     , 	String RetainKey	
     , 	String ReplaceKey	
     , 	boolean strictFlag	) {
    (new common.util.RESTValidator()).compareJson(
        	response
         , 	expectedResponse
         , 	TestCaseID
         , 	RetainKey
         , 	ReplaceKey
         , 	strictFlag)
}

def static "common.util.RESTValidator.compareJsonStructure"(
    	ResponseObject response	
     , 	String expectedResponse	
     , 	String TestCaseID	
     , 	boolean strictFlag	) {
    (new common.util.RESTValidator()).compareJsonStructure(
        	response
         , 	expectedResponse
         , 	TestCaseID
         , 	strictFlag)
}

def static "common.util.RESTValidator.compareXml"(
    	ResponseObject response	
     , 	String expectedResponse	
     , 	String TestCaseID	
     , 	boolean strictFlag	) {
    (new common.util.RESTValidator()).compareXml(
        	response
         , 	expectedResponse
         , 	TestCaseID
         , 	strictFlag)
}

def static "common.util.RESTValidator.compareXml"(
    	File f1	
     , 	File f2	
     , 	String TestCaseID	
     , 	boolean strictFlag	) {
    (new common.util.RESTValidator()).compareXml(
        	f1
         , 	f2
         , 	TestCaseID
         , 	strictFlag)
}

def static "common.util.RESTValidator.getXMLContent"(
    	ResponseObject response	
     , 	String parentLink	
     , 	boolean segment	) {
    (new common.util.RESTValidator()).getXMLContent(
        	response
         , 	parentLink
         , 	segment)
}

def static "common.util.RESTValidator.getXMLContent"(
    	File f1	
     , 	String parentLink	
     , 	boolean segment	) {
    (new common.util.RESTValidator()).getXMLContent(
        	f1
         , 	parentLink
         , 	segment)
}

def static "common.util.RESTValidator.getFileContent"(
    	String name	
     , 	String fileName	
     , 	Map map	) {
    (new common.util.RESTValidator()).getFileContent(
        	name
         , 	fileName
         , 	map)
}

def static "common.util.RESTValidator.getFileContent"(
    	String name	
     , 	ResponseObject response	
     , 	Map map	) {
    (new common.util.RESTValidator()).getFileContent(
        	name
         , 	response
         , 	map)
}

def static "common.util.RESTValidator.mapDiff"(
    	Map m1	
     , 	Map m2	
     , 	String testCaseId	
     , 	List skipList	
     , 	List sortList	
     , 	boolean strictFlag	) {
    (new common.util.RESTValidator()).mapDiff(
        	m1
         , 	m2
         , 	testCaseId
         , 	skipList
         , 	sortList
         , 	strictFlag)
}

def static "common.util.RESTValidator.getValueFromMap"(
    	java.util.HashMap<String, Object> oriMap	
     , 	java.util.ArrayList<String> list	) {
    (new common.util.RESTValidator()).getValueFromMap(
        	oriMap
         , 	list)
}

def static "common.util.RESTValidator.fileParser"(
    	String fileName	
     , 	Map colRowMap	
     , 	boolean skipRow	) {
    (new common.util.RESTValidator()).fileParser(
        	fileName
         , 	colRowMap
         , 	skipRow)
}

def static "common.util.RESTValidator.downloadFile"(
    	String ObjectRepo	
     , 	String testCaseId	
     , 	int retry	
     , 	int delay	) {
    (new common.util.RESTValidator()).downloadFile(
        	ObjectRepo
         , 	testCaseId
         , 	retry
         , 	delay)
}

def static "common.util.RESTValidator.setDownloadPath"() {
    (new common.util.RESTValidator()).setDownloadPath()
}

def static "common.util.RESTValidator.logInfo"(
    	Object object	) {
    (new common.util.RESTValidator()).logInfo(
        	object)
}

def static "common.util.RESTValidator.createRestRequest"(
    	String requestType	
     , 	Map properties	) {
    (new common.util.RESTValidator()).createRestRequest(
        	requestType
         , 	properties)
}

def static "common.util.RESTValidator.getParsedContent"(
    	String text	) {
    (new common.util.RESTValidator()).getParsedContent(
        	text)
}

def static "common.util.RESTValidator.updateText"(
    	String str	) {
    (new common.util.RESTValidator()).updateText(
        	str)
}

def static "common.util.RESTValidator.setSortKey"(
    	String SortKey	) {
    (new common.util.RESTValidator()).setSortKey(
        	SortKey)
}

def static "common.util.RESTValidator.setIgnoreKey"(
    	String IgnoreKey	) {
    (new common.util.RESTValidator()).setIgnoreKey(
        	IgnoreKey)
}

def static "common.util.RESTValidator.compareJson"(
    	ResponseObject response	
     , 	String expectedResponse	
     , 	String TestCaseID	
     , 	String RetainKey	
     , 	String ReplaceKey	) {
    (new common.util.RESTValidator()).compareJson(
        	response
         , 	expectedResponse
         , 	TestCaseID
         , 	RetainKey
         , 	ReplaceKey)
}

def static "common.util.RESTValidator.compareJson"(
    	ResponseObject response	
     , 	String expectedResponse	
     , 	String TestCaseID	
     , 	String RetainKey	) {
    (new common.util.RESTValidator()).compareJson(
        	response
         , 	expectedResponse
         , 	TestCaseID
         , 	RetainKey)
}

def static "common.util.RESTValidator.compareJson"(
    	ResponseObject response	
     , 	String expectedResponse	
     , 	String TestCaseID	) {
    (new common.util.RESTValidator()).compareJson(
        	response
         , 	expectedResponse
         , 	TestCaseID)
}

def static "common.util.RESTValidator.compareJsonStructure"(
    	ResponseObject response	
     , 	String expectedResponse	
     , 	String TestCaseID	) {
    (new common.util.RESTValidator()).compareJsonStructure(
        	response
         , 	expectedResponse
         , 	TestCaseID)
}

def static "common.util.RESTValidator.compareXml"(
    	ResponseObject response	
     , 	String expectedResponse	
     , 	String TestCaseID	) {
    (new common.util.RESTValidator()).compareXml(
        	response
         , 	expectedResponse
         , 	TestCaseID)
}

def static "common.util.RESTValidator.compareXml"(
    	File f1	
     , 	File f2	
     , 	String TestCaseID	) {
    (new common.util.RESTValidator()).compareXml(
        	f1
         , 	f2
         , 	TestCaseID)
}

def static "common.util.RESTValidator.getXMLContent"(
    	ResponseObject response	
     , 	String parentLink	) {
    (new common.util.RESTValidator()).getXMLContent(
        	response
         , 	parentLink)
}

def static "common.util.RESTValidator.getXMLContent"(
    	File f1	
     , 	String parentLink	) {
    (new common.util.RESTValidator()).getXMLContent(
        	f1
         , 	parentLink)
}

def static "common.util.RESTValidator.mapDiff"(
    	Map m1	
     , 	Map m2	
     , 	String testCaseId	
     , 	List skipList	
     , 	List sortList	) {
    (new common.util.RESTValidator()).mapDiff(
        	m1
         , 	m2
         , 	testCaseId
         , 	skipList
         , 	sortList)
}

def static "common.util.RESTValidator.mapDiff"(
    	Map m1	
     , 	Map m2	
     , 	String testCaseId	
     , 	List skipList	) {
    (new common.util.RESTValidator()).mapDiff(
        	m1
         , 	m2
         , 	testCaseId
         , 	skipList)
}

def static "common.util.RESTValidator.mapDiff"(
    	Map m1	
     , 	Map m2	
     , 	String testCaseId	) {
    (new common.util.RESTValidator()).mapDiff(
        	m1
         , 	m2
         , 	testCaseId)
}

def static "common.util.RESTValidator.fileParser"(
    	String fileName	
     , 	Map colRowMap	) {
    (new common.util.RESTValidator()).fileParser(
        	fileName
         , 	colRowMap)
}

def static "common.util.RESTValidator.fileParser"(
    	String fileName	) {
    (new common.util.RESTValidator()).fileParser(
        	fileName)
}

def static "common.util.RESTValidator.downloadFile"(
    	String ObjectRepo	
     , 	String testCaseId	
     , 	int retry	) {
    (new common.util.RESTValidator()).downloadFile(
        	ObjectRepo
         , 	testCaseId
         , 	retry)
}

def static "common.util.RESTValidator.downloadFile"(
    	String ObjectRepo	
     , 	String testCaseId	) {
    (new common.util.RESTValidator()).downloadFile(
        	ObjectRepo
         , 	testCaseId)
}

def static "sourcing.util2.DmsUtils.validateExcelSheets"(
    	String expFilePath	
     , 	String actFilePath	
     , 	String sheetName	) {
    (new sourcing.util2.DmsUtils()).validateExcelSheets(
        	expFilePath
         , 	actFilePath
         , 	sheetName)
}

def static "sourcing.util2.DmsUtils.getTimeStamp"() {
    (new sourcing.util2.DmsUtils()).getTimeStamp()
}

def static "sourcing.util2.DmsUtils.getTestDataForGivenColumn"(
    	String fileFullPath	
     , 	String sheetName	
     , 	String dataTableName	
     , 	String columnHeader	) {
    (new sourcing.util2.DmsUtils()).getTestDataForGivenColumn(
        	fileFullPath
         , 	sheetName
         , 	dataTableName
         , 	columnHeader)
}
