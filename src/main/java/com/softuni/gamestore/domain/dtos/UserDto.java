package com.softuni.gamestore.domain.dtos;

import com.softuni.gamestore.domain.entities.Game;
import com.softuni.gamestore.domain.entities.Order;
import com.softuni.gamestore.domain.entities.Role;

import java.util.Set;

public class UserDto {

    private String fullName;
    private String email;
    private String password;
    private Role role;

    public UserDto() {
    }

    public UserDto(String fullName, String email, String password, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
