package MyPackage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 5280356329609002908L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uid = request.getParameter("uid");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");		
		
		UserDao userDao = new UserDao();
		System.out.println(uid + password + fullname + email);
		if(uid != null && !uid.isEmpty()){
			if(userDao.userIsExist(uid)){
				
				Users user = new Users();		
				user.setId(uid);	
				user.setPassword(password);
				user.setFullname(fullname);
				user.setEmail(email);
				userDao.saveUser(user);	
				request.setAttribute("info", "Registered successfully");
			}else{
				request.setAttribute("info", "Registration failed");
			}
		}
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}

}
