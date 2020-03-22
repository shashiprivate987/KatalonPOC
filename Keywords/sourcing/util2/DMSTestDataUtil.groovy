package sourcing.util2

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
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

public class DMSTestDataUtil {

	private static int rowStartNum;
	private static int rowEndNum;
	static ExcelInit util = null;
	static String testDataFileFullPath = UtilConstants.FILEPATH + UtilConstants.FILE_RELATIVE_PATH + UtilConstants.TESTDATA_FILE;

	@Keyword
	static Map<String,String> getCellValuesOfRow(String sheet, String dataTableName,int rowNum) throws IOException, NoSuchFieldException {
		/*String file = "TestData.xlsx";
		 String fileFullPath = "/Users/i331477/git/S4MicroServiceKatalon/Test Files/dms/ExcelValidations/" + file;*/
		//String fileFullPath = UtilConstants.FILEPATH + UtilConstants.FILE_RELATIVE_PATH + UtilConstants.TESTDATA_FILE;
		util = new ExcelInit(testDataFileFullPath.trim(), sheet);
		List<String> cellValues = null;
		int dataTableCount = 0;
		boolean flag = false;
		int rowCnt = 0;
		int tagCnt = 0;
		int rowStart = 0;
		int rowEnd = 0;
		List<Row> rowList = new ArrayList<>();
		Map<String,String> cellValue = new HashMap<String, String>();
		Iterator<Row> rows = util.getSheetObj().iterator();

		while (rows.hasNext()) {
			Row row = rows.next();
			String tableTag = row.iterator().next().toString();

			if (tableTag.equalsIgnoreCase(dataTableName)) {
				tagCnt++;

				if(tagCnt == 1){
					rowStart = row.getRowNum() + 2;
					setRowStartNum(rowStart);
				}

				if(tagCnt == 2){
					rowEnd = row.getRowNum();
					setRowEndNum(rowEnd);
				}
			}
		}

		if(tagCnt == 2){
			flag = true;
		}else{
			KeywordUtil.logInfo("Number of table tags seems to be : "+tagCnt+" in the sheet, hence it is Invalid Table Tags, Please check the tags again...");
			throw new NoSuchFieldException("Invalid Table Tags, Please check the tags again...");
		}

		if(flag){
			for(int i= rowStart-1; i< rowEnd; i++){
				rowList.add(util.getSheetObj().getRow(i));
			}
		}

		int startCellNum = util.getSheetObj().getRow(rowStart-1).getFirstCellNum();
		int endCellNum = util.getSheetObj().getRow(rowStart-1).getLastCellNum();

		/*System.out.println("Cell Headers: ");
		 for(int i=startCellNum; i<endCellNum; i++){
		 String cellVal = util.getSheetObj().getRow(rowStart-1).getCell(i).getStringCellValue();
		 //System.out.println(cellVal);
		 }
		 System.out.println("Cell Values from first row: ");
		 for(int i=startCellNum; i<endCellNum; i++){
		 String cellVal = util.getSheetObj().getRow(rowStart).getCell(i).getStringCellValue();
		 //System.out.println(cellVal);
		 }*/

		List<String> listOfVals = new ArrayList<String>();
		Map<String,String> map = new HashMap<String, String>();

		for(int i=startCellNum; i<endCellNum; i++){
			String cellHeader = "";
			String cellVal = "";
			int headerRow = rowStart-1;
			if(util.getSheetObj().getRow(headerRow) != null && util.getSheetObj().getRow(headerRow).getCell(i) != null){
				cellHeader = util.getSheetObj().getRow(headerRow).getCell(i).getStringCellValue();
			}

			if(util.getSheetObj().getRow(headerRow + rowNum) != null && util.getSheetObj().getRow(headerRow + rowNum).getCell(i) != null) {
				cellVal = util.getSheetObj().getRow(headerRow + rowNum).getCell(i).getStringCellValue();
			}

			map.put(cellHeader,cellVal);
		}

		/*for(Map.Entry<String,String> entry: map.entrySet()){
		 System.out.println("Header: "+ entry.getKey()+" || "+"Value: "+ entry.getValue());
		 }*/

		/*int firstCell = rowList.get(0).getFirstCellNum();
		 rowList.get(0).getFirstCellNum();*/

		return map;
	}


