package sourcing.util2

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Color

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

import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFColor
import org.apache.poi.xssf.usermodel.XSSFFont
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

public class ExcelToggleParamInit {

	private String filePath;
	private XSSFWorkbook workBook;
	private XSSFSheet toggleSheet;
	private XSSFSheet paramSheet;

	public ExcelToggleParamInit(String filePath, String toggleSheet, String paramSheet) {
		this.filePath = filePath;

		if (!(null == filePath)) {
			FileInputStream fis = new FileInputStream(filePath);
			this.workBook = new XSSFWorkbook(fis);
		}

		if (!(null == toggleSheet)) {
			//this.toggleSheet = workBook.getSheet(toggleSheet);
			this.toggleSheet = getSheet(toggleSheet);
		}

		if (!(null == paramSheet)) {
			this.paramSheet = workBook.getSheet(paramSheet);
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public XSSFWorkbook getWorkbook() {
		return workBook;
	}

	public XSSFSheet getSheet(String sheetName) {

		if (sheetName.equalsIgnoreCase("Toggle") || sheetName.equalsIgnoreCase("Toggles")) {

			if (!(workBook.getSheet("Toggles") == null)) {
				this.toggleSheet = workBook.getSheet("Toggles");
				if (toggleSheet.getLastRowNum() == 0) {
					setToggleHeader();
				}
				return toggleSheet;
			} else {
				this.toggleSheet = workBook.createSheet("Toggles");
				setToggleHeader();
				return toggleSheet;
			}
		} else if (sheetName.equalsIgnoreCase("Param") || sheetName.equalsIgnoreCase("params")
		|| sheetName.equalsIgnoreCase("parameters")) {

			if (!(workBook.getSheet("Parameters") == null)) {
				this.paramSheet = workBook.getSheet("Parameters");
				if(paramSheet.getLastRowNum() == 0){
					setParameterHeader();
				}
				return paramSheet;
			} else {
				this.paramSheet = workBook.createSheet("Parameters");
				setParameterHeader();
				return paramSheet;
			}
		} else {
			return null;
		}
	}

	private void setToggleHeader(){

		XSSFCell headerCell= this.toggleSheet.createRow(0).createCell(0);
		headerCell.setCellValue("TOGGLES");
		setCellFormatting(headerCell);
	}

	private void setParameterHeader(){
		XSSFCell headerCell = this.paramSheet.createRow(0).createCell(0);
		headerCell.setCellValue("PARAMETERS");
		setCellFormatting(headerCell);
	}

	private void setCellFormatting(XSSFCell cell){
		XSSFCellStyle style = getWorkbook().createCellStyle();
		XSSFFont font = getWorkbook().createFont();
		XSSFColor myColor = new XSSFColor(Color.yellow);
		//font.setFontName(XSSFFont.DEFAULT_FONT_COLOR);
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		style.setFont(font);
		style.setWrapText(true);
		style.setFillBackgroundColor(myColor);
		//style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		//setCellFormatting(cell);
		cell.setCellStyle(style);
	}
}
