package org.w2m_challenge.fflix_service.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class SpaceshipResponseDTO extends RepresentationModel<SpaceshipResponseDTO> {
    private Integer id;
    private String name;
    private String title;
    private String status;
}
