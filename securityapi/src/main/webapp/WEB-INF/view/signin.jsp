<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>

<h2>Please Login</h2>

<form action="/login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required /><br/><br/>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required /><br/><br/>

    <button type="submit">SIGN IN HERE</button>
</form>

</body>
</html>