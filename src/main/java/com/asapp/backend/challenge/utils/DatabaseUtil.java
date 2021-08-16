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

        String sqlMessages = "CREATE TABLE IF NOT EXISTS messages (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	timestamp TEXT NOT NULL,\n"
                + "	sender integer NOT NULL,\n"
                + " recipient integer NOT NULL, \n"
                + " type TEXT NOT NULL, \n"
                + " text TEXT, \n"
                + " FOREIGN KEY(sender) REFERENCES users(id), \n"
                + " FOREIGN KEY(recipient) REFERENCES users(id) \n"
                + ");";

        String sqlImages = "CREATE TABLE IF NOT EXISTS messages_image (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	message_id integer NOT NULL,\n"
                + "	url TEXT NOT NULL, \n"
                + "	height integer NOT NULL, \n"
                + "	width integer NOT NULL, \n"
                + " FOREIGN KEY(message_id) REFERENCES messages(id), \n"
                + ");";

        String sqlVideos = "CREATE TABLE IF NOT EXISTS messages_video (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	message_id integer NOT NULL,\n"
                + "	url TEXT NOT NULL, \n"
                + "	source TEXT NOT NULL, \n"
                + " FOREIGN KEY(message_id) REFERENCES messages(id), \n"
                + ");";

        String sqlUsernameIndex = "CREATE UNIQUE INDEX IF NOT EXISTS username_index \n" +
                "ON users(username);";

        String sqlRecipientIndex = "CREATE INDEX IF NOT EXISTS recipient_index \n" +
                "ON messages(recipient);";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/proyectos/db/mydatabase.db");
            Statement stmt = connection.createStatement();
            // create a user table
            stmt.execute(sqlUsers);
            // create a messages table
            stmt.execute(sqlMessages);
            // create a messages_image table
            stmt.execute(sqlImages);
            // create a messages_video table
            stmt.execute(sqlVideos);
            // create indexes
            stmt.execute(sqlUsernameIndex);
            stmt.execute(sqlRecipientIndex);
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
