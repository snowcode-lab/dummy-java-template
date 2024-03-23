package com.snowcode.lab.dummy.java.template.controller.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record DummyCreateRequest(@NotEmpty String name) {}
