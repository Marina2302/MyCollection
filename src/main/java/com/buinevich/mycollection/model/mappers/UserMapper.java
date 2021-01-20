package com.buinevich.mycollection.model.mappers;

import com.buinevich.mycollection.model.dto.AuthRequest;
import com.buinevich.mycollection.model.dto.UserResponse;
import com.buinevich.mycollection.model.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User userRequestToUser(AuthRequest authRequest) {
        return new User(authRequest.getLogin(),
                authRequest.getPassword());
    }

    public UserResponse userToUserResponse(User user) {
        return new UserResponse(user.getLogin(), user.getStatus(), user.getRoles());
    }

}
