package Ecom.ModelDTO;


import lombok.Data;

@Data
public class ProductDTO {

	private String name;

	private String imageUrl;

	private String description;

	private double price;

	private String category;
}
