package com.spaces.bookStoreRest.model;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class BookModel {
    private Long id;
    private String title;
    private String isbn;
    private Long publisherId;
    private Set<Long> authorsIds;
}
