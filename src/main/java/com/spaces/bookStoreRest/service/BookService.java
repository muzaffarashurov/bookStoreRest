package com.spaces.bookStoreRest.service;

import com.spaces.bookStoreRest.mapper.BookMapper;
import com.spaces.bookStoreRest.model.BookModel;
import com.spaces.bookStoreRest.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookModel> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapFromEntityToModel)
                .toList();
    }

    public void create(BookModel request) {
        var entity = bookMapper.mapFromModelToEntity(request);
        bookRepository.save(entity);
    }

    public Optional<BookModel> findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapFromEntityToModel);
    }

    public BookModel update(BookModel model) {
        var saved = bookRepository.save(bookMapper.mapFromModelToEntity(model));
        return bookMapper.mapFromEntityToModel(saved);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
