package com.snowcode.lab.dummy.java.template.controller;

import com.snowcode.lab.dummy.java.template.controller.api.DummyApi;
import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyCreateRequest;
import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyResponse;
import com.snowcode.lab.dummy.java.template.controller.mapper.DummyDtoMapper;
import com.snowcode.lab.dummy.java.template.service.DummyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@RestController
@RequiredArgsConstructor
public class DummyController implements DummyApi {

    private final DummyService dummyService;
    private final DummyDtoMapper dummyDtoMapper;

    @Override
    public Optional<DummyResponse> findDummyById(UUID id) {
        log.debug("Dummy is being searched by id:{}", id);
        var dummyResponse = dummyService.findById(id).map(dummyDtoMapper::convertToDummyResponse);
        log.debug("Dummy is found by id:{}, {}", id, dummyResponse);
        return dummyResponse;
    }

    @Override
    public DummyResponse createDummy(@Validated DummyCreateRequest request) {
        log.debug("Dummy is being created {}", request);
        var dummyToCreate = dummyDtoMapper.convertToModel(request);
        var createdDummy = dummyService.create(dummyToCreate);
        var dummyResponse = dummyDtoMapper.convertToDummyResponse(createdDummy);
        log.info("Dummy is created {}", dummyResponse);
        return dummyResponse;
    }
}
