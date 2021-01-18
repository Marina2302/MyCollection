package com.buinevich.mycollection.model.repositories;

import com.buinevich.mycollection.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
    boolean existsByText(String text);

    Optional<Tag> findByText(String text);
}
