package com.rental.controller;


/**
 *
 * @author Java Programming with Aldrin
 */

import com.rental.dao.CarDAO;
import com.rental.model.Car;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/car/*")
public class CarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarDAO carDAO = new CarDAO();

    public void init() {
        carDAO = new CarDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/list";
        }
        try {
            switch (pathInfo) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCar(request, response);
                    break;
                case "/delete":
                    deleteCar(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCar(request, response);
                    break;
                case "/list":
                default:
                    listCar(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Car> listCar = carDAO.selectAllCars();
        request.setAttribute("listCar", listCar);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/car-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/car-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int carid = Integer.parseInt(request.getParameter("carid"));
        Car existingCar = carDAO.selectCar(carid);
        request.setAttribute("car", existingCar);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/car-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String category  = request.getParameter("category");
        int noSeats  = Integer.parseInt(request.getParameter("noSeats"));
        String type  = request.getParameter("type");
        double rentalAmountForDay  =  Double.parseDouble(request.getParameter("rentalAmountForDay"));
        String availability  = request.getParameter("availability");
        Car newCar = new Car(name, category, noSeats, type, rentalAmountForDay, availability);
        carDAO.insertCar(newCar);
        response.sendRedirect(request.getContextPath() + "/car/list");
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int carid = Integer.parseInt(request.getParameter("carid"));
        String name = request.getParameter("name");
        String category  = request.getParameter("category");
        int noSeats  = Integer.parseInt(request.getParameter("noSeats"));
        String type  = request.getParameter("type");
        double rentalAmountForDay  =  Double.parseDouble(request.getParameter("rentalAmountForDay"));
        String availability  = request.getParameter("availability");

        Car updatedCar = new Car(carid, name, category, noSeats, type, rentalAmountForDay, availability);
        carDAO.updateCar(updatedCar);
        response.sendRedirect(request.getContextPath() + "/car/list");
    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int carid = Integer.parseInt(request.getParameter("carid"));
        carDAO.deleteCar(carid);
        response.sendRedirect(request.getContextPath() + "/car/list");
    }
}


