<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="MyPackage.Books" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="images/styles.css">
    <script type="text/javascript">
    	function login(form){
        	if(form.username.value == ""){
        		alert("username can not be empty!");
        		return false;
        	}
        	if(form.password.value == ""){
        		alert("Needs input password!");
        		return false;
        	}
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
					Books book = (Books) session.getAttribute("onebook");  
			     	if(book!=null){
				    %>		
				  	<ul>
					  	 <ol>ISBN:&nbsp;&nbsp;&nbsp;   <%out.println(book.getISBN());%></ol><br>
						<ol> Book Name: &nbsp;&nbsp;&nbsp; <%out.println(book.getBookname());%></ol><br>
						<ol>Authors: &nbsp;&nbsp;&nbsp;  <%out.println(book.getAuthors());%></ol><br>
						<ol>Publisher: &nbsp;&nbsp;&nbsp;  <%out.println(book.getPublishername());%></ol><br>
						 <ol>Number:  &nbsp;&nbsp;&nbsp; <%out.println(book.getBooknumber());%></ol><br>
						<ol>Price:  &nbsp;&nbsp;&nbsp;  <%out.println(book.getPrice());%></ol><br>
					</ul>
					 
					 <%
			     	}
			     	else{
			     		out.println("return book is null.");
			     	}
					 %>  
	   
				  	 </div>
				</div>
		  	</div>
	  </div>
  </body>
</html>