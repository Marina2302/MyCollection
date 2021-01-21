package com.buinevich.mycollection.services;

import com.buinevich.mycollection.exceptions.NotFoundException;
import com.buinevich.mycollection.model.entities.Comment;
import com.buinevich.mycollection.model.entities.Item;
import com.buinevich.mycollection.model.repositories.ItemRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    private static final String ITEM_NOT_FOUND = "Item not found.";

    private ItemRepo itemRepo;
    private CommentService commentService;
    private UserService userService;

    public Collection<Item> findItems(String param) {
        List<Comment> comments = commentService.findComments(param);
        HashSet<Item> items = new HashSet<>(itemRepo.findByNameContainingOrDescriptionContaining(param, param));
        items.addAll(comments.stream().map(Comment::getItem).collect(Collectors.toSet()));
        return items;
    }

    public Collection<Item> getAllItems() {
        return itemRepo.findAll();
    }

    public void addLike(long itemId) {
        itemRepo.findById(itemId).orElseThrow(() -> new NotFoundException(ITEM_NOT_FOUND))
                .getUsersLiked().add(userService.getCurrentUser());
    }

    public Item findById(long itemId) {
        return itemRepo.findById(itemId).orElseThrow(() -> new NotFoundException(ITEM_NOT_FOUND));
    }

    public void deleteItem(long itemId) {
        itemRepo.deleteById(itemId);
    }
}
