package com.buinevich.mycollection.model.dto;

import com.buinevich.mycollection.model.enums.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionRequest {
    private String name;
    private String description;
    private Theme theme;
}
