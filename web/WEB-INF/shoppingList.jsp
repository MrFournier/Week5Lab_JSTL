<%-- 
    Document   : shoppingList
    Created on : Oct 9, 2018, 12:10:11 PM
    Author     : 766375
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1><br>
        
        <h4>Hello, ${username} <a href="?action=logout">Logout</a></h4><br>
        
        <h3>List</h3>
        <form  action="" method="POST">
            <input type="text" name="item">
            <input type="hidden" name="action" value="add" >
            <input type="submit" value="Add" >
        </form>
        
    <c:if test="${shoppingList != null}">
        <form action="" method="POST">
            <ul>
                <c:forEach var="item" items="${shoppingList}" >
                    <li><input type="radio" name="radioBtn" value="${item}" >${item}</li><br>
                </c:forEach>
            </ul>
            <input type="hidden" name="action" value="delete" >
            <input type="submit" value="Delete" >
        </form>
    </c:if>
    </body>
</html>
