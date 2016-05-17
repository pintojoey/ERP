package actions.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import users.Student;

/**
 * Servlet implementation class addUpdateStudentRegistrationDetails
 */
public class addUpdateStudentRegistrationDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUpdateStudentRegistrationDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(request.getParameter("req"));
	
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Student student=new Student();
		try {
			
				
				//18 fields
				student.setRegistration_id(Long.parseLong(request.getSession().getAttribute("reg_id").toString()));
				//student.setName(request.getParameter("name"));
				System.out.println(request.getParameter("first_name"));
				student.setFirst_name(request.getParameter("first_name"));
				student.setMiddle_name(request.getParameter("middle_name"));
				student.setLast_name(request.getParameter("last_name"));
				//student.setCategory(request.getParameter("category"));
				//student.setState_eligibility(request.getParameter("state_eligibility"));
				student.setMobile(request.getParameter("mobile"));
				student.setEmail(request.getParameter("email"));
				//student.setDate_of_birth(request.getParameter("date_of_birth"));
				//student.setProgram_allocated(request.getParameter("program_allocated"));
				//student.setStatus(request.getParameter("status"));				
				//student.setPwd(request.getParameter("pwd"));
				//student.setGender(request.getParameter("gender"));
				student.setNationality(request.getParameter("nationality"));
				
				student.setRegistration_id(Long.parseLong( request.getSession().getAttribute("reg_id").toString()));
				student.setGuardian_name(request.getParameter("guardian_name"));
				student.setGuardian_contact(request.getParameter("guardian_contact"));
				student.setGuardian_email(request.getParameter("guardian_email"));
				student.setGuardian_address(request.getParameter("guardian_address"));
				student.setFather_name(request.getParameter("father_name"));
				student.setFather_contact(request.getParameter("father_contact"));
				student.setMother_contact(request.getParameter("mother_contact"));
				student.setFather_name(request.getParameter("mother_name"));
				//student.setNationality(request.getParameter("nationality"));
				student.setPermanent_address(request.getParameter("permanent_address"));
				student.setLocal_address(request.getParameter("local_address"));
				if(request.getParameter("hosteller").equals(true)){
				student.setHosteller(true);}
				else student.setHosteller(false);
				
				JSONObject address_obj=new JSONObject();
				address_obj.put("room",request.getParameter("room"));
				address_obj.put("hostel",request.getParameter("hostel"));
				
				
			 
			
			postgreSQLDatabase.registration.Query.addUpdateStudentRegistrationDetails(student);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().println("validation");
		}
		
		
		
	}

}
