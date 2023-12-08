package com.spaces.bookStoreRest.rest;

import com.spaces.bookStoreRest.dto.BookResponse;
import com.spaces.bookStoreRest.dto.CreateBookRequest;
import com.spaces.bookStoreRest.mapper.BookMapper;
import com.spaces.bookStoreRest.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/all")
    public List<BookResponse> getAll() {
        return bookService.getAll().stream()
                .map(bookMapper::mopFromModelToDto)
                .toList();
    }

    @PostMapping("/create")
    public void createBook(@RequestBody CreateBookRequest request) {
        var model = bookMapper.mapFromRequestToModel(request);
        bookService.create(model);
        log.info("Book created");
    }

    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable("id") Long id) {
        return bookService.findById(id)
                .map(bookMapper::mopFromModelToDto)
                .orElseThrow(() -> new NoSuchElementException("Book with id " + id + " not found"));
    }

    @PutMapping("update/{id}")
    public BookResponse update(@RequestBody CreateBookRequest request, @PathVariable Long id) {
        var book = bookService.findById(id).orElseThrow();
        var model = bookMapper.mapFromRequestToModel(request);
        model.setId(id);
        var response = bookService.update(model);

        return bookMapper.mopFromModelToDto(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
