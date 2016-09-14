<%@ page language="java" contentType="text/html" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="images/styles.css">
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
				  	
				  
</html>