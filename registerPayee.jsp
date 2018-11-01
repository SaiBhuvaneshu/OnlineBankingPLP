<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enter PayeeDetails</title>

<style type="text/css">
td {
	padding: 7px;
	text-align: center;
}

th {
	padding: 10px;
	font-size: 20px;
	font-family: "serif";
	font-color: white;
}

body, html {
	height: 100%;
	font-family: Arial, Helvetica, sans-serif;
	/* The image used */
	background-image:
		url("https://www.indiafilings.com/learn/wp-content/uploads/2016/06/Transfer-of-Bank-Account.jpg");
	min-height: 380px;
	/* Center and scale the image nicely */
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
	position: relative;
	text-decoration: none;
}

.button {
	background-color: #ffb366 /* #ff9900 */;
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

label {
	color: black;
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
	} 
</style>
</head>
<body>
	<div align="center" class="bg-img"></div>


		<div align="center" class="content">
			<c:if test="${!registrationComplete}">
		<h1>Register Beneficiary</h1>
	
			<form:form method="post" action="registerpayeeInfo"
				modelAttribute="registerPayeeDetails">
				<table>
					<tr>
						<td><h3><form:label path="payeeAccountId">Enter To Account ID:</form:label></h3></td>
						<td><form:input path="payeeAccountId" /></td>
						<br/>
						<br/>
						<td><form:errors path="payeeAccountId"></form:errors></td>
					</tr>
					<tr>
						<td><h3><form:label path="nickName">Enter NickName of To Account</form:label></h3></td>
						<td><form:input path="nickName" /></td>
						<br/>
						<br/>
						<td><form:errors path="nickName"></form:errors></td>
					</tr>
				</table>

				<input class="button" type="submit" value="Register">
			</form:form>
			
			<c:if test="${rPayee}"><h4>Provide Account is not registered, please register the account Id</h4></c:if> 
			<c:if test="${invalidPayee}"><h4>Please enter valid Payee account</h4></c:if>
			<c:if test="${alreadyPayee}"><h4>Payee details provided are already registered.Please check details</h4></c:if>
			<c:if test="${multipleAccount}"><h4>You cannot register your account as a payee.</h4></c:if>
			
			</c:if>
	<c:if test="${registrationComplete}">
		<h3 id="head">Payee Details successfully registered</h3>
	</c:if>
			<br/><br/>
			<div class="redirect">
			<a href="redirect" style="color:white;text-decoration: none">Go to Main Menu</a><br />
			<br />
		</div>
			
			</div>
	

</body>
</html>