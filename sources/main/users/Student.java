/**
 *  @author Anshula 
 */
package users;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.IncorrectFormatException;
import utilities.UniqueList;

/**
 * @author Anshula
 *
 */
public class Student {
	private UniqueList<String> parameters=new UniqueList<String>();
	final static int address_max_length=100;
	final static int mobile_max_length=13;
	final static int name_max_length=50;

	private String allocated_category="";
	ArrayList<String> allocated_category_list=new ArrayList<String>();
	private int allocated_rank;
	private boolean applied;
	private String category="";
	ArrayList<String> category_list = new ArrayList<String>();

	private int choice_no;

	private long csab_id;

	private Date date_of_birth;
	private String email="";
	private Date entry_time;

	private String father_contact="";
	private String father_name="";
	private String first_name="";
	private String gender="";
	ArrayList<String> gender_list=new ArrayList<String>();
	private String guardian_address="";
	private String guardian_contact="";
	private String guardian_email="";
	private String guardian_name="";
	private String hostel="";
	private boolean hosteller=false;
	private int jee_adv_rollno;
	private int jee_main_rollno;
	private String last_name="";
	private String local_address="";
	private String middle_name="";
	private String mobile="";
	private String mother_contact="";
	private String mother_name="";
	private String name="";
	private String nationality;
	ArrayList<String> nationality_list = new ArrayList<String>();
	private String permanent_address="";
	private String program_allocated="";
	ArrayList<String> program_allocated_list=new ArrayList<String>();
	private boolean pwd;
	private String quota="";
	private String rc_name;
	private long registration_id;
	private boolean reported;
	private String room;
	private int round;
	private int semester;

	private String state_eligibility="";

	ArrayList<String> state_list=new ArrayList<String>();

	private String status="";

	private String student_id;
	private int verification_status;
	private boolean verified;
	private String willingness="";
	public static void main(String[] args)throws IncorrectFormatException {
		Student test=new Student();
		test.setName("joey pinto");
		test.setProgram_allocated("computer engineering (4 years bachelor of technology)");
		test.setState_eligibility("Uttar Pradesh");
		test.setStudent_id("");
	}
	public Student(){
		category_list.addAll(Arrays.asList("GENERAL","OBC","SC","ST","OBC-NCL"));
		gender_list.addAll(Arrays.asList("male","female"));
		state_list.addAll(Arrays.asList("UTTRAKHAND","MAHARASHTRA"
				,"HARYANA"
				,"DELHI (NCT)"
				,"MADHYA PRADESH"
				,"KERALA"
				,"UTTAR PRADESH"
				,"BIHAR"
				,"CHANDIGARH (UT)"
				,"PUNJAB"
				,"RAJASTHAN"
				,"TELANGANA"
				));
		program_allocated_list.addAll(Arrays.asList("computer engineering (4 years bachelor of technology)","computer engineering"));
		allocated_category_list.addAll(Arrays.asList("open","sc","st","obc-ncl","open(pwd)","obc-ncl(pwd)"));
		nationality_list.addAll(Arrays.asList("indian"));
	}

	public Student(ResultSet rs) throws Exception{
		category_list.addAll(Arrays.asList("GENERAL","OBC","SC","ST","OBC-NCL"));
		gender_list.addAll(Arrays.asList("male","female"));
		state_list.addAll(Arrays.asList("UTTRAKHAND","MAHARASHTRA"
				,"HARYANA"
				,"DELHI (NCT)"
				,"MADHYA PRADESH"
				,"KERALA"
				,"UTTAR PRADESH"
				,"BIHAR"
				,"CHANDIGARH (UT)"
				,"PUNJAB"
				,"RAJASTHAN"
				,"TELANGANA"
				));
		program_allocated_list.addAll(Arrays.asList("computer engineering (4 years bachelor of technology)","computer engineering"));
		allocated_category_list.addAll(Arrays.asList("open","sc","st","obc-ncl","open(pwd)","obc-ncl(pwd)"));
		nationality_list.addAll(Arrays.asList("indian"));
		ResultSetMetaData meta = rs.getMetaData();
		for(int i=1;i<meta.getColumnCount();i++){
			switch(meta.getColumnName(i)){

			case "name":
				
				setName(rs.getString(i));
				break;
			case "first_name":
				setFirst_name(rs.getString(i));
				break;
			case "middle_name":
				break;
			case "last_name":
				setLast_name(rs.getString(i));
				break;
			case "verification_status":
				setVerification_status(rs.getInt(i));
				break;
			case "category":
				setCategory(rs.getString(i));
				break;
			case "jee_adv_rollno":
				setJee_adv_rollno(rs.getInt(i));
				break;
			case "jee_main_rollno":
				setJee_main_rollno(rs.getInt(i));
				break;
			case "state":
				setState_eligibility(rs.getString(i));
				break;
			case "phone_number":
				setMobile(rs.getString(i));
				break;
			case "email":
				setEmail(rs.getString(i));
				break;
			case "date_of_birth":
				setDate_of_birth(rs.getString(i));
				break;
			case "program_allocated":
				setProgram_allocated(rs.getString(i));
				break;
			case "allocated_category":
				setAllocated_category(rs.getString(i));
				break;
			case "allocated_rank":
				setAllocated_rank(rs.getInt(i));
				break;
			case "status":
				setStatus(rs.getString(i));
				break;
			case "choice_no":
				 setChoice_no(rs.getInt(i));
					break;
			case "physically_disabled":
				setPwd(rs.getString(i));
				break;
			case "gender":
				setGender(rs.getString(i));
				break;
			case "quota":
			   setQuota(rs.getString(i));
				break;
			case "round":
				setRound(rs.getInt(i));
				break;
			case "willingness":
				setWillingness(rs.getString(i));
				break;
			case "address":
				setPermanent_address(rs.getString(i));
				break;
			case "rc_name":
				setRc_name(rs.getString(i));
				break;
			case "nationality":
				setNationality(rs.getString(i));
				break;
			case "id":
				setRegistration_id(rs.getLong(i));
				break;
			case "entry_date":
				setEntry_time(new SimpleDateFormat("YYYY-MM-DD HH:mm:SS.SSSSSS").parse(rs.getString(i)));
			case "verified":
				setVerified(rs.getBoolean(i));

			}
		}

	}

