package com.rental.controller;

import com.rental.dao.CarBookingDAO;
import com.rental.model.CarBooking;
import com.rental.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/status/*")
public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        User loggedInUser = (User) request.getSession().getAttribute("user");

        int userId = loggedInUser.getId();

        CarBookingDAO bookingDAO = new CarBookingDAO();

        List<CarBooking> approvedBookings = bookingDAO.getBookingsByUserAndApproved(userId);
        List<CarBooking> pendingBookings = bookingDAO.getBookingsByUserAndPending(userId);

        request.setAttribute("approvedBookings", approvedBookings);
        request.setAttribute("pendingBookings", pendingBookings);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/status.jsp");
        dispatcher.forward(request, response);
    }
}
