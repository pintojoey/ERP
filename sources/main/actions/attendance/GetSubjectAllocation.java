package actions.attendance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import postgreSQLDatabase.attendance.Allocation;

/**
 * Servlet implementation class GetSubjectAllocation
 */
@WebServlet("/GetSubjectAllocation")
public class GetSubjectAllocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter writer;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetSubjectAllocation() {
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
		writer = response.getWriter();
		String faculty_id = request.getParameter("faculty_id");
		String semester = request.getParameter("semester");
		ArrayList<Allocation> list = postgreSQLDatabase.attendance.Query.getFacultyCourseCode(faculty_id,
				Integer.parseInt(semester));
		Iterator<Allocation> iterator = list.iterator();
		JSONArray jarray = new JSONArray();

		while (iterator.hasNext()) {
			JSONObject j_object = new JSONObject();
			Allocation current = iterator.next();
			j_object.put("course_code", current.getCourse_code());
			j_object.put("semester", current.getSemester());
			j_object.put("year", current.getDate());
			jarray.put(j_object);
		}
		writer.write(jarray.toString());
	}

}
