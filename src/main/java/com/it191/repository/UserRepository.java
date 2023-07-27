package com.it191.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.it191.model.UserModel;

public class UserRepository extends RepositoryBase {
    public boolean setUserInfo(UserModel userModel) {
        try (Connection connection = getSqlConnection()) {
            // Check if a user with the same email already exists
            String checkQuery = "SELECT COUNT(*) as count FROM User WHERE email = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, userModel.getEmail());
            ResultSet checkResult = checkStatement.executeQuery();

            if (checkResult.next()) {
                int count = checkResult.getInt("count");
                if (count > 0) {
                    return false;
                }
            }

            
            // Create the SQL query to insert user data
            String insertQuery = "INSERT INTO User (first_name, last_name, dob, email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, userModel.getFirstName());
            statement.setString(2, userModel.getLastName());
            statement.setDate(3, Date.valueOf(userModel.getDateOfBirth()));
            statement.setString(4, userModel.getEmail());

            // Execute the insert query
            int rowsAffected = statement.executeUpdate();

            // Return true if at least one row was affected (insert successful)
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., log the error, throw a custom exception, etc.)
            return false;
        }
    }

    public UserModel getUserInfo(int userId) {
        try (Connection connection = getSqlConnection()) {
            // Create the SQL query to retrieve user data
            String selectQuery = "SELECT * FROM User WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, userId);

            // Execute the select query
            ResultSet resultSet = statement.executeQuery();

            // Check if a record was found
            if (resultSet.next()) {
                // Map the result set to a UserModel object
                UserModel userModel = new UserModel();
                userModel.setUserId(resultSet.getInt("user_id"));
                userModel.setFirstName(resultSet.getString("first_name"));
                userModel.setLastName(resultSet.getString("last_name"));
                userModel.setDateOfBirth(resultSet.getDate("dob").toLocalDate());
                userModel.setEmail(resultSet.getString("email"));
                return userModel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
