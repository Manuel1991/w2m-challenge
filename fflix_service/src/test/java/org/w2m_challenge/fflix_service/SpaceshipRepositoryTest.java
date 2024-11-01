package org.w2m_challenge.fflix_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.w2m_challenge.fflix_service.persistence.model.Spaceship;
import org.w2m_challenge.fflix_service.persistence.model.SpaceshipStatus;
import org.w2m_challenge.fflix_service.persistence.model.Title;
import org.w2m_challenge.fflix_service.persistence.model.TitleType;
import org.w2m_challenge.fflix_service.persistence.repository.SpaceshipRepository;
import org.w2m_challenge.fflix_service.persistence.repository.TitleRepository;

import java.util.Objects;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class SpaceshipRepositoryTest {

    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private SpaceshipRepository spaceshipRepository;

    private Title title;
    private Spaceship spaceship;

    @BeforeEach
    void init() {
        title = new Title();
        title.setName("Titan A.E");
        title.setType(TitleType.MOVIE);
        title.setRelease(2000);

        spaceship = new Spaceship();
        spaceship.setName("Titan");
        spaceship.setModel("Terraforming Ship");
        spaceship.setCaptain("Cale Tucker");
        spaceship.setMaxSpeed("Faster-than-light");
        spaceship.setAffiliation("Human Refugees");
        spaceship.setWeapons("Laser Cannons");
        spaceship.setCrewSize(12);
        spaceship.setStatus(SpaceshipStatus.ACTIVE);

        spaceship.setTitle(title);
        title.getSpaceships().add(spaceship);

        titleRepository.save(title);
    }

    @Test
    void test_Insert() {
        assert title.equals(titleRepository.getReferenceById(title.getId()));
        assert spaceship.equals(spaceshipRepository.getReferenceById(spaceship.getId()));
    }

    @Test
    void test_Update() {

        Spaceship spaceshipToUpdate = spaceshipRepository.getReferenceById(spaceship.getId());
        spaceshipToUpdate.setName("Titan - updated");

        Title titleToUpdate = spaceshipToUpdate.getTitle();
        titleToUpdate.setName("Titan A.E - updated");

        titleRepository.save(titleToUpdate);

        assert titleToUpdate.equals(titleRepository.getReferenceById(title.getId()));
        assert spaceshipToUpdate.equals(spaceshipRepository.getReferenceById(spaceship.getId()));
    }

    @Test
    void test_Delete() {
        title.getSpaceships().removeIf(s -> Objects.equals(s.getId(), spaceship.getId()));
        titleRepository.save(title);

        Assertions.assertEquals(titleRepository.count(), 1);
        Assertions.assertEquals(spaceshipRepository.count(), 0);
    }
}
