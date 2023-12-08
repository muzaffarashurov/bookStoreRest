package com.spaces.bookStoreRest.service;

import com.spaces.bookStoreRest.mapper.PublisherMapper;
import com.spaces.bookStoreRest.model.PublisherModel;
import com.spaces.bookStoreRest.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public List<PublisherModel> getAll() {
        return publisherRepository.findAll().stream()
                .map(publisherMapper::mapFromEntityToModel)
                .toList();
    }

    public void create(PublisherModel request) {
        var entity = publisherMapper.mapFromModelToEntity(request);
        publisherRepository.save(entity);
    }

    public Optional<PublisherModel> findById(Long id) {
        return publisherRepository.findById(id)
                .map(publisherMapper::mapFromEntityToModel);
    }

    public PublisherModel update(PublisherModel model) {
        var saved = publisherRepository.save(publisherMapper.mapFromModelToEntity(model));
        return publisherMapper.mapFromEntityToModel(saved);
    }

    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }
}
