package com.snowcode.lab.dummy.java.template.controller.api;

import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyCreateRequest;
import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

public interface DummyApi {

    @GetMapping("/dummy/{id}")
    Optional<DummyResponse> findDummyById(@PathVariable(name = "id") UUID id);

    @PostMapping("/dummy")
    DummyResponse createDummy(@RequestBody DummyCreateRequest request);

}
