<!doctype html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
	<link rel="stylesheet" type="text/css" href="stylelogin.css">
    <title>Student Login</title>
</head>
<body>
	<ul class="navbar">
		<li id="home"><a href="index.html">Home</a></h6>
		<li id="student"><a href="#">Student Login</a></li>
		<li id="faculty"><a href="#">Faculty Login</a></li>
	</ul>
	<div class="login-page">
	<div class="form">
		<h2> Student Login </h2>
		<form class="login-form">
			<input type="text" name="uname" placeholder="name"/>
			<input type="text" name="rno" placeholder="roll number"/>
			<button>login</button>
			<p class="message">Forgot Password? <a href="#">Click Here</a></p>
		</form>
		<%
		String name = request.getParameter("uname");
		String roll = request.getParameter("rno");
		if (name!=null && roll!=null)
		{
			
			%>
			<%@ page import = "java.sql.*" %>
		<%
			try
			{
				Class.forName("com.mysql.jdbc.Driver");  
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql","root","papan2202");
				Statement stmt = conn.createStatement();
				String sqlStr = "SELECT * FROM table1 WHERE name like ";
				sqlStr += "'" + name + "'";
				sqlStr += "AND roll like '"+roll+"'";
				System.out.println("Query statement is " + sqlStr);
				ResultSet rset = stmt.executeQuery(sqlStr);
				while(rset.next())  
				out.println(rset.getString(1)+"  "+rset.getString(2)+"\n");  
				if (rset==null) out.println ("Data not on server\n");
				conn.close();
			}
			catch (Exception e)
			{
				out.println(e.getMessage());
			}
		}
		%>	
	</div>
	</div>
</body>
</html>
