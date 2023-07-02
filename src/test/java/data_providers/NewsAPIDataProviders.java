package data_providers;

import org.testng.annotations.DataProvider;
import utils.ExcelData;

import java.io.File;

public class NewsAPIDataProviders {

    private ExcelData excelData;
    private String dataFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test_data.xlsx";

    @DataProvider(name = "testKeywordInTitle")
    public Object[][] dpTestKeywordInTitle() {
        excelData = new ExcelData(dataFilePath);

        return excelData.readStringArrays("testKeywordInTitle");
    }

}
