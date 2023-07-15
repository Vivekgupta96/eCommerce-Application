package Ecom.ServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Exception.CartException;
import Ecom.Exception.ProductException;
import Ecom.Exception.UserException;
import Ecom.Model.Cart;
import Ecom.Model.CartItem;
import Ecom.Model.Product;
import Ecom.Model.User;
import Ecom.Repository.CartRepository;
import Ecom.Repository.ProductRepository;
import Ecom.Repository.UserRepository;
import Ecom.Service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private final ProductRepository productRepository;

	private final CartRepository cartRepository;

	private final UserRepository userRepository;

	@Autowired
	public CartServiceImpl(ProductRepository productRepository, CartRepository cartRepository,
			UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}
	


	@Override
	public Cart addProductToCart(Integer userId, Product product) throws CartException {

		Product existingProduct = productRepository.findById(product.getProductId())
				.orElseThrow(() -> new ProductException("Product Not Found"));
		
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User Not Found In Database"));

		if (existingUser.getCart() != null) {
			Cart userCart = existingUser.getCart();

			List<CartItem> cartItems = userCart.getCartItems();
			
			for (int i = 0; i < cartItems.size(); i++) {
				if (cartItems.get(i).getProduct().getProductId() == product.getProductId()) {
					throw new ProductException("Product Already in the Cart");
				}
			}

			// Create a new cart item and add it to the cart
			CartItem cartItem = new CartItem();
			cartItem.setProduct(existingProduct);
			cartItem.setQuantity(1); 
			cartItem.setCart(userCart);
			cartItems.add(cartItem);

			// Update the cart total and save the changes
			userCart.setCartItems(cartItems);
			userCart.setTotalAmount(calculateCartTotal(cartItems));
			cartRepository.save(userCart);

			return userCart;
		} else {
			
			// Create a new cart for the user
			Cart newCart = new Cart();
			newCart.setUser(existingUser);

			// Create a new cart item and add it to the cart
			CartItem cartItem = new CartItem();
			cartItem.setProduct(existingProduct);
			cartItem.setQuantity(1); 
			cartItem.setCart(newCart);
			newCart.setCartItems(Collections.singletonList(cartItem));

			// Update the cart total and save the changes
			newCart.setTotalAmount(calculateCartTotal(newCart.getCartItems()));
			cartRepository.save(newCart);

			existingUser.setCart(newCart);
			userRepository.save(existingUser);

			return newCart;
		}
	}

	private BigDecimal calculateCartTotal(List<CartItem> cartItems) {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem item : cartItems) {
			BigDecimal itemPrice = item.getProduct().getPrice();
			BigDecimal itemQuantity = BigDecimal.valueOf(item.getQuantity());
			total = total.add(itemPrice.multiply(itemQuantity));
		}
		return total;
	}

	
	
	@Override
	public Cart increaseProductQuantity(Integer userId, Integer cartItemId) throws CartException {
	    User existingUser = userRepository.findById(userId)
	            .orElseThrow(() -> new UserException("User Not Found in Database"));

	    if (existingUser.getCart() == null) {
	        throw new CartException("Cart Not Found");
	    }

	    Cart userCart = existingUser.getCart();
	    List<CartItem> cartItems = userCart.getCartItems();
	    CartItem cartItemToUpdate = cartItems.stream()
	            .filter(item -> item.getCartItemId().equals(cartItemId))
	            .findFirst()
	            .orElseThrow(() -> new CartException("Cart Item Not Found"));

	    int quantity = cartItemToUpdate.getQuantity();
	    cartItemToUpdate.setQuantity(quantity + 1); // Increase quantity by 1 or any desired increment

	    // Update the cart total and save the changes
	    userCart.setCartItems(cartItems);
	    userCart.setTotalAmount(calculateCartTotal(cartItems));
	    cartRepository.save(userCart);

	    return userCart;
	}

	@Override
	public Cart decreaseProductQuantity(Integer userId, Integer cartItemId) throws CartException {
	    User existingUser = userRepository.findById(userId)
	            .orElseThrow(() -> new UserException("User Not Found in Database"));

	    if (existingUser.getCart() == null) {
	        throw new CartException("Cart Not Found");
	    }

	    Cart userCart = existingUser.getCart();
	    List<CartItem> cartItems = userCart.getCartItems();
	    CartItem cartItemToUpdate = cartItems.stream()
	            .filter(item -> item.getCartItemId().equals(cartItemId))
	            .findFirst()
	            .orElseThrow(() -> new CartException("Cart Item Not Found"));

	    int quantity = cartItemToUpdate.getQuantity();
	    if (quantity > 1) {
	        cartItemToUpdate.setQuantity(quantity - 1); // Decrease quantity by 1 or any desired decrement

	        // Update the cart total and save the changes
	        userCart.setCartItems(cartItems);
	        userCart.setTotalAmount(calculateCartTotal(cartItems));
	        cartRepository.save(userCart);
	    } else {
	        // Remove the cart item if the quantity becomes zero
	        cartItems.remove(cartItemToUpdate);

	        // Update the cart total and save the changes
	        userCart.setCartItems(cartItems);
	        userCart.setTotalAmount(calculateCartTotal(cartItems));
	        cartRepository.save(userCart);
	    }

	    return userCart;
	}


	@Override
	public void removeProductFromCart(Integer cartId, Integer productId) throws CartException {
	    Cart existingCart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new CartException("Cart Not Found"));

	    List<CartItem> cartItems = existingCart.getCartItems();
	    Optional<CartItem> cartItemToRemove = cartItems.stream()
	            .filter(item -> item.getProduct().getProductId().equals(productId))
	            .findFirst();

	    if (cartItemToRemove.isPresent()) {
	        cartItems.remove(cartItemToRemove.get());

	        // Update the cart total and save the changes
	        existingCart.setCartItems(cartItems);
	        existingCart.setTotalAmount(calculateCartTotal(cartItems));
	        cartRepository.save(existingCart);
	    } else {
	        throw new CartException("Product not found in the cart");
	    }
	}

	@Override
	public List<Product> getAllCartProduct(Integer cartId) throws CartException {
	    Cart existingCart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new CartException("Cart Not Found"));

	    List<CartItem> cartItems = existingCart.getCartItems();
	    List<Product> products = new ArrayList<>();

	    for (CartItem cartItem : cartItems) {
	        Product product = cartItem.getProduct();
	        products.add(product);
	    }

	    return products;
	}

	@Override
	public void removeAllProduct(Integer cartId) throws CartException {
	    Cart existingCart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new CartException("Cart Not Found"));

	    existingCart.getCartItems().clear();
	    existingCart.setTotalAmount(BigDecimal.ZERO);
	    cartRepository.save(existingCart);
	}

	

}
