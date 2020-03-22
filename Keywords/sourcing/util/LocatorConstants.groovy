package sourcing.util

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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class LocatorConstants {

	static final String APCRULE_LOC = "//label[@awname='AllowAdvancedPricingConditions_selectedItem::AdvancedPricingConditionRuleView_AllowAdvancedPricingConditions:currChoice']"
	static final String VALIDITYPERIOD_LOC = "//div[@awname='AdvancedPricingConditionRule.ValidityPeriodType::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodType:itemList']"
	static final String STARTDATE_LOC = "//input[@awname='AdvancedPricingConditionRule.ValidityPeriodStartDate::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodStartDate:textFieldAction_1']"
	static final String PERIOD_LOC = "//input[@awname='AdvancedPricingConditionRule.ValidityPeriodDuration::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.ValidityPeriodDuration:fieldValue']"
	static final String SCALETYPE_LOC = "//div[@awname='AdvancedPricingConditionRule.VolumeScaleType::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.VolumeScaleType:itemList']"
	static final String SCALE_LOC = "//input[@awname='AdvancedPricingConditionRule.Volumes::AdvancedPricingConditionRuleView_AdvancedPricingConditionRule.Volumes:newEntry']"
	static final String APCLOT_LOC = "//tr[@awname='0.4::AWRefreshRegion']//a//b[text()='Basket Lot']"
	static final String LOTCHILDITEM_LOC = "//tr[@awname='0.4_0.4.1::AWRefreshRegion']"
	static final String APCLINK_LOC = "//a[@awname='0.4_RITASHORTSTRINGIFZ000002::RITASHORTSTRINGIFZ000002:pricingInfoDisplay']"
	static final String LOT_TERM_APC_PRICE_LOC = "//input[@awname='_DerivedStringValue::PCCostTermView_DerivedStringValue:fieldValue']"
}
