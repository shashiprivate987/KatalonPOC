package sourcing.util2

import java.sql.Timestamp
import java.text.SimpleDateFormat
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class DmsUtilsDummy {


	private static int rowStartNum;
	private static int rowEndNum;


	static String getFileName(String fileFullPath){

		String[] items = fileFullPath.split("/");
		int itemCount = items.length;

		return items[itemCount-1].trim();
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
	static List<String> getTestDataForGivenColumn(String fileRelPath,String fileName, String sheetName, String dataTableName, String columnHeader){

		String fileFullPath = RunConfiguration.getProjectDir()+fileRelPath
		ExcelInit util = new ExcelInit(fileFullPath, fileName, sheetName);
		util.setDataTableName(dataTableName);
		System.out.println(util.getDataTableName());
		util.setColumnHeader(columnHeader);
		System.out.println(util.getColumnHeader());
		System.out.println(util.getSheetObj());


		// get List of rows from the datatable
		List<Row> rowList = getRowsInsideTableTag(util.getSheetObj(), util.getDataTableName());

		return(getAllValueForColumn(rowList, util.getColumnHeader()));
	}


	@Keyword
	static List<String> getTestDataForGivenColumn1(String fileRelPath,String fileName, String sheetName, String dataTableName, String columnHeader){

		String fileFullPath = RunConfiguration.getProjectDir()+fileRelPath
		ExcelInit util = new ExcelInit(fileFullPath, fileName, sheetName);
		util.setDataTableName(dataTableName);
		System.out.println(util.getDataTableName());
		util.setColumnHeader(columnHeader);
		System.out.println(util.getColumnHeader());
		System.out.println(util.getSheetObj());


		// get List of rows from the datatable
		List<Row> rowList = getRowsInsideTableTag(util.getSheetObj(), util.getDataTableName());

		// Get List of Columns for a particular column Header inside a tag
		List<String> colVal = getAllValueForColumn(rowList, util.getColumnHeader());
		// Iterate values against the Keyset values which are already existing keys, add the key if it does not exist in the keyset
		List<String> unSetKeys = getUnsetkeys(colVal);

		return(getAllValueForColumn(rowList, util.getColumnHeader()));
	}

	List<String> keyList = new ArrayList();

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
		/*Iterator<Cell>  headItr = headerRow.iterator();
		 while (headItr.hasNext()){
		 headerCol.add(headItr.next().toString());
		 }*/

		// based on the column header param, iterate all the columns under it
		int startCells = headerRow.getFirstCellNum();
		int EndCells = headerRow.getLastCellNum();

		for(int i=startCells; i< EndCells; i++){

			if(headerRow.getCell(i).toString().trim().equalsIgnoreCase(columnHeader.trim())){
				//headerColNum = i ;
				headerColNum = headerRow.getCell(i).getColumnIndex();
				break;
			}

		}

		// Iterate the Row List and get columns values under a particular column header
		KeywordUtil.logInfo("DATA FETCHED: ");
		for(int i = 1 ; i < rowList.size(); i++){
			Row myRow = rowList.get(i);
			columns.add(myRow.getCell(headerColNum).toString());
			//KeywordUtil.logInfo(" " +myRow.getCell(headerColNum).toString()+" ");
		}



		// Get all columns and iterate against the Keyset array list

		uniqueValuesRegistry(columnHeader, columns);


		return columns;
	}


	static Map<String,List<String>> keyRegistryMap = new HashMap<String,List<String>>();
	private static Map<String,List<String>> uniqueValuesRegistry(String columnHeader, List<String> columnVals){

		boolean flag = false;



		// For the matching column header, iterate each Map Key, iterate the values

		for(Map.Entry<String, List<String>> entry: keyRegistryMap.entrySet()){

			if(entry.getKey().equalsIgnoreCase(columnHeader)){

				List<String> values = entry.getValue();

				// Iterate each colmn Values with the existing registry and add the value only if it is not present
				// return the values which are added into the registry


			}else{
				// print header not found
			}
		}

		return
	}


	//TODO:
	/**
	 * 1. Evaluate Complete data of the excel
	 * 2. Add Sheet level data validation
	 * 3. 
	 * 
	 * 
	 */






}

