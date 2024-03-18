package com.snowcode.lab.dummy.java.template.dao.postgresql.mapper;

import com.snowcode.lab.dummy.java.template.dao.model.Dummy;
import com.snowcode.lab.dummy.java.template.dao.postgresql.model.DummyEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DummyDaoMapper {

    Dummy convertToModel(DummyEntity dummyEntity);

    DummyEntity convertToEntity(Dummy dummy);

}
