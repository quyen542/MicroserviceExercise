package org.example.vasservice.controllers;

import java.util.List;

import org.example.vasservice.dto.OperatorDTO;
import org.example.vasservice.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operators")
public class OperatorController {

  @Autowired
  private OperatorService operatorSer;


  @GetMapping("")
  List<OperatorDTO> getOperatorByCategoryId(@RequestParam("categoryId") Long id) {
    return operatorSer.getOperatorsByCategoryId(id);
  }
}
