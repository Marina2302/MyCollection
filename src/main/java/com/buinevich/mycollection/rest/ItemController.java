package com.buinevich.mycollection.rest;

import com.buinevich.mycollection.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/items")
public class ItemController {

    private ItemService itemService;

    public void create() {

    }

    public void update() {

    }

    public void delete() {

    }

    public void findByName(){

    }
}
