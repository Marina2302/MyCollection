package com.buinevich.mycollection.services;

import com.buinevich.mycollection.model.entities.Comment;
import com.buinevich.mycollection.model.repositories.CommentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    private CommentRepo commentRepo;

    public List<Comment> findComments(String text) {
        return commentRepo.findByTextContaining(text);
    }

    public Comment createComment(Comment commentRequest) {
        return commentRepo.save(commentRequest);
    }
}
