<%@ page language="java" contentType="text/html" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>user login</title>
    <link rel="stylesheet" type="text/css" href="images/styles.css">
    <script type="text/javascript">
    	function login(form){
        	if(form.username.value == ""){
        		alert("username can not be empty!");
        		return false;
        	}
        	if(form.password.value == ""){
        		alert("Needs password!£¡");
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
					
				  	
				  	 <div class="div3"> 
					    <form action="LoginServlet" method="post" onSubmit="return login(this);">
						    <table align="center" width="300" border="0" class="tb1">
						    	<tr>
						    		<td align="right">username£º</td>
						    		<td>
						    			<input type="text" name="uid">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">password£º</td>
						    		<td>
						    			<input type="password" name="password">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td colspan="2" align="center" height="50">
						    			<input type="submit" value="Login">
						    			<input type="reset" value="Reset">
						    		</td>
						    	</tr>
						    </table>
						</form>
				  	 </div>
				</div>
		  	</div>
	  </div>
  </body>
</html>
