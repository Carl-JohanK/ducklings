<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Budget Ducklings inc - login</title>
</head>
<body>
<h1>Budget Ducklings inc</h1>
<h3>login</h3>
<form method="post" action="/invoice/login">
  <label>Username</label>
  <input type="text" name="username"/>
  <br>
  <label>Password</label>
  <input type="text" name="password"/>
  <br>
  <button>login</button>
</form>
<br/>
<h3>sign up - no input + valid username/password ""</h3>
<form method="post" action="/sign-up">
  <label>Username</label>
  <input type="text" name="username" required="required"/>
  <br>
  <label>Password</label>
  <input type="text" name="password" required="required"/>
  <br>
  <button>sign up</button>
</form>
</body>
</html>