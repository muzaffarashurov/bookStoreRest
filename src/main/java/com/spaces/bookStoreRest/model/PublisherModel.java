package com.spaces.bookStoreRest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublisherModel {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
}
