package com.asapp.backend.challenge.persistence.repository.impl;

import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.persistence.entities.UserEntity;
import com.asapp.backend.challenge.persistence.repository.api.UserRepository;
import com.asapp.backend.challenge.utils.DatabaseUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {


    @Override
    public UserEntity createUser(String username, String password) throws InvalidUserException {

        String sql = "INSERT INTO users(username,password) VALUES(?,?)";
        int generatedKey = 0;
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(DatabaseUtil.JDCB_URL);

            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
            if (generatedKeys.next()) {
                generatedKey = generatedKeys.getInt(1);
            }
            connection.commit();
        } catch(SQLException e)
        {
            log.error(e.getMessage(), e);
            throw new InvalidUserException();
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
                log.error(e.getMessage(), e);
            }
        }
        return new UserEntity(generatedKey, username, password);

    }

    @Override
    public Optional<UserEntity> getUser(String username, String password) throws SQLException {

        String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
        UserEntity userEntity = null;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DatabaseUtil.JDCB_URL);

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
            log.error(e.getMessage(), e);
            throw e;
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
                log.error(e.getMessage(), e);
            }
        }

        return Optional.ofNullable(userEntity);
    }

}
