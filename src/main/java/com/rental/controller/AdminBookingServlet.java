package com.rental.controller;

import com.rental.dao.CarBookingDAO;
import com.rental.model.CarBooking;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/adminbookings/*")
public class AdminBookingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CarBookingDAO bookingDAO = new CarBookingDAO();
        List<CarBooking> allBookings = bookingDAO.getAllBookings();


        request.setAttribute("bookings", allBookings);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminBookings.jsp");
        dispatcher.forward(request, response);
    }
}