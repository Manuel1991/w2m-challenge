package org.w2m_challenge.fflix_service.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.w2m_challenge.fflix_service.persistence.model.Title;

public interface TitleRepository extends JpaRepository<Title, Integer> {
}
