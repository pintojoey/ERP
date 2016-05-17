package actions.authentication;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.SessionException;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter(
        urlPatterns = {"/SessionFilter","/student/*","/office/*","/administration/*"},
        filterName = "SessionFilter",
        description = "Filter all admin URLs"       
)
public class Session implements Filter {

    /**
     * Default constructor. 
     */
    public Session() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
	
	
		    HttpServletRequest httpReq = (HttpServletRequest) request;
		    HttpServletResponse httpResp = (HttpServletResponse) response;
		    HttpSession session = httpReq.getSession();
		 
		    httpReq.setCharacterEncoding("UTF-8");
		    httpResp.setCharacterEncoding("UTF-8");
		    httpResp.setContentType("application/json");
		 
		
		    
		    
		 
		if(session.getAttribute("erpId")==null)
			{
			
				
					SessionException exception =new SessionException("Session Failed");
					try {
						throw(exception);
					} catch (SessionException e) {
						// TODO Auto-generated catch block
						httpResp.sendRedirect("../SessionExpired.jsp");
					}
				
		
				
			
			}
		
		else	chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
