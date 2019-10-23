package vm.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vm.utility.ReadFile;

public class SearchFile {
	
	private String folderPath;
	private String stringToSearch;
	
	SearchFile(String folderPath, String stringToSearch){
		this.folderPath= folderPath;
		this.stringToSearch = stringToSearch;
	}
	
	public List<File> checkFolder(){
		List<File> listOfFiles = new ArrayList<File>();
		File folder = new File(folderPath);
		File[] lof = folder.listFiles();
		listOfFiles =  Arrays.asList(lof);
	
		return listOfFiles;
		
	}
	
	public void searchString() throws IOException{
		
		List<File> fileList = new ArrayList<File>();
		ArrayList<String> filedata = new ArrayList<String>();
		ArrayList<Integer> lineList = new ArrayList<Integer>();
		HashMap<String,ArrayList<Integer>> outputMap = new HashMap<String,ArrayList<Integer>>();
		
		ReadFile fileToRead = new ReadFile();
		
		fileList = checkFolder();
		Integer lineNumber=0;
		
		for(File file : fileList){
			String filePath = file.getPath();
			String fileName = file.getName();
			filedata= fileToRead.textData(filePath);
			
			for(String lineItem : filedata){
				boolean val = lineItem.contains(stringToSearch);
				lineNumber++;
				
				if(val){
					lineList.add(lineNumber);
					outputMap.put(fileName, lineList);
				}
			}
			lineNumber=0;
			for(Map.Entry m : outputMap.entrySet()){
				System.out.println(m.getKey()+" : "+m.getValue());
			}
		}
	}
	
	public static void main (String args[]){
		SearchFile sf = new SearchFile("C:\\Users\\ashish.k.chawla\\Downloads\\testfolder","sda");
		try {
			sf.searchString();
			//sf.checkFolder();
		} catch (IOException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
