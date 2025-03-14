package com.rental.controller;

import com.rental.dao.CarBookingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/approveBooking")
public class ApproveBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verify admin is logged in (you'll need to adjust this based on how your admin authentication works)


        // Get booking ID from request
        String bookingIdStr = request.getParameter("bookingId");

        if (bookingIdStr != null && !bookingIdStr.isEmpty()) {
            try {
                int bookingId = Integer.parseInt(bookingIdStr);

                // Update booking status to approved
                CarBookingDAO bookingDAO = new CarBookingDAO();
                boolean updated = bookingDAO.updateBookingPermission(bookingId, "approved");

                if (updated) {
                    // Set success message
                    request.getSession().setAttribute("message", "Booking #" + bookingId + " has been approved successfully.");
                } else {
                    // Set error message
                    request.getSession().setAttribute("error", "Failed to approve booking #" + bookingId + ".");
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("error", "Invalid booking ID format.");
            }
        } else {
            request.getSession().setAttribute("error", "Booking ID is required.");
        }

        // Redirect back to the admin bookings page
        response.sendRedirect(request.getContextPath() + "/adminbookings");
    }
}