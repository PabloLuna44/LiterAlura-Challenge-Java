package com.booknexus.booknexus.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataLanguage(
        @JsonAlias("language") String name
) {
    @JsonCreator
    public DataLanguage(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
