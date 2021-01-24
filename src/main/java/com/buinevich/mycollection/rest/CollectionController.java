package com.buinevich.mycollection.rest;

import com.buinevich.mycollection.exceptions.AccessException;
import com.buinevich.mycollection.model.dto.CollectionRequest;
import com.buinevich.mycollection.model.dto.CollectionResponse;
import com.buinevich.mycollection.model.entities.User;
import com.buinevich.mycollection.model.enums.Role;
import com.buinevich.mycollection.model.mappers.CollectionMapper;
import com.buinevich.mycollection.services.CollectionService;
import com.buinevich.mycollection.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/collections")
public class CollectionController {

    private static final String NOT_ENOUGH_RIGHTS = "Not enough rights.";

    private CollectionService collectionService;
    private UserService userService;
    private CollectionMapper collectionMapper;

    @GetMapping
    public List<CollectionResponse> getAllCollections(){
        return collectionService.getAllCollections().stream()
                .map(collection -> collectionMapper.collectionToCollectionResponse(collection))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public List<CollectionResponse> getCollectionsByOwnerId(@PathVariable long id){
        return collectionService.getCollectionsByOwnerId(id).stream()
                .map(collection -> collectionMapper.collectionToCollectionResponse(collection))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public CollectionResponse getAllCollections(@PathVariable long id){
        return collectionMapper.collectionToCollectionResponse(collectionService.getCollection(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public CollectionResponse createCollection(@RequestBody CollectionRequest collectionRequest) {
        rightsValidation(collectionRequest);
        return collectionMapper.collectionToCollectionResponse(
                collectionService.createCollection(collectionMapper.collectionRequestToCollection(collectionRequest)));
    }

    private void rightsValidation(CollectionRequest collectionRequest) {
        User currentUser = userService.getCurrentUser();
        if (!currentUser.getRoles().contains(Role.ROLE_ADMIN)) {
            if (currentUser.getId() != collectionRequest.getOwnerId()) {
                throw new AccessException(NOT_ENOUGH_RIGHTS);
            }
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping(path = "/{id}")
    public CollectionResponse update(@PathVariable long id, @RequestBody CollectionRequest collectionRequest) {
        return collectionMapper.collectionToCollectionResponse(
                collectionService.updateCollection(id, collectionMapper.collectionRequestToCollection(collectionRequest)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        collectionService.deleteCollection(id);
    }

}
