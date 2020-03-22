package sourcing.util2

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet

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

import internal.GlobalVariable
import oracle.sql.utilpack

public class ExcelToggleParamStoreStrategy {

	private ExcelToggleParamInit initExcel = null;
	List<String> togglesToBeSet = new ArrayList<String>();
	List<String> paramsToBeSet = new ArrayList<String>();
	
	List<String> toggles = new ArrayList<String>();
	List<String> params = new ArrayList<String>();

	public ExcelToggleParamStoreStrategy() throws IOException {
		// Initializing the excel is not done at constructor because writing the excel multiple times will throw error which is EXCEL DEFECT
		//initExcel = new ExcelPojo(UtilConstants.FILEPATH, UtilConstants.TOGGLE_SHEET_NAME, UtilConstants.PARAMETER_SHEET_NAME);
	}

	/**
	 * Desc: Accepts the input toggles and returns the actual toggles to be set by comparing with the existing toggles and also whether it is set or unset 
	 * @param toggles
	 * @return
	 * @throws IOException
	 * 
	 */
	public synchronized List<String> registerTogglesAndGetActualToEnable(List<String> toggles) throws IOException {
		initExcelObject();
		resetToggleToBeSet();
		List<String> ipToggles = removeDuplicatesFromInputValues(toggles);
		List<String> existingT = getExistingToggles();
		List<String> existingToggles = getTotalExistingTogglesComparingNewToggles(existingT, ipToggles);
		writeTogglesIntoExcel(existingToggles, getTogglesTobeSet());
		//toggles = togglesToBeSet;
		//resetToggleToBeSet();
		//KeywordUtil.logInfo("Total Toggles to be set are: " + getTogglesTobeSet.size() + " and those are "+getTogglesToBeSet().toString());
		//return getTogglesTobeSetOrUnset();
		return getTogglesTobeSet();
	}

	private void resetToggleToBeSet(){
		togglesToBeSet.clear();
	}
	
	/**
	 * Desc: Get the unique List of items
	 * @param ipList
	 * @return
	 */
	private List<String> removeDuplicatesFromInputValues(List<String> ipList) {

		if(ipList == null){
			return getTogglesTobeSet();
		}

		Set<String> set = new LinkedHashSet<String>();
		set.addAll(ipList);
		ipList.clear();
		ipList.addAll(set);
		return ipList;
	}


	/**
	 * Desc: Get the existing list and get the toggles to be set and write into the excel clearing the existing and adding both lists 
	 * @param existingToggles
	 * @param togglesToBeSet
	 */
	private void writeTogglesIntoExcel(List<String> existingToggles, List<String> togglesToBeSet) {

		List<String> concateAllToggles = new ArrayList<String>();

		int firstRow = initExcel.getSheet("toggle").getFirstRowNum();
		int lastRow = initExcel.getSheet("toggle").getLastRowNum();

		for (int i = firstRow + 1; i <= lastRow; i++) {
			initExcel.getSheet("toggle").removeRow(initExcel.getSheet("toggle").getRow(i));
		}

		if (existingToggles.equals(togglesToBeSet)) {
			concateAllToggles = togglesToBeSet;
		} else {
			concateAllToggles.addAll(existingToggles);
			concateAllToggles.addAll(togglesToBeSet);
		}

		XSSFCellStyle style = initExcel.getWorkbook().createCellStyle();
		for (int i = 0; i < concateAllToggles.size(); i++) {
			XSSFRow row = initExcel.getSheet("toggle").createRow(i + 1);
			row.createCell(0).setCellValue(concateAllToggles.get(i).trim());
			style.setWrapText(true);
		}

		writeIntoFile();
	}

