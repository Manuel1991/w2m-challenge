package org.w2m_challenge.fflix_service.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewSpaceshipDTO {

    @NotBlank(message = "'name' field cannot be null or empty.")
    @Size(max = 100, message = "The maximum length of the 'name' field must not be greater than 100.")
    private final String name;

    @Size(max = 100, message = "The maximum length of the 'model' field must not be greater than 100.")
    private final String model;

    @Size(max = 100, message = "The maximum length of the 'captain' field must not be greater than 100.")
    private final String captain;

    @Size(max = 50, message = "The maximum length of the 'maxSpeed' field must not be greater than 50.")
    private final String maxSpeed;

    @Size(max = 100, message = "The maximum length of the 'affiliation' field must not be greater than 100.")
    private final String affiliation;

    @Size(max = 255, message = "The maximum length of the 'weapons' field must not be greater than 100.")
    private final String weapons;

    @Min(value = 1)
    private Integer crewSize;

    @Pattern(regexp = "(ACTIVE|DESTROYED|LOST)", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String status;

    private Integer titleId;
}
