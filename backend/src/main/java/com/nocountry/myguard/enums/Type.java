package com.nocountry.myguard.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;

import java.util.stream.Stream;

public enum Type {
    WEEK("Week"),
    WEEKEND("Weekwnd");

    @Column(name = "type")
    private final String type;

    Type(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    @JsonCreator
    public static Type fromType(String type){
        return Stream.of(Type.values())
                .filter(targetEnum -> targetEnum.type.equals(type))
                .findFirst()
                .orElse(null);
    }
}
