package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Arrays;

public class dataProviders {

    @DataProvider(name= "Data")
    public Object[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir")+ "//testData//userData.xlsx";
        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet1");
        System.out.println("check row count: " + rowNum);
        var colCount = xl.getCellCount("Sheet1", 1);
        System.out.println("check cell count: " + colCount);
        String apiData[][] = new String[rowNum][colCount];

        for (int i = 1; i<= rowNum; i++) {

            for (int j=0; j< colCount; j++) {
                apiData[i-1][j] = xl.getCellData("Sheet1", i, j);
                System.out.println("Data at row " + i + ", column " + j + ": " + apiData[i - 1][j]);
            }
        }

        return apiData;
    }

    @DataProvider(name= "DataOne")
    public Object[][] getOneData() throws IOException {
        String path = System.getProperty("user.dir")+ "//testData//userOneData.xlsx";
        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet1");
        System.out.println("check row count: " + rowNum);
        var colCount = xl.getCellCount("Sheet1", 1);
        System.out.println("check cell count: " + colCount);
        Object[][] apiData = new String[rowNum][colCount];

        for (int i = 1; i<= rowNum; i++) {

            for (int j=0; j< colCount; j++) {
                apiData[i-1][j] = xl.getVaryCellData("Sheet1", i, j);
                System.out.println("Data at row " + i + ", column " + j + ": " + apiData[i - 1][j]);
            }
        }
        System.out.println("check data: "+ Arrays.deepToString(apiData));
        return apiData;
    }

    @DataProvider(name= "Member")
    public Object[][] getMember() throws IOException {
        String path = System.getProperty("user.dir")+ "//testData//userOneData.xlsx";
        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet2");
        System.out.println("check row count: " + rowNum);
        var colCount = xl.getCellCount("Sheet2", 1);
        System.out.println("check cell count: " + colCount);
        Object[][] apiData = new String[rowNum][colCount];

        for (int i = 1; i<= rowNum; i++) {

            for (int j=0; j< colCount; j++) {
                apiData[i-1][j] = xl.getVaryCellData("Sheet2", i, j);
                System.out.println("Data at row " + i + ", column " + j + ": " + apiData[i - 1][j]);
            }
        }
        System.out.println("check data: "+ Arrays.deepToString(apiData));
        return apiData;
    }

}
