package common.util

import java.util.concurrent.TimeUnit
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import common.util.fileHandling as fh;
import org.junit.After
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.internal.Invoker.FailureContext

import com.google.common.base.Predicate

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.exception.StepErrorException

import junit.framework.AssertionFailedError
import com.kms.katalon.core.configuration.RunConfiguration
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.remote.LocalFileDetector as LocalFileDetector
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.Keys as Keys

class aribakeywords {

	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}


	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	static def aribaClick(TestObject to ) {
		WebElement element = null;
		try {
			TestObject parentObject = to.getParentObject()
			WebUiBuiltInKeywords.waitForElementClickable(to, 200)
			WebUiBuiltInKeywords.scrollToElement(to, 200)
			WebUiBuiltInKeywords.waitForElementVisible(to,200)
			if(parentObject) {
				switchIframe(parentObject)
			}
			element = WebUiBuiltInKeywords.findWebElement(to);
			if (element.getAttribute("awname") != null) {
				if (element.getAttribute("awname").compareToIgnoreCase("TestStagerForm:runStager") == 0) {
					WebUiBuiltInKeywords.click(to)
					waitForPageToLoad(120)
					runStager()
					waitForPageToLoad(120)
					return
				}
			}

			KeywordUtil.logInfo("Clicking element")
			WebUiBuiltInKeywords.click(to)
			if(parentObject){
				switchToDefaultFrame()
			}
			waitForPageToLoad(120)
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			String message = "Element not found :" + to.toString() + "\n"
			String testElement = "Test Element : " +  element.toString() + "\n\n"
			String stacktrace = "Stack Trace : " + e.toString() + "\n\n"
			KeywordUtil.markFailedAndStop(message + testElement + stacktrace)
		} catch (Exception e) {
			String message = "Fail to click on Object : " + to.toString() + "\n"
			String testElement = "Test Element : " +  element.toString() + "\n\n"
			String stacktrace = "Stack Trace : " + e.toString() + "\n\n"
			KeywordUtil.markFailedAndStop(message + testElement + stacktrace)
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}

	@Keyword
	static def waitForPageToLoad(int timeout) {
		WebDriver driver = DriverFactory.getWebDriver()

		boolean isAngularPage = false
		boolean is
		isAngularPage = (Boolean) ((JavascriptExecutor) driver)
				.executeScript("return !!(window.angular || window.ng)");

		if (isAngularPage) {
			waitForSpinnerLoad(driver)
			//WebUiBuiltInKeywords.waitForAngularLoad(timeout)
			//WebUiBuiltInKeywords.waitForJQueryLoad(timeout)
			// waitUntilJSReady(driver)
			// waitforAjax(driver)
			waitUntilAngular5Ready(driver)
			return

			//waitForAngularPage(timeout)
		} else {
			waitForNonAngularPage(timeout)
		}
	}

	static void waitforAjax(WebDriver driver){

		KeywordUtil.logInfo("Ajax Loading - Before");

		(Boolean) ((JavascriptExecutor) driver)
				.executeScript("var callback = arguments[arguments.length - 1];"
				+ "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
				+ "xhr.onreadystatechange = function() {" + " if (xhr.readyState == 4) {"
				+ "  callback(xhr.responseText);" + " }" + "};" + "xhr.send();");

		KeywordUtil.logInfo("Ajax Loading - After");
	}

	static void waitUntilAngular5Ready(WebDriver driver) {
		try {
			Object angular5Check = ((JavascriptExecutor) driver).executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
			if (angular5Check != null) {
				Boolean angularPageLoaded = ((JavascriptExecutor) driver).executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
				if (!angularPageLoaded) {
					poll(20);

					waitForAngular5Load(driver);

					poll(20);
				}
			}
		} catch (WebDriverException ignored) {
		}
	}

	static void waitUntilAngularReady(WebDriver driver) {
		try {
			Boolean angularUnDefined = ((JavascriptExecutor) driver).executeScript("return window.angular === undefined");
			if (!angularUnDefined) {
				Boolean angularInjectorUnDefined = ((JavascriptExecutor) driver).executeScript("return angular.element(document).injector() === undefined");
				if (!angularInjectorUnDefined) {
					poll(20);

					waitForAngularLoad(driver);

					poll(20);
				}
			}
		} catch (WebDriverException ignored) {
		}
	}

	static void waitForAngularLoad() {
		String angularReadyScript = "return angular.element(document).injector().get('\$http').pendingRequests.length === 0";
		angularLoads(angularReadyScript);
	}

	static void waitForAngular5Load(WebDriver driver) {
		String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
		angularLoads(driver, angularReadyScript);
	}

	static void angularLoads(WebDriver driver, String angularReadyScript) {
		try {
			boolean angularLoad = ((JavascriptExecutor) driver)
					.executeScript(angularReadyScript).toString();

			boolean angularReady = ((JavascriptExecutor) driver)
					.executeScript(angularReadyScript).toString();
			WebDriverWait jsWait = new WebDriverWait(driver, 120)
			if (!angularReady) {
				jsWait.until(angularLoad);
			}
		} catch (WebDriverException ignored) {
		}
	}



	static void waitUntilJSReady(WebDriver driver) {
		try {

			ExpectedCondition<Boolean> jsLoad = Boolean.ValueOf(((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
			// boolean jsLoad = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

			KeywordUtil.logInfo("JSLOAD "  + jsLoad);

			boolean jsReady = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

			KeywordUtil.logInfo("JSREADY "  + jsReady);

			WebDriverWait jsWait = new WebDriverWait(driver, 120)
			if (!jsReady) {
				jsWait.until(jsLoad);
			}
		} catch (WebDriverException e) {
		}
	}

	static def waitForAngularPage(int timeout){
		WebDriver driver = DriverFactory.getWebDriver()
		boolean isAngularLoaded = false
		KeywordUtil.logInfo("**** Angular Page *****");
		while (!isAngularLoaded && timeout > 0) {
			isAngularLoaded = (Boolean) ((JavascriptExecutor) driver) .executeScript("return document.readyState == 'complete'");
			boolean isloaded = (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return document.readyState == 'complete' && window.ariba != undefined && window.ariba.Request != undefined && !window.ariba.Request.isRequestInProgress();alert(window.ariba.Request.isRequestInProgress())");
			KeywordUtil.logInfo("**** Angular JS Executed *****");
			KeywordUtil.logInfo(isAngularLoaded);
			if (!isAngularLoaded){
				KeywordUtil.logInfo("Angular injector is defined on this site!");
				WebUiBuiltInKeywords.waitForAngularLoad(120)
			} else {
				KeywordUtil.logInfo("Angular injector is not defined on this site!");
				WebUiBuiltInKeywords.delay(1)
			}

			if (!isAngularLoaded) {
				WebUiBuiltInKeywords.delay(1)
				--timeout
			}
		}
	}

	static def waitForNonAngularPage(int timeout){
		WebDriver driver = DriverFactory.getWebDriver()
		boolean isloaded  = false
		while (!isloaded && timeout > 0) {
			isloaded = (Boolean) ((JavascriptExecutor) driver)
					.executeScript("if (window.jQuery != undefined) {return document.readyState === 'complete' && jQuery.active == 0 && window.ariba != undefined && window.ariba.Request != undefined && !window.ariba.Request.isRequestInProgress();alert(window.ariba.Request.isRequestInProgress())} else {return document.readyState === 'complete' && window.ariba != undefined && window.ariba.Request != undefined && !window.ariba.Request.isRequestInProgress();alert(window.ariba.Request.isRequestInProgress())}");
			KeywordUtil.logInfo("************* JS EXECUTED ************: "+isloaded);

			//if (!isloaded) {
			WebUiBuiltInKeywords.delay(1)
			--timeout
			//}
		}
	}

	static void runStager() {
		int timeout = 60
		boolean isSetUserClickable = false
		boolean isAnyStackTrace = false
		boolean isStagerError = false
		boolean isGotoLoginPage = false
		while (timeout > 0){
			isSetUserClickable=WebUiBuiltInKeywords.verifyElementClickable(findTestObject('Object Repository/Page_Ariba Spend Management/input__bt5lrc'),FailureHandling.OPTIONAL)
			isAnyStackTrace=WebUiBuiltInKeywords.verifyElementNotPresent(findTestObject('Object Repository/Page_Ariba Spend Management/StackTrace'), 1)
			isStagerError=WebUiBuiltInKeywords.verifyElementNotPresent(findTestObject('Object Repository/Page_Ariba Spend Management/StagerError'), 1)
			isGotoLoginPage=WebUiBuiltInKeywords.verifyElementNotPresent(findTestObject('Object Repository/Page_Ariba Spend Management/ReturnToLoginPage'), 1)

			KeywordUtil.logInfo("in Run stager method");
			if (!isAnyStackTrace || !isStagerError || !isGotoLoginPage){
				KeywordUtil.logInfo("is any stack trace value " + isAnyStackTrace);
				KeywordUtil.logInfo("isStagerError  value " + isStagerError);
				KeywordUtil.logInfo("is isGotoLoginPage value " + isGotoLoginPage);
				KeywordUtil.logInfo("got one stacktrace page. So returning back.");
				return
			}
			if (isSetUserClickable){
				KeywordUtil.logInfo(" I am able to see set user. So returning from runstager wait.");
				waitForPageToLoad(120)
				//WebUiBuiltInKeywords.delay(1)
				return
			}
			else {
				WebUiBuiltInKeywords.delay(1)
				--timeout
			}
		}
	}
	@Keyword
	def fileUpload(TestObject to,String filepath) {
		/*
		 String usrDir = RunConfiguration.getProjectDir()
		 //String usrDir = System.getProperty("workingDir")
		 if (usrDir == null) {
		 usrDir = System.getProperty("user.home")
		 }
		 filepath = usrDir+File.separator+filepath
		 KeywordUtil.logInfo(filepath);
		 */
		if (filepath.startsWith("/")) filepath = filepath.substring(1, filepath.length());
		WebElement El = WebUiCommonHelper.findWebElement(to, 30)
		((RemoteWebElement) El ).setFileDetector(new LocalFileDetector())
		El.sendKeys(filepath)
	}


	@Keyword
	def navigateToUrl(String rawURL) {
		WebUiBuiltInKeywords.navigateToUrl(rawURL)
		WebDriver driver = DriverFactory.getWebDriver()
		//WebElement element = WebUiCommonHelper.findWebElement(findTestObject('iebody'), 30)
		String script = "if(document.getElementById('overridelink'))"+
				" document.getElementById('overridelink').click();";
		//testFarmClient.runJavaScript("//body", script);
		//testFarmClient.waitPageToLoad();
		Boolean isloaded = (Boolean) ((JavascriptExecutor) driver).executeScript(script)
	}

	static void poll(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static def waitForSpinnerLoad(WebDriver driver) {
		String spinner = "var styleElem = document.getElementsByClassName('loading-mask')[0];\n"
		Boolean spinnerElementPresent = ((JavascriptExecutor) driver).executeScript(spinner+
				"if(styleElem) {return 'block' === window.getComputedStyle(styleElem).display;}")
		//KeywordUtil.logInfo("element " + element + "document.activeElement")
		if(spinnerElementPresent) {
			WebElement spinnerElem = ((JavascriptExecutor) driver).executeScript(spinner + "return styleElem;")
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOf(spinnerElem));
		}
	}


	/**
	 * Switch to Parent Frame
	 */
	@Keyword
	static def switchIframe(TestObject to) {
		WebDriver driver = DriverFactory.getWebDriver();
		WebElement element = WebUiBuiltInKeywords.findWebElement(to);
		driver.switchTo().frame(element);
		// waitForPageToLoad(120);
	}


	/**
	 * Switch to Default Frame 
	 */
	@Keyword
	static def switchToDefaultFrame() {
		WebDriver driver = DriverFactory.getWebDriver();
		driver.switchTo().defaultContent();
		// waitForPageToLoad(120);
	}


	/**
	 * Set Text to a Element
	 * @param to Katalon test object
	 * @param textToEnter value to be entered in field
	 */
	@Keyword
	static def aribaSetText(TestObject to, String textToEnter){
		try {
			WebDriver driver = DriverFactory.getWebDriver()
			//Switching to Iframe, if true have to switch back to default frame (after entering Text)
			Boolean isParentObject = checkIframe(driver, to)
			WebUiBuiltInKeywords.verifyElementPresent(to, 200);
			WebUiBuiltInKeywords.scrollToElement(to, 200)
			WebUiBuiltInKeywords.waitForElementClickable(to, 200)
			WebUiBuiltInKeywords.waitForElementVisible(to, 200)
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);

			KeywordUtil.logInfo("Setting Text")

			//click input field
			boolean inputActive = ((JavascriptExecutor) driver).executeScript("if(document.activeElement.nodeName"+
					"== 'TEXTAREA' || document.activeElement.nodeName == 'INPUT') {return true;\n}")
			if(!inputActive){
				KeywordUtil.logInfo("Input area is not active so clicking");
				//element.click();
				element.clear();
				waitForPageToLoad(120);
			}
			//Replace existing text and replace with entered text.
			if (textToEnter.length() > 0)
				element.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), textToEnter);
			else element.clear();
			waitForPageToLoad(120);
			//if Iframe has been switched, switched back to default frame.
			if(isParentObject){
				switchToDefaultFrame()
			}
			KeywordUtil.markPassed("Setting Text:'"+textToEnter+"' for element '"+to.toString()+"' done");
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop("aribaSetText:Fail to set text '"+textToEnter+"' on element: '"+to.toString()+"'");
		}
	}

	//static method to switch to parent frame
	//@param -> Test Object
	//return -> True/ false
	static Boolean checkIframe(WebDriver driver, TestObject to){
		TestObject parentObject = to.getParentObject()

		//if parent object present switch to parent frame
		if(parentObject) {
			switchIframe(parentObject, driver)
			return true
		}
	}

	/**
	 * Mimic Drop File behavior
	 * @param to: Object which will receive Drop File
	 * @param fileName: File Name along with Relative Path
	 */
	@Keyword
	static String dragAndDropFile (TestObject to, String fileName) {
		fileName = fh.dragAndDropFile(to, fileName);
		waitForPageToLoad(120);
		fh.cleanup(fileName);
		return fileName;
	}

	/**
	 * Download a File
	 * @param to: Clicking on Object File download operation should start
	 * @param fileName: Expected File Name
	 */
	@Keyword
	static public File downloadFile(TestObject to, String fileName, int retry = 5, int delay = 1) {
		File file = fh.downloadFile(to, fileName, retry, delay);
		waitForPageToLoad(120);
		return file;
	}

	/**
	 * Create Dynamic Object Repository
	 * @param xpath - String: Xpath value
	 */
	@Keyword
	static def createTestObject(String xpath) {
		return new TestObject(xpath).addProperty('xpath', ConditionType.EQUALS, xpath);
	}

	/**
	 * Drag and Drop Element strictly on 'awname'
	 * @param TestObject:	Source
	 * @param TestObject:	Target
	 * Valid		New Clause.docx	->	New Clause.docx		takes target xpath
	 * Non-Valid	New Clause.docx	->	AWRefreshRegion
	 * Valid		New Clause.docx	->	AWDropContainer		takes first child's awname
	 * 
	 * Valid		AWRefreshRegion ->	AWDropContainer		remove last '_' and prefix till last '_' [move section]
	 * Valid		AWDropContainer	->	AWDropContainer		remove last '_' and prefix till last '_' [move section]
	 * Valid		AWRefreshRegion	->	AWRefreshRegion		No Change
	 * Valid		AWDropContainer	->	AWRefreshRegion		No Change
	 */
	@Keyword
	static public void dragAndDropElement (TestObject sTO, TestObject tTO) {
		TestObject tableTO = createTestObject("//*[@awname='displayGroup:tableBody']");
		if (WebUiBuiltInKeywords.verifyElementPresent(tableTO, 1, FailureHandling.OPTIONAL)) {
			WebDriver driver = DriverFactory.getWebDriver();
			boolean sFlag = WebUiBuiltInKeywords.verifyElementPresent(sTO, 1, FailureHandling.OPTIONAL);
			boolean tFlag = WebUiBuiltInKeywords.verifyElementPresent(tTO, 1, FailureHandling.OPTIONAL);
			boolean newFlag = sTO.findPropertyValue('xpath').contains("New Clause.docx");

			if (newFlag && tTO.findPropertyValue('xpath').contains("AWRefreshRegion") && !tTO.findPropertyValue('xpath').contains("New Clause.docx"))
				KeywordUtil.markFailedAndStop("Check with Core Katalon Team as your Source and Target TO scenario falls in invalid condition");
			String xpath = newFlag ? "//*[@awname='displayGroup:tableBody']//tr[contains(@awname,'New Clause.docx')]": "//*[@awname='displayGroup:tableBody']//tr[@awname][not(contains(@awname,'New Clause.docx'))]";
			int revCounter = 0;
			List<WebElement> list = driver.findElements(By.xpath(xpath));
			int size = list.size();
			ArrayList<String> uniques = null;
			while(!(sFlag && tFlag && revCounter==0)) {
				uniques = new ArrayList<String>(list);
				if (sFlag && revCounter==0) {
					WebUiBuiltInKeywords.scrollToElement(sTO, 30);
					WebUiBuiltInKeywords.delay(2);
					waitForPageToLoad(120);
					tFlag = WebUiBuiltInKeywords.verifyElementPresent(tTO, 1, FailureHandling.OPTIONAL);
					if (!tFlag) {
						String tempAwname = list.get(size-1).getAttribute("awname");
						String tempSource = WebUiBuiltInKeywords.getAttribute(sTO, "awname");
						String tempPre = "";
						int tIndex = tempAwname.lastIndexOf("_");
						if (tIndex != -1) tempPre = tempAwname.substring(0,tIndex+1);
						int sIndex = tempSource.lastIndexOf("_")+1;
						if (sIndex == -1) sIndex = 0;
						TestObject tempTO = createTestObject("//*[@awname='"+list.get(size-1).getAttribute("awname")+"']");
						WebUiBuiltInKeywords.dragAndDropToObject(sTO, tempTO);
						WebUiBuiltInKeywords.delay(2);
						waitForPageToLoad(120);
						sTO = newFlag ? tempTO : createTestObject("//*[@awname='"+tempPre+tempSource.substring(sIndex)+"']");
						tempSource = WebUiBuiltInKeywords.getAttribute(sTO, "awname");
						WebUiBuiltInKeywords.scrollToElement(sTO, 30);
						WebUiBuiltInKeywords.delay(2);
						waitForPageToLoad(120);
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollBy(0,2000)", WebUiBuiltInKeywords.findWebElement(createTestObject("//div[contains(@class,'yScroll tableBody')]")));
						WebUiBuiltInKeywords.delay(2);
						waitForPageToLoad(120);
						tFlag = WebUiBuiltInKeywords.verifyElementPresent(tTO, 1, FailureHandling.OPTIONAL);
					}
					if (!tFlag) {
						list = driver.findElements(By.xpath(xpath));
						uniques.removeAll(list);
						if (uniques.size() == 0 && list.size() == size) KeywordUtil.markFailedAndStop("Target TestObject NOT Present");
						size = list.size();
					}
				} else if (tFlag) {
					sFlag = WebUiBuiltInKeywords.verifyElementPresent(sTO, 1, FailureHandling.OPTIONAL);
					if (!sFlag) {
						TestObject tempTO = createTestObject("//*[@awname='"+list.get(size-1).getAttribute("awname")+"']");
						WebUiBuiltInKeywords.scrollToElement(tempTO, 30);
						WebUiBuiltInKeywords.delay(2);
						waitForPageToLoad(120);
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollBy(0,2000)", WebUiBuiltInKeywords.findWebElement(createTestObject("//div[contains(@class,'yScroll tableBody')]")));
						WebUiBuiltInKeywords.delay(2);
						waitForPageToLoad(120);
						revCounter++;
						list = driver.findElements(By.xpath(xpath));
						uniques.removeAll(list);
						if (uniques.size() == size && list.size() == size) KeywordUtil.markFailedAndStop("Source TestObject NOT Present");
						size = list.size();
					} else revCounter--;
					if (sFlag && revCounter>=0) {
						WebUiBuiltInKeywords.scrollToElement(sTO, 30);
						WebUiBuiltInKeywords.delay(2);
						waitForPageToLoad(120);
						list = driver.findElements(By.xpath(xpath));
						String tempAwname = list.get(0).getAttribute("awname");
						String tempSource = WebUiBuiltInKeywords.getAttribute(sTO, "awname");
						String tempPre = "";
						int tIndex = tempAwname.lastIndexOf("_");
						if (tIndex != -1) tempPre = tempAwname.substring(0,tIndex+1);
						int sIndex = tempSource.lastIndexOf("_")+1;
						if (sIndex == -1) sIndex = 0;
						TestObject tempTO = createTestObject("//*[@awname='"+list.get(0).getAttribute("awname")+"']");
						WebUiBuiltInKeywords.dragAndDropToObject(sTO, tempTO);
						WebUiBuiltInKeywords.delay(2);
						waitForPageToLoad(120);
						sTO = newFlag ? tempTO : createTestObject("//*[@awname='"+tempPre+tempSource.substring(sIndex)+"']");
						WebUiBuiltInKeywords.scrollToElement(sTO, 30);
						WebUiBuiltInKeywords.delay(2);
						waitForPageToLoad(120);
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollBy(0,-2000)", WebUiBuiltInKeywords.findWebElement(createTestObject("//div[contains(@class,'yScroll tableBody')]")));
						WebUiBuiltInKeywords.delay(2);
						waitForPageToLoad(120);
					}
				} else {
					TestObject tempTO = createTestObject("//*[@awname='"+list.get(size-1).getAttribute("awname")+"']");
					WebUiBuiltInKeywords.scrollToElement(tempTO, 30);
					WebUiBuiltInKeywords.delay(2);
					waitForPageToLoad(120);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollBy(0,2000)", WebUiBuiltInKeywords.findWebElement(createTestObject("//div[contains(@class,'yScroll tableBody')]")));
					WebUiBuiltInKeywords.delay(2);
					waitForPageToLoad(120);
					sFlag = WebUiBuiltInKeywords.verifyElementPresent(sTO, 1, FailureHandling.OPTIONAL);
					tFlag = WebUiBuiltInKeywords.verifyElementPresent(tTO, 1, FailureHandling.OPTIONAL);
					if(!(sFlag || tFlag)) {
						list = driver.findElements(By.xpath(xpath));
						uniques.removeAll(list);
						if (uniques.size() == size) KeywordUtil.markFailedAndStop("Source & Target TestObject NOT Present");
						size = list.size();
					}
				}
			}
			WebUiBuiltInKeywords.dragAndDropToObject(sTO, tTO);
			WebUiBuiltInKeywords.delay(2);
			waitForPageToLoad(120);

		} else KeywordUtil.markFailedAndStop("Check with Core Katalon Team as awname:'displayGroup:tableBody' not present");
	}

	/**
	 * Document Compare
	 * @param File:	Source
	 * @param File:	Destination
	 * https://wiki.ariba.com/display/ENGDPTS/Ariba+Customization#AribaCustomization-DocumentCompare(Doc)
	 */
	@Keyword
	/*static public void compareDocuments(File source, File destination) {
	 CompareDocxDocuments c = CompareDocxDocuments.instance();
	 boolean flag = c.compare(source, destination);
	 if (flag) KeywordUtil.markPassed("Documents are compared successfully")
	 else KeywordUtil.markFailedAndStop("Documents mismatched, Please refer : "+destination.getName().replace(".docx", ".html")+"'")
	 }*/

	/**
	 * xinhaSetText strictly on 'awname'
	 * @param TestObject:	to
	 * @param TestObject:	text
	 */
	@Keyword
	static public def xinhaSetText(TestObject to, String text) {
		String strEle = to.findPropertyValue("awname");
		if (strEle == null) KeywordUtil.markFailedAndStop("Element: '"+to+"' doesn't have 'awname'");
		WebUiBuiltInKeywords.verifyElementPresent(createTestObject("//iframe[1]"), 200);
		WebUiBuiltInKeywords.executeJavaScript("var element = document.getElementsByTagName('iframe');"+
				"for(var i=0;i<element.length;i++) {"+
				"if(element[i].contentDocument.getElementsByTagName('body')[0].getAttribute('awname') == '"+strEle+"') {"+
				"element[i].click();"+
				"element[i].contentDocument.body.innerHTML='"+ text +"';"+
				"element[i].click();}}", null);
	}
}
