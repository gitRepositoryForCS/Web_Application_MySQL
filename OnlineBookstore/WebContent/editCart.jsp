    <%@ page language="java" contentType="text/html" pageEncoding="GBK"%>
<%@ page import="MyPackage.Users" %>
<%@ page import= "java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<% 
HashMap<String,Integer> e  =(HashMap<String,Integer>) session.getAttribute("editCart");
Users user = (Users) session.getAttribute("user");	
for(String k : e.keySet()){
	

%>
  <form action="EditCart" method="post" onsubmit="return reg(this);">
						    <table align="center" width="450" border="0">
						    	<tr>
						    		<td align="right">Number£º</td>
						    		<td>
						    			<input type="text" value= "<%out.print(e.get(k)); %>" name="number">
						    			<input type="hidden" name="ISBN" value= "<%out.println(k);%>">	
						    			<input type="hidden" name="uid" value= "<%out.println(user.getId());}%>">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td colspan="2" align="center">
						    			<input type="submit" value="Submit">
						    			<input type="reset" value="Reset">
						    		</td>
						    	</tr>
						    </table>
					    </form>



</body>
</html>


