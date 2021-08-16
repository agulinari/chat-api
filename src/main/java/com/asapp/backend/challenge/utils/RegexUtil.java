package com.asapp.backend.challenge.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    private static final RegexUtil instance = new RegexUtil();
    private Pattern urlPattern;

    public static RegexUtil getInstance() {
        return instance;
    }

    private RegexUtil(){
        String urlRegex = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";

        this.urlPattern = Pattern.compile(urlRegex);
    }

    public boolean matchesUrl(String url) {
        Matcher matcher = urlPattern.matcher(url);
        return matcher.matches();
    }


}
