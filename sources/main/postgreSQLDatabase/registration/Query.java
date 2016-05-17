/**
 * 
 */
package postgreSQLDatabase.registration;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import exceptions.IncorrectFormatException;
import users.Student;
import utilities.StringPermutation;

/**
 * @author Anshula
 *
 */
public class Query {

		public static void updateVerificationStatus(int status, long reg_id) {
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection()
					.prepareStatement("SELECT public.\"updateVerificationStatus\"(?,?);");
			proc.setInt(1, status);
			proc.setLong(2, reg_id);
			proc.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<Student> getStudentRegistrationList() throws SQLException, IncorrectFormatException {
		ArrayList<Student> students = null;

		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT * from public.\"getStudentRegistrationList\"();");
			students = new ArrayList<Student>();
			ResultSet rs = proc.executeQuery();
			

			Student current;
			while (rs.next()) {
				current = new Student(rs);
				
				students.add(current);
			}
			Iterator<Student> iterator = students.iterator();
			while (iterator.hasNext()) {
				Student curr = iterator.next();
				 System.out.println(curr.getRegistration_id()+curr.getName());
			}

			rs.close();
			proc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return students;
	}

	/**
	 * @param username
	 * @param name
	 * @param user_type
	 * @return erp id
	 */
	public static String registerUser(String username, String name, String user_type) {
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"addUser\"(?,?,?);");
			proc.setString(1, username);
			proc.setString(2, name);
			proc.setString(3, user_type);
			ResultSet rs = proc.executeQuery();
			rs.next();
			String erp_id = String.valueOf(rs.getObject(1));
			return erp_id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	public static JSONObject retrieveUsernameGenParameters(long reg_id) {
		JSONObject current_object = null;
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection()
					.prepareStatement("SELECT public.\"retrieveUsernameGenParameters\"(?);");
			proc.setLong(1, reg_id);
			ResultSet rs = proc.executeQuery();
			current_object = new JSONObject(rs.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return current_object;

	}

	public static ArrayList<Student> getCsabStudentList() throws SQLException, IncorrectFormatException {
		ArrayList<Student> students = null;
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"displayCsabList\"();");

			students = new ArrayList<Student>();
			ResultSet rs = proc.executeQuery();
			// System.out.println(proc);
			rs.next();
//TODO Add null handling 
			JSONArray jArray = new JSONArray(rs.getString(1));

			for (int i = 0; i < jArray.length(); i++) {
				JSONObject current_object = jArray.getJSONObject(i);
				Student current = new Student();
				current.setName(current_object.getString("name"));
				current.setFirst_name(current_object.getString("first_name"));
				current.setMiddle_name(current_object.getString("middle_name"));
				current.setLast_name(current_object.getString("last_name"));
				current.setCategory(current_object.getString("category"));
				current.setJee_adv_rollno(current_object.getInt("jee_adv_rollno"));
				current.setJee_main_rollno(current_object.getInt("jee_main_rollno"));
				current.setState_eligibility(current_object.getString("state"));
				current.setMobile(current_object.getString("phone_number"));
				current.setEmail(current_object.getString("email"));
				current.setDate_of_birth(current_object.getString("date_of_birth"));
				current.setProgram_allocated(current_object.getString("program_allocated"));
				current.setAllocated_category(current_object.getString("allocated_category"));
				current.setAllocated_rank(current_object.getInt("allocated_rank"));
				current.setStatus(current_object.getString("status"));
				current.setChoice_no(current_object.getInt("choice_no"));
				current.setPwd(current_object.getBoolean("physically_disabled"));
				current.setGender(current_object.getString("gender"));
				current.setQuota(current_object.getString("quota"));
				current.setRound(current_object.getInt("round"));
				current.setWillingness(current_object.getString("willingness"));
				current.setPermanent_address(current_object.getString("address"));
				current.setRc_name(current_object.getString("rc_name"));
				current.setNationality(current_object.getString("nationality"));
				current.setCsab_id(current_object.getInt("id"));
				current.setRegistration_id(current_object.getLong("registration_id"));
				current.setEntry_time(new java.sql.Date(new SimpleDateFormat("YYYY-MM-DD HH:mm:SS.SSSSSS")
						.parse(current_object.getString("entry_date")).getTime()));
				current.setReported(current_object.getBoolean("reported"));

				students.add(current);
			}
			Iterator<Student> iterator = students.iterator();
			while (iterator.hasNext()) {
				Student current = iterator.next();
				// System.out.println(current.getCsab_id()+"
				// "+current.getName());
			}

			rs.close();
			proc.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return students;
	}
	public static JSONObject getRegistrationStundetDataJSON(long reg_id) throws SQLException, IncorrectFormatException {
		Student student = getRegistrationStudentData(reg_id) ;
	
		JSONObject current_object = new JSONObject();
		current_object.put("registration_id", student.getRegistration_id());
		current_object.put("name", student.getName());
		current_object.put("first_name",student.getFirst_name());
		current_object.put("middle_name", student.getMiddle_name());
		current_object.put("last_name", student.getLast_name());
		current_object.put("category", student.getCategory());
		current_object.put("mobile", student.getMobile());
		current_object.put("email", student.getEmail());
		current_object.put("date_of_birth", student.getDate_of_birth());
		current_object.put("program_allocated", student.getProgram_allocated());
		current_object.put("physically_disabled", student.isPwd());
		current_object.put("gender", student.getGender());
		current_object.put("reg_id", student.getRegistration_id());
		current_object.put("guardian_name", student.getGuardian_name());
		current_object.put("guardian_contact", student.getGuardian_contact());
		current_object.put("guardian_email", student.getGuardian_email());
		current_object.put("guardian_address",student.getGuardian_address());
		current_object.put("father_name",student.getFather_name());
		current_object.put("mother_name",student.getMother_name());
		current_object.put("father_contact",student.getFather_contact());
		current_object.put("mother_contact",student.getMother_contact());
		current_object.put("permanent_address",student.getPermanent_address());
		current_object.put("local_address",student.getLocal_address());
		current_object.put("hosteller",student.isHosteller());
		current_object.put("hostel_address",student.getHostel());
		current_object.put("semester", student.getSemester());
	JSONObject address_obj = new JSONObject();
	address_obj.put("room", student.getRoom());
	address_obj.put("hostel", student.getHostel());
	 current_object.put("hostel",address_obj.toString());
	 return current_object;
}
	public static JSONObject getCsabStudentProfile(long csab_id) throws SQLException, IncorrectFormatException {
		Student current = new Student();
		JSONObject current_object = null;
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"displayCsabProfile\"(?);");
			proc.setLong(1, csab_id);

