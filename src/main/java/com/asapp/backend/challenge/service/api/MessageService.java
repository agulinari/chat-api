package com.asapp.backend.challenge.service.api;

import com.asapp.backend.challenge.controller.responses.SendMessageResponse;
import com.asapp.backend.challenge.exceptions.InvalidMessageException;
import com.asapp.backend.challenge.resources.Content;
import com.asapp.backend.challenge.resources.MessageResource;

import java.sql.SQLException;
import java.util.List;

public interface MessageService {

    /**
     * Send a message from one user to another.
     * @param message: We support three types of messages text, image and video.
     * @return ID and timestamp of the new message
     * @throws InvalidMessageException
     * @throws SQLException
     */
    SendMessageResponse sendMessage(MessageResource<Content> message) throws InvalidMessageException, SQLException;

    /**
     * Fetch all existing messages to a given recipient, within a range of message IDs.
     * @param recipient: User ID of recipient
     * @param start: Starting message ID
     * @param limit: Limit the response to this many messages
     * @return List of requested messages
     * @throws SQLException
     */
    List<MessageResource<Content>> getMessages(Integer recipient, Integer start, Integer limit) throws SQLException;
}
