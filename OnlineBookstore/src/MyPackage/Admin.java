package MyPackage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin extends HttpServlet {
	private static final long serialVersionUID = -3009431503363456775L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ISBN = request.getParameter("ISBN");
		String bookname = request.getParameter("bookname");	
		String author = request.getParameter("author");
		String topic = request.getParameter("topic");
		String publishername = request.getParameter("publishername");
		String image = request.getParameter("image");
		String number = request.getParameter("booknumber");
		Integer booknumber =-1;
		Double price= 0.0;
		if(number!=null){
			booknumber = (Integer.parseInt(number));
		}
		if(request.getParameter("price")!=null){
			price = (Double.parseDouble(request.getParameter("price"))); 
		}
	
		String deleteISBN = request.getParameter("deletebook");
		
		UserDao userDao = new UserDao();
		
		if(deleteISBN != null){
			 
			String result = userDao.deleteBook(deleteISBN);
			if(result =="succeed"){
				request.setAttribute("info", "deleted the book successfully.");
				request.getRequestDispatcher("AEDresult.jsp").forward(request, response);
			}
			else{
				request.setAttribute("info", result);
				request.getRequestDispatcher("AEDresult.jsp").forward(request, response);
			}
		}
		else{
			if(ISBN != null && !ISBN.isEmpty()){
				
				Books book = new Books();		
				book.setISBN(ISBN);	
				book.setBookname(bookname);
				book.setAuthors(author);	
				book.setTopic(topic);
				book.setPublishername(publishername);
				book.setImage(image);
			 	book.setBooknumber(booknumber);
			 	book.setPrice(price);
			 	
				String result = userDao.insertBook(book);	
			   
				if(result =="succeed"){
					request.setAttribute("info", "added the book successfully.");
					request.getRequestDispatcher("AEDresult.jsp").forward(request, response);
				}
				else{
					request.setAttribute("info", result);
					request.getRequestDispatcher("AEDresult.jsp").forward(request, response);
				}
				
			}else{
				request.setAttribute("info", "ISBN is not valid.");
				request.getRequestDispatcher("AEDresult.jsp").forward(request, response);
			}
			
		}

		
		
	
		
		
		
	}

}