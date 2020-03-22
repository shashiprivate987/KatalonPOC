package common.util

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
import org.apache.commons.lang.RandomStringUtils

import internal.GlobalVariable

public class dataGenerationUtil {
	static String getRandomText(int textSize){
		String charset = (('A'..'Z') + ('0'..'9')).join()
		String randomString = RandomStringUtils.random(textSize, charset.toCharArray())
	}
	static int getRandomNumeric(int max){
		Random rand = new Random()
		int random_num = rand.nextInt(max+1)
		return random_num;
	}

	static String getDate(Date date, int delta=0,String format="MM/dd/yyyy"){
		if(date==null){
			date=new Date();
		}
		date=date.plus(delta);
		return date.format(format);
	}

	static int getEpochTime(long time=System.currentTimeMillis(),int delta=0){
		return time+delta;
	}

	static String getTime(int hh=0,int mm=0){
		Calendar cal=Calendar.getInstance();
		cal.setTimeZone(TimeZone.getDefault());
		int hour=0;
		int min=0;
		String tm=" AM";

		if(hh!=0){
			hour = cal.get(Calendar.HOUR_OF_DAY)
			hour=hour+hh;
		}
		if(mm!=0){
			min=cal.get(Calendar.HOUR_OF_DAY)
			min=min+mm;
		}
		if(hour>=12){
			tm=" PM"
		}
		if(hour>12){
			hour=hour-12
		}
		return hour+":"+min+tm;
	}
}
