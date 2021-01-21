package com.buinevich.mycollection.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponse {
    private long id;
    private String name;
    private String description;
    private String image;
    private List<CollectionResponse> collections;
    private List<CommentResponse> comments;
    private List<Long> userLikedIds;
}
