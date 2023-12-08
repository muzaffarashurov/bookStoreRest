package com.spaces.bookStoreRest.rest;

import com.spaces.bookStoreRest.dto.CreatePublisherRequest;
import com.spaces.bookStoreRest.dto.PublisherResponse;
import com.spaces.bookStoreRest.mapper.PublisherMapper;
import com.spaces.bookStoreRest.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService publisherService;
    private final PublisherMapper publisherMapper;

    @GetMapping("/all")
    public List<PublisherResponse> getAll() {
        return publisherService.getAll().stream()
                .map(publisherMapper::mopFromModelToDto)
                .toList();
    }

    @PostMapping("/create")
    public void createPublisher(@RequestBody CreatePublisherRequest request) {
        var model = publisherMapper.mapFromRequestToModel(request);
        publisherService.create(model);
        log.info("Publisher created");
    }

    @GetMapping("/{id}")
    public PublisherResponse findById(@PathVariable("id") Long id) {
        return publisherService.findById(id)
                .map(publisherMapper::mopFromModelToDto)
                .orElseThrow(() -> new NoSuchElementException("Publisher with id " + id + " not found"));
    }

    @PutMapping("update/{id}")
    public PublisherResponse update(@RequestBody CreatePublisherRequest request, @PathVariable Long id) {
        var publisher = publisherService.findById(id).orElseThrow();
        var model = publisherMapper.mapFromRequestToModel(request);
        model.setId(id);
        var response = publisherService.update(model);

        return publisherMapper.mopFromModelToDto(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        publisherService.deleteById(id);
    }
}
