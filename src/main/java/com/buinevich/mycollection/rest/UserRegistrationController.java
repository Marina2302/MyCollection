package com.buinevich.mycollection.rest;

import com.buinevich.mycollection.config.JwtProvider;
import com.buinevich.mycollection.model.dto.AuthRequest;
import com.buinevich.mycollection.model.dto.AuthResponse;
import com.buinevich.mycollection.model.entities.User;
import com.buinevich.mycollection.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserRegistrationController {

    private UserService userService;
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid AuthRequest AuthRequest) {
        userService.createUser(AuthRequest);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getLogin(), user.getId());
        return new AuthResponse(token);
    }
}
