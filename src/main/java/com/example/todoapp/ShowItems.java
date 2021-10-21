package com.example.todoapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.todoapp.HibernateUtil;

@WebServlet("/")
public class ShowItems extends HttpServlet {

    private HibernateUtil hibernateUtil;

    public void init()
    {
        hibernateUtil = new HibernateUtil();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {


                case "/delete":
                    deleteItem(request, response);
                    break;

                    default:
                    listItems(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

        //request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);



    }

    private void listItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
    {
        List<ToDo> listItems = hibernateUtil.listAll();

        request.setAttribute("listAll", listItems);
        for(ToDo items: listItems)
        {
            System.out.println(items.getItem());
        }
        System.out.println(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        hibernateUtil.deleteItem(id);
        response.sendRedirect("list");
    }


}
