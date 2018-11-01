<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction Details</title>

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


		<form:form method="post" action="../ownAcc/${PayId}"
			modelAttribute="tranPwd">
			<br />
			<br />


			<br />
			<form:label path="transactionPassword">
				<b><h2>Enter Transaction Password:</h2></b>
			</form:label>
			<form:input path="transactionPassword" type="password" />
			<br />
			<form:errors path="transactionPassword"></form:errors>
			<br />
			<br />

			<c:if test="${transaction}">
				<%-- <c:out value="${count}"></c:out>  --%>
				<input class="button" type="submit" value="Submit" />
			</c:if>

			<c:if test="${!transaction}">
				<%-- <c:out value="${count}"></c:out> --%>
				<input class="button" type="submit" value="Submit" disabled="true" />
			</c:if>
		</form:form>

		<c:if test="${show}">
			<c:out value="Invalid Transaction Password">
				<br />
			</c:out>
		</c:if>


		<br /> <br /> <br /> <br />

		<c:if test="${amountForm}">


			<c:choose>
				<c:when test="${append == 1}">

					<div align="center">
						<form:form method="post" action="../payPayee"
							modelAttribute="amount">
							<form:label path="accountBalance"><b><h2>Enter Amount to be Transferred:</h2></b></form:label>
							<form:input path="accountBalance" />
							<br />
							<form:errors path="accountBalance"></form:errors>

							<input class="button" type="submit" value="Submit" />
							<br />
							<br />
							<c:if test="${negative}">
								<span style="color: red;">AccountBalance should be
									greater than 0.</span>
							</c:if>
						</form:form>
					</div>
				</c:when>

				<c:when test="${append == 0}">

					<div align="center">
						<form:form method="post"
							action="../OnlineBanking_Spring1/payPayee"
							modelAttribute="amount">
							<form:label path="accountBalance"><b><h2>Enter Amount to be Transferred:</h2></b></form:label>
							<form:input path="accountBalance" />
							<br />
							<form:errors path="accountBalance"></form:errors>

							<input class="button" type="submit" value="Submit" />
							<br />
							<br />
							<c:if test="${negative}">
								<span style="color: red;">AccountBalance should be
									greater than 0.</span>
							</c:if>
						</form:form>
						<br /> <br /> <br />
					</div>
				</c:when>
			</c:choose>



			<%-- <div align="center">
				<form:form method="post" action="../payPayee"
					modelAttribute="amount">
					<form:label path="accountBalance">
						<h2>
							<b>Enter Amount to be Transferred:</b>
						</h2>
					</form:label>
					<form:input path="accountBalance" />
					<br />
					<br />
					<br />
					<form:errors path="accountBalance"></form:errors>

					<input class="button" type="submit" value="Submit" />
				</form:form>
				<br />
				<br />
				<br />
			</div> --%>
		</c:if>
		<div class="redirect">
			<a href="redirect" style="color: white; text-decoration: none">Go
				to Main Menu</a><br /> <br />
		</div>
	</div>
</body>
</html>