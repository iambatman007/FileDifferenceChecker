package vm.files;

import java.io.IOException;
import java.util.ArrayList;

import vm.utility.ReadFile;


public class CompareFile {
	
	private String originalFile;
	private String updatedFile;
	private Integer originalFileLines; 
	private Integer updatedFileLines;
	
	ArrayList<String> originalFileData = new ArrayList<String>();
	ArrayList<String> updatedFileData = new ArrayList<String>();
	int i=0;
	int g=0;
	int start=0;
	Boolean differFlag=false;
	
	CompareFile(String originalFile, String updatedFile){
		this.originalFile = originalFile;
		this.updatedFile = updatedFile;
	}
	
	public void compare() {
		
		ReadFile fileToRead = new ReadFile();
		
		
		try {
			originalFileData= fileToRead.textData(originalFile);
			updatedFileData= fileToRead.textData(updatedFile);
			
			originalFileLines = fileToRead.countLines(originalFile);
			updatedFileLines = fileToRead.countLines(updatedFile);
			
		System.out.println("originalFileLines "+originalFileLines);
		System.out.println("updatedFileLines "+updatedFileLines);
		
		if (originalFileLines > updatedFileLines) {
			System.out.println("Currently System is not designed for line deletion");
			}
		
		Boolean isSame=true;
		
		for(;i<originalFileLines;i++) {
			Boolean matching = originalFileData.get(i).equals(updatedFileData.get(g));
			g=g+1;
			if(!matching && (originalFileLines == updatedFileLines)) {
				isSame=false;
				inLineEditMatch(i);
				break;
			}else if(!matching && (originalFileLines < updatedFileLines)) {
				isSame=false;
				int m=g-1;
				diffLineChecker(m);
			}
		}
		if(isSame) {
			System.out.println("Both the files are same");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void diffLineChecker(int m) {
		int j;
		int k;
		int count=0;
		j = i;
			for(k=m;k<=updatedFileLines;k++) {
				Boolean matching = originalFileData.get(j).equals(updatedFileData.get(k));
				if(!matching) {
					count++;
				}
				if(!matching && !differFlag) {
					start=k+1;
					differFlag = true;
				}else if(matching) {
					//System.out.println("Value K when match:"+ k);
					if(count>1) {
						System.out.println("The new line is added from "+start+" to "+k);	
					} else {
						System.out.println("There is a change at :"+start);
					}
					break;
				}
				if(count==(updatedFileLines-j)) {
					break;	
				}
			}
			if(count==(updatedFileLines-j)) {
				System.out.println("The line is changed at **: "+start);
				//g=j+1;
			}else {
				g=k+1;
			}
			differFlag=false;
			count=0;
	}

	private void inLineEditMatch(int i) {
		for(;i<originalFileLines;i++) {
			Boolean a = originalFileData.get(i).equals(updatedFileData.get(i));
			if(!a) {
				System.out.println("The file is changed at this line :"+(i+1));
			}
		}
	}

	
	public static void main(String[] args) {
		CompareFile cmp = new CompareFile("C:\\Users\\ashish.k.chawla\\Downloads\\testdata\\fileA.txt","C:\\Users\\ashish.k.chawla\\Downloads\\testdata\\fileB.txt");
		cmp.compare();
	}
}


