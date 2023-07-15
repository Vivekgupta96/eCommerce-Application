package Ecom.Service;

import java.util.List;

import Ecom.Exception.CartException;
import Ecom.Model.Cart;
import Ecom.Model.Product;

public interface CartService {
	
	public Cart addProductToCart(Integer userId,Product product) throws CartException;
	
	public Cart increaseProductQuantity(Integer cartId,Integer quantity) throws CartException;
	
	public Cart decreaseProductQuantity(Integer cartId,Integer quantity) throws CartException;
	
	public void removeProductFromCart(Integer cartId,Integer productId) throws CartException;
	
	public List<Product> getAllCartProduct(Integer cartId)throws CartException;
	
	public void removeAllProduct(Integer cartId)throws CartException;

}
