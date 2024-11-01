package org.w2m_challenge.fflix_service.persistence.model;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public enum SpaceshipStatus {
    ACTIVE("ACTIVE"),
    DESTROYED("DESTROYED"),
    LOST("LOST"),
    UNKNOWN("UNKNOWN");

    private final String value;

    SpaceshipStatus(String value) {
        this.value = value;
    }

    public static SpaceshipStatus fromString(String status) {
        if (!StringUtils.hasText(status))
            return UNKNOWN;

        for (SpaceshipStatus spaceshipStatus : SpaceshipStatus.values()) {
            if (spaceshipStatus.value.equalsIgnoreCase(status)) {
                return spaceshipStatus;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }
}
