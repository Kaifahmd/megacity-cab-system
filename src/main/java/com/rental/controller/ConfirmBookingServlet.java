package com.rental.controller;

import com.rental.dao.CarBookingDAO;
import com.rental.model.Car;
import com.rental.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/confirmBooking/*")
public class ConfirmBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Car bookedCar = (Car) request.getSession().getAttribute("bookedCar");
        User loggedInUser = (User) request.getSession().getAttribute("user");

        if (bookedCar == null || loggedInUser == null) {
            request.setAttribute("error", "Session expired. Please log in and try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int rentalDays = Integer.parseInt(request.getParameter("rentalDays"));
        double totalRental = bookedCar.getRentalAmountForDay() * rentalDays;
        int userId = loggedInUser.getId();


        CarBookingDAO carBookingDAO = new CarBookingDAO();
        boolean bookingSuccessful = carBookingDAO.addBooking(bookedCar.getCarid(), userId, rentalDays, totalRental);

        if (bookingSuccessful) {

            request.setAttribute("bookedCar", bookedCar);
            request.setAttribute("rentalDays", rentalDays);
            request.setAttribute("totalRental", totalRental);


            RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmation.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "There was an issue with your booking.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmation.jsp");
            dispatcher.forward(request, response);
        }
    }
}
