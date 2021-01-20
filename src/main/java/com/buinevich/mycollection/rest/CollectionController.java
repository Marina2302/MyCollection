package com.buinevich.mycollection.rest;

import com.buinevich.mycollection.model.dto.CollectionRequest;
import com.buinevich.mycollection.model.dto.CollectionResponse;
import com.buinevich.mycollection.services.CollectionService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/collections")
public class CollectionController {

    private CollectionService collectionService;

    @GetMapping
    public List<CollectionResponse> getAllCollections(){
        return collectionService.getAllCollections();
    }

    @GetMapping(path = "/{id}")
    public CollectionResponse getAllCollections(@PathVariable long id){
        return collectionService.getCollection(id);
    }

    @PostMapping
    @Transactional
    public CollectionResponse createCollection(@Valid @RequestBody CollectionRequest collectionRequest) {

        return collectionService.createCollection(collectionRequest);
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public CollectionResponse update(@PathVariable long id, @Valid @RequestBody CollectionRequest collectionRequest) {
        return collectionService.updateCollection(id, collectionRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        collectionService.deleteCollection(id);
    }

}
