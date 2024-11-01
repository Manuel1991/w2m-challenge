package org.w2m_challenge.fflix_service.mapper;

import org.w2m_challenge.fflix_service.persistence.model.Title;
import org.w2m_challenge.fflix_service.web.dto.TitleResponseDTO;

import java.util.Optional;

public class TitleMapper {

    public static TitleResponseDTO mapTitleResponseDTO(Title title) {
        return Optional
                .ofNullable(title)
                .map(t -> TitleResponseDTO
                        .builder()
                        .id(t.getId())
                        .name(t.getName())
                        .type(t.getType().getValue())
                        .build())
                .orElse(new TitleResponseDTO());
    }
}
