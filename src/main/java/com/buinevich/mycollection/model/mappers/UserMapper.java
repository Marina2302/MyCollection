package com.buinevich.mycollection.model.mappers;

import com.buinevich.mycollection.model.dto.AuthRequest;
import com.buinevich.mycollection.model.entities.User;

public class UserMapper {

    public static User UserRequestToUser(AuthRequest authRequest) {
        return new User(authRequest.getLogin(),
                authRequest.getPassword());
    }

}
