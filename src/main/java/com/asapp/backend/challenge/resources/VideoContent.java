package com.asapp.backend.challenge.resources;

import com.asapp.backend.challenge.exceptions.InvalidFieldException;
import com.asapp.backend.challenge.exceptions.RequiredFieldException;
import com.asapp.backend.challenge.utils.RegexUtil;
import lombok.Data;

@Data
public class VideoContent extends Content {

    private String type;
    private String url;
    private String source;

    @Override
    public void validate() throws RequiredFieldException, InvalidFieldException {
        if (!type.equals("video")) {
            throw new InvalidFieldException("type");
        }
        if (url == null || url.trim().isEmpty()) {
            throw new RequiredFieldException("url");
        }
        if (!RegexUtil.getInstance().matchesUrl(url)) {
            throw new InvalidFieldException("url");
        }
        if (!SourceEnum.contains(source)) {
            throw new InvalidFieldException("source");
        }
    }
}
