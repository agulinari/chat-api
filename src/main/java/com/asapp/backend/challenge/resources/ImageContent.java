package com.asapp.backend.challenge.resources;

import com.asapp.backend.challenge.exceptions.InvalidFieldException;
import com.asapp.backend.challenge.exceptions.RequiredFieldException;
import com.asapp.backend.challenge.resources.enums.ContentTypeEnum;
import com.asapp.backend.challenge.utils.RegexUtil;
import lombok.Data;

@Data
public class ImageContent  extends Content {

    private String type;
    private String url;
    private Integer height;
    private Integer width;

    @Override
    public void validate() throws RequiredFieldException, InvalidFieldException {
        if (!ContentTypeEnum.IMAGE.getValue().equals(type)) {
            throw new InvalidFieldException("type");
        }
        if (url == null || url.trim().isEmpty()) {
            throw new RequiredFieldException("url");
        }
        if (height == null) {
            throw new RequiredFieldException("height");
        }
        if (width == null) {
            throw new RequiredFieldException("width");
        }
        if (!RegexUtil.getInstance().matchesUrl(url)) {
            throw new InvalidFieldException("url");
        }
    }
}
