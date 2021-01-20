package com.buinevich.mycollection.model.mappers;

import com.buinevich.mycollection.exceptions.NotFoundException;
import com.buinevich.mycollection.model.dto.CollectionRequest;
import com.buinevich.mycollection.model.dto.CollectionResponse;
import com.buinevich.mycollection.model.entities.Collection;
import com.buinevich.mycollection.model.entities.Tag;
import com.buinevich.mycollection.model.repositories.TagRepo;
import com.buinevich.mycollection.model.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CollectionMapper {

    private static final String USER_NOT_FOUND = "User not found.";

    private UserRepo userRepo;
    private TagRepo tagRepo;

    private ItemMapper itemMapper;

    public Collection collectionRequestToCollection(CollectionRequest collectionRequest) {
        return Collection.builder()
                .name(collectionRequest.getName())
                .description(collectionRequest.getDescription())
                .image(collectionRequest.getImage())
                .theme(collectionRequest.getTheme())
                .tags((collectionRequest.getTags() != null && !collectionRequest.getTags().isEmpty())
                        ? collectionRequest.getTags().stream()
                        .map(tagText -> {
                            Tag tag = tagRepo.findByText(tagText).orElseGet(() -> tagRepo.save(Tag.builder()
                                    .text(tagText)
                                    .build()));
                            return tag;
                        })
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .owner(userRepo.findById(collectionRequest.getOwnerId()).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND)))
                .build();
    }

    public CollectionResponse collectionToCollectionResponse(Collection collection) {
        return CollectionResponse.builder()
                .id(collection.getId())
                .name(collection.getName())
                .description(collection.getDescription())
                .image(collection.getImage())
                .theme(collection.getTheme())
                .ownerId(collection.getOwner().getId())
                .items(collection.getItems().stream().map(item -> itemMapper.itemToItemResponse(item)).collect(Collectors.toList()))
                .tags(collection.getTags().stream().map(Tag::getText).collect(Collectors.toList()))
                .build();
    }
}
