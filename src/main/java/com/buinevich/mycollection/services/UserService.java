package com.buinevich.mycollection.services;

import com.buinevich.mycollection.exceptions.ConflictException;
import com.buinevich.mycollection.exceptions.NotFoundException;
import com.buinevich.mycollection.model.dto.AuthRequest;
import com.buinevich.mycollection.model.dto.UserResponse;
import com.buinevich.mycollection.model.entities.User;
import com.buinevich.mycollection.model.enums.Role;
import com.buinevich.mycollection.model.enums.Status;
import com.buinevich.mycollection.model.mappers.UserMapper;
import com.buinevich.mycollection.model.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND = "User not found.";
    private static final String USER_ALREADY_EXISTS = "User with this login already exists.";
    private static final String INVALID_CREDENTIALS = "Invalid name or password.";

    private UserRepo userRepo;
    private BCryptPasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public User createUser(AuthRequest authRequest) {
        authRequest.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        if (userRepo.existsByLogin(authRequest.getLogin())) {
            throw new ConflictException(USER_ALREADY_EXISTS);
        }
        User createdUser = userMapper.userRequestToUser(authRequest);
        if (userRepo.findAll().isEmpty()) {
            createdUser.getRoles().add(Role.ADMIN);
        }
        createdUser.setStatus(Status.ACTIVE);
        createdUser.getRoles().add(Role.USER);
        return userRepo.save(createdUser);
    }

    public User findByLoginAndPassword(String name, String password) {
        User user = userRepo.findByLogin(name).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        throw new UsernameNotFoundException(INVALID_CREDENTIALS);
    }


    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream()
                .map(user -> userMapper.userToUserResponse(user))
                .collect(Collectors.toList());
    }

    public UserResponse getUser(long id) {
        return userMapper.userToUserResponse(userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND)));
    }

    public void changeUserStatus(long id, Status status) {
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.setStatus(status);
    }

    public void changeUserRoles(long id, HashSet<Role> roles) {
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.setRoles(roles);
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login;
        if (principal instanceof UserDetails) {
            login = ((UserDetails) principal).getUsername();
        } else {
            login = principal.toString();
        }
        return userRepo.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }
}
