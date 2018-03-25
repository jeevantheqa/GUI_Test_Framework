package com.utilities;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVUtils {

    private static final char DEFAULT_SEPARATOR = ',';
	private static String dataRow;
	private static BufferedReader CSVFile;

    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    //https://tools.ietf.org/html/rfc4180
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }
    
    public static String[][] readCSV(String filePath) throws Exception{
    	try{
    		 
    		 CSVFile =  new BufferedReader(new FileReader(filePath));
    		
    		dataRow = CSVFile.readLine();
    		int iRow = 0;

    		String[][] testData = new String[1][dataRow.split(",").length];
    		dataRow = CSVFile.readLine();

    		while (dataRow != null) {
    			String[] dataArray = dataRow.split(",");
    			if (dataArray.length == 0)
    				break;

    			iRow++;
    			String[][] newData = new String[iRow][dataRow.split(",").length];
    			System.arraycopy(testData, 0, newData, 0, iRow - 1);
    			testData = newData;

    			for (int iCol = 0; iCol < dataArray.length; iCol++) {
    				testData[iRow - 1][iCol] = dataArray[iCol];
    			}

    			dataRow = CSVFile.readLine();
    		}

    		return testData;
    		
    		
    	}catch(Exception e){
    		throw(e);
    	}
    	
    }


}