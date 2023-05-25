package com.nocountry.myguard.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;

import java.util.stream.Stream;

public enum Role {
    ADMIN("Admin"),
    USER("User");


    @Column(name = "role")
    private final String role;

    Role(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole(){
        return role;
    }

    @JsonCreator
    public static Role fromRole(String role){
        return Stream.of(Role.values())
                .filter(targetEnum -> targetEnum.role.equals(role))
                .findFirst()
                .orElse(null);
    }

}
