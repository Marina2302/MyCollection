package com.buinevich.mycollection.model.dto;

import com.buinevich.mycollection.model.enums.Role;
import com.buinevich.mycollection.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    String login;
    Status status;
    Set<Role> roles;

}
