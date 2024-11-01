package org.w2m_challenge.fflix_service.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "TITLE")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TitleType type;

    private Integer release;

    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Spaceship> spaceships = new HashSet<>();

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Title title = (Title) o;

        return Objects.equals(id, title.id) &&
                Objects.equals(name, title.name) &&
                type ==  title.type &&
                Objects.equals(release, title.release);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, release);
    }
}
