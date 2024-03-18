package com.snowcode.lab.dummy.java.template.dao.model;

import lombok.Builder;
import lombok.With;

import java.util.UUID;

@With
@Builder(toBuilder = true)
public record Dummy(UUID id,
                    String name) {}
