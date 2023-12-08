package com.spaces.bookStoreRest.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AuthorModel {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<Long> booksIds;
}
