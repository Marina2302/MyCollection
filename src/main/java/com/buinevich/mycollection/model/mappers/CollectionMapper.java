package com.buinevich.mycollection.model.mappers;

import com.buinevich.mycollection.model.dto.CollectionRequest;
import com.buinevich.mycollection.model.dto.CollectionResponse;
import com.buinevich.mycollection.model.entities.Collection;
import com.buinevich.mycollection.model.repositories.ItemRepo;
import com.buinevich.mycollection.model.repositories.TagRepo;
import com.buinevich.mycollection.model.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CollectionMapper {

    private ItemRepo itemRepo;
    private UserRepo userRepo;
    private TagRepo tagRepo;

    public Collection collectionRequestToCollection(CollectionRequest collectionRequest) {
        return null;
    }

    public CollectionResponse collectionToCollectionResponse(Collection collection) {
        return null;
    }
}
