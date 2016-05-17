/**
 * 
 */
package settings.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Joey
 *
 */
public class PostgreSQLConnection {
	static Connection conn ;
	public static Connection getConnection() throws SQLException{

		if(conn==null||conn.isClosed()){
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager
					.getConnection("jdbc:postgresql://192.168.0.104:5432/iiitk",
							"postgres", "iiitk_2013");
		}
		return conn;
	}
public static void closeConnection() throws SQLException{
if(conn!=null)	conn.close();
}
}