			ResultSet rs = proc.executeQuery();

			rs.next();

			JSONArray jArray = new JSONArray(rs.getString(1));

			current_object = jArray.getJSONObject(0);

			current.setName(current_object.getString("name"));
			current.setFirst_name(current_object.getString("first_name"));
			current.setMiddle_name(current_object.getString("middle_name"));
			current.setLast_name(current_object.getString("last_name"));
			current.setCategory(current_object.getString("category"));
			current.setJee_adv_rollno(current_object.getInt("jee_adv_rollno"));
			current.setJee_main_rollno(current_object.getInt("jee_main_rollno"));
			current.setState_eligibility(current_object.getString("state"));
			current.setMobile(current_object.getString("phone_number"));
			current.setEmail(current_object.getString("email"));
			current.setDate_of_birth(current_object.getString("date_of_birth"));
			current.setProgram_allocated(current_object.getString("program_allocated"));
			current.setAllocated_category(current_object.getString("allocated_category"));
			current.setAllocated_rank(current_object.getInt("allocated_rank"));
			current.setStatus(current_object.getString("status"));
			current.setChoice_no(current_object.getInt("choice_no"));
			current.setPwd(current_object.getBoolean("physically_disabled"));
			current.setGender(current_object.getString("gender"));
			current.setQuota(current_object.getString("quota"));
			current.setRound(current_object.getInt("round"));
			current.setWillingness(current_object.getString("willingness"));
			current.setPermanent_address(current_object.getString("address"));
			current.setRc_name(current_object.getString("rc_name"));
			current.setNationality(current_object.getString("nationality"));
			current.setCsab_id(current_object.getInt("id"));
			current.setEntry_time(new java.sql.Date(new SimpleDateFormat("YYYY-MM-DD HH:mm:SS.SSSSSS")
					.parse(current_object.getString("entry_date")).getTime()));

