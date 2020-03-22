package sourcing.util2

import java.sql.Timestamp
import java.text.SimpleDateFormat
import javax.naming.directory.InvalidAttributesException
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.InvalidArgumentException
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
//import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class DmsUtils {


	private static int rowStartNum;
	private static int rowEndNum;

	static Object getExcelSheetObject(String fileName, String fileWithFullPath, String sheetname){

		switch (fileName.substring(fileName.lastIndexOf(".") + 1,
		fileName.length())) {
			case "xls":
				return readXLS(fileWithFullPath, sheetname);
			case "xlsx":
				return readXLSX(fileWithFullPath, sheetname);
			default:
				KeywordUtil.logInfo("Passed excel is not in right format..");
				KeywordUtil.logInfo("Excel sheet Format error..");
				return null;
		}
	}


	static HSSFSheet readXLS(String fileWithFullPath, String sheetName){
		HSSFSheet sheet = null;
		FileInputStream fis = new FileInputStream(fileWithFullPath);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		return sheet;
	}

	static XSSFSheet readXLSX(String fileWithFullPath, String sheetName){
		XSSFSheet sheet = null;
		FileInputStream fis = new FileInputStream(fileWithFullPath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		return sheet;
	}

	/**
	 *
	 * @param exp
	 * @param act
	 */
	@Keyword
	static void validateExcelSheets(String expFilePath, String actFilePath, String sheetName){
		String usrDir = RunConfiguration.getProjectDir();

		String expFile = null;
		String actFile = null;
		Object expObj = null;
		Object actObj = null;

		expFile = getFileName(expFilePath);
		actFile = getFileName(actFilePath);

		expObj = getExcelSheetObject(expFile, usrDir+expFilePath, sheetName);
		actObj = getExcelSheetObject(actFile, usrDir+actFilePath, sheetName);

		if(expObj instanceof XSSFSheet && actObj instanceof XSSFSheet){
			compareXSSFSheets(expObj, actObj);
		}else if(expObj instanceof HSSFSheet && actObj instanceof HSSFSheet){
			compareHSSFSheets(expObj, actObj);
		} else{
			KeywordUtil.logInfo("Invalid Excel Format exception!");
			throw new IllegalFormatException("Invalid Excel Format exception!");
		}
	}


	static String getFileName(String fileFullPath){

		String[] items = fileFullPath.split("/");
		int itemCount = items.length;

		return items[itemCount-1].trim();
	}

	static void compareXSSFSheets(XSSFSheet exp, XSSFSheet act){
		try{
			Iterator<XSSFRow> rowIterator1 = exp.iterator();
			Iterator<XSSFRow> rowIterator2 = act.iterator();
			while (rowIterator1.hasNext() && rowIterator2.hasNext()) {
				XSSFRow currentRow1 = rowIterator1.next();
				XSSFRow currentRow2 = rowIterator2.next();
				Iterator<XSSFCell> cellIterartor1 = currentRow1.iterator();
				Iterator<XSSFCell> cellIterator2 = currentRow2.iterator();
				while (cellIterartor1.hasNext() && cellIterator2.hasNext()) {
					XSSFCell currentCell1 = cellIterartor1.next();
					XSSFCell currentCell2 = cellIterator2.next();

					//logic to compare values
					if(!currentCell1.toString().equalsIgnoreCase(currentCell2.toString())){

						System.out.print("####### "+exp.getRow(0).getCell(currentCell1._cellNum).getStringCellValue().toUpperCase()+" in EXPECTED SHEET is: "+currentCell1.toString()+" but in ACTUAL SHEET is: "
								+currentCell2.toString()+" #######");
						//WebUI.verifyMatch(currentCell1.toString(), currentCell2.toString(), false, FailureHandling.STOP_ON_FAILURE)
					}

				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	static void compareHSSFSheets(HSSFSheet exp, HSSFSheet act){

		try{
			Iterator<HSSFRow> rowIterator1 = exp.iterator();
			Iterator<HSSFRow> rowIterator2 = act.iterator();
			while (rowIterator1.hasNext() && rowIterator2.hasNext()) {
				HSSFRow currentRow1 = rowIterator1.next();
				HSSFRow currentRow2 = rowIterator2.next();
				Iterator<HSSFCell> cellIterartor1 = currentRow1.iterator();
				Iterator<HSSFCell> cellIterator2 = currentRow2.iterator();
				while (cellIterartor1.hasNext() && cellIterator2.hasNext()) {
					HSSFCell currentCell1 = cellIterartor1.next();
					HSSFCell currentCell2 = cellIterator2.next();

					//logic to compare values
					if(!currentCell1.toString().equalsIgnoreCase(currentCell2.toString())){

						KeywordUtil.logInfo("####### "+exp.getRow(0).getCell(currentCell1.getColumnIndex()).getStringCellValue().toUpperCase()+" in EXPECTED SHEET is: "+currentCell1.toString()+" but in ACTUAL SHEET is: "
								+currentCell2.toString()+" #######");
						//WebUI.verifyMatch(currentCell1.toString(), currentCell2.toString(), false, FailureHandling.STOP_ON_FAILURE)
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}

	}


	/**
	 * 
	 * @return
	 */
	@Keyword
	static String getTimeStamp() {
		final SimpleDateFormat sdf = new
				SimpleDateFormat("yyyyMMdd_HHmmss");
		Timestamp tm = new Timestamp(System.currentTimeMillis());
		String timestamp=sdf.format(tm);
		println timestamp
		return timestamp;
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
	static List<String> getTestDataForGivenColumn(String fileFullPath, String sheetName, String dataTableName, String columnHeader){

		//String fileFullPath = RunConfiguration.getProjectDir()+fileRelPath
		ExcelInit util = new ExcelInit(fileFullPath, sheetName);
		util.setDataTableName(dataTableName);
		System.out.println(util.getDataTableName());
		util.setColumnHeader(columnHeader);
		System.out.println(util.getColumnHeader());
		System.out.println(util.getSheetObj());


		// get List of rows from the datatable
		List<Row> rowList = getRowsInsideTableTag(util.getSheetObj(), util.getDataTableName());



		return(getAllValueForColumn(rowList, util.getColumnHeader()));
	}

	private static List<String> getUnsetkeys(List<String> colVal){
		List<String> uniqKeyList = new ArrayList();

		for(int i=0; i< colVal.size(); i++){

			if(uniqKeyList.size()==0){
				return null;
			}
			for(int j=0; j<uniqKeyList.size(); j++){
				//

			}
		}

		return uniqKeyList;
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


	private static void setRowStartNum(rowStart){
		this.rowStartNum = rowStart;
	}

	private static void setRowEndNum(rowEnd){
		this.rowEndNum = rowEnd;
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
			columns.add(myRow.getCell(headerColNum).toString());
			//KeywordUtil.logInfo(" " +myRow.getCell(headerColNum).toString()+" ");
		}

		return columns;
	}




	//TODO:
	/**
	 * 1. Evaluate Complete data of the excel
	 * 2. Add Sheet level data validation
	 * 3. 
	 * 
	 * 
	 */

	public static void/*List<Map<String,String>>*/ getCellValuesOfRow(String filePath, String fileName, String sheet, String dataTableName){
		String fileFullPath = RunConfiguration.getProjectDir()+filePath+fileName
		ExcelInit util = new ExcelInit(fileFullPath, sheet);
		List<String> cellValues = null;
		int dataTableCount = 0;
		boolean flag = false;
		int rowCnt = 0;
		int tagCnt = 0;
		int rowStart = 0;
		int rowEnd = 0;
		List<Row> rowList = new ArrayList<Row>();
		Map<String,String> cellValue = new HashMap();
		Iterator<XSSFRow> rows = util.getSheetObj().iterator();


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


		//util.getSheetObj().getRow(rowStart-1).getCell(3).getStringCellValue()


		int firstCell = rowList.get(0).getFirstCellNum();
		rowList.get(0).getFirstCellNum().
				println firstCell;

	}





}

