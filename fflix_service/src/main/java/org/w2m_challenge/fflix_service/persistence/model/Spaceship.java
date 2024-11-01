package org.w2m_challenge.fflix_service.persistence.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "SPACESHIP")
public class Spaceship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    private String model;

    @Column(length = 100)
    private String captain;

    @Column(length = 50, name = "max_speed")
    private String maxSpeed;

    @Column(length = 100)
    private String affiliation;

    @Column
    private String weapons;

    @Column(name = "crew_size")
    private Integer crewSize;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpaceshipStatus status;

    @ManyToOne
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Spaceship spaceship = (Spaceship) o;

        return Objects.equals(id, spaceship.id) &&
                Objects.equals(name, spaceship.name) &&
                Objects.equals(model, spaceship.model) &&
                Objects.equals(captain, spaceship.captain) &&
                Objects.equals(maxSpeed, spaceship.maxSpeed) &&
                Objects.equals(affiliation, spaceship.affiliation) &&
                Objects.equals(weapons, spaceship.weapons) &&
                Objects.equals(crewSize, spaceship.crewSize) &&
                status == spaceship.status &&
                Objects.equals(
                        title != null ? title.getId() : null,
                        spaceship.title != null ? spaceship.title.getId() : null
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, model, captain, maxSpeed, affiliation, weapons, crewSize, status, title != null ? title.getId() : null);
    }

    @Override
    public String toString() {
        return String.format("""
                {
                'id': %d,
                'name': '%s',
                'model': '%s',
                'captain': '%s',
                'maxSpeed': '%s',
                'affiliation': '%s',
                'weapons': '%s',
                'crewSize': '%s',
                'status': '%s'
                }""", id, name, model, captain, maxSpeed, affiliation, weapons, crewSize, status);
    }
}
