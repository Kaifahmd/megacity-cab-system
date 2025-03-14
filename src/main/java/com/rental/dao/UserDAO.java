package com.rental.dao;

import com.rental.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/carrentaldb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Kaif46538377";

    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, email, idNo, password, userType) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_EMAIL = "SELECT id, name, email, idNo, password, userType FROM users WHERE email = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";

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

    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getIdNo());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUserType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public User authenticateUser(String email, String password) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String idNo = rs.getString("idNo");
                String storedPassword = rs.getString("password");
                String userType = rs.getString("userType");

                if (password.equals(storedPassword)) {
                    user = new User(rs.getInt("id"), name, email, idNo, password, userType);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
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
