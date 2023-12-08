package com.spaces.bookStoreRest.rest;

import com.spaces.bookStoreRest.dto.AuthorResponse;
import com.spaces.bookStoreRest.dto.CreateAuthorRequest;
import com.spaces.bookStoreRest.mapper.AuthorMapper;
import com.spaces.bookStoreRest.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping("/all")
    public List<AuthorResponse> getAll() {
        return authorService.getAll().stream()
                .map(authorMapper::mopFromModelToDto)
                .toList();
    }

    @PostMapping("/create")
    public void createAuthor(@RequestBody CreateAuthorRequest request) {
        var model = authorMapper.mapFromRequestToModel(request);
        authorService.create(model);
        log.info("Author created");
    }

    @GetMapping("/{id}")
    public AuthorResponse findById(@PathVariable("id") Long id) {
        return authorService.findById(id)
                .map(authorMapper::mopFromModelToDto)
                .orElseThrow(() -> new NoSuchElementException("Author with id " + id + " not found"));
    }

    @PutMapping("update/{id}")
    public AuthorResponse update(@RequestBody CreateAuthorRequest request, @PathVariable Long id) {
        var author = authorService.findById(id).orElseThrow();
        var model = authorMapper.mapFromRequestToModel(request);
        model.setId(id);
        var response = authorService.update(model);

        return authorMapper.mopFromModelToDto(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}
