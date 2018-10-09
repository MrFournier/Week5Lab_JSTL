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
        if(request.getParameter("action") == "register") {
            String uName = request.getParameter("username");
            
            if((uName != null) || !(uName.equals(" "))) {
                // create the session
                session.setAttribute("userName", uName);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
 
          // action is add
        } else if(request.getParameter("action") == "add") {
            if(session.getAttribute("itemList") == null) {
                ArrayList items = new ArrayList();
                session.setAttribute("itemList", items);
            } else {
//                ArrayList items = (ArrayList) session.getAttribute("items");
//                items.add(request.getParameter("item"));
                ((ArrayList) session.getAttribute("items")).add(request.getParameter("item"));
                request.setAttribute("shoppingList", session.getAttribute("items"));
            }
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
            
          // action is delete
        } else if(request.getParameter("action") == "delete") {
            String item = (String) request.getAttribute("radioBtn");
           ((ArrayList) session.getAttribute("items")).indexOf(item);
            
           request.setAttribute("shoppingList", session.getAttribute("items"));
           
           getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
           return;
          // action is logout  
        } else if(request.getParameter("action") == "logout") {
            (session).invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }
}
