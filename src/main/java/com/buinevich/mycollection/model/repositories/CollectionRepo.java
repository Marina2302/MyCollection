package com.buinevich.mycollection.model.repositories;

import com.buinevich.mycollection.model.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepo extends JpaRepository<Collection, Long> {

    List<Collection> findAllByOwnerId(long ownerId);
}
