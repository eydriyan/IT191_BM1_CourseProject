package com.it191.repository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.it191.model.UserModel;

public class UserRepository extends RepositoryBase {
    public boolean signUpUser(UserModel userModel) {
        String sqlQuery = "INSERT INTO users VALUES (null, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getSqlConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(sqlQuery)) {
                statement.setString(1, userModel.getUsername());
                statement.setString(2, userModel.getPassword());
                statement.setString(3, userModel.getFirstName());
                statement.setString(4, userModel.getLastName());
                statement.setDate(5, new Date(userModel.getDateOfBirth().getTime()));
                statement.setString(6, userModel.getEmail());
                
                // Execute the INSERT query
                int rowsAffected = statement.executeUpdate();
                
                // Check if the user sign-up was successful
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean logInUser(UserModel userModel) {
        return false;
    }

    public void logOutUser(UserModel userModel) {
        //
    }

    private String hashPassword(String RawPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Reference: https://www.baeldung.com/java-password-hashing
        KeySpec spec = new PBEKeySpec(RawPassword.toCharArray(), new byte[16], 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        
        // Reference: https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
    
    public boolean comparePasswords(String password, String hashedPassword) {
        try {
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
