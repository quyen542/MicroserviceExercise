package org.example.vasservice.services;

import java.util.List;
import org.example.vasservice.dto.ProductDTO;
import org.example.vasservice.entities.Product;

public interface ProductService {

  List<ProductDTO> getProductByOperatorId(Long id);

  Product getProductByProductId(Long id);

}
