package org.example.vasservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.example.vasservice.dto.CategoryDTO;
import org.example.vasservice.entities.Category;
import org.example.vasservice.entities.Operator;
import org.example.vasservice.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;


  @Autowired
  private ModelMapper modelMapper;


  @Override
  public void addCategory(Category category) {
    for (Operator op : category.getOperators()) {
      op.setCategory(category);
    }
    categoryRepository.save(category);
  }


  @Override
  public String getCategoryNameById(Long id) {
    Category c = categoryRepository.findById(id).get();
    return c.getName();
  }

  @Override
  public List<CategoryDTO> getCategories() {
    List<Category> categories = categoryRepository.findAll();
    return categories.stream().map(c -> this.modelMapper.map(c, CategoryDTO.class))
        .collect(Collectors.toList());
  }


}
