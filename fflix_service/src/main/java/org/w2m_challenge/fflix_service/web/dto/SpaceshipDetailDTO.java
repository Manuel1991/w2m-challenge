package org.w2m_challenge.fflix_service.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceshipDetailDTO {

    private Integer id;

    private String name;
    private String model;
    private String captain;
    private String maxSpeed;
    private String affiliation;
    private String weapons;
    private String status;

    private Integer crew_size;

    private TitleResponseDTO title;
}
