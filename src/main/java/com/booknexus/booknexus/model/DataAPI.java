package com.booknexus.booknexus.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAPI(
        @JsonAlias("results")ArrayList<DataBook> books
        ) {
}
