package com.einstein.event.mapper;

import com.einstein.event.dtos.request.RectorRequestDto;
import com.einstein.event.dtos.response.RectorResponseDto;
import com.einstein.event.entites.RectorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RectorDtoMapper {
    RectorEntity toEntity(RectorRequestDto rectorRequestDto);
    RectorResponseDto toResponse(RectorEntity rectorEntity);
}
