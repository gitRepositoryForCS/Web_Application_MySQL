package MyPackage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchByTopic extends HttpServlet {
	private static final long serialVersionUID = -3009431503363456775L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String topic = request.getParameter("topic");
		String isbn = request.getParameter("ISBN");
		String bookname = request.getParameter("bookname");
		String lowprice = request.getParameter("lowprice");
		String highprice = request.getParameter("highprice");
		String orderby = request.getParameter("orderby");
		
		String onebook = request.getParameter("onebook");	
		
		String addtocart=request.getParameter("addtocart");
		String uid=request.getParameter("uid");
		
		UserDao userDao = new UserDao();

		 if(onebook != null){
				Books bn = userDao.queryBookname(onebook);
					request.getSession().setAttribute("onebook", bn);
					request.getRequestDispatcher("bookDetail.jsp").forward(request, response);
			}
		else if(addtocart!=null){
			HashMap<String,Integer> l = userDao.addToCart(uid, addtocart);
			request.getSession().setAttribute("cartlist", l);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		else{
			List<Books> book = ((UserDao)userDao).queryBook(topic,isbn, bookname,lowprice,highprice,orderby);
			if(book != null){
				//RequestDispatcher rd = ;
				request.setAttribute("result", book);
				request.getRequestDispatcher("searchresult.jsp").forward(request, response);
			}
			else{
				request.setAttribute("info", "return null of book");
				request.getRequestDispatcher("searchresult.jsp").forward(request, response);
			}
		}
		
		
		/*if(isbn != null || bookname != null || topic != null){
			List<Books> book = ((UserDao)userDao).queryBook(isbn, bookname, topic);
			if(book != null){
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("result", book);
				rd.forward(request, response);
			}
			else{
				request.setAttribute("info", "return null of book");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
		}
		if(onebook != null){
			Books bn = userDao.queryBookname(onebook);
				request.getSession().setAttribute("onebook", bn);
				request.getRequestDispatcher("bookDetail.jsp").forward(request, response);
		}*/
		 
		 
		 /*
			<%	
			HashMap<String,Integer> l = (HashMap<String, Integer>) session.getAttribute("cartlist");
			if(l!=null){
			for(String s : l.keySet())
			{
				%>		
				<br><% out.println(s);%></br>
				<% 
			}}
			%>
			
			*/
		
	}

}