package com.buinevich.mycollection.model.mappers;

import com.buinevich.mycollection.model.dto.CommentResponse;
import com.buinevich.mycollection.model.entities.Comment;
import com.buinevich.mycollection.model.entities.Item;
import com.buinevich.mycollection.model.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment commentRequestToComment(Item item, String text, User author) {
        return Comment.builder()
                .item(item)
                .author(author)
                .text(text)
                .build();
    }

    public CommentResponse commentToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .author(comment.getAuthor().getLogin())
                .text(comment.getText())
                .build();
    }
}
