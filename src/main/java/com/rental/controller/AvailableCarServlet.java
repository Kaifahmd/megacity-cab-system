package com.rental.controller;

import com.rental.dao.AvailableCarDAO;
import com.rental.model.Car;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/availablecars/*") // Map to URL pattern
public class AvailableCarServlet extends HttpServlet {
    private AvailableCarDAO availableCarDAO;

    public void init() {
        availableCarDAO = new AvailableCarDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("book")) {
            bookCar(request, response);
        } else {
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {

            if (pathInfo == null || pathInfo.equals("/")) {
                listAvailableCar(request, response);
            } else if (pathInfo.equals("/book")) {

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAvailableCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Car> listCar = availableCarDAO.selectAvailableCars();
        request.setAttribute("availableListCar", listCar);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/booking-carlist.jsp");
        dispatcher.forward(request, response);
    }

    private void bookCar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int carId = Integer.parseInt(request.getParameter("carId"));
        String carName = request.getParameter("carName");
        String carCategory = request.getParameter("carCategory");
        String carSeats = request.getParameter("carSeats");
        String carType = request.getParameter("carType");
        String carRental = request.getParameter("carRental");
        String carAvailability = request.getParameter("carAvailability");

        Car car = new Car(carId,carName, carCategory, Integer.parseInt(carSeats), carType, Double.parseDouble(carRental), carAvailability);

        request.getSession().setAttribute("bookedCar", car);

        response.sendRedirect(request.getContextPath() + "/bookedcar.jsp");
    }
}
