package com.buinevich.mycollection.rest;

import com.buinevich.mycollection.model.entities.Tag;
import com.buinevich.mycollection.model.repositories.TagRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/tags")
@AllArgsConstructor
public class TagController {

    private TagRepo tagRepo;

    @GetMapping
    public Collection<String> getTags() {
        return tagRepo.findAll(Sort.by(Sort.Direction.DESC, "popularity")).stream().map(Tag::getText).collect(Collectors.toList());
    }
}
