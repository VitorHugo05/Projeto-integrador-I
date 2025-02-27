package com.einstein.event.mapper;

import com.einstein.event.dtos.request.CourseRequestDto;
import com.einstein.event.dtos.response.CourseResponseDto;
import com.einstein.event.entites.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseDtoMapper {
    @Mapping(target = "coordinator", ignore = true)
    CourseEntity toEntity(CourseRequestDto courseRequestDto);

    CourseResponseDto toResponse(CourseEntity courseEntity);
}
