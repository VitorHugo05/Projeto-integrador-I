package com.einstein.event.mapper;

import com.einstein.event.dtos.response.InscriptionResponseDto;
import com.einstein.event.dtos.response.StudentPresenceResponseDto;
import com.einstein.event.entites.StudentPresenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PresenceDtoMapper {
    @Mapping(source = "student.ra", target = "ra")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.title", target = "title")
    @Mapping(source = "event.description", target = "description")
    @Mapping(source = "event.startTime", target = "startTime")
    @Mapping(source = "event.endTime", target = "endTime")
    @Mapping(source = "event.date", target = "date")
    InscriptionResponseDto toResponse(StudentPresenceEntity presenceEntity);
}
