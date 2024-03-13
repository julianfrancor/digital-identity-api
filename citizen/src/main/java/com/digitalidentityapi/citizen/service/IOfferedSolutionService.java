package com.digitalidentityapi.citizen.service;

import com.digitalidentityapi.citizen.dto.OfferedSolutionDto;
import java.util.List;

public interface IOfferedSolutionService {
    OfferedSolutionDto createOfferedSolution(OfferedSolutionDto offeredSolutionDto);
    OfferedSolutionDto updateOfferedSolution(int id, OfferedSolutionDto offeredSolutionDto);
    void deleteOfferedSolution(int id);
    OfferedSolutionDto getOfferedSolutionById(int id);
    List<OfferedSolutionDto> getAllOfferedSolutions();
}
