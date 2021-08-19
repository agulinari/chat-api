package com.asapp.backend.challenge.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties properties;

    public static void loadProperties() throws IOException {
        Properties allProps = new Properties();
        InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");

        allProps.load(is);
        is.close();

        properties = allProps;
    }

}
