package com.buinevich.mycollection.model.dto;

import com.buinevich.mycollection.model.enums.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionRequest {
    @NotBlank(message = "Name is mandatory field.")
    private String name;
    private String description;
    private String image;
    @NotBlank(message = "Theme is mandatory field.")
    private Theme theme;
    private List<String> tags;
    private long ownerId;
}
