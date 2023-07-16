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
import Ecom.Repository.CartItemRepository;
import Ecom.Repository.CartRepository;
import Ecom.Repository.ProductRepository;
import Ecom.Repository.UserRepository;
import Ecom.Service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private final ProductRepository productRepository;

	private final CartRepository cartRepository;

	private final CartItemRepository cartItemRepository;

	private final UserRepository userRepository;

	@Autowired
	public CartServiceImpl(ProductRepository productRepository, CartRepository cartRepository,
			UserRepository userRepository, CartItemRepository cartItemRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.cartItemRepository = cartItemRepository;
	}

	@Override
	public Cart addProductToCart(Integer userId, Integer productId) throws CartException {

		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ProductException("Product Not Found"));

		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User Not Found In Database"));

		if (existingUser.getCart() != null) {
			Cart userCart = existingUser.getCart();

			List<CartItem> cartItems = userCart.getCartItems();
			for (int i = 0; i < cartItems.size(); i++) {
				if (cartItems.get(i).getProduct().getProductId() == productId) {
					throw new ProductException("Product Already in the Cart");
				}
			}
			
			CartItem cartItem = new CartItem();// Create a new cart item and add it to the cart
			cartItem.setProduct(existingProduct);
			cartItem.setQuantity(1);
			cartItem.setCart(userCart);
			userCart.setCartItems(cartItems);// Update the cart total and save the changes
			userCart.setTotalAmount(calculateCartTotal(cartItems));
			cartRepository.save(userCart);
			cartItemRepository.save(cartItem);
			return userCart;
		} else {
			
			Cart newCart = new Cart();// Create a new cart for the user

			CartItem cartItem = new CartItem();// Create a new cart item and add it to the cart

			cartItem.setProduct(existingProduct);
			cartItem.setQuantity(1);
			cartItem.setCart(newCart);

			newCart.getCartItems().add(cartItem);
			cartItem.setCart(newCart);

			newCart.setTotalAmount(calculateCartTotal(newCart.getCartItems()));// Update the cart total and save the changes

			existingUser.setCart(newCart);
			newCart.setUser(existingUser);

			cartItemRepository.save(cartItem);
			cartRepository.save(newCart);
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
	public Cart increaseProductQuantity(Integer userId, Integer productId) throws CartException {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User Not Found in Database"));

		if (existingUser.getCart() == null) {
			throw new CartException("Cart Not Found");
		}

		Cart userCart = existingUser.getCart();
		List<CartItem> cartItems = userCart.getCartItems();
		CartItem cartItemToUpdate = cartItems.stream().filter(item -> item.getProduct().getProductId().equals(productId))
				.findFirst().orElseThrow(() -> new CartException("Cart Item Not Found"));

		int quantity = cartItemToUpdate.getQuantity();
		cartItemToUpdate.setQuantity(quantity + 1);

		// Update the cart total and save the changes
		userCart.setCartItems(cartItems);
		userCart.setTotalAmount(calculateCartTotal(cartItems));
		cartRepository.save(userCart);

		return userCart;
	}

	@Override
	public Cart decreaseProductQuantity(Integer userId, Integer productId) throws CartException {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User Not Found in Database"));

		if (existingUser.getCart() == null) {
			throw new CartException("Cart Not Found");
		}

		Cart userCart = existingUser.getCart();
		List<CartItem> cartItems = userCart.getCartItems();
		CartItem cartItemToUpdate = cartItems.stream().filter(item -> item.getProduct().getProductId().equals(productId))
				.findFirst().orElseThrow(() -> new CartException("Cart Item Not Found"));

		int quantity = cartItemToUpdate.getQuantity();
		if (quantity > 1) {
			cartItemToUpdate.setQuantity(quantity - 1); // Decrease quantity by 1 or any desired decrement

			// Update the cart total and save the changes
			userCart.setCartItems(cartItems);
			userCart.setTotalAmount(calculateCartTotal(cartItems));
			cartRepository.save(userCart);
		} else {
			cartItems.remove(cartItemToUpdate);// Remove the cart item if the quantity becomes zero
			userCart.setCartItems(cartItems);	// Update the cart total and save the changes
			userCart.setTotalAmount(calculateCartTotal(cartItems));
			cartRepository.save(userCart);
		}

		return userCart;
	}

	@Override
	public void removeProductFromCart(Integer cartId, Integer productId) throws CartException {
		Cart existingCart = cartRepository.findById(cartId).orElseThrow(() -> new CartException("Cart Not Found"));

		List<CartItem> cartItems = existingCart.getCartItems();
		Optional<CartItem> cartItemToRemove = cartItems.stream()
				.filter(item -> item.getProduct().getProductId().equals(productId)).findFirst();

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
		Cart existingCart = cartRepository.findById(cartId).orElseThrow(() -> new CartException("Cart Not Found"));

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
		Cart existingCart = cartRepository.findById(cartId).orElseThrow(() -> new CartException("Cart Not Found"));

		existingCart.getCartItems().clear();
		existingCart.setTotalAmount(BigDecimal.ZERO);
		cartRepository.save(existingCart);
	}

}
