package com.buinevich.mycollection.model.repositories;

import com.buinevich.mycollection.model.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

    List<Item> findByNameContainingOrDescriptionContaining(String name, String description);
}
