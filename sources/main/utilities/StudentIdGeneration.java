/**
 * 
 */
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import ldap.User;

/**
 * @author Arushi
 *
 */
public class StudentIdGeneration {
	static Connection conn;
	public static Connection getConnection() throws SQLException {

		if (conn == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:postgresql://172.16.1.231:5432/iiitk", "developer", "developer");
		}
		return conn;
	}
	
	public static User generate_id(Long erp_id,Long reg_id){
		String student_id;
		String code=null;
		int num=0;
		String course=null;
		String department=null;
		String batch=null;
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		try{
			PreparedStatement proc = getConnection().prepareStatement("SELECT public.\"getProgram\"(?);");
			proc.setLong(1, reg_id);
			ResultSet rs = proc.executeQuery();
			rs.next();
			System.out.println(proc);
			course="B.Tech";
			String program=rs.getString(1);
			//System.out.println(program);
			if(program.equals("Computer Engineering (4 Years Bachelor of Technology)")){
				code="KUCP";
			}
			if(program.equals("Electronics and Communication Engineering (4 Years Bachelor of Technology)")){
				code="KUEC";
			}
			if(program.equals("Computer Engineering (2 Years Master of Technology)")){
				code="KPCP";
			}
			if(program.equals("Electronics and Communication Engineering (2 Years Master of Technology)")){
				code="KPEC";
			}
			if(code.equals("KUCP") || code.equals("KPCP")){
				num=1001;
				department="computer_science";
			}
			if(code.equals("KUEC") || code.equals("KPEC")){
				num=2001;
				department="electronics_communication";
			}
			//System.out.println(code);
			PreparedStatement proc1 = getConnection().prepareStatement("SELECT public.\"getStudentRegistrationCount\"(?,?);");
			proc1.setString(1, department);
			proc1.setInt(2, 1);
			System.out.println(proc1);
			ResultSet rs1 = proc1.executeQuery();
			rs1.next();
			 num=rs1.getInt(1)+num;
			
			
			student_id=year+code+num;
			System.out.println(student_id);
			if(code.equals("KUCP")|| code.equals("KUEC")){
				course="undergraduate";
			}
			if(code.equals("KPCP")|| code.equals("KPEC")){
				course="postgraduate";
			}
			PreparedStatement proc2 = getConnection().prepareStatement("SELECT public.\"addStudent\"(?,?,?,?,?,?,?);");
			proc2.setLong(1,erp_id);
			proc2.setString(2, student_id);
			proc2.setInt(3, 1);
			proc2.setString(4, course);
			proc2.setString(5, department); //branch
			proc2.setString(6, batch);
			proc2.setLong(7, reg_id);
			ResultSet rs2 = proc2.executeQuery();
			rs2.next();
			User user=new User();
			user.setDepartment(department);
			user.setRole("students");
			user.setRole_id(student_id);
		return user;
	
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
