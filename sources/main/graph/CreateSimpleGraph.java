/**
 * 
 */
package graph;

/**
 * @author Joey
 *
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.management.timer.Timer;
import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;
import org.json.JSONObject;

public class CreateSimpleGraph
{
public static 	Neo4jConnection connect; 
public static void main(String[] args) throws SQLException {
	final Properties props = new Properties();
    props.put("user", "neo4j");
    props.put("password", "joey");  
    
if(connect==null)	 connect = new Driver().
			  connect("jdbc:neo4j://localhost:7474", props);

for(int i=0;i<100;i++)			 
{	
	 
		
			ResultSet resultSet = connect.createStatement().
			  executeQuery("match(n) return n;");
			while(resultSet.next()) {
				System.out.println(resultSet.getObject(1));
JSONObject json_object=new JSONObject(resultSet.getObject(1));
System.out.println(json_object);

			}
}			
}

}
