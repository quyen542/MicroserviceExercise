package org.example.vasservice.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductDTO {

  private Long id;
  private String name;
  private String description;
  private BigDecimal price;

}