			rs.close();
			proc.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return current_object;
	}

	public static Student getRegistrationStudentData(Long reg_id) throws SQLException, IncorrectFormatException {
		Student current = new Student();
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection()
					.prepareStatement("SELECT public.\"retrieveRegistrationStudentData\"(?);");
			proc.setLong(1, reg_id);
System.out.println(proc);
			ResultSet rs = proc.executeQuery();
			// System.out.println(rs.next());
			rs.next();

			JSONArray jArray = new JSONArray(rs.getString(1));

			JSONObject current_object = jArray.getJSONObject(0);

			
			current.setRegistration_id(Long.parseLong(current_object.get("id").toString()));
			current.setVerification_status(Integer.parseInt(current_object.get("verification_status").toString()));
			current.setName(current_object.get("name").toString());
			current.setFirst_name(current_object.get("first_name").toString());
			current.setMiddle_name(current_object.get("middle_name").toString());
			current.setLast_name(current_object.get("last_name").toString());
			current.setCategory(current_object.get("category").toString());
			current.setState_eligibility(current_object.get("state").toString());
			current.setMobile(current_object.get("phone_number").toString());
			current.setEmail(current_object.get("email").toString());
			current.setDate_of_birth(current_object.get("date_of_birth").toString());
			current.setProgram_allocated(current_object.get("program_allocated").toString());
			current.setSemester(Integer.parseInt(current_object.get("semester").toString()));
			current.setStatus(current_object.get("status").toString());
			current.setPwd(current_object.getBoolean("physically_disabled"));
			current.setGender(current_object.get("gender").toString());
			current.setPermanent_address(current_object.get("permanent_address").toString());
			current.setLocal_address(current_object.get("local_address").toString());
			current.setNationality(current_object.get("nationality").toString());
			current.setGuardian_name(current_object.get("guardian_name").toString());
			current.setGuardian_contact(current_object.get("guardian_contact").toString());
			current.setGuardian_email(current_object.get("guardian_email").toString());
			current.setGuardian_address(current_object.get("guardian_address").toString());
			current.setFather_name(current_object.get("father_name").toString());
			current.setMother_name(current_object.get("mother_name").toString());
			current.setFather_contact(current_object.get("father_contact").toString());
			current.setMother_name(current_object.get("mother_name").toString());
			try {
				current.setHosteller(current_object.getBoolean("hosteller"));
				JSONObject address_obj = current_object.getJSONObject("hostel_address");

				current.setHostel(address_obj.get("hostel").toString());
				current.setRoom(address_obj.get("room").toString());
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
						// System.out.println(current.getName());
			rs.close();
			proc.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return current;
	}
	public static Student getStudentProfile(Long erp_id) throws SQLException, IncorrectFormatException {
		Student current = new Student();
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection()
					.prepareStatement("SELECT public.\"retrieveStudentProfile\"(?);");
			proc.setLong(1, erp_id);

			ResultSet rs = proc.executeQuery();
			rs.next();
System.out.println(proc);
			JSONArray jArray = new JSONArray(rs.getString(1));

			JSONObject current_object = jArray.getJSONObject(0);

			
			current.setStudent_id(current_object.get("student_id").toString());
			current.setName(current_object.get("name").toString());
			current.setFirst_name(current_object.get("first_name").toString());
			current.setMiddle_name(current_object.get("middle_name").toString());
			current.setLast_name(current_object.get("last_name").toString());
			current.setCategory(current_object.get("category").toString());
			current.setState_eligibility(current_object.get("state").toString());
			current.setMobile(current_object.get("phone_number").toString());
			current.setEmail(current_object.get("email").toString());
			current.setDate_of_birth(current_object.get("date_of_birth").toString());
			current.setProgram_allocated(current_object.get("program_allocated").toString());
			current.setPwd(current_object.getBoolean("physically_disabled"));
			current.setGender(current_object.get("gender").toString());
			current.setPermanent_address(current_object.get("permanent_address").toString());
			current.setLocal_address(current_object.get("local_address").toString());
			current.setNationality(current_object.get("nationality").toString());
			current.setGuardian_name(current_object.get("guardian_name").toString());
			current.setGuardian_contact(current_object.get("guardian_contact").toString());
			current.setGuardian_email(current_object.get("guardian_email").toString());
			current.setGuardian_address(current_object.get("guardian_address").toString());
			current.setFather_name(current_object.get("father_name").toString());
			current.setMother_name(current_object.get("mother_name").toString());
			current.setFather_contact(current_object.get("father_contact").toString());
			current.setMother_name(current_object.get("mother_name").toString());
			try {
				current.setHosteller(current_object.getBoolean("hosteller"));
				JSONObject address_obj = current_object.getJSONObject("hostel_address");

				current.setHostel(address_obj.get("hostel").toString());
				current.setRoom(address_obj.get("room").toString());
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
						// System.out.println(current.getName());
			rs.close();
			proc.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return current;
	}
	public static JSONArray retrieveRegistrationData() throws SQLException {

		PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"displayRegistrationList\"();");
		ResultSet rs = proc.executeQuery();
		// System.out.println(proc);
		rs.next();

		JSONArray jArray = new JSONArray(rs.getString(1));

		return jArray;
	}

	public static void updateVerified(int reg_id) {
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"updateVerified\"(?);");
			proc.setInt(1, reg_id);
			
			proc.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static JSONObject getRegistrationStudentDataUpdateJSON(Long reg_id) throws SQLException, IncorrectFormatException {
		
		
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection()
					.prepareStatement("SELECT public.\"retrieveStudentRegistrationDataUpdate\"(?);");
			proc.setLong(1, reg_id);

			ResultSet rs = proc.executeQuery();
			System.out.println(proc);
			rs.next();

			JSONArray jArray = new JSONArray(rs.getString(1));

			JSONObject current_object = jArray.getJSONObject(0);
			return current_object;
		}
	public static Student getRegistrationStudentDataUpdate(Long reg_id) throws SQLException, IncorrectFormatException {
		Student current = new Student();
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection()
					.prepareStatement("SELECT public.\"retrieveRegistrationStudentDataUpdate\"(?);");
			proc.setLong(1, reg_id);

			ResultSet rs = proc.executeQuery();
			System.out.println(proc);
			rs.next();

			JSONArray jArray = new JSONArray(rs.getString(1));

			JSONObject current_object = jArray.getJSONObject(0);

			current.setName(current_object.getString("name"));
			current.setFirst_name(current_object.getString("first_name"));
			current.setMiddle_name(current_object.getString("middle_name"));
			current.setLast_name(current_object.getString("last_name"));
			current.setCategory(current_object.getString("category"));
			current.setState_eligibility(current_object.getString("state"));
			current.setMobile(current_object.getString("phone_number"));
			current.setEmail(current_object.getString("email"));
			current.setDate_of_birth(current_object.getString("date_of_birth"));
			current.setProgram_allocated(current_object.getString("program_allocated"));
			current.setStatus(current_object.getString("status"));
			current.setPwd(current_object.getBoolean("physically_disabled"));
			current.setGender(current_object.getString("gender"));
			current.setPermanent_address(current_object.getString("permanent_address"));
			current.setLocal_address(current_object.getString("local_address"));
			current.setNationality(current_object.getString("nationality"));
			current.setGuardian_name(current_object.getString("guardian_name"));
			current.setGuardian_contact(current_object.getString("guardian_contact"));
			current.setGuardian_email(current_object.getString("guardian_email"));
			current.setGuardian_address(current_object.getString("guardian_address"));
			current.setFather_name(current_object.getString("father_name"));
			current.setMother_name(current_object.getString("mother_name"));
			current.setFather_contact(current_object.getString("father_contact"));
			current.setMother_name(current_object.getString("mother_name"));
			current.setHosteller(current_object.getBoolean("hosteller"));
			current.setApplied(current_object.getBoolean("applied"));
			current.setEntry_time((Date) new SimpleDateFormat("YYYY-MM-DD HH:mm:SS.SSSSSS")
					.parse(current_object.getString("entry_time")));
			String json_string = current_object.getString("hostel_address");
			JSONObject address_obj = new JSONObject(json_string);
			current.setHostel(address_obj.getString("hostel"));
			current.setRoom(address_obj.getString("room"));

			rs.close();
			proc.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return current;
	}

	public static void addUpdateStudentRegistrationDetails(Student student) throws SQLException {
		PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement(
				"SELECT public.\"addUpdateRegistrationStudentDetails\"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
//text, text, text, text, text, integer, text, text, text, text, text, text, text, text, boolean, json, text, text
		proc.setString(1, student.getFirst_name());
		proc.setString(2, student.getMiddle_name());
		proc.setString(3, student.getLast_name());
		proc.setString(4, student.getMobile());
		proc.setString(5, student.getEmail());
		proc.setLong(6, student.getRegistration_id());
		proc.setString(7, student.getGuardian_name());
		proc.setString(8, student.getGuardian_contact());
		proc.setString(9, student.getGuardian_email());
		proc.setString(10, student.getGuardian_address());
		proc.setString(11, student.getFather_name());
		proc.setString(12, student.getMother_name());
		proc.setString(13, student.getFather_contact());
		proc.setString(14, student.getMother_contact());
		
		proc.setBoolean(15, student.isHosteller());
		JSONObject address_obj = new JSONObject();
		address_obj.put("room", student.getRoom());
		address_obj.put("hostel",student.getHostel());
		PGobject jsonObject = new PGobject();
		jsonObject.setType("json");
		jsonObject.setValue(address_obj.toString());
		
		proc.setObject(16, jsonObject);
		proc.setString(17, student.getPermanent_address());
		proc.setString(18, student.getLocal_address());
		/*
		 * proc.setBoolean(28,student.isApplied());
		 * proc.setDate(29,student.getEntry_time());
		 */
System.out.println(proc);
		proc.executeQuery();
	}

	public static void applyUpdate(long reg_id) throws SQLException {
		PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"applyUpdate\"(?);");
		proc.setLong(1, reg_id);
		proc.executeQuery();
	}

	public static int retrieveRegistrationStatus(Long reg_id) {

		try {
			int status = getRegistrationStudentData(reg_id).getVerification_status();
            return status;
//			proc = settings.database.PostgreSQLConnection.settings.database.PostgreSQLConnection.getConnection()
//					.prepareStatement("SELECT public.\"existsRegId\"(?);");
//			proc.setLong(1, reg_id);
//			ResultSet rs = proc.executeQuery();
//			rs.next();
//			
//			System.out.println("ID exists " + rs.getBoolean(1));
//			if (rs.getBoolean(1)) {
//				proc = PostgreSQLConnection.settings.database.PostgreSQLConnection.getConnection()
//						.prepareStatement("SELECT public.\"retrieveRegistrationStatus\"(?);");
//				proc.setLong(1, reg_id);
//				rs = proc.executeQuery();
//				rs.next();
//				boolean verified = rs.getBoolean(1);
//				System.out.println("verified " + verified);
//				if (verified) {
//					return 1;
//				} else {
//					return 0;
//				}
//			
//			} else {
//				return -1;
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;

	}

	public static int reportStudent(int csab_id) {
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"report_student\"(?);");
			proc.setInt(1, csab_id);
			ResultSet rs = proc.executeQuery();
			rs.next();
			int reg_id = rs.getInt(1);
			return reg_id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public static void retrieveStudentList() {
		PreparedStatement proc;
		try {
			proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT \"retrieveStudentList\"();");

			ResultSet rs = proc.executeQuery();
			rs.next();
			JSONArray student_list = new JSONArray(rs.getString(1));
			int i = 0;
			JSONObject student;
			for (i = 0; i < student_list.length(); i++) {
				student = student_list.getJSONObject(i);

				Student current = new Student();
				current.setStudent_id(student.get("student_id").toString());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
			

	}
	public static ArrayList<String> getUsernameGenerationData(long reg_id){
		 UsernameGeneration ug=null;
		 ArrayList<String> usernames=new ArrayList<String>();
		try {
			PreparedStatement proc = settings.database.PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"getUsernameGenerationData\"(?);");
		    proc.setLong(1, reg_id);
		    ResultSet rs=proc.executeQuery();
		    rs.next();
		   ug=new UsernameGeneration();
		   System.out.println(rs.getString(1));
		   JSONArray jArray=new JSONArray(rs.getString(1));
		    JSONObject current=jArray.getJSONObject(0);
		    
		    ug.setFirst(current.getString("first_name"));
		    //ug.setMiddle(current.getString("middle_name"));
		    ug.setLast(current.getString("last_name"));
		    //ug.setProgram_allocated(current.getString("program_allocated"));
		    //ug.setReg_year(current.getInt("reg_year"));
		    ug.setBirth_year(current.getInt("birth_year"));
		    System.out.println(ug.getFirst()+"  "+ug.getLast()+"  "+String.valueOf(ug.getBirth_year()));
		    
		    usernames=StringPermutation.generatePermutations(ug.getFirst(),ug.getLast(),String.valueOf(ug.getBirth_year()));
		    proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usernames;
	}
	public static void main(String[] args) {
		// retrieveStudentList();
		long id = 69;
		try {
			getStudentRegistrationList();
		} catch (SQLException | IncorrectFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
