
    <%@ page language="java" contentType="text/html" pageEncoding="GBK"%>
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

  
  <form action="Admin" method="post" onsubmit="return reg(this);">
						    <table align="center" width="450" border="0">
						    	<tr>
						    		<td align="right">ISBN��</td>
						    		<td>
						    			<input type="text" name="ISBN">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Book name��</td>
						    		<td>
						    			<input type="text" name="bookname">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Author��</td>
						    		<td>
						    			<input type="text" name="author">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Topic��</td>
						    		<td>
						    			<input type="text" name="topic">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Publisher name��</td>
						    		<td>
						    			<input type="text" name="publishername">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Image��</td>
						    		<td>
						    			<input type="text" name="image">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Book number��</td>
						    		<td>
						    			<input type="text" name="booknumber">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td align="right">Price��</td>
						    		<td>
						    			<input type="text" name="price">
						    		</td>
						    	</tr>
						    	<tr>
						    		<td colspan="2" align="center">
						    			<input type="submit" value="AddBook">
						    			<input type="reset" value="Reset">
						    		</td>
						    	</tr>
						    </table>
					    </form>



</body>
</html>