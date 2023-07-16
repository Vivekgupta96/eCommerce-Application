package Ecom.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Exception.ProductException;
import Ecom.Model.Product;
import Ecom.ModelDTO.ProductDTO;
import Ecom.Repository.CategoryRepository;
import Ecom.Repository.ProductRepository;
import Ecom.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	@Autowired
	private  CategoryRepository categoryRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product addProduct(Product product) throws ProductException {
		if (product == null)
			throw new ProductException("Product Can not be Null");
		return productRepository.save(product);
	}
	
//	
//	public Product addProduct(Product product) throws ProductException {
//        // Check if the category exists or create a new one if needed
//        Category category = product.getCategory();
//        if (category != null && category.getCategoryId() == null) {
//        	
//        	Category category1 = new Category();
//        	category1.setName(category.getName());
//            categoryRepository.save(category1);
//            product.setCategory(category1);
//        
//        }
//        return productRepository.save(product);
//    
//
//	
//	}	
	
	
	@Override
	public Product updateProduct(Integer productId, ProductDTO updatedProduct) throws ProductException {

		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ProductException("Product with ID " + productId + " not found."));
		
		// Update the existing product's properties with the new data
		existingProduct.setName(updatedProduct.getName());
		existingProduct.setCategory(updatedProduct.getCategory());
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setImageUrl(updatedProduct.getImageUrl());
		existingProduct.setDescription(null);
		
		return productRepository.save(existingProduct);
	}

	@Override
	public List<Product> getProductByName(String name) throws ProductException {
		// Retrieve products by name from the database
		List<Product> existProductByName = productRepository.findByName(name)
				.orElseThrow(() -> new ProductException("Product Not found with name " + name));

		return existProductByName;
	}

	@Override
	public List<Product> getAllProduct() throws ProductException {

		List<Product> getAllpProduct = productRepository.findAll();
		if (getAllpProduct.isEmpty()) {
			throw new ProductException("Product List Empty");
		}
		return getAllpProduct;
	
	}

	@Override
	public List<Product> getProductByCategory(String category) throws ProductException {
		// Retrieve products by category from the database
	 List<Product> allproductCategoryName = productRepository.getProductCategoryName(category);
	 if(allproductCategoryName.isEmpty()) throw new ProductException("Product with category Name " + category + " not found.");
	
	 return allproductCategoryName;
	}
	

	@Override
	public void removeProduct(Integer productId) throws ProductException {
	
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ProductException("Product with ID " + productId + " not found."));
		
		productRepository.delete(existingProduct);
	}

	
}
