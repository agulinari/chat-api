package com.asapp.backend.challenge;

import com.asapp.backend.challenge.controller.AuthController;
import com.asapp.backend.challenge.controller.HealthController;
import com.asapp.backend.challenge.controller.MessagesController;
import com.asapp.backend.challenge.controller.UsersController;
import com.asapp.backend.challenge.filter.TokenValidatorFilter;
import com.asapp.backend.challenge.utils.DatabaseUtil;
import com.asapp.backend.challenge.utils.Path;
import com.asapp.backend.challenge.utils.PropertiesUtil;
import spark.Spark;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {

    public static void main(String[] args) throws IOException {

        PropertiesUtil.loadProperties();

        DatabaseUtil.init();

        // Spark Configuration
        Spark.port(8080);

        // Configure Endpoints
        // Users
        Spark.post(Path.USERS, UsersController.createUser);
        // Auth
        Spark.post(Path.LOGIN, AuthController.login);
        // Messages
        Spark.before(Path.MESSAGES, TokenValidatorFilter.validateUser);
        Spark.post(Path.MESSAGES, MessagesController.sendMessage);
        Spark.get(Path.MESSAGES, MessagesController.getMessages);
        // Health
        Spark.post(Path.HEALTH, HealthController.check);

    }

}
