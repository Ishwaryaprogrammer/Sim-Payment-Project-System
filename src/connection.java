/*
import java.sql.*;

public class connection{
    // JDBC URL, username, and password for connecting to the database
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/database_name";
    static final String USERNAME = "username";
    static final String PASSWORD = "password";

    // Method to register a new user
    public static void registerUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Check if the username already exists
            String checkQuery = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, username);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Username already exists. Please choose a different username.");
                        return;
                    }
                }
            }

            // Insert new user into the database
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setString(1, username);
                insertStatement.setString(2, password);
                insertStatement.executeUpdate();
                System.out.println("User registered successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to authenticate a user during login
    public static boolean authenticateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); // Returns true if a row is found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // Register a new user
        registerUser("user123", "password123");

        // Authenticate a user during login
        boolean isAuthenticated = authenticateUser("user123", "password123");
        if (isAuthenticated) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}
*/