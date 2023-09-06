package Ecom.Service;

import java.util.List;

import Ecom.Exception.ProductException;
import Ecom.Model.Product;
import Ecom.ModelDTO.ProductDTO;

public interface ProductService {
	
	public Product addProduct(Product products)throws ProductException;
	
	public Product updateProduct(Integer productId,ProductDTO product)throws ProductException;
	
	public List<Product> getProductByName(String name)throws ProductException;
	
	public List<Product> getAllProduct()throws ProductException;
	
	public List<Product> getProductByCategory(String catagory) throws ProductException;
	
	public void removeProduct(Integer productId)throws ProductException;

	public Product getSingleProduct(Integer productId);
}
 