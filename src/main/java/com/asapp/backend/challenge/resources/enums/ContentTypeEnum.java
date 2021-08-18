package com.asapp.backend.challenge.resources.enums;

public enum ContentTypeEnum {

    TEXT("text"),
    IMAGE("image"),
    VIDEO("video");

    private String value;

    ContentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        for ( ContentTypeEnum e : values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

}
