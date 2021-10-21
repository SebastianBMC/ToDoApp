package com.example.todoapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateItem", value = "/CreateItem")
public class CreateItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/AddItem.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HibernateUtil hib = new HibernateUtil();
        ToDo item = new ToDo();

        //item.setId(Integer.parseInt(request.getParameter("id")));
        item.setItem(request.getParameter("item"));

        if(hib.createItem(item))
        {
            request.setAttribute("msg","Item has been added!");
        }else
        {
            request.setAttribute("msg", "Could not add item");
        }
        request.getRequestDispatcher("/AddItem.jsp").forward(request, response);
    }
}
