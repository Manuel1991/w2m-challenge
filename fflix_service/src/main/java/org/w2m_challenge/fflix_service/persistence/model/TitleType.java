package org.w2m_challenge.fflix_service.persistence.model;

import lombok.Getter;

@Getter
public enum TitleType {

    MOVIE("MOVIE"),
    SERIES("SERIES");

    private final String value;

    TitleType(String type) {
        value = type;
    }

    public static TitleType fromValue(String value) {
        for (TitleType type : TitleType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid MediaType: " + value);
    }
}
