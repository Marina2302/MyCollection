package com.buinevich.mycollection.model.dto;

import com.buinevich.mycollection.model.entities.Item;
import com.buinevich.mycollection.model.entities.Tag;
import com.buinevich.mycollection.model.enums.Theme;
import com.buinevich.mycollection.model.entities.User;
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
    private List<Tag> tags;
    private List<Item> items;
    private User owner;
}
