/**
 * 
 */
package actions.gradingModule;

import java.io.IOException;
import java.util.ArrayList;

import csv.Parser;
import utilities.StringFormatter;

/**
 * @author SS Verma
 *
 */
public class ImportCSV {
	Parser obj=new csv.Parser("registration.csv");
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//new AddUsers().addUser("firstname","lastname","id");

		new ImportCSV().getCSV("id" , "name" , "grade");
	}
	public ArrayList<StudentWrapper> getCSV(String...s) throws IOException{
		
	//	obj.run("F://TableToImport.csv");
		int k=0;
		int j[]=new int[s.length];
		//String username,first_name,last_name,password;
		for(int i=0;i<s.length;i++){
			 j[k]=obj.checkAndAddColumn(s[i]);
		     k++;
			
		}
		
		System.out.println("Total Rows : " + obj.getNumRows());
		
		ArrayList<StudentWrapper> studentCSV_list = new ArrayList<>();
		
		String id = "";
		String name = "";
		String grade = "";
		
		for(int i=1;i<obj.getNumRows();i++){
			StudentWrapper sWrapper = new StudentWrapper();
			
			id = StringFormatter.TitleCase(obj.get(i,j[0]));
			name = StringFormatter.TitleCase(obj.get(i,j[1]));
			grade = StringFormatter.TitleCase(obj.get(i,j[2]));
			
			sWrapper.setStudent_id(id);
			sWrapper.setStudent_name(name);
			sWrapper.setStudent_grade(grade);
			
			studentCSV_list.add(sWrapper);
			
		}
		
		System.out.println("List Size : " + studentCSV_list.size());
		return studentCSV_list;
	}

}
