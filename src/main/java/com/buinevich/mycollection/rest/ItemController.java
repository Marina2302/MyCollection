package com.buinevich.mycollection.rest;

import com.buinevich.mycollection.model.dto.CommentResponse;
import com.buinevich.mycollection.model.dto.ItemRequest;
import com.buinevich.mycollection.model.dto.ItemResponse;
import com.buinevich.mycollection.model.mappers.CommentMapper;
import com.buinevich.mycollection.model.mappers.ItemMapper;
import com.buinevich.mycollection.services.CollectionService;
import com.buinevich.mycollection.services.CommentService;
import com.buinevich.mycollection.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/items")
public class ItemController {

    private ItemService itemService;
    private ItemMapper itemMapper;
    private CommentService commentService;
    private CommentMapper commentMapper;
    private CollectionService collectionService;

    @GetMapping("/find")
    public Collection<ItemResponse> findItems(@RequestParam String param) {
        return itemService.findItems(param).stream()
                .map(item -> itemMapper.itemToItemResponse(item))
                .collect(Collectors.toList());
    }

    @GetMapping()
    public Collection<ItemResponse> getAllItems() {
        return itemService.getAllItems().stream()
                .map(item -> itemMapper.itemToItemResponse(item))
                .collect(Collectors.toList());
    }

    @GetMapping("/collection/{id}")
    public Collection<ItemResponse> getAllItemsByCollectionId(@PathVariable long id) {
        return collectionService.getCollection(id).getItems().stream()
                .map(item -> itemMapper.itemToItemResponse(item))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ItemResponse getItem(@PathVariable long id) {
        return itemMapper.itemToItemResponse(itemService.getItem(id));
    }

    @PostMapping
    public ItemResponse create(@RequestBody ItemRequest itemRequest) {
        return itemMapper.itemToItemResponse(itemService.createItem(itemMapper.itemRequestToItem(itemRequest)));
    }

    @PutMapping("/{id}")
    public ItemResponse update(@PathVariable long id, @RequestBody ItemRequest itemRequest) {
        return itemMapper.itemToItemResponse(itemService.updateItem(id, itemMapper.itemRequestToItem(itemRequest)));
    }

    @PatchMapping("/{id}/comment")
    public CommentResponse addComment(@PathVariable long id, @RequestBody String comment) {
        return commentMapper.commentToCommentResponse(commentService.createComment(id, comment));
    }

    @PatchMapping("/{id}/like")
    public void addLike(@PathVariable long id) {
        itemService.addLike(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        itemService.deleteItem(id);
    }

}
