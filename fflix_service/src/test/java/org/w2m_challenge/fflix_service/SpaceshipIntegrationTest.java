package org.w2m_challenge.fflix_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.w2m_challenge.fflix_service.persistence.model.Title;
import org.w2m_challenge.fflix_service.persistence.model.TitleType;
import org.w2m_challenge.fflix_service.persistence.repository.TitleRepository;
import org.w2m_challenge.fflix_service.web.dto.NewSpaceshipDTO;
import org.w2m_challenge.fflix_service.web.dto.SpaceshipDetailDTO;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
public class SpaceshipIntegrationTest {

    private final String urlBase = "/api/spaceship";

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwt = SecurityMockMvcRequestPostProcessors
            .jwt()
            .jwt(jwt -> jwt
                    .claim("scope", "read:spaceship create:spaceship update:spaceship delete:spaceship")
                    .subject("test-user"));


    NewSpaceshipDTO createSpaceshipDTO = NewSpaceshipDTO
            .builder()
            .name("Valkyrie")
            .model("Advanced Fighter Jet")
            .captain("Jake Morrison")
            .maxSpeed("Faster-than-light")
            .affiliation("Earth Defense Force")
            .weapons("Plasma Cannons, Missiles")
            .crewSize(2)
            .status("ACTIVE")
            .build();

    NewSpaceshipDTO updateSpaceshipDTO = NewSpaceshipDTO
            .builder()
            .name("Valkyrie-x-tst")
            .model("Advanced Fighter Jet-tst")
            .captain("Jake Morrison-tst")
            .maxSpeed("Faster-than-light-tst")
            .affiliation("Earth Defense Force-tst")
            .weapons("Plasma Cannons, Missiles-tst")
            .crewSize(3)
            .status("LOST")
            .build();

    @BeforeEach
    void init() {
        Title title = new Title();
        title.setName("Titan A.E");
        title.setType(TitleType.MOVIE);
        title.setRelease(2000);

        int titleID = titleRepository.save(title).getId();

        createSpaceshipDTO.setTitleId(titleID);
        updateSpaceshipDTO.setTitleId(titleID);
    }

    @Test
    void test_CRUD() throws Exception {

        SpaceshipDetailDTO createdSpaceshipDTO = createSpaceship(createSpaceshipDTO);

        assert isSameSpaceship(createSpaceshipDTO, createdSpaceshipDTO);

        SpaceshipDetailDTO updatedSpaceshipDTO = updateSpaceship(updateSpaceshipDTO, createdSpaceshipDTO.getId());

        assert isSameSpaceship(updateSpaceshipDTO, updatedSpaceshipDTO);

        mockMvc.perform(delete(urlBase + "/" + createdSpaceshipDTO.getId()).with(jwt))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(urlBase + "/" + createdSpaceshipDTO.getId()).with(jwt))
                .andExpect(status().isNotFound());
    }

    private SpaceshipDetailDTO createSpaceship(NewSpaceshipDTO dto) throws Exception {

        String result = mockMvc.perform(post(urlBase)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .with(jwt))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return getSpaceshipDetail(result);
    }

    private SpaceshipDetailDTO updateSpaceship(NewSpaceshipDTO dto, int spaceshipID) throws Exception {

        String result = mockMvc.perform(put(urlBase + "/" + spaceshipID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .with(jwt))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return getSpaceshipDetail(result);
    }

    private SpaceshipDetailDTO getSpaceshipDetail(String jsonResult) throws Exception {

        String href = objectMapper.readTree(jsonResult).get("href").asText();

        String jsonResponse = mockMvc
                .perform(get(href)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue(jsonResponse, SpaceshipDetailDTO.class);
    }

    private boolean isSameSpaceship(NewSpaceshipDTO a, SpaceshipDetailDTO b) {
        return Objects.equals(a.getName(), b.getName())
                &&
                Objects.equals(a.getModel(), b.getModel())
                &&
                Objects.equals(a.getCaptain(), b.getCaptain())
                &&
                Objects.equals(a.getMaxSpeed(), b.getMaxSpeed())
                &&
                Objects.equals(a.getAffiliation(), b.getAffiliation())
                &&
                Objects.equals(a.getWeapons(), b.getWeapons())
                &&
                Objects.equals(a.getStatus(), b.getStatus())
                &&
                Objects.equals(a.getCrewSize(), b.getCrew_size())
                &&
                Objects.equals(a.getTitleId(), b.getTitle().getId());
    }
}
