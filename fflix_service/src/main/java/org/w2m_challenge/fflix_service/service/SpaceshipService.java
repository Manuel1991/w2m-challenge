package org.w2m_challenge.fflix_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w2m_challenge.fflix_service.exception.ExceptionMessages;
import org.w2m_challenge.fflix_service.exception.NotFoundException;
import org.w2m_challenge.fflix_service.mapper.SpaceshipMapper;
import org.w2m_challenge.fflix_service.persistence.model.Spaceship;
import org.w2m_challenge.fflix_service.persistence.model.Title;
import org.w2m_challenge.fflix_service.persistence.repository.SpaceshipRepository;
import org.w2m_challenge.fflix_service.persistence.repository.TitleRepository;
import org.w2m_challenge.fflix_service.web.dto.NewSpaceshipDTO;
import org.w2m_challenge.fflix_service.web.dto.SpaceshipDetailDTO;
import org.w2m_challenge.fflix_service.web.dto.SpaceshipResponseDTO;

@Service
public class SpaceshipService {

    private final TitleRepository titleRepository;
    private final SpaceshipRepository spaceshipRepository;
    private final LoggerService loggerService;

    public SpaceshipService(
            TitleRepository titleRepository,
            SpaceshipRepository spaceshipRepository,
            LoggerService loggerService
    ) {
        this.titleRepository = titleRepository;
        this.spaceshipRepository = spaceshipRepository;
        this.loggerService = loggerService;
    }

    public SpaceshipDetailDTO findById(int id) {
        return spaceshipRepository
                .findById(id)
                .map(SpaceshipMapper::mapSpaceshipDetailDTO)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.SPACESHIP_NOT_FOUND));
    }

    public void deleteSpaceship(int id) {
        if (!spaceshipRepository.existsById(id))
            throw new NotFoundException(ExceptionMessages.SPACESHIP_NOT_FOUND);

        Spaceship spaceship = spaceshipRepository.getReferenceById(id);

        spaceshipRepository.delete(spaceship);

        loggerService.info(String.format("deleted_spaceship:%s", spaceship));
    }

    public Integer createSpaceship(NewSpaceshipDTO dto) {

        Title title = titleRepository
                .findById(dto.getTitleId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.TITLE_NOT_FOUND));

        Spaceship spaceship = spaceshipRepository.save(SpaceshipMapper.mapSpaceship(dto, title));

        loggerService.info(String.format("created_spaceship:%s", spaceship));

        return spaceship.getId();
    }

    public Integer updateSpaceship(int id, NewSpaceshipDTO dto) {

        Title title = titleRepository
                .findById(dto.getTitleId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.TITLE_NOT_FOUND));

        Spaceship spaceship = spaceshipRepository
                .findById(id)
                .map(s -> SpaceshipMapper.mapSpaceship(dto, s, title))
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.SPACESHIP_NOT_FOUND));

        return spaceshipRepository.save(spaceship).getId();
    }

    public Page<SpaceshipResponseDTO> findAll(Pageable pageable) {
        return spaceshipRepository
                .findAll(pageable)
                .map(SpaceshipMapper::mapSpaceshipResponseDTO);
    }

    public Page<SpaceshipResponseDTO> findByName(String q, Pageable pageable) {
        return spaceshipRepository
                .findByNameContainingIgnoreCase(q, pageable)
                .map(SpaceshipMapper::mapSpaceshipResponseDTO);
    }
}
