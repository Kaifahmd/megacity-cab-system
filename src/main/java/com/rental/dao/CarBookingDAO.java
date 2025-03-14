package com.rental.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.rental.model.CarBooking;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class CarBookingDAO {

    // Database connection details
    private String jdbcURL = "jdbc:mysql://localhost:3306/carrentaldb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Kaif46538377";

    public boolean addBooking(int carId, int userId, int rentalDays, double totalRental) {
        String insertSQL = "INSERT INTO carbookings (car_id, user_id, rental_days, total_rental, permission) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setInt(1, carId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, rentalDays);
            preparedStatement.setDouble(4, totalRental);
            preparedStatement.setString(5, "pending"); // Default permission to 'pending'

            // Log the SQL query being executed
            System.out.println("Executing query: " + preparedStatement);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<CarBooking> getAllBookings() {
        List<CarBooking> bookings = new ArrayList<>();
        String query = "SELECT * FROM carbookings";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Check if any data is returned
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No data found in the carbookings table.");
            }

            while (resultSet.next()) {
                int id = resultSet.getInt("booking_id");
                int carId = resultSet.getInt("car_id");
                int userId = resultSet.getInt("user_id");
                int rentalDays = resultSet.getInt("rental_days");
                double totalRental = resultSet.getDouble("total_rental");
                String permission = resultSet.getString("permission");

                // Create a CarBooking object and add it to the list
                CarBooking booking = new CarBooking(id, carId, userId, rentalDays, totalRental, permission);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        // Log the number of bookings fetched
        System.out.println("Bookings fetched: " + bookings.size());
        return bookings;
    }

    public CarBooking getBookingById(int bookingId) {
        CarBooking booking = null;
        String query = "SELECT * FROM carbookings WHERE booking_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter for the query
            preparedStatement.setInt(1, bookingId);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if any data is returned
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No booking found with the specified booking ID.");
            }

            // Fetch the booking details
            if (resultSet.next()) {
                int id = resultSet.getInt("booking_id");
                int carId = resultSet.getInt("car_id");
                int userId = resultSet.getInt("user_id");
                int rentalDays = resultSet.getInt("rental_days");
                double totalRental = resultSet.getDouble("total_rental");
                String permission = resultSet.getString("permission");

                // Create the CarBooking object
                booking = new CarBooking(id, carId, userId, rentalDays, totalRental, permission);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        return booking;
    }

    public List<CarBooking> getBookingsByUserAndPermission(int userId, String permission) {
        List<CarBooking> bookings = new ArrayList<>();
        String query = "SELECT * FROM carbookings WHERE permission = ? AND user_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameters for the query
            preparedStatement.setString(1, permission); // For 'pending' or 'approved'
            preparedStatement.setInt(2, userId); // For the particular logged-in user ID

            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if any data is returned
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No bookings found for the user with the specified permission.");
            }

            while (resultSet.next()) {
                int id = resultSet.getInt("booking_id");
                int carId = resultSet.getInt("car_id");
                int rentalDays = resultSet.getInt("rental_days");
                double totalRental = resultSet.getDouble("total_rental");
                String permissionStatus = resultSet.getString("permission");

                // Create a CarBooking object and add it to the list
                CarBooking booking = new CarBooking(id, carId, userId, rentalDays, totalRental, permissionStatus);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        // Log the number of bookings fetched
        System.out.println("Bookings fetched: " + bookings.size());
        return bookings;
    }

    // Get Approved Bookings by User ID
    public List<CarBooking> getBookingsByUserAndApproved(int userId) {
        return getBookingsByUserAndPermission(userId, "approved");
    }

    // Get Pending Bookings by User ID
    public List<CarBooking> getBookingsByUserAndPending(int userId) {
        return getBookingsByUserAndPermission(userId, "pending");
    }
    // Add this method to your existing CarBookingDAO.java file

    // Add this method to your existing CarBookingDAO.java file

    public boolean updateBookingPermission(int bookingId, String permission) {
        String updateSQL = "UPDATE carbookings SET permission = ? WHERE booking_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, permission);
            preparedStatement.setInt(2, bookingId);

            // Log the SQL query being executed
            System.out.println("Executing update query: " + preparedStatement);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }




}