	/**
	 * @return the allocated_category
	 */
	public String getAllocated_category() {
		return allocated_category;
	}

	/**
	 * @return the allocated_rank
	 */
	public int getAllocated_rank() {
		return allocated_rank;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @return the choice_no
	 */
	public int getChoice_no() {
		return choice_no;
	}
	/**
	 * @return the csab_id
	 */
	public long getCsab_id() {
		return csab_id;
	}
	/**
	 * @return the date_of_birth
	 */
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the entry_date
	 */
	public Date getEntry_time() {
		return entry_time;
	}
	/**
	 * @return the father_contact
	 */
	public String getFather_contact() {
		return father_contact;
	}

	/**
	 * @return the father_name
	 */
	public String getFather_name() {
		return father_name;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}


	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @return the guardian_address
	 */
	public String getGuardian_address() {
		return guardian_address;
	}
	/**
	 * @return the guardian_contact
	 */
	public String getGuardian_contact() {
		return guardian_contact;
	}
	/**
	 * @return the guardian_email
	 */
	public String getGuardian_email() {
		return guardian_email;
	}
	/**
	 * @return the guardian_name
	 */
	public String getGuardian_name() {
		return guardian_name;
	}
	/**
	 * @return the hostel
	 */
	public String getHostel() {
		return hostel;
	}
	/**
	 * @return the jee_adv_rollno
	 */
	public int getJee_adv_rollno() {
		return jee_adv_rollno;
	}
	/**
	 * @return the jee_main_rollno
	 */
	public int getJee_main_rollno() {
		return jee_main_rollno;
	}
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * @return the local_address
	 */
	public String getLocal_address() {
		return local_address;
	}
	/**
	 * @return the middle_name
	 */
	public String getMiddle_name() {
		return middle_name;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @return the mother_contact
	 */
	public String getMother_contact() {
		return mother_contact;
	}
	/**
	 * @return the mother_name
	 */
	public String getMother_name() {
		return mother_name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @return the permanent_address
	 */
	public String getPermanent_address() {
		return permanent_address;
	}
	/**
	 * @return the program_allocated
	 */
	public String getProgram_allocated() {
		return program_allocated;
	}
	/**
	 * @return the quota
	 */
	public String getQuota() {
		return quota;
	}
	/**
	 * @return the rc_name
	 */
	public String getRc_name() {
		return rc_name;
	}


	/**
	 * @return the registration_id
	 */
	public long getRegistration_id() {
		return registration_id;
	}

	/**
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * @return the round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * @return the state_eligibility
	 */
	public String getState_eligibility() {
		return state_eligibility;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the student_id
	 */
	public String getStudent_id() {
		return student_id;
	}

	/**
	 * @return the verification_status
	 */
	public int getVerification_status() {
		return verification_status;
	}

	public boolean getVerified() {
		return verified;
	}

	/**
	 * @return the willingness
	 */
	public String getWillingness() {
		return willingness;
	}

	/**
	 * @return the applied
	 */
	public boolean isApplied() {
		return applied;
	}

	/**
	 * @return the hosteller
	 */
	public boolean isHosteller() {
		return hosteller;
	}

	/**
	 * @return the pwd
	 */
	public boolean isPwd() {
		return pwd;
	}

	/**
	 * @return the reported
	 */
	public boolean isReported() {
		return reported;
	}

	/**
	 * @param allocated_category the allocated_category to set
	 */
	public void setAllocated_category(String allocated_category)throws IncorrectFormatException {
		//if(allocated_category_list.contains(allocated_category.toLowerCase()))
		this.allocated_category = allocated_category;
		//else
		//throw new IncorrectFormatException("allocated category");
		parameters.add("allocated_category");

	}

	/**
	 * @param allocated_rank the allocated_rank to set
	 */
	public void setAllocated_rank(int allocated_rank) {
		this.allocated_rank = allocated_rank;
		parameters.add("allocated_rank");
	}

	/**
	 * @param applied the applied to set
	 */
	public void setApplied(boolean applied) {
		this.applied = applied;
		parameters.add("applied");
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category)throws IncorrectFormatException {
		if(category_list.contains(category.toUpperCase()))
			this.category = category;
		else
			throw new IncorrectFormatException("category");
        parameters.add("category");
	}

	/**
	 * @param choice_no the choice_no to set
	 */
	public void setChoice_no(int choice_no) {
		this.choice_no = choice_no;
		parameters.add("choice_no");
	}

	public void setCsab_id(long csab_id) {
		this.csab_id = csab_id;
		parameters.add("csab_id");
	}

	/**
	 * @param date_of_birth the date_of_birth to set
	 */
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
		parameters.add("date_of_birth");
	}

	/**
	 * @param date_of_birth the date_of_birth to set
	 */
	public void setDate_of_birth(String date_of_birth)throws IncorrectFormatException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date date;
		try {
			date = sdf.parse(date_of_birth);
			this.date_of_birth = new java.sql.Date(date.getTime());
			parameters.add("date_of_birth");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new IncorrectFormatException("date_of_birth");
		}



	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)throws IncorrectFormatException {
		if(email==null){this.email="";return;}
		String regex = Pattern.quote("");
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(email);
		if(matcher.find()){
			this.email = email;
			parameters.add("email");
		}
		else{
			throw new IncorrectFormatException("email");
		}

	}

	/**
	 * @param entry_date the entry_date to set
	 */
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
		parameters.add("entry_time");
	}

	/**
	 * @param father_contact the father_contact to set
	 */
	public void setFather_contact(String father_contact) {
		this.father_contact = father_contact;
		parameters.add("father_contact");
	}

	/**
	 * @param father_name the father_name to set
	 */
	public void setFather_name(String father_name) {
		this.father_name = father_name;
		parameters.add("father_name");
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		if(first_name!=null && !first_name.equals("null"))	{this.first_name = first_name.trim();
		parameters.add("first_name");
		}
		else this.first_name="";
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender)throws IncorrectFormatException {

		if(gender_list.contains(gender.toLowerCase()))
			{this.gender = gender;
			parameters.add("gender");
			}
		else
			throw new IncorrectFormatException("gender");

	}

	/**
	 * @param guardian_address the guardian_address to set
	 */
	public void setGuardian_address(String guardian_address) {
		this.guardian_address = guardian_address;
		parameters.add("guardian_address");
	}

	/**
	 * @param guardian_contact the guardian_contact to set
	 */
	public void setGuardian_contact(String guardian_contact) {
		this.guardian_contact = guardian_contact;
		parameters.add("guardian_contact");
	}

	/**
	 * @param guardian_email the guardian_email to set
	 */
	public void setGuardian_email(String guardian_email) {
		this.guardian_email = guardian_email;
		parameters.add("guardian_email");
	}

	/**
	 * @param guardian_name the guardian_name to set
	 */
	public void setGuardian_name(String guardian_name) {
		this.guardian_name = guardian_name;
		parameters.add("guardian_name");
	}

	/**
	 * @param hostel the hostel to set
	 */
	public void setHostel(String hostel) {
		this.hostel = hostel;
		parameters.add("hostel");
	}

	/**
	 * @param hosteller the hosteller to set
	 */
	public void setHosteller(boolean hosteller) {
		this.hosteller = hosteller;
		parameters.add("hosteller");
	}

	/**
	 * @param jee_adv_rollno the jee_adv_rollno to set
	 */
	public void setJee_adv_rollno(int jee_adv_rollno) {
		this.jee_adv_rollno = jee_adv_rollno;
		parameters.add("jee_adv_rollno");
	}

	/**
	 * @param jee_main_rollno the jee_main_rollno to set
	 */
	public void setJee_main_rollno(int jee_main_rollno) {
		this.jee_main_rollno = jee_main_rollno;
		parameters.add("jee_main_rollno");
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		if(last_name!=null && !last_name.equals("null")){this.last_name = last_name.trim();parameters.add("last_name");}
		else this.last_name="";
	}

	/**
	 * @param local_address the local_address to set
	 */
	public void setLocal_address(String local_address) {
		this.local_address = local_address;
		parameters.add("local_address");
	}

	/**
	 * @param middle_name the middle_name to set
	 */
	public void setMiddle_name(String middle_name) {
		if(!middle_name.equals("null"))	{this.middle_name = middle_name.trim();parameters.add("middle_name");}
		else this.middle_name="";
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile)throws IncorrectFormatException {
		//		 Pattern pattern= Pattern.compile("\\+?[0-9]*");
		//	      Matcher matcher = pattern.matcher(mobile);
		//		if(matcher.find()&& mobile.length()<=mobile_max_length && mobile.length()>=10){
		this.mobile = mobile;
		//		}
		//		else{
		//			this.mobile="";
		//			throw new IncorrectFormatException("mobile");
		//		}
		parameters.add("mobile");
	}

	/**
	 * @param mother_contact the mother_contact to set
	 */
	public void setMother_contact(String mother_contact) {
		this.mother_contact = mother_contact;
		parameters.add("mother_contact");
	}

	/**
	 * @param mother_name the mother_name to set
	 */
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
		parameters.add("mother_name");
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)throws IncorrectFormatException {
		Pattern pattern= Pattern.compile("[a-zA-Z\\s]+");
		Matcher matcher = pattern.matcher(name);
		//String regex = Pattern.quote("^[a-zA-Z\\s]+$");
		//Pattern pattern=Pattern.compile(regex);
		//Matcher matcher=pattern.matcher(name);
		//System.out.println(name.length()+" "+name_max_length);
		if(matcher.find()&&name.length()<=name_max_length){
			this.name = name;
			parameters.add("name");
		}
		else{
			throw new IncorrectFormatException("name123");
		}
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality)throws IncorrectFormatException {
		//if(nationality_list.contains(nationality.toLowerCase()))
		this.nationality = nationality;
		//else
		//throw new IncorrectFormatException("nationality");
		parameters.add("nationality");
	}

