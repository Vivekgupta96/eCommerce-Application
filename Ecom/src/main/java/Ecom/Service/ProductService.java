package Ecom.Service;

import Ecom.Exception.ProductException;
import Ecom.Model.Product;

public interface ProductService {
	
	public Product addProduct(Product products)throws ProductException;

}
 