<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">

	td{
		padding:7px;
		text-align:center;
		
	}
	th{
		padding:10px;
		font-size:20px;
		font-family:"serif";
		font-color:white;
		
	}
	body, html{
	    height: 100%;
	    font-family: Arial, Helvetica, sans-serif;    	
    	
	}
	.button{
		background-color:#ffb366 /* #ff9900 */;
	    border: none;
	    color: white;
	    padding: 10px 28px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	    margin: 4px 2px;
	    cursor: pointer;
	}
.bg-img {
	background-image:
		url("https://www.indiafilings.com/learn/wp-content/uploads/2016/06/Transfer-of-Bank-Account.jpg");
	background-size: cover;
	background-position: center;
	display: block;
	filter: blur(5px);
	-webkit-filter: blur(5px);
	height: 800px;
	left: 0;
	position: fixed;
	right: 0;
	z-index: 1;
}

.content {
	background: rgba(255, 255, 255, 0.35);
	border-radius: 3px;
	box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
	font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
	top: 10px;
	left: 0;
	position: fixed;
	margin-left: 20px;
	margin-right: 20px;
	right: 0;
	z-index: 2;
	padding: 0 10px;
}
	#head,.row,label{
	color: black;
	 background-color:#ffb366 
	} 
	</style> 
</head>
<body>
	 <div align="center" class="bg-img"> </div>
	<div align="center" class="content">
	<h2 id="head">Beneficiary Details</h2><br/>

		<form:form method="post" action="userAccount">
			<table align="center" border="1">

				<tr class ="row">
					<th>Payee Account Id</th>
					<th>Action</th>
				</tr>

				<c:forEach items="${ownAccList}" var="ownAcc">
					<tr class ="row">
						<td>${ownAcc}</td>						
						<td><a href="ownAcc/${ownAcc}" style="color:white">Pay</a></td>
					</tr>
				</c:forEach>
				<br/>
			</table>
		</form:form>
		<br/>
		<br/>
		<br/>
			<div class="redirect">
			<a href="redirect" style="color:white;text-decoration: none">Go to Main Menu</a><br />
			<br />
		</div>
	</div>

</body>
</html>