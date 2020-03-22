import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.contribution.WebUiDriverCleaner
import com.kms.katalon.core.mobile.contribution.MobileDriverCleaner
import com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner


DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())


RunConfiguration.setExecutionSettingFile('/var/folders/b7/mkk5d1ld2cg52sff2pks4rvr0000gn/T/Katalon/Test Cases/DMS/PricingConditionSupportForLots/dummy2/20200123_222706/execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCase('Test Cases/DMS/PricingConditionSupportForLots/dummy2', new TestCaseBinding('Test Cases/DMS/PricingConditionSupportForLots/dummy2',[:]), FailureHandling.STOP_ON_FAILURE , false)
    
