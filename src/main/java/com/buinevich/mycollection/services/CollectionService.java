package com.buinevich.mycollection.services;

import com.buinevich.mycollection.exceptions.NotFoundException;
import com.buinevich.mycollection.model.dto.CollectionRequest;
import com.buinevich.mycollection.model.dto.CollectionResponse;
import com.buinevich.mycollection.model.entities.Collection;
import com.buinevich.mycollection.model.mappers.CollectionMapper;
import com.buinevich.mycollection.model.repositories.CollectionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CollectionService {

    private static final String COLLECTION_NOT_FOUND = "Collection not found.";

    private CollectionRepo collectionRepo;
    private CollectionMapper collectionMapper;

    public CollectionResponse createCollection(CollectionRequest collectionRequest) {
        //TODO  - add admin check
        Collection newCollection = collectionMapper.collectionRequestToCollection(collectionRequest);
        collectionRepo.save(newCollection);
        return collectionMapper.collectionToCollectionResponse(newCollection);
    }

    public List<CollectionResponse> getAllCollections() {
        //TODO  - change to use query
        return collectionRepo.findAll().stream()
                .map(collection -> collectionMapper.collectionToCollectionResponse(collection))
                .collect(Collectors.toList());
    }

    public CollectionResponse getCollection(long id) {
        return collectionMapper.collectionToCollectionResponse(collectionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(COLLECTION_NOT_FOUND)));
    }

    public CollectionResponse updateCollection(long id, CollectionRequest collectionRequest) {
        Collection collection = collectionRepo.findById(id).orElseThrow(() -> new NotFoundException(COLLECTION_NOT_FOUND));
        updateCollectionFields(collectionRequest, collection);
        return collectionMapper.collectionToCollectionResponse(collectionRepo.save(collection));
    }

    private void updateCollectionFields(CollectionRequest collectionRequest, Collection collection) {
        if (collectionRequest.getName() != null) collection.setName(collectionRequest.getName());
        if (collectionRequest.getDescription() != null) collection.setDescription(collectionRequest.getDescription());
        if (collectionRequest.getTheme() != null) collection.setTheme(collectionRequest.getTheme());
    }

    public void deleteCollection(long id) {
        collectionRepo.deleteById(id);
    }
}
