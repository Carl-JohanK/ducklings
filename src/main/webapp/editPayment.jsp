<%--
  Created by IntelliJ IDEA.
  User: Carl-Johan
  Date: 2024-01-22
  Time: 08:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit payment</title>
</head>
<body>
<form method="post" action="/edit-payment/edit">
    <label>Title on payment you want to change</label>
    <input type="text" name="title" required="required">
    <br><br>
    <label>Change Title</label>
    <input type="text" name="change title" required="required">
    <br>
    <label>Change Description</label>
    <input type="text" name="change description" required="required">
    <br>
    <label>Change Category</label>
    <input type="text" name="change category" required="required">
    <br>
    <label>Change Price</label>
    <input type="number" name="change price" required="required">
    <br>
    <button>Change payment</button>
</form>
<form method="post" action="/edit-payment/delete">
    <label>Title on payment you want to delete</label>
    <input type="text" name="title" required="required">
    <br>
    <button>delete payment</button>
</form>
</body>
</html>
