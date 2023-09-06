package Ecom.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Exception.ProductException;
import Ecom.Model.Product;
import Ecom.ModelDTO.ProductDTO;
import Ecom.Repository.ProductRepository;
import Ecom.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
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
	
	@Override
	public Product updateProduct(Integer productId, ProductDTO updatedProduct) throws ProductException {

		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ProductException("Product with ID " + productId + " not found."));
		
		// Update the existing product's properties with the new data
		existingProduct.setName(updatedProduct.getName());
		existingProduct.setCategory(updatedProduct.getCategory());
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setImageUrl(updatedProduct.getImageUrl());
		existingProduct.setDescription(updatedProduct.getDescription());
		
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

	@Override
	public Product getSingleProduct(Integer productId) {

		Product  single=productRepository.findById(productId).orElseThrow(()-> new ProductException("Product not found"));
		return single;
	}


}
