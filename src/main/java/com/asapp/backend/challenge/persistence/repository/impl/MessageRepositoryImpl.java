package com.asapp.backend.challenge.persistence.repository.impl;

import com.asapp.backend.challenge.persistence.entities.ImageMessageEntity;
import com.asapp.backend.challenge.persistence.entities.MessageEntity;
import com.asapp.backend.challenge.persistence.entities.UserEntity;
import com.asapp.backend.challenge.persistence.entities.VideoMessageEntity;
import com.asapp.backend.challenge.persistence.repository.api.MessageRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageRepositoryImpl implements MessageRepository {

    @Override
    public List<MessageEntity> getMessages(Integer recipient, Integer start, Integer limit) {
        String sql = "SELECT id, url, height, width FROM messages WHERE recipient = ? and id >= ? LIMIT ?";
        UserEntity userEntity = null;
        Connection connection = null;
        List<MessageEntity> messages = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/proyectos/db/mydatabase.db");

            PreparedStatement pstmt  = connection.prepareStatement(sql);

            pstmt.setInt(1,recipient);
            pstmt.setInt(2, start);
            pstmt.setInt(3, limit);

            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String timestamp = rs.getString("timestamp");
                int sender = rs.getInt("sender");
                int rec = rs.getInt("recipient");
                String type = rs.getString("type");
                String text = rs.getString("text");

                LocalDateTime ts = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_DATE_TIME);
                MessageEntity message = new MessageEntity(id,ts,sender, rec, type, text);
                messages.add(message);
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

        return messages;
    }

    @Override
    public Optional<ImageMessageEntity> getImageMessage(Integer messageId) {
        String sql = "SELECT id, url, height, width FROM messages_image WHERE message_id = ? ";
        ImageMessageEntity entity = null;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/proyectos/db/mydatabase.db");

            PreparedStatement pstmt  = connection.prepareStatement(sql);

            pstmt.setInt(1,messageId);

            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String url = rs.getString("url");
                int height = rs.getInt("height");
                int width = rs.getInt("width");
                entity = new ImageMessageEntity(id, messageId, url, height, width);
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

        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<VideoMessageEntity> getVideoMessage(Integer messageId) {
        String sql = "SELECT id, url, source FROM messages_video WHERE message_id = ? ";
        VideoMessageEntity entity = null;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/proyectos/db/mydatabase.db");

            PreparedStatement pstmt  = connection.prepareStatement(sql);

            pstmt.setInt(1,messageId);

            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String url = rs.getString("url");
                String source = rs.getString("source");
                entity = new VideoMessageEntity(id, messageId, url, source);
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

        return Optional.ofNullable(entity);
    }

    @Override
    public Integer saveMessage(MessageEntity messageEntity) {

        String sql = "INSERT INTO messages(timestamp,sender,recipient,type,text) VALUES(?,?,?,?,?)";
        int generatedKey = 0;
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/proyectos/db/mydatabase.db");

            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, messageEntity.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));
            pstmt.setInt(2, messageEntity.getSender());
            pstmt.setInt(3, messageEntity.getRecipient());
            pstmt.setString(4, messageEntity.getType());
            pstmt.setString(5, messageEntity.getText());
            pstmt.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
            if (generatedKeys.next()) {
                generatedKey = generatedKeys.getInt(1);
            }
            connection.commit();
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
        return generatedKey;

    }

    @Override
    public Integer saveImageMessage(MessageEntity messageEntity, ImageMessageEntity imageMessageEntity) {

        String sql = "INSERT INTO messages(timestamp,sender,recipient,type) VALUES(?,?,?,?,?)";
        String sqlDetail = "INSERT INTO messages_image(message_id,url,height,width) VALUES(?,?,?,?)";
        int generatedKey = 0;
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/proyectos/db/mydatabase.db");

            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, messageEntity.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));
            pstmt.setInt(2, messageEntity.getSender());
            pstmt.setInt(3, messageEntity.getRecipient());
            pstmt.setString(4, messageEntity.getType());
            pstmt.setString(5, messageEntity.getText());
            pstmt.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
            if (generatedKeys.next()) {
                generatedKey = generatedKeys.getInt(1);
                //TODO: validate generatedKey is not 0
                PreparedStatement ps = connection.prepareStatement(sqlDetail);
                ps.setInt(1, generatedKey);
                ps.setString(2, imageMessageEntity.getUrl());
                ps.setInt(3, imageMessageEntity.getHeight());
                ps.setInt(4, imageMessageEntity.getWidth());
                ps.executeUpdate();
            }

            connection.commit();
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
        return generatedKey;
    }

    @Override
    public Integer saveVideoMessage(MessageEntity messageEntity, VideoMessageEntity videoMessageEntity) {
        String sql = "INSERT INTO messages(timestamp,sender,recipient,type) VALUES(?,?,?,?,?)";
        String sqlDetail = "INSERT INTO messages_video(message_id,url,source) VALUES(?,?,?)";
        int generatedKey = 0;
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/proyectos/db/mydatabase.db");

            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, messageEntity.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));
            pstmt.setInt(2, messageEntity.getSender());
            pstmt.setInt(3, messageEntity.getRecipient());
            pstmt.setString(4, messageEntity.getType());
            pstmt.setString(5, messageEntity.getText());
            pstmt.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
            if (generatedKeys.next()) {
                generatedKey = generatedKeys.getInt(1);
                //TODO: validate generatedKey is not 0
                PreparedStatement ps = connection.prepareStatement(sqlDetail);
                ps.setInt(1, generatedKey);
                ps.setString(2, videoMessageEntity.getUrl());
                ps.setString(3, videoMessageEntity.getSource());
                ps.executeUpdate();
            }

            connection.commit();
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
        return generatedKey;
    }
}
