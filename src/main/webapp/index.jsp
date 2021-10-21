<%@ page import="com.example.todoapp.ToDo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.todoapp.HibernateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
    <body>
   <h1>
       <a href="CreateItem">Add Item</a>
   </h1>


        <%
            HibernateUtil hibernateUtil = new HibernateUtil();
            List<ToDo> listItems = hibernateUtil.listAll();

            request.setAttribute("listAll", listItems);
        %>
    <div>
        <table border="1" width="90%">
            <tr>
                <th>ID</th>
                <th>Item</th>
            </tr>
            <c:forEach var="todo" items="${listAll}">

                <tr>
                    <td><c:out value='${todo.getId()}'/></td>
                    <td><c:out value='${todo.getItem()}' /></td>
                    <td>
                        <a href="delete?id=<c:out value='${todo.getId()}'/>">Delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>



    </body>
</html>