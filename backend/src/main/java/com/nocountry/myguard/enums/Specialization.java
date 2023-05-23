package com.nocountry.myguard.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;

import java.util.stream.Stream;

public enum Specialization {
    PSYCHOLOGY("Psychology"),
    PSYCHIATRY("Psychiatry"),
    SOCIAL_WORK("Social Work");

    @Column(name = "name")
    private final String name;

    Specialization(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static Specialization fromName(String name) {
        return Stream.of(Specialization.values())
                .filter(targetEnum -> targetEnum.name.equals(name))
                .findFirst()
                .orElse(null);



    }
}
