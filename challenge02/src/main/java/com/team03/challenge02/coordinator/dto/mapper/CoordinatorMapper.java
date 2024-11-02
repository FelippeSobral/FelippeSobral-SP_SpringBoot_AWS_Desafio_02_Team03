package com.team03.challenge02.coordinator.dto.mapper;

import com.team03.challenge02.coordinator.dto.CoordinatorDTO;
import com.team03.challenge02.coordinator.entity.Coordinator;

import java.util.List;

public class CoordinatorMapper {

    public static CoordinatorDTO toDto(Coordinator coordinator){
        return new CoordinatorDTO(coordinator.getId(), coordinator.getFirstName(),
                coordinator.getLastName(), coordinator.getDisciplinas(), coordinator.getCourse());
    }

    public static List<CoordinatorDTO> toAllDto(List<Coordinator> coordinator){
        return coordinator.stream().map(CoordinatorMapper::toDto).toList();
    }
}
