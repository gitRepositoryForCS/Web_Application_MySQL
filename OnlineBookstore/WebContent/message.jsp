<%@ page language="java" contentType="text/html" pageEncoding="GBK"%>
<%@ page import="MyPackage.Users" %>
<%@ page import="MyPackage.Books" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import= "java.util.List"%>
<%@ page import= "java.util.HashMap"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Information</title>
		<link rel="stylesheet" type="text/css" href="images/styles.css">
    </script>
		
	</head>

	<table border="0" cellpadding="0" cellspacing="0" width="777">
			<td>
					<p align="center"><font size="5" color="#000080">
					<a href="index.jsp">Home Page</a>&nbsp;
					<a href="reg.jsp">Sign Up</a>
					<a href="login.jsp">Log in</a>&nbsp;     
					<a href="message.jsp">Your Information</a>&nbsp;
					<a href="ExitServlet">Log out</a>&nbsp;</font>
			</td>
	</table>

	<body>
		<div align="center">
		  	<div class="div1">
		  		<div class="top"></div>
		  		<div class="bottom">
					<div class="div2">
					<ul>			  			
				  			<li>	 						     						     
						     <form action="SearchByTopic" method="post" onSubmit="return login(this);">
						    	<table class="tb1">
						    	<tr>
						    		<td>
						    			<p>Category:</p>
						    		</td>
						    	</tr>
						    	<tr>
						    	<td>
								     <select name="topic"> 
								        <option></option>
								        <option value="art">art</option> 
								        <option value="business">business</option> 
								        <option value="cs">cs</option> 
								        <option value="eco">eco</option> 
								        <option value="edu">edu</option>
								        <option value="engineering">engineering</option> 
								        <option value="health">health</option> 
								        <option value="history">history </option> 
								        <option value="lit">lit</option> 
								        <option value="math">math</option>
								        <option value="phy">phy</option>
								        <option value="sport">sport</option>
								     </select> 
								     </td>
								 </tr>	
								 	<tr>
						    		<td>
						    			<p>ISBN:</p>
						    		</td>
						    	</tr>					 							    	
						    	<tr>
						    		<td>
						    			<input type="text" name="ISBN">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td>
						    			<p>Book Name:</p>
						    		</td>
						    	</tr>
						    	<tr>
						    		<td>
						    			<input type="text" name="bookname">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td>
						    			<p>Low price:</p>
						    		</td>
						    	</tr>
						    	<tr>
						    		<td>
						    			<input type="number" name="lowprice">
						    		</td>
						    			<tr>
						    		<td>
						    			<p>High price:</p>
						    		</td>
						    	</tr>
						    		</tr>
						    		<tr>
						    		<td>
						    			<input type="number" name="highprice">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td>
						    			<p>Order by price:</p>
						    		</td>
						    	</tr>
						    	<tr>
						    	<tr>
						    	<td>
								     <select name="orderby"> 
								        <option value="lowtohigh">Low to high</option> 
								        <option value="hightolow">High to low</option>     
								     </select> 
								     </td>
								 </tr>
						    	<tr>
						    		<td>
						    			<input type="submit" value="Search">
						    		</td>
						    	</tr>
						    	</table>
							</form>																																		
				  			</li>
				  		</ul>				  		
				  	</div>

				  	 <div class="div3"> 
				       <% 
							String info = (String)request.getAttribute("info");
					    	
							if(info != null){
								%>
								
								<p align = "center">
								<%out.println(info); %>
								</p>
							<% 	
							}
					    	
							Users user = (Users) session.getAttribute("user");	
							HashMap<String,Integer> cartlist = (HashMap<String, Integer>) session.getAttribute("cartlist");
							HashMap<Users, HashMap<String, List<Integer>>> userAndAdmin = (HashMap<Users, HashMap<String, List<Integer>>>) session.getAttribute("userAndAdmin");	
							
							HashMap<String,Integer> checkout = new HashMap<String, Integer>();
							
							if(user != null){
						%>
						<table align="center" width="350" border="1" height="200" bordercolor="#E8F4CC">
							<tr>
					    		<td align="center" colspan="2">
					    			<span style="font-weight: bold;font-size: 18px;"></span>
					    			Your Information
					    		</td>
					    	</tr>
					    	<tr>
					    		<td align="right" width="30%">Your ID£º</td>
					    		<td><%=user.getId() %></td>
					    		<td>					    			
					    		</td>
					    	</tr>
					    	<tr>
					    		<td align="right">Your Name£º</td>
					    		<td><%=user.getFullname() %></td>
					    	</tr>
					    	<tr>
					    		<td align="right">E-mail£º</td>
					    		<td><%=user.getEmail()%></td>
					    	</tr>
					    	<%
					    	
					    	if(cartlist==null){
					    	
					    	HashMap<String,Integer> h = user.getBooklist();  
					    	for(String k : h.keySet())
							{	 
					    		HashMap<String,Integer> e = new HashMap<String,Integer>();
					    		e.put(k, h.get(k));
					    		if(!checkout.containsKey(k)){
						    		checkout.put(k,h.get(k));}
					         %>
					    		<tr>
					    		<td align="right">ISBN: </td>
					    		<td><%out.println(k);%></td>
					    		<td align="right">Number: </td>
					    		<td><%out.println(h.get(k)); %></td>
					    		<td>	
					    		     
							       <a href = "editCart.jsp">Edit<% session.setAttribute("editCart",e);}%></a>
								
					    		</td>
					    	</tr>	
					    	<% 
							}else{
								HashMap<String,Integer> h = user.getBooklist();  
								for(String k : cartlist.keySet())
								{
									HashMap<String,Integer> e = new HashMap<String,Integer>();
						    		e.put(k, h.get(k));
						    		if(!checkout.containsKey(k))
						    		{checkout.put(k,h.get(k));}
									%>		
									<tr>
					    		<td align="right">ISBN: </td>
					    		<td><%out.println(k);%></td>
					    		<td align="right">Number: </td>
					    		<td><%out.println(cartlist.get(k));%></td>
					    		<td>	
					    		     
							       <a href = "editCart.jsp">Edit<% session.setAttribute("editCart",e);}%></a>
								
					    		</td>
					    	   </tr>
						    <% 
								 cartlist.clear();
							}
					    	String checked =user.getId().trim();
					    	for(String k : checkout.keySet()){
					    		checked =checked +"/" + k.trim();
					    	}
					    	
					    	%>
						</table>
						 <form action="CheckOut" method="post" onSubmit="return login(this);">
						    <table align="center" width="300" border="0" class="tb1">
						    	<tr>
						    		<td colspan="2" align="center" height="50">
						    			  <input type="hidden" name="checkout" value= "<%out.println(checked);%>">
						    			<input type="submit" value="Check Out">
						    		</td>
						    	</tr>
						    </table>
						</form>
						    <%								
						    	} else if(userAndAdmin!=null)
						        { 
									for(Users k : userAndAdmin.keySet())
						        	{%>
						        	
						    <table align="center" width="350" border="1" height="150" bordercolor="#E8F4CC">
							<tr>
							<td>
					    			Admin Information
					    	</td>
					    	</tr>
					    	<tr>
								      <td>Categories</td> 
								      <td>Number of books</td>
								      <td>books that are in cart</td>   
							</tr>
						        	
						        	<% 
						            	//HashMap<Users, HashMap<String, List<Integer>>>
						        		HashMap<String, List<Integer>> h = userAndAdmin.get(k);
						        	    HashMap<String, List<Integer>> usernumber = new HashMap<String, List<Integer>>();;
						        		for(String key : h.keySet())
						        		{
						        			List<Integer> list = h.get(key);
						        			 if(list.size()>1){
						             %>

							<tr>
								      <td><%out.println(key);%></td>
								      <td><%out.println(list.get(0));%></td>
								      <td><%out.println(list.get(1)); }else{%></td>
					    	</tr>
					                 <%  usernumber.put(key, h.get(key));}} %>
					    	
					     

					    	<tr>
					    	 		<td>total users: <%out.println(usernumber.get("users").get(0));}%></td>

					    	</tr>
					    	
					    	
				 					   <form action="addBook.jsp" method="post" onSubmit="return login(this);">
						   <table class="tb1">						    		
				    		<tr>
				    			<td>
						    		<input type="submit" value="Add a new book" align = "right">	
				    			</td>
				    		</tr>
				    		 </table>
						     </form>
					    
							</table>
						
					
						        
					 	           					        	
						    <%
						        } else{
								    %> 
						    	<p align= "center">	<%out.println("<br>Your state is log out."); %></p>
							   <%  } %>
												
				  	 </div>
				</div>
		  	</div>
	  </div>
	</body>
</html>
