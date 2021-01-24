package com.buinevich.mycollection.model.enums;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String roleUser;

    Role(String roleUser) {
        this.roleUser = roleUser;
    }

    public String getRoleUser() {
        return roleUser;
    }
}
