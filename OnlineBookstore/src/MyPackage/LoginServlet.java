package MyPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	public class LoginServlet extends HttpServlet {
		private static final long serialVersionUID = -3009431503363456775L;
		
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String uid = request.getParameter("uid");
			String password = request.getParameter("password");
			UserDao userDao = new UserDao();	
			Users user = userDao.login(uid, password);
			if(user != null){
				if(user.getId().equals("admin")){
					HashMap<String, List<Integer>> l = userDao.queryAdmin();
					HashMap<Users, HashMap<String, List<Integer>>> wholeInfo = new HashMap<Users, HashMap<String, List<Integer>>>();
					wholeInfo.put(user, l);			
					request.getSession().setAttribute("userAndAdmin", wholeInfo);
					request.getRequestDispatcher("message.jsp").forward(request, response);
				}
				else{
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("message.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("info", "no such user.");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
		}

	}


