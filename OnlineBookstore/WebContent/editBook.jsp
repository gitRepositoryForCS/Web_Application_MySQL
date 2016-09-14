
    <%@ page language="java" contentType="text/html" pageEncoding="GBK"%>
    <%@ page import="MyPackage.Books" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="images/styles.css">
        <script type="text/javascript">
	    	function reg(form){
	        	if(form.ISBN.value == ""){
	        		alert("You must provide a ISBN");
	        		return false;
	        	}
	        	if(form.bookname.value == ""){
	        		alert("You must provide bookname");
	        		return false;
	        	}
	        	if(form.author.value == ""){
	        		alert("You must provide author name");
	        		return false;
	        	}
	        	if(form.topic.value == ""){
	        		alert("You must provide topic");
	        		return false;
	        	}
	        	if(form.publishername.value == ""){
	        		alert("You must provide publishername");
	        		return false;
	        	}
	        	if(form.booknumber.value == ""){
	        		alert("You must provide booknumber");
	        		return false;
	        	}
        		if(isNaN(form.booknumber.value)){
        			alert("booknumber must be a number");
        			return false;
        		}
	        	if(form.price.value == ""){
	        		alert("You must provide price");
	        		return false;
	        	}
	        	if(isNaN(form.price.value)){
        			alert("Price must be a number");
        			return false;
        		}
	    	}
	    </script>
</head>
<body>

<% 
     Books book = new Books();
     book = (Books) session.getAttribute("editBook");

%>
  <form action="Admin" method="post" onsubmit="return reg(this);">
						    <table align="center" width="450" border="0">
						    	<tr>
						    		<td align="right">ISBN£º</td>
						    		<td>
						    			<input type="text" value= "<%out.print(book.getISBN()); %>" name="ISBN">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Book name£º</td>
						    		<td>
						    			<input type="text" value= "<%out.print(book.getBookname()); %>" name="bookname">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Author£º</td>
						    		<td>
						    			<input type="text" value= "<%out.print(book.getAuthors()); %>" name="author">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Topic£º</td>
						    		<td>
						    			<input type="text" value= "<%out.print(book.getTopic()); %>" name="topic">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Publisher name£º</td>
						    		<td>
						    			<input type="text" value= "<%out.print(book.getPublishername()); %>" name="publishername">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Image£º</td>
						    		<td>
						    			<input type="text" value= "<%out.print(book.getImage()); %>" name="image">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Book number£º</td>
						    		<td>
						    			<input type="text" value= "<%out.print(book.getBooknumber()); %>" name="booknumber">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Price£º</td>
						    		<td>
						    			<input type="text" value= "<%out.print(book.getPrice()); %>" name="price">
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