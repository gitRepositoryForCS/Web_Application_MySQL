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
     <script type="text/javascript">
	    	function myfunction(){
	    		alert("Item successfully added to your cart.");
	    	}
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
						    			Category:
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
						    			ISBN:
						    		</td>
						    	</tr>					 							    	
						    	<tr>
						    		<td>
						    			<input type="text" name="ISBN">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td>
						    			Book Name:
						    		</td>
						    	</tr>
						    	<tr>
						    		<td>
						    			<input type="text" name="bookname">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td>
						    			Low price:
						    		</td>
						    	</tr>
						    	<tr>
						    		<td>
						    			<input type="number" name="lowprice">
						    		</td>
						    			</tr>
						    		<tr>
						    		<td>
						    			High price:
						    		</td>
						    	</tr>
						    		
						    	<tr>
						    		<td>
						    			<input type="number" name="highprice">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td>
						    			Order by price:
						    		</td>
						    	</tr>
						    	
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
						List<Books> result = (List<Books>) request.getAttribute("result");	
				     	Users user = (Users) session.getAttribute("user");
				     	HashMap<Users, HashMap<String, List<Integer>>> userAndAdmin = (HashMap<Users, HashMap<String, List<Integer>>>) session.getAttribute("userAndAdmin");
				     					     	
				  		  if(result == null){
							out.println("return null");
						   }else{ 
				  		    
						    //for(int i=0;i<topic.size();i++) 
						    	for(Books o: result)
							{
				    %>		
				    <form action="SearchByTopic" method="post" onSubmit="return login(this);">
						    <table class="tb1">						    		
				    		<tr>
				    			<td>
				    			<%if(user == null && userAndAdmin == null) {%>
						    		<input type="submit" style = "none" name="onebook" value="<%out.print(o.getBookname());%>">	
						    		<input type="hidden" name="onebook" value= "<%out.println(o.getISBN());%>">					
								<%}else{
									if(user != null)
									{
								%>
									<input type="submit" name="onebook" value="<%out.println(o.getBookname());%>">	
						    		<input type="hidden" name="addtocart" value= "<%out.println(o.getISBN());%>">	
						    		<input type="hidden" name="uid" value= "<%out.println(user.getId());%>">
						    		
						    		<input type="submit" value="Add to cart">
						    	<%
									}
									else
									{ %>
									<form>
									
								  <input type="submit" name="editBook" value="<%out.print(o.getBookname());%>">
							     <a href = "editBook.jsp"> Edit Book<% session.setAttribute("editBook", o);%></a>
									</form>	
									   
									  <% //<input type="submit" name="editBook" value="Edit">	 %> 
						    		
						    		
						    		<form action="Admin" method="post" onSubmit="return login(this);">
						    		   <input type="hidden" name="deletebook" value= "<%out.println(o.getISBN());%>">	
						    		   <input type="submit" value="Delete">
									</form>
									
										
								<%		
									}
						    	}
								%>
						    	
				    			</td>
				    		</tr>
				    		 </table>
						</form>	
				    <% 
							}														
						   }	
					%>	
						   	 
							
				  	 </div>
				</div>
		  	</div>
	  </div>
	</body>
</html>
