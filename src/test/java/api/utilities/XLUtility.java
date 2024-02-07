package api.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.apache.poi.ss.util.CellUtil.getRow;

public class XLUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFCell cell;
    public XSSFRow row;
    public CellStyle style;
     String path;

     public XLUtility(String path) {
         this.path =path;
     }

     public int getRowCount(String sheetName) throws IOException {
         fi = new FileInputStream(path);
         workbook = new XSSFWorkbook(fi);
         sheet = workbook.getSheet(sheetName);
         int rowCount = sheet.getLastRowNum();
         workbook.close();
         fi.close();
         return rowCount;
     }

     public int getCellCount(String sheetName, int i) throws IOException {
         fi = new FileInputStream(path);
         workbook = new XSSFWorkbook(fi);
         sheet = workbook.getSheet(sheetName);
         Row row1 = getRow(i, sheet);
         if (row1 == null) {
             workbook.close();
             fi.close();
             throw new RuntimeException("Row at index " + i + " not found in sheet " + sheetName);
         }
         int cellCount = row1.getLastCellNum();
         workbook.close();
         fi.close();
         return cellCount;
     }

     public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
         fi = new FileInputStream(path);
         workbook = new XSSFWorkbook(fi);
         sheet = workbook.getSheet(sheetName);
         row = sheet.getRow(rowNum);
         cell = row.getCell(colNum);

         DataFormatter formatter = new DataFormatter();
         String data;
         try {
             data = formatter.formatCellValue(cell);
         }
         catch (Exception e) {
             data = "";
         }
         workbook.close();
         fi.close();
         return data;
     }

    public Object getVaryCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        Object data = getCellValue(cell);

        workbook.close();
        fi.close();

        return data;
    }

    private Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            case ERROR:
            default:
                return null;
        }
    }

}
