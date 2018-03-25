package com.utilities;

        	import java.io.FileInputStream;

            import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;

        	import org.apache.poi.xssf.usermodel.XSSFRow;

        	import org.apache.poi.xssf.usermodel.XSSFSheet;

        	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

    public class ExcelLib {

        		private static XSSFSheet ExcelWSheet;

        		private static XSSFWorkbook ExcelWBook;

        		private static XSSFCell Cell;

        		private static XSSFRow Row;
        		
        		private static DataFormatter fmt = new DataFormatter();



    		public static void setExcelFile(String Path,String SheetName) throws Exception {

       			try {


					FileInputStream ExcelFile = new FileInputStream(Path);

					// Access the required test data sheet

					ExcelWBook = new XSSFWorkbook(ExcelFile);

					ExcelWSheet = ExcelWBook.getSheet(SheetName);

					} catch (Exception e){

						throw (e);

					}

			}

    	    public static String getCellData(int RowNum, int ColNum) throws Exception{

       			try{

          			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

          			String CellData = Cell.getStringCellValue();

          			return CellData;

          			}catch (Exception e){

						return"";

          			}

		    }

    		public static void setCellData(String tc_id, String Result) throws Exception	{

       			try{

       				int count = ExcelWSheet.getPhysicalNumberOfRows();
       				
       				for(int i=1;i<=count;i++){
       					
       					Row  = ExcelWSheet.getRow(i);
       					
       					Cell = ExcelWSheet.getRow(i).getCell(0);

              			String CellData = fmt.formatCellValue(Cell);
              			
              			if(CellData.equals(tc_id)){
              				
              				Cell = Row.getCell(3, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
              				if (Cell == null) {
              					 
        						Cell = Row.createCell(3);
         
        						Cell.setCellValue(Result);
        						break;
         
        						} else {
         
        							Cell.setCellValue(Result);
        							break;
         
        						}
              			}
       				}
          			
          				FileOutputStream fileOut = new FileOutputStream(".\\src\\testdata\\Book1.xlsx");

          				ExcelWBook.write(fileOut);

          				fileOut.flush();

 						fileOut.close();

						}catch(Exception e){

							throw (e);
					}
				}
	}