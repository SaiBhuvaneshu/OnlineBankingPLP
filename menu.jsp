<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
table, th, td {
	border: 1px solid black;
}

td {
	padding: 7px;
	text-align: center;
}

th {
	padding: 10px;
	font-size: 20px;
	font-family: "serif";
}

td:hover {
	background-color: rgb(192, 192, 192);
}

body, html {
	height: 100%;
	font-family: Arial, Helvetica, sans-serif;
	text-decoration: none;
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

* {
	box-sizing: border-box;
}

/* Add styles to the form container */
.container {
	position: absolute;
	right: 0;
	margin: 20px;
	max-width: 300px;
	padding: 16px;
	background-color: white;
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
		<br />
		<h1>Welcome to Bank Application</h1>
		<table>
			<thead>
				<tr>
					<th style="color: #ff9900;"><h3>Pick your operation</h3></th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td><a href="miniStatement"
						style="color: black; text-decoration: none"><b>View Mini
								Statement</b></a></td>
				</tr>
				<tr>
					<td><a href="detailedStatement"
						style="color: black; text-decoration: none"><b>View
								detailed Statement</b></a></td>
				</tr>
				<tr>
					<td><a href="changeAddress"
						style="color: black; text-decoration: none"><b>Change in
								address/mobile communication</b></a></td>
				</tr>
				<tr>
					<td><a href="chequeRequest"
						style="color: black; text-decoration: none"><b>Request for
								Cheque Book/Pass Book/Loan</b></a></td>
				</tr>
				<tr>
					<td><a href="trackService"
						style="color: black; text-decoration: none"><b>Track
								Service request</b></a></td>
				</tr>
				<tr>
					<td><a href="fundTransfer"
						style="color: black; text-decoration: none"><b>Fund
								Transfer</b></a></td>
				</tr>
				<tr>
					<td><a href="changePassword"
						style="color: black; text-decoration: none"><b>Change
								Password</b></a></td>
				</tr>
			</tbody>

		</table>

		<!-- </div> -->
		<br /> <br /> <br />
		<c:if test="${show}">

			<a href="OwnUserAccount" style="color: black; text-decoration: none"><b>Transfer
					to your own bank account across India</b></a>
			<br />
			<br />
			<br />
			<a href="otherAccount" style="color: black; text-decoration: none"><b>Transfer
					to other account of same bank across India</b></a>
			<br />
			<br />
		</c:if>

	</div>
</body>
</html>