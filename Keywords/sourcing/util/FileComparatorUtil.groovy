package sourcing.util

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

public class FileComparatorUtil {


	private static int rowStartNum;
	private static int rowEndNum;
	static List<String> errorVal = new ArrayList<String>();


	/**
	 * 
	 * @param expFile
	 * @param actFile
	 * @param sheetName
	 * @return
	 */
	@Keyword
	public static boolean validateExcelSheets(String expFile, String actFile, String sheetName){

		boolean matching = true;
		errorVal.clear();

		String usrDir = RunConfiguration.getProjectDir();
		String expFilePath = usrDir + "/Test Files/dms/ExcelValidations/" + expFile
		String actFilePath = /*System.getProperty("user.home")*/ usrDir + "/Downloads/" + actFile

		Object expObj = null;
		Object actObj = null;


		expObj = getExcelSheetObject(expFile, expFilePath, sheetName);
		actObj = getExcelSheetObject(actFile, actFilePath, sheetName);


		if(expObj == null || actObj == null){

			KeywordUtil.logInfo("ALERT...Input sheet "+ sheetName.toUpperCase()+ " is not present in the file.  !");
			return matching = false;
		}


		if(expObj instanceof XSSFSheet && actObj instanceof XSSFSheet){

			if(expObj.getLastRowNum() != actObj.getLastRowNum()){
				KeywordUtil.logInfo("Sheet total rows wont match!! , for the sheet "+
						expObj.getSheetName().toUpperCase()+" the expected row count is "+ expObj.getLastRowNum()+
						", whereas the actual total row count is "+ actObj.getLastRowNum()+"." );
				return false;
			}

			return compareXSSFSheets(expObj, actObj);

		}else if(expObj instanceof HSSFSheet && actObj instanceof HSSFSheet){

			if(expObj.getLastRowNum() != actObj.getLastRowNum()){

				setDescripencyValues("Sheet total rows wont match!! , for the sheet "+
						expObj.getSheetName().toUpperCase()+" the expected row count is "+ expObj.getLastRowNum()+
						", whereas the actual total row count is "+ actObj.getLastRowNum()+"." );
				

			}
			/*return*/ compareHSSFSheets(expObj, actObj);

		} else{
			KeywordUtil.logInfo("Invalid Excel Format exception!");
			throw new IllegalFormatException("Invalid Excel Format exception!");
		}

		if(errorVal.size()>0){
			matching = false;

			for(int i=0; i< errorVal.size(); i++){
				KeywordUtil.logInfo(errorVal.get(i));
			}

		}

		return matching;
	}


	/**
	 * 
	 * @param expFile
	 * @param actFile
	 * @return
	 */
	public static boolean validateAllSheetsInFile(String expFile, String actFile){

		List<HSSFSheet> expSheetList = new ArrayList<>();
		List<HSSFSheet> actSheetList = new ArrayList<>();
		
		List<String> expSheetNames = new ArrayList<>();
		List<String> actSheetNames = new ArrayList<>();
		
		boolean flag = true;

		errorVal.clear();

		String usrDir = RunConfiguration.getProjectDir();
		String expFilePath = usrDir + "/Test Files/dms/ExcelValidations/" + expFile
		String actFilePath = /*System.getProperty("user.home")*/ usrDir + "/Downloads/" + actFile


		FileInputStream fisExp = new FileInputStream(expFilePath);
		HSSFWorkbook expWB = new HSSFWorkbook(fisExp);
		int expCnt = expWB.getNumberOfSheets();

		FileInputStream fisAct = new FileInputStream(actFilePath);
		HSSFWorkbook actWB = new HSSFWorkbook(fisAct);
		int actCnt = actWB.getNumberOfSheets();

		
		
		for(int i=0; i<expWB.getNumberOfSheets(); i++){
			expSheetList.add(expWB.getSheetAt(i));
			//actSheetList.add(actWB.getSheetAt(i));
		}

		for(int i=0; i<actWB.getNumberOfSheets(); i++){
			//expSheetList.add(expWB.getSheetAt(i));
			actSheetList.add(actWB.getSheetAt(i));
		}

		for(int i=0; i<expWB.getNumberOfSheets(); i++){
			expSheetNames.add(expWB.getSheetAt(i).getSheetName());
			//actSheetNames.add(actWB.getSheetAt(i).getSheetName());
		}

		for(int i=0; i<actWB.getNumberOfSheets(); i++){
			//expSheetNames.add(expWB.getSheetAt(i).getSheetName());
			actSheetNames.add(actWB.getSheetAt(i).getSheetName());
		}
		
		Collections.sort(expSheetNames);
		Collections.sort(actSheetNames);
		
		
		if(expCnt != actCnt){
			KeywordUtil.logInfo("Sheet count mismacth!!... Actual Sheet Count is : "+ actCnt+ " but the expected sheet count is : "+ expCnt);
			KeywordUtil.logInfo("There are some missing sheets, namely: "+getMissingSheetsList(expSheetNames, actSheetNames).toString()+" , please check sheets again.. ");
			return false;
		}
		

		for(int i=0; i< expSheetList.size(); i++){
			
			
			if(!(expSheetList.get(i).getSheetName().equalsIgnoreCase(actSheetList.get(i).getSheetName()))){
				KeywordUtil.logInfo("one of the Sheets wont match !!! Expected sheets: "+ expSheetList.toString()+
						" and actual sheets: "+ actSheetList);
				return false;
			}

			HSSFSheet expSheet = expWB.getSheet(expSheetNames.get(i));
			HSSFSheet actSheet = actWB.getSheet(actSheetNames.get(i));


			if(expSheet.getLastRowNum() != actSheet.getLastRowNum()){

				setDescripencyValues("Sheet total rows wont match!! , for the sheet "+
						expSheet.getSheetName().toUpperCase()+" the expected row count is "+ expSheet.getLastRowNum()+
						", whereas the actual total row count is "+ actSheet.getLastRowNum()+"." );

				continue;
			}



			compareHSSFSheets(expSheet, actSheet);

		}


		if(errorVal.size()>0){
			flag = false;

			for(int i=0; i< errorVal.size(); i++){
				KeywordUtil.logInfo(errorVal.get(i));
			}

		}

		return flag;
	}

	
	private static List<String> getMissingSheetsList(List<String> expSheets, List<String> actSheets){
		
		if(expSheets.size() == actSheets.size()){
			return null;
		} else if(expSheets.size() > actSheets.size()){
			return getMissingSheets(expSheets,actSheets);
		} else (expSheets.size() < actSheets.size()){
			return getMissingSheets(actSheets,expSheets);
		}
	}
	
