package MyPackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditCart")
public class EditCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ISBN = request.getParameter("ISBN");
		Integer number = Integer.parseInt(request.getParameter("number"));
		String uid = request.getParameter("uid");
			   
	    UserDao userDao = new UserDao();
	    String result= userDao.editCart(uid, ISBN, number);
			
	    if(result =="update succeed"){
				request.setAttribute("info", "update cart successfully.");
				request.getRequestDispatcher("AEDresult.jsp").forward(request, response);
			}
	    else if(result =="delete succeed"){
	    	request.setAttribute("info", "delete cart successfully.");
			request.getRequestDispatcher("AEDresult.jsp").forward(request, response);
	    }
	   else{
				request.setAttribute("info", result);
				request.getRequestDispatcher("AEDresult.jsp").forward(request, response);
			}
	}	

}
