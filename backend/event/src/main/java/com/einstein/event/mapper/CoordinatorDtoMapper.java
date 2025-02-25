package com.einstein.event.mapper;

import com.einstein.event.dtos.request.CoordinatorRequestDto;
import com.einstein.event.dtos.response.CoordinatorResponseDto;
import com.einstein.event.entites.CoordinatorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordinatorDtoMapper {
    CoordinatorEntity toEntity(CoordinatorRequestDto coordinatorRequestDto);
    CoordinatorResponseDto toResponse(CoordinatorEntity coordinatorEntity);
}
