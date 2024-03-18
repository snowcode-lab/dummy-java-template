package com.snowcode.lab.dummy.java.template.controller.mapper;

import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyCreateRequest;
import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyResponse;
import com.snowcode.lab.dummy.java.template.dao.model.Dummy;
import org.mapstruct.Mapper;

@Mapper
public interface DummyDtoMapper {

    Dummy convertToModel(DummyCreateRequest dummyCreateRequest);

    DummyResponse convertToDummyResponse(Dummy dummy);

}
