package vm.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *This class only reads from text file and return the list text data in array list.  
 * @author:Ashish Chawla
 */
public class ReadFile {
	private String filePath;
	private String fileName;
	
	
	
	/*
	 * It counts the number of line in the text file
	 */
public Integer countLines(String filePath, String fileName) throws IOException {
		
		FileReader file_to_read = new FileReader(filePath);
		BufferedReader bufferRead = new BufferedReader(file_to_read);
		
		Integer numberOfLines = 0;
		
		while((bufferRead.readLine()!= null)){
			numberOfLines++;
		}
		
		System.out.println("Number of Lines in "+fileName+": "+numberOfLines);
		bufferRead.close();
		return numberOfLines;
	}

public Integer countLines(String filePath) throws IOException {
	
	FileReader file_to_read = new FileReader(filePath);
	BufferedReader bufferRead = new BufferedReader(file_to_read);
	
	Integer numberOfLines = 0;
	
	while((bufferRead.readLine()!= null)){
		numberOfLines++;
	}
	
	bufferRead.close();
	return numberOfLines;
}



/*
 * Returns the text data
 */

public ArrayList<String> textData(String filePath) throws IOException{
	
	FileReader fr = new FileReader(filePath);
	BufferedReader textReader = new BufferedReader(fr);
	int lines= countLines(filePath);
	String[] LineItem = new String[lines];
	for(int i=0; i<lines; i++){
		LineItem[i] = textReader.readLine();
	}
	ArrayList<String> OrderList = new ArrayList<String>(Arrays.asList(LineItem));
	
	return OrderList;
	
}

}