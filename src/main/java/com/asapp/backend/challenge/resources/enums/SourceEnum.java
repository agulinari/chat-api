package com.asapp.backend.challenge.resources.enums;

public enum SourceEnum {

    YOUTUBE("youtube"),
    VIMEO("vimeo");

    private String value;

    SourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        for ( SourceEnum e : values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
