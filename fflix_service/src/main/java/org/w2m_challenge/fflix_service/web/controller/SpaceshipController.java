package org.w2m_challenge.fflix_service.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.w2m_challenge.fflix_service.service.SpaceshipService;
import org.w2m_challenge.fflix_service.web.dto.NewSpaceshipDTO;
import org.w2m_challenge.fflix_service.web.dto.SpaceshipDetailDTO;
import org.w2m_challenge.fflix_service.web.dto.SpaceshipResponseDTO;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/spaceship")
public class SpaceshipController {

    private final SpaceshipService service;

    public SpaceshipController(SpaceshipService service) {
        this.service = service;
    }

    @Operation(summary = "Get a spaceship by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spaceship found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Spaceship not found", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
    @Cacheable(value = "spaceship", key = "#id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SpaceshipDetailDTO> getSpaceshipById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Delete a spaceship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Spaceship deleted"),
            @ApiResponse(responseCode = "404", description = "Spaceship to delete doesn't exist", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
    @Caching(evict = {@CacheEvict(value = "spaceships", allEntries = true), @CacheEvict(value = "spaceship", key = "#id")})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSpaceship(@PathVariable Integer id) {
        service.deleteSpaceship(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a spaceship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spaceship updated", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Spaceship to update doesn't exist", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
    @Caching(evict = {@CacheEvict(value = "spaceships", allEntries = true), @CacheEvict(value = "spaceship", key = "#id")})
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Link> updateSpaceship(
            @PathVariable Integer id,
            @RequestBody @Valid NewSpaceshipDTO body
    ) {
        Link link = linkToSpaceship(service.updateSpaceship(id, body));
        return ResponseEntity.ok(link);
    }


    @Operation(summary = "Create a spaceship")
    @ApiResponse(responseCode = "201", description = "Spaceship created")
    @CacheEvict(value = "spaceships", allEntries = true)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Link> createSpaceship(@RequestBody @Valid NewSpaceshipDTO body) {
        Link link = linkToSpaceship(service.createSpaceship(body));
        return ResponseEntity.created(link.toUri()).body(link);
    }

    @Operation(summary = "Get all spaceships. Also you can filter spaceships by name")
    @Cacheable(cacheNames = "spaceships", key = "#request.getRequestURI() + '?' + #request.getQueryString()")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PagedModel<SpaceshipResponseDTO>> findSpaceships(
            HttpServletRequest request,
            @PageableDefault(direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) String name
    ) {
        Page<SpaceshipResponseDTO> result = Optional
                .ofNullable(name)
                .filter(StringUtils::hasText)
                .map(n -> service.findByName(n, pageable))
                .orElse(service.findAll(pageable));

        return ResponseEntity.ok(new PagedModel<>(result));
    }

    private static Link linkToSpaceship(Integer id) {
        return linkTo(SpaceshipController.class)
                .slash(id)
                .withSelfRel();
    }
}
