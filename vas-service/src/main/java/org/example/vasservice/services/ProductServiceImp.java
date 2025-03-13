package org.example.vasservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.example.vasservice.dto.ProductDTO;
import org.example.vasservice.entities.Operator;
import org.example.vasservice.entities.Product;
import org.example.vasservice.repositories.OperatorRepository;
import org.example.vasservice.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {

  @Autowired
  private OperatorRepository operatorRepo;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private ProductRepository productRepo;


  @Override
  public List<ProductDTO> getProductByOperatorId(Long id) {
    Operator operator = operatorRepo.findById(id).get();
    return operator.getProducts().stream().map(p -> this.modelMapper.map(p, ProductDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public Product getProductByProductId(Long id) {
    return productRepo.findById(id).get() ;
  }
}
