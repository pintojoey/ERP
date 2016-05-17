/**
 * 
 */
package postgreSQLDatabase.registration;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

/**
 * @author Arushi
 *
 */
public class ImportCSAB {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		new ImportCSAB().createJSON("c1.csv");

	}
	public  void createJSON(String filename) throws IOException, SQLException{
		int k=0;
		csv.Parser obj=new csv.Parser(filename);
       
       // JSONArray jarray=new JSONArray();
        String[] colname=new String[obj.getNumCols(0)];
        String jarray[]=new String[obj.getNumRows()-1];
        
        for(int i=0;i<obj.getNumCols(0);i++){
        	colname[i]=obj.get(0, i);
        	//System.out.println(colname[i]);
        }
        //int j[]=new int[colname.length];
        for(int i=0;i<colname.length;i++){
			 //j[k]=obj.checkAndAddColumn(colname[i]);
			 //System.out.println(j[k]);
		     //k++;
        	switch(colname[i]){
        	case "firstname":{colname[i]="first_name";break;}
        	case "lastname":{colname[i]="last_name";break;}
        	case "Stu_Id":{colname[i]="id";break;}
        	case "JEE(Main) RollNo":{colname[i]="jee_main_rollno";break;}
        	case "JEE(Adv) RollNo":{colname[i]="jee_adv_rollno";break;}
        	case "Name":{colname[i]="name";break;}
        	case "Category":{colname[i]="category";break;}
        	case "PwD":{colname[i]="physically_disabled";break;}
        	case "Gender":{colname[i]="gender";break;}
        	case "DOB":{colname[i]="date_of_birth";break;}
        	case "State (Eligibility)":{colname[i]="state";break;}
        	case "Nationality":{colname[i]="nationality";break;}
        	case "Program Allocated":{colname[i]="program_allocated";break;}
        	case "Allocated Rank":{colname[i]="allocated_rank";break;}
        	case "Quota":{colname[i]="quota";break;}
        	case "Round":{colname[i]="round";break;}
        	case "Willingness":{colname[i]="willingness";break;}
        	case "Status":{colname[i]="status";break;}
        	case "Mobile":{colname[i]="phone_number";break;}
        	case "Email":{colname[i]="email";break;}
        	case "Address":{colname[i]="address";break;}
        	case "RC Name":{colname[i]="rc_name";break;}
        	case "Choice No.":{colname[i]="choice_no";break;}
        	case "Allocated Cat.":{colname[i]="allocated_category";break;}
        	}
        	//System.out.println(colname[i]);
		}
        
        int col_no=0;
        ArrayList<ArrayList<String>> list=obj.getArray();
        Iterator<ArrayList<String>> iterator = list.iterator();
        iterator.next();
        int index=-1;
        while (iterator.hasNext()) {
        	 col_no=0;
        	 index++;
             ArrayList<String> column = iterator.next();
             Iterator<String> column_iterator = column.iterator();
             JSONObject jObject=new JSONObject();
             
             while(column_iterator.hasNext() && col_no<colname.length){
            	//Student current=new users.Student();
            	//current.setFirst_name(first_name);
            	 String data=column_iterator.next();
            	// System.out.println(col_no);
            	 jObject.put(colname[col_no], data);
            	 jObject.put("first_name", "null");
            	 jObject.put("middle_name", "null");
            	 jObject.put("last_name", "null");
            	 jObject.put("reported", "false");
            	// String timestamp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS").format(new Date());
            	 //System.out.println(timestamp);
            	 //jObject.put("entry_date", timestamp);
            	 col_no++;
            	 
             }
         //    jarray.put(jObject);
             jarray[index]=jObject.toString();
        }
        
        //System.out.println(jarray);
        PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"addCSABData\"(?);");
        proc.setArray(1, settings.database.PostgreSQLConnection.getConnection().createArrayOf("json", jarray));
      
        
        System.out.println(proc);
        
	}
	
	
}
