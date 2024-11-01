package org.w2m_challenge.fflix_service.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w2m_challenge.fflix_service.persistence.model.Spaceship;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Integer> {
    Page<Spaceship> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