	private static List<String> getMissingSheets(List<String> moreSheets, List<String> lessSheets){
		
		List<String> missingSheets = new ArrayList<String>();
		for(int i=0; i< moreSheets.size(); i++){
			if(!(lessSheets.contains(moreSheets.get(i)))){
				missingSheets.add(moreSheets.get(i));
			}
		}
		return missingSheets;
	}
	

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
		if(workbook.getSheet(sheetName) == null){
			return null;
		}
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

	static String getFileName(String fileFullPath){
		String[] items = fileFullPath.split("/");
		int itemCount = items.length;
		return items[itemCount-1].trim();
	}

	/**
	 * 
	 * @param exp
	 * @param act
	 * @return
	 */
	static boolean compareXSSFSheets(XSSFSheet exp, XSSFSheet act){
		boolean matching = true;

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
					String expVal = currentCell1.toString().toUpperCase().equals("") ? "Blank" : currentCell1.toString().toUpperCase();
					String actVal = currentCell2.toString().toUpperCase().equals("") ? "Blank" : currentCell2.toString().toUpperCase();

					if(!convertNumericToStringCellValue(currentCell1).equalsIgnoreCase(convertNumericToStringCellValue(currentCell2))){
						String headerCol = exp.getRow(0).getCell(currentCell1.getColumnIndex()).toString();
						KeywordUtil.logInfo("####### The evaluated sheet is "+exp.getSheetName().toUpperCase()+
								" and value in the EXPECTED SHEET for the header "+headerCol.toUpperCase()+" is : "+
								expVal+" but the value in ACTUAL SHEET is: "
								+actVal+" #######");
						matching = false;
						break;
					}

				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return matching;
	}


	/**
	 * 
	 * @param exp
	 * @param act
	 * @return
	 */
	static void compareHSSFSheets(HSSFSheet exp, HSSFSheet act){
		boolean matching = true;

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
					if(!convertNumericToStringCellValue(currentCell1).equalsIgnoreCase(convertNumericToStringCellValue(currentCell2))){
						String headerCol = exp.getRow(0).getCell(currentCell1.getColumnIndex()).toString();

						String expVal = currentCell1.toString().toUpperCase().equals("") ? "Blank" : currentCell1.toString().toUpperCase();
						String actVal = currentCell2.toString().toUpperCase().equals("") ? "Blank" : currentCell2.toString().toUpperCase();

						setDescripencyValues("SHEET: "+exp.getSheetName()+" HEADER: "+headerCol.toUpperCase()+" EXPECTED: "+expVal+ " ACTUAL: "+actVal);
						//break;
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}


		//return matching;

	}

	private static void setDescripencyValues(String cell){
		errorVal.add(cell);
	}

	private static String convertNumericToStringCellValue(Cell cell){

		if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getStringCellValue();
		}else{

			return cell;
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


}

