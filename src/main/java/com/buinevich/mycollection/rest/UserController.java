package com.buinevich.mycollection.rest;

import com.buinevich.mycollection.model.dto.UserResponse;
import com.buinevich.mycollection.model.enums.Role;
import com.buinevich.mycollection.model.enums.Status;
import com.buinevich.mycollection.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PatchMapping("status/{id}")
    public void changeUserStatus(@PathVariable long id, @RequestParam("status") Status status) {
        userService.changeUserStatus(id, status);
    }

    @PatchMapping("role/{id}")
    public void changeUserRole(@PathVariable long id, @RequestParam("status") HashSet<Role> roles) {
        userService.changeUserRole(id, roles);
    }

}
