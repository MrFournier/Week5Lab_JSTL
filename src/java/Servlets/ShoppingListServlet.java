/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 766375
 */
public class ShoppingListServlet extends HttpServlet {

/**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        if(request.getParameter("action") != null && request.getParameter("action").equals("logout")) {
            (session).invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        
        if(session.getAttribute("userName") != null) {
            request.setAttribute("shoppingList", session.getAttribute("items"));
           
           getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
           return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // action is register
        if(request.getParameter("action").equals("register")) {
            String uName = request.getParameter("username");
            
            if((uName != null) || !(uName.equals(" "))) {
                // create the session
                session.setAttribute("username", uName);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
            
          // action is add
        } else if(request.getParameter("action").equals("add")) {
            if(session.getAttribute("shoppingList") == null) {
                ArrayList<String> items = new ArrayList<String>();
                session.setAttribute("shoppingList", items);
                ((ArrayList<String>) session.getAttribute("shoppingList")).add(request.getParameter("item"));
            } else if((request.getParameter("item") != null) || !(request.getParameter("item").equals(" "))){
                ((ArrayList<String>) session.getAttribute("shoppingList")).add(request.getParameter("item"));
            }
            
          // action is delete
        } else if(request.getParameter("action").equals("delete")) {
            String item = (String) request.getParameter("radioBtn");
           ((ArrayList<String>) session.getAttribute("shoppingList")).remove(item);
           
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }
}
