package com.buinevich.mycollection.services;

import com.buinevich.mycollection.model.entities.Comment;
import com.buinevich.mycollection.model.mappers.CommentMapper;
import com.buinevich.mycollection.model.repositories.CommentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    private CommentRepo commentRepo;
    private CommentMapper commentMapper;
    private UserService userService;
    private ItemService itemService;

    public List<Comment> findComments(String text) {
        return commentRepo.findByTextContaining(text);
    }

    public Comment createComment(long itemId, String text) {
       return commentRepo.save(commentMapper.commentRequestToComment(itemService.findById(itemId), text, userService.getCurrentUser()));
    }
}
