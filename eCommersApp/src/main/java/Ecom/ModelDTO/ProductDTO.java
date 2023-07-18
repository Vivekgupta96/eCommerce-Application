package Ecom.ModelDTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {

	private String name;

	private String imageUrl;

	private String description;

	private BigDecimal price;

	private String category;
}
