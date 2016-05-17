package actions.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Joey
 * This class is meant to generate name suggestions for text ont type
 * @category AJAX quick response servlet
 *
 */
@WebServlet(
	name="Auto Suggest Servlet",
	urlPatterns={"/AutoSuggest"}
)
public class AutoSuggest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private final static Logger LOGGER = Logger.getLogger(AutoSuggest.class.getName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Add redirect to error page
		LOGGER.log(Level.WARNING,"Attempt to reach secure servlet by GET Request");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.write(postgreSQLDatabase.authentication.Query.getAutoSuggest(request.getParameter("string")).toString());
	}

}
