package com.rental.dao;


/**
 *
 * @author Java Programming with Aldrin
 */


import com.rental.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AvailableCarDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/carrentaldb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Kaif46538377";



    private static final String SELECT_AVA_CAR = "SELECT * FROM cars WHERE availability='Available'";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public List<Car> selectAvailableCars() {
        List<Car> avaCars = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVA_CAR)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int carid = rs.getInt("carid");
                String name = rs.getString("name");
                String category = rs.getString("category");
                int noSeats = rs.getInt("noSeats");
                String type = rs.getString("type");
                Double rentalAmountForDay = rs.getDouble("rentalAmountForDay");
                String availability = rs.getString("availability");
                avaCars.add(new Car(carid, name, category, noSeats,type,rentalAmountForDay,availability));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return avaCars;
    }



    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
