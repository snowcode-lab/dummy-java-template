package com.snowcode.lab.dummy.java.template.controller.api.dto;

import lombok.Builder;
import lombok.With;

import java.util.UUID;

@With
@Builder(toBuilder = true)
public record DummyResponse(UUID id,
                            String name) {}
