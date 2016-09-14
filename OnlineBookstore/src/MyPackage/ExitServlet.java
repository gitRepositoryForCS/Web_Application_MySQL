package MyPackage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1599366365079846238L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Users user = (Users)session.getAttribute("user");
		
		HashMap<Users, HashMap<String, List<Integer>>> userAndAdmin = (HashMap<Users, HashMap<String, List<Integer>>>) session.getAttribute("userAndAdmin");

		if(user != null){

			session.removeAttribute("user");
			request.setAttribute("info", user.getId());
		}
		if(userAndAdmin != null)
		{
			session.removeAttribute("userAndAdmin");
			request.setAttribute("info", "You've logged out.");
		}

		request.getRequestDispatcher("message.jsp").forward(request, response);
	}

}

