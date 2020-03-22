package aribaui.util

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Calendar as Calendar

import org.eclipse.persistence.sessions.serializers.KryoSerializer

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class AribaUIHelper {
	@Keyword
	static def getNotificationDueDate(def format, int advance) {
		def notificationDueDate

		if (advance == 0) {
			notificationDueDate = new SimpleDateFormat(format, Locale.US).format(Calendar.getInstance().getTime()).toString()

			return notificationDueDate
		} else {
			Calendar cal = Calendar.getInstance()

			cal.add(Calendar.DAY_OF_MONTH, advance)

			notificationDueDate = new SimpleDateFormat(format, Locale.US).format(cal.getTime()).toString()

			return notificationDueDate
		}

		return notificationDueDate
	}

	@Keyword
	static void aribaUIClick(def format, int advance) {
	}
}
