package com.spaces.bookStoreRest.mapper;

import com.spaces.bookStoreRest.dto.CreatePublisherRequest;
import com.spaces.bookStoreRest.dto.PublisherResponse;
import com.spaces.bookStoreRest.entity.Publisher;
import com.spaces.bookStoreRest.model.PublisherModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublisherMapper {

    public PublisherModel mapFromEntityToModel(Publisher publisher) {
        log.info(publisher.toString());
        return PublisherModel.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .address(publisher.getAddress())
                .city(publisher.getCity())
                .state(publisher.getState())
                .zip(publisher.getZip())
                .build();
    }

    public PublisherResponse mopFromModelToDto(PublisherModel publisher) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .address(publisher.getAddress())
                .city(publisher.getCity())
                .state(publisher.getState())
                .zip(publisher.getZip())
                .build();
    }

    public Publisher mapFromModelToEntity(PublisherModel publisher) {
        return Publisher.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .address(publisher.getAddress())
                .city(publisher.getCity())
                .state(publisher.getState())
                .zip(publisher.getZip())
                .build();
    }

    public PublisherModel mapFromRequestToModel(CreatePublisherRequest request) {
        return PublisherModel.builder()
                .id(request.getId())
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .zip(request.getZip())
                .build();
    }
}
