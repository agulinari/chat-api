package com.asapp.backend.challenge.persistence.repository.impl;

import com.asapp.backend.challenge.persistence.entities.UserEntity;
import com.asapp.backend.challenge.persistence.repository.api.UserRepository;

import java.sql.*;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {


    @Override
    public UserEntity createUser(String username, String password) {

        String sql = "INSERT INTO users(username,password) VALUES(?,?)";
        int generatedKey = 0;
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/proyectos/db/mydatabase.db");

            connection.setAutoCommit(false); // Starts transaction.
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
            if (generatedKeys.next()) {
                generatedKey = generatedKeys.getInt(1);
            }
            connection.commit(); // Commits transaction.
        } catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return new UserEntity(generatedKey, username, password);

    }

    @Override
    public Optional<UserEntity> getUser(String username, String password) {

        String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
        UserEntity userEntity = null;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/proyectos/db/mydatabase.db");

            PreparedStatement pstmt  = connection.prepareStatement(sql);

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String usr = rs.getString("username");
                String pass = rs.getString("password");
                userEntity = new UserEntity(id, usr, pass);
            }

        } catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        return Optional.ofNullable(userEntity);
    }

}
