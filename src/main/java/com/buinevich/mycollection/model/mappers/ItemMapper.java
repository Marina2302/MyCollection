package com.buinevich.mycollection.model.mappers;

import com.buinevich.mycollection.exceptions.NotFoundException;
import com.buinevich.mycollection.model.dto.ItemCollectionResponse;
import com.buinevich.mycollection.model.dto.ItemRequest;
import com.buinevich.mycollection.model.dto.ItemResponse;
import com.buinevich.mycollection.model.entities.Collection;
import com.buinevich.mycollection.model.entities.Item;
import com.buinevich.mycollection.model.entities.User;
import com.buinevich.mycollection.model.repositories.CollectionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ItemMapper {

    private static final String COLLECTION_NOT_FOUND = "Collection not found.";

    private CollectionRepo collectionRepo;
    private CommentMapper commentMapper;

    public Item itemRequestToItem(ItemRequest itemRequest) {
        Item.ItemBuilder itemBuilder = Item.builder()
                .name(itemRequest.getName())
                .description(itemRequest.getDescription())
                .image(itemRequest.getImage());
        if (itemRequest.getCollectionId() != null) {
            ArrayList<Collection> collections = new ArrayList<>();
            collections.add(collectionRepo.findById(itemRequest.getCollectionId())
                    .orElseThrow(() -> new NotFoundException(COLLECTION_NOT_FOUND)));
            itemBuilder.collections(collections);
        }
        return itemBuilder.build();
    }

    public ItemResponse itemToItemResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .image(item.getImage())
                .collections(item.getCollections().stream()
                        .map(collection -> ItemCollectionResponse.builder()
                                .id(collection.getId())
                                .name(collection.getName())
                                .build())
                        .collect(Collectors.toList()))
                .comments(item.getComments().stream()
                        .map(comment -> commentMapper.commentToCommentResponse(comment))
                        .collect(Collectors.toList()))
                .userLikedIds(item.getUsersLiked().stream()
                        .map(User::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
