<%@ page language="java" contentType="text/html" pageEncoding="GBK"%>
<%@ page import="MyPackage.Users" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Registration</title>
    	<link rel="stylesheet" type="text/css" href="images/styles.css">
        <script type="text/javascript">
	    	function reg(form){
	        	if(form.username.value == ""){
	        		alert("You must provide a user name");
	        		return false;
	        	}
	        	if(form.password.value == ""){
	        		alert("You must provide a password");
	        		return false;
	        	}
	        	if(form.repassword.value == ""){
	        		alert("Confirm");
	        		return false;
	        	}
	        	if(form.password.value != form.repassword.value){
	        		alert("The password does Not amtch");
	        		return false;
	        	}
	        	if(form.fullname.value == ""){
	        		alert("You must provide your name");
	        		return false;
	        	}
	        	if(form.email.value == ""){
	        		alert("You must provide a e-mail");
	        		return false;
	        	}
	    	}
	    	function change(){
				var photo = document.getElementById("photo");
				var photoImg = document.getElementById("photoImg");
				photoImg.src = photo.value;
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
				  	</div>
				  	 <div class="div3"> 
				  	 <% 
				  	 	Users user = (Users) session.getAttribute("user");
						if(user == null){
						%>
					    <form action="RegServlet" method="post" onsubmit="return reg(this);">
						    <table align="center" width="450" border="0">
						    	<tr>
						    		<td align="right">User Name£º</td>
						    		<td>
						    			<input type="text" name="uid">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Enter password£º</td>
						    		<td>
						    			<input type="password" name="password">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Type it again£º</td>
						    		<td>
						    			<input type="password" name="repassword">
						    		</td>
						    	</tr>
						    	
						    	<tr>
						    		<td align="right">Your name£º</td>
						    		<td>
						    			<input type="text" name="fullname">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">E-mail address£º</td>
						    		<td>
						    			<input type="text" name="email">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td colspan="2" align="center">
						    			<input type="submit" value="Register">
						    			<input type="reset" value="Reset">
						    		</td>
						    	</tr>
						    </table>
					    </form>
					    <%								
							}else{
								out.println("<br>You can not register.");
							}
						%>
				  	 </div>
				</div>
		  	</div>
	  </div>
  </body>
</html>
