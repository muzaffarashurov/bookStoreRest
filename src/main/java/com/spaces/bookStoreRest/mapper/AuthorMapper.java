package com.spaces.bookStoreRest.mapper;

import com.spaces.bookStoreRest.dto.AuthorResponse;
import com.spaces.bookStoreRest.dto.CreateAuthorRequest;
import com.spaces.bookStoreRest.entity.Author;
import com.spaces.bookStoreRest.model.AuthorModel;
import com.spaces.bookStoreRest.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorMapper {
    private final BookRepository bookRepository;

    public AuthorModel mapFromEntityToModel(Author author) {
        log.info(author.toString());
        var booksIds = author.getBooks().stream()
                .map(b -> b.getId())
                .collect(Collectors.toSet());

        return AuthorModel.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .booksIds(booksIds)
                .build();
    }

    public AuthorResponse mopFromModelToDto(AuthorModel author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .booksIds(author.getBooksIds())
                .build();
    }

    public Author mapFromModelToEntity(AuthorModel author) {
        var books = new HashSet<>(bookRepository.findAllById(author.getBooksIds()));

        return Author.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .books(books)
                .build();
    }

    public AuthorModel mapFromRequestToModel(CreateAuthorRequest request) {
        return AuthorModel.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .booksIds(request.getBooksIds())
                .build();

    }
}
