package com.einstein.event.mapper;

import com.einstein.event.dtos.request.RectorRequestDto;
import com.einstein.event.dtos.response.RectorResponseDto;
import com.einstein.event.entites.RectorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RectorDtoMapper {
    @Mapping(target = "id", ignore = true)
    RectorEntity toEntity(RectorRequestDto rectorRequestDto);
    RectorResponseDto toResponse(RectorEntity rectorEntity);
}
