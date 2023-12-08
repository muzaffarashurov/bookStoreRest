package com.spaces.bookStoreRest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
}
