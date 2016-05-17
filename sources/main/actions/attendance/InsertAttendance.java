package actions.attendance;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class InsertAttendance
 */
@WebServlet("/InsertAttendance")
public class InsertAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println(request.getParameter("attendance_json"));
		JSONArray j_array = new JSONArray(request.getParameter("attendance_json"));
		JSONObject[] j_objects = new JSONObject[j_array.length()];
		for (int i = 0; i < j_array.length(); i++) {
			j_objects[i] = j_array.getJSONObject(i);

		}
		postgreSQLDatabase.attendance.Query.insertAttendance(request.getParameter("class_id"), j_objects);
	}

}
