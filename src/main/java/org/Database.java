package org;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_database";
    private static final String DB_USER = "username";
    private static final String DB_PASS = "password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            // INSERT
            String insertQuery = "INSERT INTO users (username, email) VALUES (?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, "newuser");
            insertStatement.setString(2, "newuser@example.com");
            int insertedRows = insertStatement.executeUpdate();
            System.out.println("Inserted " + insertedRows + " row(s)");

            // SELECT
            String selectQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, "newuser");
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username") +
                        ", Email: " + resultSet.getString("email"));
            }

            //UPDATE
            String updateQuery = "UPDATE users SET email = ? WHERE username = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, "updated@example.com");
            updateStatement.setString(2, "newuser");
            int updatedRows = updateStatement.executeUpdate();
            System.out.println("Updated " + updatedRows + " row(s)");

            // DELETE
            String deleteQuery = "DELETE FROM users WHERE username = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, "newuser");
            int deletedRows = deleteStatement.executeUpdate();
            System.out.println("Deleted " + deletedRows + " row(s)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
