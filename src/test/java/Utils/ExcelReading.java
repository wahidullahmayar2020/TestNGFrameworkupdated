package Utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReading {
    static Workbook book;
    static Sheet sheet;

    public static void openExcel(String filepath) {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            book = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void getSheet(String sheetName) {

        sheet = book.getSheet(sheetName);
    }

    public static int getRowcount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public static int getColscount(int rowIndex) {

        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    public static String getCellData(int rowIndex, int colIndex) {
        return sheet.getRow(rowIndex).getCell(colIndex).toString();

    }
    public static List<Map<String, String>> excelIntoListMap(String filepath, String sheetName){
        openExcel(filepath);
        getSheet(sheetName);

        List<Map<String, String>> ListData=new ArrayList<>();
        //outer loop
        for (int row=1;row<getRowcount();row++){
            //creating a map for every row.
            Map<String, String> map=new LinkedHashMap<>();
            for(int col=0;col<getColscount(row);col++){
                map.put(getCellData(0,col),getCellData(row,col));
            }
            ListData.add(map);
        }
return ListData;
    }

}
