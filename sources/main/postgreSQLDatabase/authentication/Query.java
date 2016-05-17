/**
 * 
 */
package postgreSQLDatabase.authentication;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;


/**
 * @author Joey
 *
 */
public class Query {

	public static JSONArray getAutoSuggest(String input) {
		System.out.println(input);
		JSONArray jArray = null;
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT * from public.\"getUserAutoSuggest\"(?);");
			proc.setString(1, input);
			ResultSet rs = proc.executeQuery();
			rs.next();
			System.out.println(rs.getString(1));
		//	jArray = new JSONArray(rs.getString(1));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return jArray;
			
	}
	


public static String getUserUsername(Long erp_id){
	PreparedStatement proc ;
	try {
		proc= settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"getUserUsername\"(?);");
		
		proc.setLong(1, erp_id);
		ResultSet rs=proc.executeQuery();
		rs.next();
	return rs.getString(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	
	
}
public static void main(String[] args) {
	getAutoSuggest("jo");
}

}
