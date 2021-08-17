package com.asapp.backend.challenge.service.impl;

import com.asapp.backend.challenge.controller.responses.SendMessageResponse;
import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.persistence.entities.ImageMessageEntity;
import com.asapp.backend.challenge.persistence.entities.MessageEntity;
import com.asapp.backend.challenge.persistence.entities.VideoMessageEntity;
import com.asapp.backend.challenge.persistence.repository.api.MessageRepository;
import com.asapp.backend.challenge.resources.*;
import com.asapp.backend.challenge.service.api.MessageService;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

    private MessageService messageService;
    private MessageRepository messageRepository;

    @Before
    public void setUp() {
        this.messageRepository = mock(MessageRepository.class);
        messageService = new MessageServiceImpl(messageRepository);
    }

    @Test
    public void testSendTextMessageOk() throws InvalidUserException, SQLException {

        int messageId = 1;
        TextContent content = new TextContent();
        content.setType("text");
        content.setText("hello");
        MessageResource<Content> messageResource = new MessageResource<>();
        messageResource.setSender(1);
        messageResource.setRecipient(1);
        messageResource.setContent(content);

        when(messageRepository.saveMessage(any(MessageEntity.class))).thenReturn(messageId);

        SendMessageResponse response = messageService.sendMessage(messageResource);
        verify(messageRepository, times(1)).saveMessage(any());
        verify(messageRepository, never()).saveImageMessage(any(), any());
        verify(messageRepository, never()).saveVideoMessage(any(), any());
        assertEquals(response.getId().intValue(), messageId);
    }

    @Test
    public void testSendImageMessageOk() throws InvalidUserException, SQLException {

        int messageId = 2;
        ImageContent content = new ImageContent();
        content.setType("image");
        content.setUrl("http://my-image/car.jpg");
        content.setWidth(800);
        content.setHeight(600);
        MessageResource<Content> messageResource = new MessageResource<>();
        messageResource.setSender(1);
        messageResource.setRecipient(1);
        messageResource.setContent(content);

        when(messageRepository.saveImageMessage(any(MessageEntity.class), any(ImageMessageEntity.class))).thenReturn(messageId);

        SendMessageResponse response = messageService.sendMessage(messageResource);
        verify(messageRepository, never()).saveMessage(any());
        verify(messageRepository, times(1)).saveImageMessage(any(), any());
        verify(messageRepository, never()).saveVideoMessage(any(), any());
        assertEquals(response.getId().intValue(), messageId);
    }

    @Test
    public void testSendVideoMessageOk() throws InvalidUserException, SQLException {

        int messageId = 3;
        VideoContent content = new VideoContent();
        content.setType("image");
        content.setUrl("http://youtube.com/watch?v=video");
        content.setSource("video");
        MessageResource<Content> messageResource = new MessageResource<>();
        messageResource.setSender(1);
        messageResource.setRecipient(1);
        messageResource.setContent(content);

        when(messageRepository.saveVideoMessage(any(MessageEntity.class), any(VideoMessageEntity.class))).thenReturn(messageId);

        SendMessageResponse response = messageService.sendMessage(messageResource);
        verify(messageRepository, never()).saveMessage(any());
        verify(messageRepository, never()).saveImageMessage(any(), any());
        verify(messageRepository, times(1)).saveVideoMessage(any(), any());
        assertEquals(response.getId().intValue(), messageId);
    }

    @Test(expected = InvalidUserException.class)
    public void testInvalidUser() throws SQLException, InvalidUserException {

        TextContent content = new TextContent();
        content.setType("text");
        content.setText("hello");
        MessageResource<Content> messageResource = new MessageResource<>();
        messageResource.setSender(5); //User 5 does not exists
        messageResource.setRecipient(1);
        messageResource.setContent(content);

        when(messageRepository.saveMessage(any(MessageEntity.class))).thenThrow(new InvalidUserException());

        messageService.sendMessage(messageResource);

    }

    @Test
    public void testGetMessages() throws SQLException {

        int recipient = 2;
        int start = 1;
        int limit = 3;

        MessageEntity message1 = new MessageEntity();
        message1.setId(1);
        message1.setSender(1);
        message1.setRecipient(2);
        message1.setType("text");
        message1.setText("hola");

        MessageEntity message2 = new MessageEntity();
        message2.setId(2);
        message2.setSender(3);
        message2.setRecipient(2);
        message2.setType("image");

        ImageMessageEntity message2Image = new ImageMessageEntity();
        message2Image.setId(1);
        message2Image.setMessageId(2);
        message2Image.setUrl("http://my-image.com/image.png");
        message2Image.setWidth(500);
        message2Image.setHeight(1000);

        MessageEntity message3 = new MessageEntity();
        message3.setId(3);
        message3.setSender(1);
        message3.setRecipient(2);
        message3.setType("video");

        VideoMessageEntity message3Video = new VideoMessageEntity();
        message3Video.setId(1);
        message3Video.setMessageId(3);
        message3Video.setUrl("http://youtube.com/video");
        message3Video.setSource("youtube");

        List<MessageEntity> messageEntities = Arrays.asList(message1, message2, message3);

        when(this.messageRepository.getMessages(recipient, start, limit)).thenReturn(messageEntities);
        when(this.messageRepository.getImageMessage(2)).thenReturn(Optional.of(message2Image));
        when(this.messageRepository.getVideoMessage(3)).thenReturn(Optional.of(message3Video));

        List<MessageResource<Content>> messages = messageService.getMessages(recipient, start, limit);

        assertEquals(messages.size(), 3);
        assertEquals(messages.get(0).getId(), message1.getId());
        assertEquals(messages.get(0).getSender(), message1.getSender());
        assertEquals(messages.get(0).getRecipient(), message1.getRecipient());
        assertEquals(messages.get(0).getContent().getType(), "text");
        assertEquals(messages.get(1).getId(), message2.getId());
        assertEquals(messages.get(1).getSender(), message2.getSender());
        assertEquals(messages.get(1).getRecipient(), message2.getRecipient());
        assertEquals(messages.get(1).getContent().getType(), "image");
        assertEquals(messages.get(2).getId(), message3.getId());
        assertEquals(messages.get(2).getSender(), message3.getSender());
        assertEquals(messages.get(2).getRecipient(), message3.getRecipient());
        assertEquals(messages.get(2).getContent().getType(), "video");

    }
}
