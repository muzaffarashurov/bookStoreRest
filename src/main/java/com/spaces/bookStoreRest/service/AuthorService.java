package com.spaces.bookStoreRest.service;

import com.spaces.bookStoreRest.mapper.AuthorMapper;
import com.spaces.bookStoreRest.model.AuthorModel;
import com.spaces.bookStoreRest.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorModel> getAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::mapFromEntityToModel)
                .toList();
    }

    public void create(AuthorModel request) {
        var entity = authorMapper.mapFromModelToEntity(request);
        authorRepository.save(entity);
    }

    public Optional<AuthorModel> findById(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::mapFromEntityToModel);
    }

    public AuthorModel update(AuthorModel model) {
        var saved = authorRepository.save(authorMapper.mapFromModelToEntity(model));
        return authorMapper.mapFromEntityToModel(saved);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
