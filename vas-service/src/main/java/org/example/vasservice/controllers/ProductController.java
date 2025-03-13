package org.example.vasservice.controllers;

import java.util.List;

import org.example.vasservice.dto.ProductDTO;
import org.example.vasservice.entities.Product;
import org.example.vasservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    List<ProductDTO> getProductByOperatorId(@RequestParam("operatorId") Long id) {
        return productService.getProductByOperatorId(id);
    }
//    @GetMapping("")
//    ResponseEntity<Product> getProductById(@RequestParam("productId") Long id) {
//        return  ResponseEntity.ok(productService.getProductByProductId(id));
//    }
}
