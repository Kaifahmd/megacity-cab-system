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


        String bookingIdStr = request.getParameter("bookingId");

        if (bookingIdStr != null && !bookingIdStr.isEmpty()) {
            try {
                int bookingId = Integer.parseInt(bookingIdStr);


                CarBookingDAO bookingDAO = new CarBookingDAO();
                boolean updated = bookingDAO.updateBookingPermission(bookingId, "approved");

                if (updated) {

                    request.getSession().setAttribute("message", "Booking #" + bookingId + " has been approved successfully.");
                } else {

                    request.getSession().setAttribute("error", "Failed to approve booking #" + bookingId + ".");
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("error", "Invalid booking ID format.");
            }
        } else {
            request.getSession().setAttribute("error", "Booking ID is required.");
        }


        response.sendRedirect(request.getContextPath() + "/adminbookings");
    }
}