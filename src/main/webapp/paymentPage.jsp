<%--
  Created by IntelliJ IDEA.
  User: Carl-Johan
  Date: 2024-01-19
  Time: 09:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>payment</title>
</head>
<body>
<form method="post" action="/my-payment">
    <label>Title</label>
    <input type="text" name="title">
<br>
    <label>Description</label>
    <input type="text" name="description">
<br>
    <label>Category</label>
    <input type="text" name="category">
<br>
    <label>Price</label>
    <input type="number" name="price">
<br>
    <button>create new payment</button>
</form>
</body>
</html>
