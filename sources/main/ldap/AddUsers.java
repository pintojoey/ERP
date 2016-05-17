/**
 * 
 */
package ldap;

import java.io.IOException;

import csv.Parser;
import ldap.SimpleLdapAuthentication;
import postgreSQLDatabase.registration.Query;
import utilities.StringFormatter;

/**
 * @author Arushi
 *
 */
public class AddUsers {
	Parser obj;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		new AddUsers().addUser("firstname","lastname","id");

	}
	public String addUser(String...s) throws IOException{
		obj=new Parser("firstyear.csv");
		int k=0;
		int j[]=new int[s.length];
		String username,first_name,last_name,password;
		for(int i=0;i<s.length;i++){
			 j[k]=obj.checkAndAddColumn(s[i]);
		     k++;
			
		}
		
		
		for(int i=1;i<obj.getNumRows();i++){
			first_name=StringFormatter.TitleCase(obj.get(i,j[0])).trim();
			last_name=StringFormatter.TitleCase(obj.get(i,j[1])).trim();
			username=first_name.toLowerCase()+last_name.toLowerCase().trim();
			password=obj.get(i,j[0]).toLowerCase().trim();
			String erp_id=Query.registerUser(username, first_name+" "+last_name, "student");
			User user =new User();
			user.setFirst_name(first_name);
			user.setLast_name(last_name);
			user.setUsername(username);
			user.setDepartment("computer_science");
			user.setErp_id(erp_id);
			user.setPassword(password);
			user.setRole("students");
			System.out.println(username);
			SimpleLdapAuthentication.addEntry(user);
			
		}
		return "done";
	}

}
