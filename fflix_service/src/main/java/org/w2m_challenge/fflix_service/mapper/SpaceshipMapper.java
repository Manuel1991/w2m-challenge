package org.w2m_challenge.fflix_service.mapper;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import org.w2m_challenge.fflix_service.persistence.model.Spaceship;
import org.w2m_challenge.fflix_service.persistence.model.SpaceshipStatus;
import org.w2m_challenge.fflix_service.persistence.model.Title;
import org.w2m_challenge.fflix_service.web.controller.SpaceshipController;
import org.w2m_challenge.fflix_service.web.dto.NewSpaceshipDTO;
import org.w2m_challenge.fflix_service.web.dto.SpaceshipDetailDTO;
import org.w2m_challenge.fflix_service.web.dto.SpaceshipResponseDTO;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.w2m_challenge.fflix_service.mapper.TitleMapper.mapTitleResponseDTO;

@Component
public class SpaceshipMapper {

    public static Spaceship mapSpaceship(NewSpaceshipDTO dto, Title title) {
        return mapSpaceship(dto, new Spaceship(), title);
    }

    public static Spaceship mapSpaceship(NewSpaceshipDTO dto, Spaceship spaceship, Title title) {
        spaceship.setName(dto.getName());
        spaceship.setModel(dto.getModel());
        spaceship.setCaptain(dto.getCaptain());
        spaceship.setMaxSpeed(dto.getMaxSpeed());
        spaceship.setAffiliation(dto.getAffiliation());
        spaceship.setWeapons(dto.getWeapons());
        spaceship.setCrewSize(dto.getCrewSize());
        spaceship.setStatus(SpaceshipStatus.fromString(dto.getStatus()));
        spaceship.setTitle(title);
        return spaceship;
    }

    public static SpaceshipResponseDTO mapSpaceshipResponseDTO(Spaceship s) {
        return SpaceshipResponseDTO
                .builder()
                .id(s.getId())
                .name(s.getName())
                .status(s.getStatus().getValue())
                .title(Optional.ofNullable(s.getTitle()).map(Title::getName).orElse(null))
                .build()
                .add(linkToSelf(s.getId()));
    }

    public static SpaceshipDetailDTO mapSpaceshipDetailDTO(Spaceship s) {
        return SpaceshipDetailDTO
                .builder()
                .id(s.getId())
                .name(s.getName())
                .model(s.getModel())
                .captain(s.getCaptain())
                .maxSpeed(s.getMaxSpeed())
                .affiliation(s.getAffiliation())
                .weapons(s.getWeapons())
                .status(s.getStatus().getValue())
                .crew_size(s.getCrewSize())
                .title(mapTitleResponseDTO(s.getTitle()))
                .build();
    }

    private static Link linkToSelf(Integer id) {
        return linkTo(SpaceshipController.class)
                .slash(id)
                .withSelfRel();
    }
}
