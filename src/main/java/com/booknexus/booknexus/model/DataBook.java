package com.booknexus.booknexus.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(

    @JsonAlias("id") Integer id,
    @JsonAlias("title") String title,
    @JsonAlias("download_count") Integer downloadAccount,
    @JsonAlias("authors")List<DataAuthor> authors,
    @JsonAlias("languages") List<DataLanguage> languages


) {
}
