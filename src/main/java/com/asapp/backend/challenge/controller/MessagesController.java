package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.responses.ErrorResponse;
import com.asapp.backend.challenge.controller.responses.GetMessagesResponse;
import com.asapp.backend.challenge.controller.responses.SendMessageResponse;
import com.asapp.backend.challenge.exceptions.InvalidFieldException;
import com.asapp.backend.challenge.exceptions.InvalidUserException;
import com.asapp.backend.challenge.exceptions.RequiredFieldException;
import com.asapp.backend.challenge.resources.Content;
import com.asapp.backend.challenge.resources.MessageResource;
import com.asapp.backend.challenge.service.api.MessageService;
import com.asapp.backend.challenge.service.impl.MessageServiceImpl;
import com.asapp.backend.challenge.utils.HttpCodes;
import com.asapp.backend.challenge.utils.JSONUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class MessagesController {

    private static final MessageService messageService = new MessageServiceImpl();

    public static Route sendMessage = (Request req, Response resp) -> {

        resp.header("Content-Type", "application/json");

        try {

            MessageResource<Content> message = JSONUtil.jsonToData(req.body(), new TypeReference<MessageResource<Content>>() {
            });
            validateSendMessageRequest(message);
            SendMessageResponse response = messageService.sendMessage(message);
            return JSONUtil.dataToJson(response);

        }  catch (JsonParseException | JsonMappingException jpe) {

            resp.status(HttpCodes.HTTP_BAD_REQUEST);
            return JSONUtil.dataToJson(new ErrorResponse("Malformed request"));

        } catch (InvalidUserException iue) {

            resp.status(HttpCodes.HTTP_CONFLICT);
            return JSONUtil.dataToJson(new ErrorResponse("Invalid sender or recipient"));

        } catch (RequiredFieldException  | InvalidFieldException fe) {

            resp.status(HttpCodes.HTTP_BAD_REQUEST);
            return JSONUtil.dataToJson(new ErrorResponse(fe.getMessage()));

        } catch (Exception e ) {

            resp.status(HttpCodes.HTTP_INTERNAL_SERVER_EXCEPTION);
            return JSONUtil.dataToJson(new ErrorResponse(e.getMessage()));

        }
    };

    private static void validateSendMessageRequest(MessageResource<Content> message) throws RequiredFieldException, InvalidFieldException {

        if (message.getSender() == null) {
            throw new RequiredFieldException("sender");
        }

        if (message.getRecipient() == null) {
            throw new RequiredFieldException("recipient");
        }

        message.getContent().validate();

    }

    public static Route getMessages = (Request req, Response resp) -> {

        resp.header("Content-Type", "application/json");

        String srecipient = req.queryParams("recipient");
        String sstart = req.queryParams("start");
        String slimit = req.queryParams("limit");

        try {

            validateGetMessagesRequest(srecipient, sstart);
            Integer recipient = Integer.valueOf(srecipient);
            Integer start = Integer.valueOf(sstart);
            Integer limit = (slimit != null) ? Integer.valueOf(slimit) : 100;

            List<MessageResource<Content>> messages = messageService.getMessages(recipient, start, limit);

            return JSONUtil.dataToJson(new GetMessagesResponse(messages));

        } catch (RequiredFieldException  | NumberFormatException fe) {

            resp.status(HttpCodes.HTTP_BAD_REQUEST);
            return JSONUtil.dataToJson(new ErrorResponse(fe.getMessage()));

        } catch (Exception e ) {

            resp.status(HttpCodes.HTTP_INTERNAL_SERVER_EXCEPTION);
            return JSONUtil.dataToJson(new ErrorResponse(e.getMessage()));

        }
    };

    private static void validateGetMessagesRequest(String srecipient, String sstart) throws RequiredFieldException, InvalidFieldException {

        if (srecipient == null) {
            throw new RequiredFieldException("recipient");
        }

        if (sstart == null) {
            throw new RequiredFieldException("start");
        }

    }

}
