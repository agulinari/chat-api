package com.asapp.backend.challenge.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

    public static void init() {

        String sqlUsers = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	username TEXT NOT NULL,\n"
                + "	password TEXT NOT NULL\n"
                + ");";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/proyectos/db/mydatabase.db");
            Statement stmt = connection.createStatement();
            // create a user table
            stmt.execute(sqlUsers);
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
    }

}
