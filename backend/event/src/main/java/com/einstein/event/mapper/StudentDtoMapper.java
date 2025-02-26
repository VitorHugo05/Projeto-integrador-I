package com.einstein.event.mapper;

import com.einstein.event.dtos.request.StudentRequestDto;
import com.einstein.event.entites.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentDtoMapper {
    @Mapping(target = "course", ignore = true)
    StudentEntity toEntity(StudentRequestDto studentRequestDto);
}
