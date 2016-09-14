package MyPackage;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String checkout = request.getParameter("checkout").trim();
		
        String[] checked = checkout.split("/");
        String uid = checked[0];
        String[] newchecked = new String[checked.length-1];
        if(checked.length > 1){
        	System.arraycopy(checked, 1, newchecked, 0, checked.length-1);
        }
			   
	    UserDao userDao = new UserDao();
	    String result= userDao.checkout(uid, newchecked);
			
	    if(result =="succeed"){
				request.setAttribute("info", "check out successfully.");
				request.getRequestDispatcher("AEDresult.jsp").forward(request, response);
			}
	   else{
				request.setAttribute("info", result);
				request.getRequestDispatcher("AEDresult.jsp").forward(request, response);
			}
	}	

}