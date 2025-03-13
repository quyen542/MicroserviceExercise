package org.example.vasservice.services;


import java.util.List;
import org.example.vasservice.dto.CategoryDTO;
import org.example.vasservice.entities.Category;


public interface CategoryService {

  void addCategory(Category category);

  String getCategoryNameById(Long id);

  List<CategoryDTO> getCategories();

}
