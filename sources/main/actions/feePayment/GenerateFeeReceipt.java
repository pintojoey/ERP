package actions.feePayment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;

/**
 * Servlet implementation class GenerateFeeReceipt
 */
public class GenerateFeeReceipt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateFeeReceipt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		URL url = new URL(utilities.ServletUtils.getBaseUrl(request)+"/templates/feeReceipt.jsp");
		 Map<String,Object> params = new LinkedHashMap<>();
	        params.put("reg_id", request.getSession().getAttribute("reg_id"));
	 
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String,Object> param : params.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        } 
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	 
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);
				 
       BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
       String format="";
       String l;
       while ((l=in.readLine())!=null) {
          format+=l;
       }
       
//       
//	//	response.sendRedirect("studentRegistration/congratulations.jsp");
response.setContentType("application/pdf");
		
	//	Document document =new Document(com.itextpdf.text.PageSize.A4);
		
		
		try{

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(format);
		ServletOutputStream os = response.getOutputStream();
		renderer.layout();
		
			renderer.createPDF(os);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("Sorry there was an error");
		}
	}

}
