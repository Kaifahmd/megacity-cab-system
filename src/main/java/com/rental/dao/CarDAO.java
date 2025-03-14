package com.rental.dao;


/**
 *
 * @author Java Programming with Aldrin
 */


import com.rental.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/carrentaldb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Kaif46538377";

    private static final String INSERT_CARS_SQL = "INSERT INTO cars ( name, category, noSeats, type, rentalAmountForDay, availability ) VALUES (?, ?, ?,?,?,?)";
    private static final String SELECT_CAR_BY_ID = "SELECT carid, name, category, noSeats, type, rentalAmountForDay, availability  FROM cars WHERE carid = ?";
    private static final String SELECT_ALL_CARS = "SELECT * FROM cars";
    private static final String SELECT_AVA_CAR = "SELECT * FROM cars";
    private static final String DELETE_CARS_SQL = "DELETE FROM cars WHERE carid = ?";
    private static final String UPDATE_CARS_SQL = "UPDATE cars SET name = ?, category=?, noSeats=?, type=?, rentalAmountForDay=?, availability=?  WHERE carid = ?";

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

    public void insertCar(Car car) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARS_SQL)) {
            preparedStatement.setString(1, car.getName());
            preparedStatement.setString(2, car.getCategory());
            preparedStatement.setInt(3, car.getNoSeats());
            preparedStatement.setString(4, car.getType());
            preparedStatement.setDouble(5, car.getRentalAmountForDay());
            preparedStatement.setString(6, car.getAvailability());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Car selectCar(int carid) {
        Car car = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_BY_ID)) {
            preparedStatement.setInt(1, carid);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                int noSeats = rs.getInt("noSeats");
                String type = rs.getString("type");
                Double rentalAmountForDay = rs.getDouble("rentalAmountForDay");
                String availability = rs.getString("availability");


                car = new Car(carid, name, category, noSeats,type,rentalAmountForDay,availability);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return car;
    }

    public List<Car> selectAllCars() {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int carid = rs.getInt("carid");
                String name = rs.getString("name");
                String category = rs.getString("category");
                int noSeats = rs.getInt("noSeats");
                String type = rs.getString("type");
                Double rentalAmountForDay = rs.getDouble("rentalAmountForDay");
                String availability = rs.getString("availability");
                cars.add(new Car(carid, name, category, noSeats,type,rentalAmountForDay,availability));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cars;
    }


    public boolean deleteCar(int carid) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CARS_SQL)) {
            statement.setInt(1, carid);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateCar(Car car) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CARS_SQL)) {
            statement.setString(1, car.getName());
            statement.setString(2, car.getCategory());
            statement.setInt(3, car.getNoSeats());
            statement.setString(4, car.getType());
            statement.setDouble(5, car.getRentalAmountForDay());
            statement.setString(6, car.getAvailability());
            statement.setInt(7, car.getCarid());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
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


