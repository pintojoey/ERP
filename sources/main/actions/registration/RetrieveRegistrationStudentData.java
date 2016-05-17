package actions.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import exceptions.IncorrectFormatException;

/**
 * Servlet implementation class retrieveRegistrationStudentData
 */
public class RetrieveRegistrationStudentData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveRegistrationStudentData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long registration_id = Long.parseLong(request.getParameter("registration_id").toString());
		try {
			JSONObject original_data=postgreSQLDatabase.registration.Query.getRegistrationStundetDataJSON(registration_id);
			PrintWriter writer=response.getWriter();
			JSONObject updated_data = null;
			try{
			updated_data=postgreSQLDatabase.registration.Query.getRegistrationStudentDataUpdateJSON(registration_id);
			}
			catch(Exception e){
				System.out.println("No updated Data");
				writer.write(original_data.toString());
				return;
			}
			
			Iterator<String> update_iterator = updated_data.keys();
			
			while(update_iterator.hasNext()){
				
				String key=update_iterator.next();
				original_data.put("update_"+key, updated_data.get(key));
			}
			
			writer.write(original_data.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
