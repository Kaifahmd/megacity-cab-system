package com.rental.controller;

import com.rental.dao.UserDAO;
import com.rental.model.User;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/","/user/*"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String action = request.getServletPath();
        if (action.equals("/") && pathInfo == null) {
            homePage(request, response);
            return;
        }
        // Handle /user/* paths
        if (action.equals("/user")) {
            if (pathInfo == null) {
                homePage(request, response);
                return;
            }

            action = pathInfo;
        }
        try {
            switch (action) {
                case "/signup":
                    signupUser(request, response);
                    break;
                case "/login":
                    loginUser(request, response);
                    break;
                case "/logout":
                    logoutUser(request, response);
                    break;
                default:
                    homePage(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void homePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    private void signupUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String idNo = request.getParameter("idNo");
        String password = request.getParameter("password");

        User newUser = new User(name, email, idNo, password, "customer");
        userDAO.insertUser(newUser);

        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.authenticateUser(email, password);

        if (user != null) {

            request.getSession().setAttribute("user", user);

            if (user.getUserType().equals("admin")) {
                response.sendRedirect(request.getContextPath() + "/admin.jsp");
            } else if (user.getUserType().equals("customer")) {
                response.sendRedirect(request.getContextPath() + "/booking.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp?error=invalid_user_type");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=invalid_credentials");
        }
    }



    private void logoutUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

}