	/**
	 * Desc: Write the file and close the file
	 */
	private void writeIntoFile() {
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(initExcel.getFilePath());
			initExcel.getWorkbook().write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Desc: Logic to compare the existing toggle with the i/p toggles also check whether the toggle is set or unset and get the actual existing toggles
	 * @param existingToggles
	 * @param newToggles
	 * @return
	 */
	private List<String> getTotalExistingTogglesComparingNewToggles(List<String> existingToggles, List<String> newToggles) {


		for (int i = 0; i < newToggles.size(); i++) {
			if (!(newToggles.get(i).toString().contains("set") || newToggles.get(i).toString().contains("unset") && newToggles.get(i).toString().contains(":"))) {
				throw new IllegalArgumentException(" toggle format or name is wrong : " + newToggles.get(i));
			}
		}

		if (existingToggles.size() == 0 || existingToggles == null) {
			togglesToBeSet = newToggles;
			return getTogglesToBeSet();
		}

		/*
		 * Toggle set logic
		 * Old - existingToggles
		 New - newToggles
		 1. Check whether existingToggles array has no value, then set newToggles to existingToggles and return
		 2. Iterate New across old and check below conditions
		 a. If toggles match then continue and dont return it
		 b. If toggles don't match then check first parts are matching
		 - if yes then check the second part of new (set or unset) then remove that item from old and add item from new into old
		 */
		for (int i = 0; i < newToggles.size(); i++) {
			boolean isPresent = true;
			if (existingToggles.contains(newToggles.get(i))) {
				continue;
			} else {
				for (int j = 0; j < existingToggles.size(); j++) {
					if (!(newToggles.get(i).equalsIgnoreCase(existingToggles.get(j)))) {
						if (newToggles.get(i).split(":")[0].equalsIgnoreCase(existingToggles.get(j).split(":")[0])) {
							isPresent = true;
							if (newToggles.get(i).split(":")[1].equalsIgnoreCase("unset") ||
							newToggles.get(i).split(":")[1].equalsIgnoreCase("set")) {
								isPresent = true;
								togglesToBeSet.add(newToggles.get(i));
								existingToggles.remove(j);

								if (existingToggles.size() == 0) {
									togglesToBeSet = newToggles;
									return togglesToBeSet;
								}

								break;
							} else {

								throw new IllegalArgumentException(" toggle format or name is wrong : " + newToggles.get(i));
							}
						} else {
							isPresent = false;
						}
					}
				}
				if (!isPresent) {
					togglesToBeSet.add(newToggles.get(i));
				}
			}
		}

		return existingToggles;
	}


	public List<String> getTogglesTobeSet() {
		return togglesToBeSet;
	}

	List<String> localList = new ArrayList<String>();
	public List<String> getTogglesTobeSetOrUnset() {
		
		localList.clear();
		if(getTogglesTobeSet().size()>0){
			for(int i=0; i< getTogglesTobeSet().size(); i++){
				localList.add(getTogglesTobeSet().get(i).split(":")[0]);
			}
			//togglesToBeSet = null;
			//togglesToBeSet = localList
		}

		return localList;
	}
	
	public List<String> getParamsTobeSet() {
		return paramsToBeSet;
	}


	/**
	 * Desc: Register parameters by passing i/p params
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public List<String> registerParametersAndGetActualToEnable(List<String> params) throws IOException {
		initExcelObject();
		List<String> ipParams = removeDuplicatesFromInputValues(params);
		List<String> existingParams = getExisingParams();
		List<String>  actualParams = getActualParamsToBeAddedComparingWithExisting(existingParams,ipParams);
		writeParamsIntoExcel(existingParams,actualParams);
		return actualParams;
	}


	/**
	 * Get the existing list and get the params to be added and write into the excel clearing the existing and adding both lists
	 * @param existingParams
	 * @param actualParams
	 */
	private void writeParamsIntoExcel(List<String> existingParams, List<String> actualParams) {
		int firstRow = initExcel.getSheet("params").getLastRowNum();
		int j =0;
		for (int i = firstRow + 1; i <= firstRow + actualParams.size(); i++) {
			Row row = initExcel.getSheet("params").createRow(i);
			row.createCell(0).setCellValue(actualParams.get(j).trim());
			j++;
		}

		writeIntoFile();
	}

	/**
	 * Desc: Get the get existing params comparing with i/p params
	 * @param existingParams
	 * @param ipParams
	 * @return
	 */
	private List<String> getActualParamsToBeAddedComparingWithExisting(List<String> existingParams, List<String> ipParams) {

		if (existingParams.size() == 0 || existingParams == null) {
			paramsToBeSet = ipParams;
			return getParamsTobeSet();
		}

		for (Iterator<String> itr = ipParams.iterator(); itr.hasNext(); ) {
			String param = itr.next();
			if (!(existingParams.contains(param))) {
				paramsToBeSet.add(param);
			}
		}

		return getParamsTobeSet();
	}

	/**
	 * Desc: Get all the existing params
	 * @return
	 */
	private List<String> getExisingParams() {
		List<String> existingParams = new ArrayList<String>();
		XSSFSheet paramSheet = initExcel.getSheet("parameters");
		Iterator<Row> rows = initExcel.getSheet("parameters").iterator();

		if (paramSheet.getLastRowNum() <= 0) {
			return existingParams;
		}

		for (int i = paramSheet.getFirstRowNum() + 1; i <= paramSheet.getLastRowNum(); i++) {
			String vals = paramSheet.getRow(i).getCell(0).getStringCellValue();
			existingParams.add(vals);
		}

		//System.out.println("Existing params: " + existingParams.toString());
		return existingParams;
	}


	/**
	 * Desc: Get all the existing toggles
	 * @return
	 */
	private List<String> getExistingToggles() {
		List<String> existingToggles = new ArrayList<String>();
		XSSFSheet togglesSheet = initExcel.getWorkbook().getSheet("Toggles");

		if (togglesSheet.getLastRowNum() == 0) {
			return existingToggles;
		}

		for (int i = togglesSheet.getFirstRowNum() + 1; i <= togglesSheet.getLastRowNum(); i++) {
			String vals = togglesSheet.getRow(i).getCell(0).getStringCellValue();
			existingToggles.add(vals);
		}

		//System.out.println("Existing params: " + existingParams.toString());
		return existingToggles;
	}

	/**
	 * Desc: Get the toggle sheet and clear all the rows in it
	 * @throws IOException
	 */
	public void clearAllToggles() throws IOException {
		initExcelObject();
		XSSFSheet sheet = initExcel.getWorkbook().getSheet("Toggles");
		clearContentsFromSheet("Toggles");
		System.out.println("All Toggles cleared");
	}

	/**
	 * Desc: Get the param sheet and clear all the rows in it
	 * @throws IOException
	 */
	public void clearAllParameters() throws IOException {
		initExcelObject();
		XSSFSheet sheet = initExcel.getWorkbook().getSheet("Parameters");
		clearContentsFromSheet("Parameters");
		System.out.println("All Paramters cleared");
	}

	/**
	 * Desc: Get the name of the sheet and delete it
	 * @param sheetName
	 * @throws IOException
	 */
	public void deleteSheet(String sheetName) throws IOException {
		initExcelObject();
		initExcel.getWorkbook().removeSheetAt(initExcel.getWorkbook().getSheetIndex(initExcel.getSheet(sheetName)));
		System.out.println(sheetName+ " sheet is removed..");
		writeIntoFile();
	}

	/**
	 * Desc: Clear all rows from the i/p sheet
	 * @param sheet
	 * @throws IOException
	 */
	private void clearContentsFromSheet(String sheetName) throws IOException {

		XSSFSheet sheet = initExcel.getWorkbook().getSheet(sheetName);
		int firstRow = initExcel.getWorkbook().getSheet(sheetName).getFirstRowNum();
		int lastRow = initExcel.getWorkbook().getSheet(sheetName).getLastRowNum();
		System.out.println("Rows removed: ");
		for (int i= firstRow; i<= lastRow; i++ ){
			System.out.println(sheet.getRow(i).getCell(0).getStringCellValue());
			sheet.removeRow(sheet.getRow(i));
		}


		writeIntoFile();
	}

	/**
	 * Init the Excel pojo, this has to be called every time for the method which involves excel write operation
	 * @return
	 */
	private void initExcelObject(){
		initExcel = null;
		String FILE = UtilConstants.FILEPATH + UtilConstants.FILE_RELATIVE_PATH + UtilConstants.TESTDATA_FILE;
		initExcel = new ExcelToggleParamInit(FILE, UtilConstants.TOGGLE_SHEET_NAME, UtilConstants.PARAMETER_SHEET_NAME);
	}
}
