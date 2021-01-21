package com.buinevich.mycollection.model.mappers;

import com.buinevich.mycollection.model.dto.ItemRequest;
import com.buinevich.mycollection.model.dto.ItemResponse;
import com.buinevich.mycollection.model.entities.Comment;
import com.buinevich.mycollection.model.entities.Item;
import com.buinevich.mycollection.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ItemMapper {

    private CollectionMapper collectionMapper;
    private CommentMapper commentMapper;

    public Item itemRequestToItem(ItemRequest itemRequest) {
        return Item.builder()
                .name(itemRequest.getName())
                .description(itemRequest.getDescription())
                .image(itemRequest.getImage())
                .build();
    }

    public ItemResponse itemToItemResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .image(item.getImage())
                .collections(item.getCollections().stream()
                        .map(collection -> collectionMapper.collectionToCollectionResponse(collection))
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