	static String getCellValueForKey(String sheetName, String dataTableName, String key) throws NoSuchFieldException {
		ExcelInit util = new ExcelInit(testDataFileFullPath.trim(), sheetName);
		List<String> cellValues = null;
		int dataTableCount = 0;
		boolean flag = false;
		int rowCnt = 0;
		int tagCnt = 0;
		int rowStart = 0;
		int rowEnd = 0;
		//String dataTableName = "DataSet3";
		String mappingValue = null;
		Iterator<Row> rows = util.getSheetObj().iterator();

		while (rows.hasNext()) {
			Row row = rows.next();
			String tableTag = row.iterator().next().toString();

			if (tableTag.equalsIgnoreCase(dataTableName)) {
				tagCnt++;

				if(tagCnt == 1){
					rowStart = row.getRowNum() + 2;
					setRowStartNum(rowStart);
				}

				if(tagCnt == 2){
					rowEnd = row.getRowNum();
					setRowEndNum(rowEnd);
				}
			}
		}

		if(tagCnt == 2){
			flag = true;
		}else{
			KeywordUtil.logInfo("Number of table tags seems to be : "+tagCnt+" in the sheet, hence it is Invalid Table Tags, Please check the tags again...");
			throw new NoSuchFieldException("Invalid Table Tags, Please check the tags again...");
		}

		int startCellNum = util.getSheetObj().getRow(rowStart-1).getFirstCellNum();
		int endCellNum = util.getSheetObj().getRow(rowStart-1).getLastCellNum();
		List<String> listOfVals = new ArrayList<String>();
		Map<String,String> map = new HashMap<String, String>();
		for(int i=startCellNum; i<endCellNum; i++){
			String cellheader = util.getSheetObj().getRow(rowStart-1).getCell(i).getStringCellValue();
			String cellVal = util.getSheetObj().getRow(rowStart).getCell(i).getStringCellValue();
			map.put(cellheader,cellVal);
		}

		for(Map.Entry<String,String> entry: map.entrySet()){
			//System.out.println("Header: "+ entry.getKey()+" || "+"Value: "+ entry.getValue());

			if(entry.getKey().equalsIgnoreCase(key)){
				//System.out.println("Value for given key: "+key+ " is : "+entry.getValue());
				mappingValue = entry.getValue();
				return mappingValue;
			}
		}


		return null;
	}


	static List<Map<String,String>> getAllDataFromAllRows(String sheet, String dataTableName) throws NoSuchFieldException, IOException {
		//String file = "TestData.xlsx";
		//String fileFullPath = "/Users/i331477/git/S4MicroServiceKatalon/Test Files/dms/ExcelValidations/" + file;
		util = new ExcelInit(testDataFileFullPath.trim(), sheet);
		List<String> cellValues = null;
		int dataTableCount = 0;
		boolean flag = false;
		int rowCnt = 0;
		int tagCnt = 0;
		int rowStart = 0;
		int rowEnd = 0;
		String mappingValue = null;
		//util = new ExcelInit(fileFullPath, sheet);
		Iterator<Row> rows = util.getSheetObj().iterator();

		while (rows.hasNext()) {
			Row row = rows.next();
			String tableTag = row.iterator().next().toString();

			if (tableTag.equalsIgnoreCase(dataTableName)) {
				tagCnt++;

				if(tagCnt == 1){
					rowStart = row.getRowNum() + 2;
					setRowStartNum(rowStart);
				}

				if(tagCnt == 2){
					rowEnd = row.getRowNum();
					setRowEndNum(rowEnd);
				}
			}
		}

		if(tagCnt == 2){
			flag = true;
		}else{
			// KeywordUtil.logInfo("Number of table tags seems to be : "+tagCnt+" in the sheet, hence it is Invalid Table Tags, Please check the tags again...");
			throw new NoSuchFieldException("Invalid Table Tags, Please check the tags again...");
		}


		List<XSSFRow> rowList1 = new ArrayList<XSSFRow>();

		if(flag){
			for(int i= rowStart-1; i< rowEnd; i++){
				rowList1.add(util.getSheetObj().getRow(i));
			}
		}



		List<Map<String,String>> rowList = new ArrayList<Map<String,String>>();
		for(int i=rowStart; i< rowEnd; i++){

			int startCellNum = util.getSheetObj().getRow(rowStart-1).getFirstCellNum();
			int endCellNum = util.getSheetObj().getRow(rowStart-1).getLastCellNum();
			List<String> listOfVals = new ArrayList<String>();
			Map<String,String> mapOfCellValues = new HashMap<String, String>();

			for(int j=startCellNum; j<endCellNum; j++){
				String cellHeader = "";
				String cellVal = "";
				if(null != util.getSheetObj().getRow(rowStart-1).getCell(j)){
					cellHeader = util.getSheetObj().getRow(rowStart-1).getCell(j).getStringCellValue();
				}

				if(null != util.getSheetObj().getRow(i).getCell(j)){
					cellVal = util.getSheetObj().getRow(i).getCell(j).getStringCellValue();
				}

				mapOfCellValues.put(cellHeader,cellVal);
			}

			rowList.add(mapOfCellValues);
		}

		/*// Get List of Rows and foreach row
		 Map<String,String> cells = new HashMap<String, String>();
		 cells.put("head1","x");
		 List<String> rowList1 = new ArrayList<String>();
		 rowList1.add("a");
		 rowList1.add("b");
		 List<Map<String,String>> list = new ArrayList<Map<String, String>>();
		 list.add(cells);*/

		return rowList;
	}


