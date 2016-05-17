/**
 * 
 */
package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Arushi
 *
 */
public class Parser {
	 private ArrayList<ArrayList<String>>	array;
	String file_name;
	private static final String DELIMITER = ",";
	
	private static final String NEW_LINE_SEPARATOR = "\n";

	

	public void ensureCapacity(int num)
	{
		getArray().ensureCapacity(num);
	}
 
	/**
	 * Ensures that the given row has at least the given capacity. Note that
	 * this method will also ensure that getNumRows() >= row
	 * 
	 * @param row
	 * @param num
	 */
	public void ensureCapacity(int row, int num)
	{
		ensureCapacity(row);
		while (row < getNumRows())
		{
			getArray().add(new ArrayList<String>());
		}
		getArray().get(row).ensureCapacity(num);
	}
 
	/**
	 * Adds an item at the end of the specified row. This will guarantee that at least row rows exist.
	 */
	public void Add(String data, int row)
	{
		ensureCapacity(row);
		while(row >= getNumRows())
		{
			getArray().add(new ArrayList<String>());
		}
		getArray().get(row).add(data);
	}
 
	public String get(int row, int col)
	{
		return getArray().get(row).get(col);
	}
 
	public void set(int row, int col, String data)
	{
		//System.out.println("array size"+array.get(row).size());
		getArray().get(row).set(col,data);
	}
 
	public void remove(int row, int col)
	{
		getArray().get(row).remove(col);
	}
 
	public boolean contains(String data)
	{
		for (int i = 0; i < getArray().size(); i++)
		{
			if (getArray().get(i).contains(data))
			{
				return true;
			}
		}
		return false;
	}
 
	public int getNumRows()
	{
		return getArray().size();
	}
 
	public int getNumCols(int row)
	{
		return getArray().get(row).size();
	}
	public int getNumCols()
	{   int max_colums=0;
	   for(int i=0;i<getNumRows();i++)
		   if(max_colums<=getArray().get(i).size())max_colums=getArray().get(i).size();
		return max_colums;
	}
	
	public static void main(String args[]){
		Parser obj=new Parser("registration.csv");
		System.out.println(obj.getNumCols());
	}
	
	public void printList(){
		int rows=getNumRows();
		
		for(int i=0;i<rows;i++){
		for(int j=0;j<getNumCols(i);j++){
			System.out.println(get(i,j));
		}
		System.out.println("\n");
		}
	}
	
	public int checkAndAddColumn(String colName) throws IOException{
		
		for(int i=0;i<getNumCols(0);i++){
			if(colName.equals(get(0,i))){
				return i;
			}
			
		}
		Add(colName,0);
		
		write(file_name,-1);
			return 0;
	}
	
	public Parser(String csvfile) {
		int r;
		setArray(new ArrayList<ArrayList<String>>());
		
		file_name=csvfile;
		BufferedReader br = null;
		String line = "";

		try {
             
			try {
				br = new BufferedReader(new FileReader(file_name));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			r=0;
			
			try {
				while ((line = br.readLine()) != null) {

					String[] arr = line.split(DELIMITER);
					
					for(int i=0;i<arr.length;i++){
						Add(arr[i],r);
					
					}
					r++;
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	  }
	public void updateCSV(String fileToUpdate, String replace,int r, int c) throws IOException {

		set(r,c,replace);
		write(file_name,-1);
		
		
		}
	//TODO incomplete code
	public void removeBlankColumn(){
		for(int i=0;i<getNumCols(2);i++){
			for(int j=0;j<getNumRows();j++){
				System.out.println(get(j,i));
				/*if(get(j,i).equals("")){
					count++;
				}
				if(count==getNumRows()){
					write("c1.csv",i);
					System.out.println("removed");
				}*/
			}
		}
	}
	
	public int EmptyRows(){
		int count=0;int empty=0;
		for(int i=0;i<getNumRows();i++){
			for(int j=0;j<getNumCols(i);j++){
				if(get(i,j).equals("")){count++;}
				
			}
			if(count==getNumCols(i)){empty++;}
		}
		return empty;
	}
	
	public void removeColumn(String colName){
		int c=-1;
		for(int i=0;i<getNumCols(2);i++){
			if(colName.equals(get(2,i))){
				System.out.println("found");
				c=i;break;
			}
			//System.out.println(get(2,i));
			
		}
		write(file_name,c);
	}
	public void write(String filename,int c){
		FileWriter fileWriter = null;

	    try{
	    	
	    	fileWriter = new FileWriter(filename);
	    	if(c==-1){
	    	for(int i=0;i<getNumRows();i++){
	    		for(int j=0;j<getNumCols(i);j++){
	    			//System.out.println(get(i,j));
	    			fileWriter.append(get(i,j));
	    			fileWriter.append(DELIMITER);
	    		}
	    		//fileWriter.append(get(i,getNumCols(i)));
	    		fileWriter.append(NEW_LINE_SEPARATOR);

	    	}
	    	 System.out.println("CSV file was created successfully !!!");
	    	}
	    	else{
	    		for(int i=0;i<getNumRows();i++){
		    		for(int j=0;j<getNumCols(i);j++){
		    			if(j==c){continue;}
		    			else{
		    			fileWriter.append(get(i,j));
		    			fileWriter.append(DELIMITER);}
		    		}
		    		//fileWriter.append(get(i,getNumCols(i)));
		    		fileWriter.append(NEW_LINE_SEPARATOR);

		    	}
		    	 System.out.println("CSV file was created successfully !!!");
	    	}
	    	
	    }
	    catch (Exception e) {
	    	
	    	            System.out.println("Error in CsvFileWriter !!!");
	    	
	    	            e.printStackTrace();
	    	
	    	        } 
	    finally {
	    	            try {
	    	            	fileWriter.flush();
	    	            	fileWriter.close();
	    	            } catch (IOException e) {
	    	            	System.out.println("Error while flushing/closing fileWriter !!!");
	    	
	    	                e.printStackTrace();
	    	
	    	            }
	    	    }
}

	/**
	 * @return the array
	 */
	public ArrayList<ArrayList<String>> getArray() {
		return array;
	}

	/**
	 * @param array the array to set
	 */
	public void setArray(ArrayList<ArrayList<String>> array) {
		this.array = array;
	}
	
	
}
