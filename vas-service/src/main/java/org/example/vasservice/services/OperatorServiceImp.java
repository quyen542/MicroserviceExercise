package org.example.vasservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.example.vasservice.dto.OperatorDTO;
import org.example.vasservice.entities.Category;
import org.example.vasservice.repositories.CategoryRepository;
import org.example.vasservice.repositories.OperatorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorServiceImp implements OperatorService {

  @Autowired
  private OperatorRepository operatorRepo;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private CategoryRepository categoryRepository;




  @Override
  public List<OperatorDTO> getOperatorsByCategoryId(Long id) {
    Category c = categoryRepository.findById(id).get();

    return c.getOperators().stream()
        .map(operatorDto -> this.modelMapper.map(operatorDto, OperatorDTO.class))
        .collect(Collectors.toList());
  }
}
