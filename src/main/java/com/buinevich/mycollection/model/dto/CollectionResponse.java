package com.buinevich.mycollection.model.dto;

import com.buinevich.mycollection.model.enums.Theme;
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
public class CollectionResponse {
    private long id;
    private String name;
    private String description;
    private String image;
    private Theme theme;
    private List<String> tags;
    private List<ItemResponse> items;
    private long ownerId;
}
