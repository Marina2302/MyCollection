package com.buinevich.mycollection.services;

import com.buinevich.mycollection.model.repositories.ItemRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {
    private static final String ITEM_NOT_FOUND = "Item not found.";

    private ItemRepo itemRepo;
}
