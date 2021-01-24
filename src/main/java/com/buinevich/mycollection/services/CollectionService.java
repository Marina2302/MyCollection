package com.buinevich.mycollection.services;

import com.buinevich.mycollection.exceptions.NotFoundException;
import com.buinevich.mycollection.model.entities.Collection;
import com.buinevich.mycollection.model.repositories.CollectionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CollectionService {

    private static final String COLLECTION_NOT_FOUND = "Collection not found.";

    private CollectionRepo collectionRepo;

    public Collection createCollection(Collection newCollection) {
        return collectionRepo.save(newCollection);
    }

    public List<Collection> getAllCollections() {
        return collectionRepo.findAll();
    }

    public Collection getCollection(long id) {
        return collectionRepo.findById(id).orElseThrow(() -> new NotFoundException(COLLECTION_NOT_FOUND));
    }

    public Collection updateCollection(long id, Collection collectionRequest) {
        Collection collection = collectionRepo.findById(id).orElseThrow(() -> new NotFoundException(COLLECTION_NOT_FOUND));
        updateCollectionFields(collectionRequest, collection);
        return collectionRepo.save(collection);
    }

    private void updateCollectionFields(Collection collectionRequest, Collection collection) {
        if (collectionRequest.getName() != null && !collectionRequest.getName().isEmpty())
            collection.setName(collectionRequest.getName());
        if (collectionRequest.getDescription() != null) collection.setDescription(collectionRequest.getDescription());
        if (collectionRequest.getTheme() != null) collection.setTheme(collectionRequest.getTheme());
        if (collectionRequest.getImage() != null) collection.setImage(collectionRequest.getImage());
        if (collectionRequest.getTags() != null) collection.setTags(collectionRequest.getTags());
    }

    public void deleteCollection(long id) {
        collectionRepo.deleteById(id);
    }

    public List<Collection> getCollectionsByOwnerId(long ownerId) {
        return collectionRepo.findAllByOwnerId(ownerId);
    }
}
