<%@ page import="com.example.todoapp.ToDo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.todoapp.HibernateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

        <h2>Add a new item</h2>
        <form method="post" action="${pageContext.request.contextPath }/CreateItem">
            <table cellpadding="2" cellspacing="2">
                <tr>
                    <td>Item</td>
                    <td>
                        <input type="text" name="item">
                    </td>
                </tr>

            </table>
        </form>
        <h3>
            <a href="index.jsp">GO BACK</a>
        </h3>




</body>
</html>
