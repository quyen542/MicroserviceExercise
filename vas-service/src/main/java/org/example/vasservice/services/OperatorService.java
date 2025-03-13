package org.example.vasservice.services;

import java.util.List;
import org.example.vasservice.dto.OperatorDTO;

public interface OperatorService {

    List<OperatorDTO> getOperatorsByCategoryId(Long id);

}
