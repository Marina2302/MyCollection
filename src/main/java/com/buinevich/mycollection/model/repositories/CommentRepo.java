package com.buinevich.mycollection.model.repositories;

import com.buinevich.mycollection.model.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo  extends JpaRepository<Comment, Long> {

    List<Comment> findByTextContaining(String text);
}
