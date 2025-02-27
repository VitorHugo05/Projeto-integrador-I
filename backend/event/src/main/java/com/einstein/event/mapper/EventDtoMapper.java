package com.einstein.event.mapper;

import com.einstein.event.dtos.request.EventRequestDto;
import com.einstein.event.dtos.response.EventResponseDto;
import com.einstein.event.entites.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventDtoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "coordinator", ignore = true)
    @Mapping(target = "studentPresenceList", ignore = true)
    EventEntity toEntity(EventRequestDto eventRequestDto);

    EventResponseDto toResponse(EventEntity eventEntity);
}
