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

        // Retrieve user from session
        User loggedInUser = (User) request.getSession().getAttribute("user");

        // Check if the user is logged in


        int userId = loggedInUser.getId(); // Get userId from the logged-in user object

        // Create CarBookingDAO object to get the bookings for the logged-in user
        CarBookingDAO bookingDAO = new CarBookingDAO();

        // Get the list of approved and pending bookings for the user
        List<CarBooking> approvedBookings = bookingDAO.getBookingsByUserAndApproved(userId);
        List<CarBooking> pendingBookings = bookingDAO.getBookingsByUserAndPending(userId);

        // Set approved and pending bookings as request attributes
        request.setAttribute("approvedBookings", approvedBookings);
        request.setAttribute("pendingBookings", pendingBookings);

        // Forward the request to the status.jsp page for displaying the bookings
        RequestDispatcher dispatcher = request.getRequestDispatcher("/status.jsp");
        dispatcher.forward(request, response);
    }
}
