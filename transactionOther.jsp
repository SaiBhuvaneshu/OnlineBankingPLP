<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enter Transaction Details</title>

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
</style>
</head>
<body>
	<div align="center" class="bg-img"></div>


	<div align="center" class="content">
		<c:if test="${transDescription}">

			<br />
			<c:if test="${inValidDescription}">
				<h2>Transaction Description should be atleast 3 letter long</h2>
			</c:if>

			<form:form method="post" action="transactionInfo"
				modelAttribute="transactionDetails">

				<table>
					<tr>
						<td><form:label path="tranAmount">
								<h3>
									<b>Enter Transaction Amount:</b>
								</h3>
							</form:label></td>
						<td><form:input path="tranAmount" /></td>
						<br />
						<br />
						<c:if test="${nAmount}">
							<h3>Amount should not be negative</h3>
						</c:if>
						<br />
						<c:if test="${notValidAmount}">
							<h3>Transaction amount exceeds your account balance</h3>
						</c:if>
						<td><form:errors path="tranAmount"></form:errors></td>
					</tr>
					<tr>
						<td><form:label path="tranDescription">
								<h3>
									<b>Enter Transaction Description:</b>
							</form:label>
							</h3></td>
						<td><form:input path="tranDescription" /></td>
						<br />
						<br />
						<td><form:errors path="tranDescription"></form:errors></td>
					</tr>

				</table>
				<input class="button" type="submit" value="Enter">
			</form:form>
	</div>
	</c:if>

	<c:if test="${transPassword}">

		<div align="center">

			<form:form method="post" action="transactionPassInfo"
				modelAttribute="transPass">
				<table>
					<tr>
						<td><form:label path="transactionPassword">
								<h3>
									<b>Enter Transaction Password:</b>
								</h3>
							</form:label></td>
						<td><form:input path="transactionPassword" type="password" /></td>
						<br />
						<br />
						<td><form:errors path="transactionPassword"></form:errors></td>
					</tr>
				</table>
				<input class="button" type="submit" value="Enter">
			</form:form>
			<c:if test="${notValidPassword}">Please provide valid credentials</c:if>
		</div>
	</c:if>
	<br />
	<br />
	<div class="redirect">
		<a href="redirect" style="color: white; text-decoration: none">Go
			to Main Menu</a><br /> <br />
	</div>
</body>
</html>