package guru.qa;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class XlsTest {
    @Test
    void simpleXlsTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("xls_school.xlsx");
        XLS xlsFile = new XLS(stream);
        System.out.println(xlsFile.excel);
        String stringCellValue = xlsFile.excel.getSheetAt(1).getRow(2).getCell(1).getStringCellValue();
        System.out.println(stringCellValue);

    }

    @CsvFileSource(resources = "table.csv")
    @ParameterizedTest
    void csvTest(String firstArg, String secondArg) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("table.csv");
        List<String[]> result;
        try (CSVReader reader = new CSVReader(new InputStreamReader(stream))) {
            result = reader.readAll();
        }
        assertThat(result).contains(
                new String[]{"title", "author"},
                new String[]{"Война и мир", "Толстой"}
        );
    }
}
