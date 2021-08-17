package Login;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
    Workbook wb;

		public Excel(String pathWithFileName)  {
			try {
			if(pathWithFileName.endsWith(".xls"))
			{
		
				 wb= new HSSFWorkbook(new FileInputStream(pathWithFileName));
		
			} else if(pathWithFileName.endsWith(".xlsx")) {
				 wb= new XSSFWorkbook(new FileInputStream(pathWithFileName));
			}
			}
			catch(Exception E) {
				System.out.println("Error with file reading "+ E.getMessage());
				}
			
		}
			public String readData(String sheetName,int row,int col) {
				String data=wb.getSheet(sheetName).getRow(row).getCell(col).toString();
		return data;
		
			}
			public int getLastRowNum(String sheetName) {
				return wb.getSheet(sheetName).getLastRowNum();
			}
			
			public  void writeDataLogin(String sheetName, int row, int col, String value) throws Exception {
				wb.getSheet(sheetName).getRow(row).createCell(col).setCellValue(value);
				wb.write(new FileOutputStream("C:\\Users\\jainm\\OneDrive\\Desktop\\eclipse\\sele\\chromedriver.exe"));
			}
}
