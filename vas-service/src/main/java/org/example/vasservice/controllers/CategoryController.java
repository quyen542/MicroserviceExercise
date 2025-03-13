package org.example.vasservice.controllers;

import java.util.List;

import org.example.vasservice.dto.CategoryDTO;
import org.example.vasservice.entities.Category;
import org.example.vasservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  CategoryService categoryService;

//  @PostMapping("/add")
//  String addCategory(@RequestBody Category category) {
//    categoryService.addCategory(category);
//    return "Category added";
//  }


  @GetMapping("/")
  List<CategoryDTO> getAllCategory() {
    return categoryService.getCategories();
  }


}