	/**
	 *
	 * @param fileRelPath
	 * @param fileName
	 * @param sheetName
	 * @param dataTableName
	 * @param columnHeader
	 * @return
	 */
	@Keyword
	static List<String> getTestDataForGivenColumn(String sheetName, String dataTableName, String columnHeader){
		//String fileFullPath = RunConfiguration.getProjectDir()+fileRelPath
		//String file = "TestData.xlsx";
		//String fileFullPath = "/Users/i331477/git/S4MicroServiceKatalon/Test Files/dms/ExcelValidations/" + file;
		ExcelInit util = new ExcelInit(testDataFileFullPath.trim(), sheetName);
		util.setDataTableName(dataTableName);
		System.out.println(util.getDataTableName());
		util.setColumnHeader(columnHeader);
		System.out.println(util.getColumnHeader());
		System.out.println(util.getSheetObj());


		// get List of rows from the datatable
		List<Row> rowList = getRowsInsideTableTag(util.getSheetObj(), util.getDataTableName());



		return(getAllValueForColumn(rowList, util.getColumnHeader()));
	}

	private static List<Row> getRowsInsideTableTag(XSSFSheet sheet, String dataTableName){

		int dataTableCount = 0;
		boolean flag = false;
		int rowCnt = 0;
		int tagCnt = 0;
		int rowStart = 0;
		int rowEnd = 0;
		List<Row> rowList = new ArrayList<Row>();
		Iterator<Row> rows = sheet.iterator();


		while (rows.hasNext()) {
			Row row = rows.next();
			String tableTag = row.iterator().next().toString();

			if (tableTag.equalsIgnoreCase(dataTableName)) {
				tagCnt++;

				if(tagCnt == 1){
					rowStart = row.getRowNum() + 2;
					setRowStartNum(rowStart);
				}

				if(tagCnt == 2){
					rowEnd = row.getRowNum();
					setRowEndNum(rowEnd);
				}
			}
		}

		if(tagCnt == 2){
			flag = true;
		}else{
			KeywordUtil.logInfo("Number of table tags seems to be : "+tagCnt+" in the sheet, hence it is Invalid Table Tags, Please check the tags again...");
			throw new NoSuchFieldException("Invalid Table Tags, Please check the tags again...");
		}

		if(flag){
			for(int i= rowStart-1; i< rowEnd; i++){
				rowList.add(sheet.getRow(i));
			}
		}

		return rowList;

	}

	private static List<String> getAllValueForColumn(List<Row> rowList, String columnHeader) {

		int headerColNum = 0;
		List<String> columns = new ArrayList<String>();
		List<String> headerCol = new ArrayList<String>();
		Row headerRow = rowList.get(0);

		// Header Cell Values
		Iterator<Cell>  headItr = headerRow.iterator();

		while (headItr.hasNext()){
			headerCol.add(headItr.next().toString());
		}

		// based on the column header param, iterate all the columns under it
		int startCells = headerRow.getFirstCellNum();
		int EndCells = headerRow.getLastCellNum();

		boolean headerFound =false;
		for(int i=startCells; i< EndCells; i++){

			if(headerRow.getCell(i).toString().trim().equalsIgnoreCase(columnHeader.trim())){
				headerFound = true;
				headerColNum = headerRow.getCell(i).getColumnIndex();
				break;
			}
		}

		if(!headerFound){
			throw new NoSuchElementException("Column Header name either wrong or not present as header. Please rechek...!")
		}

		// Iterate the Row List and get columns values under a particular column header
		KeywordUtil.logInfo("DATA FETCHED: ");
		for(int i = 1 ; i < rowList.size(); i++){
			Row myRow = rowList.get(i);
			/*if(rowList.get(i) != null || myRow.getCell(headerColNum).toString() != null || myRow.getCell(headerColNum).toString() != ""){
				columns.add(myRow.getCell(headerColNum).toString());
				KeywordUtil.logInfo(" " +myRow.getCell(headerColNum).toString()+" ");
			}*/
			
			if(rowList.get(i) != null){
				if(myRow.getCell(headerColNum) != null || !(myRow.getCell(headerColNum).toString().equalsIgnoreCase("null"))){
					if(myRow.getCell(headerColNum).toString() != ""){
						columns.add(myRow.getCell(headerColNum).toString());
						KeywordUtil.logInfo(" " +myRow.getCell(headerColNum).toString()+" ");
					}
				}
			}
			
			
		}

		return columns;
	}


	private static void setRowStartNum(rowStart){
		this.rowStartNum = rowStart;
	}

	private static void setRowEndNum(rowEnd){
		this.rowEndNum = rowEnd;
	}
}