	/**
	 * @param permanent_address the permanent_address to set
	 */
	public void setPermanent_address(String permanent_address) {
		this.permanent_address = permanent_address;
		parameters.add("permanent_address");
	}

	/**
	 * @param program_allocated the program_allocated to set
	 */
	public void setProgram_allocated(String program_allocated)throws IncorrectFormatException {
		//if(program_allocated_list.contains(program_allocated.toLowerCase()))
		this.program_allocated = program_allocated;

		//else
		//throw new IncorrectFormatException("program_alloted");

		//else
		//throw new IncorrectFormatException("program_allocated"+program_allocated);

		parameters.add("program_allocated");
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(boolean pwd) {
		this.pwd = pwd;
		parameters.add("pwd");
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		if(pwd.equalsIgnoreCase("yes"))
			this.pwd = true;
		else
			this.pwd=false;
		parameters.add("pwd");
	}

	/**
	 * @param quota the quota to set
	 */
	public void setQuota(String quota) {
		this.quota = quota;
		parameters.add("quota");
	}

	/**
	 * @param rc_name the rc_name to set
	 */
	public void setRc_name(String rc_name) {
		this.rc_name = rc_name;
		parameters.add("rc_name");
	}



	/**
	 * @param registration_id the registration_id to set
	 */
	public void setRegistration_id(long registration_id) {
		this.registration_id = registration_id;
		parameters.add("registration_id");
	}

	/**
	 * @param reported the reported to set
	 */
	public void setReported(boolean reported) {
		this.reported = reported;
		parameters.add("reported");
	}


	/**
	 * @param room the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
		parameters.add("room");
	}

	/**
	 * @param round the round to set
	 */
	public void setRound(int round) {
		this.round = round;
		parameters.add("round");
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
		parameters.add("semester");
	}

	/**
	 * @param state_eligibility the state_eligibility to set
	 */
	public void setState_eligibility(String state_eligibility) throws IncorrectFormatException{

		//if(state_list.contains(state_eligibility.toLowerCase()))
		this.state_eligibility = state_eligibility;
		//else
		//throw new IncorrectFormatException("state");

		if(state_list.contains(state_eligibility.toUpperCase()))
			{this.state_eligibility = state_eligibility;parameters.add("state_eligibility");}
		else
			throw new IncorrectFormatException("state"+state_eligibility);

	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
		parameters.add("status");
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
		parameters.add("student_id");
	}

	/**
	 * @param verification_status the verification_status to set
	 */
	public void setVerification_status(int verification_status) {
		this.verification_status = verification_status;
		parameters.add("verification_status");
	}

	/**
	 * @param csab_id the csab_id to set
	 */
	public void setVerified(boolean verified) {
		this.verified = verified;
		parameters.add("verified");
	}

	/**
	 * @param willingness the willingness to set
	 */
	public void setWillingness(String willingness) {
		this.willingness = willingness;
		parameters.add("willingness");
	}

}
