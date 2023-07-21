package Ecom.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Enum.OrderStatus;
import Ecom.Exception.OrdersException;
import Ecom.Exception.UserException;
import Ecom.Model.Cart;
import Ecom.Model.CartItem;
import Ecom.Model.OrderItem;
import Ecom.Model.Orders;
import Ecom.Model.User;
import Ecom.ModelDTO.OrdersDTO;
import Ecom.Repository.CartItemRepository;
import Ecom.Repository.CartRepository;
import Ecom.Repository.OrderItemRepository;
import Ecom.Repository.OrderRepository;
import Ecom.Repository.ProductRepository;
import Ecom.Repository.UserRepository;
import Ecom.Service.OrdersService;
import jakarta.transaction.Transactional;

@Service
public class OrdersServiceImpl implements OrdersService {

	private final OrderRepository orderRepository;

	private final UserRepository userRepository;

	private final OrderItemRepository orderItemRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	public OrdersServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
			OrderItemRepository orderItemRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.orderItemRepository = orderItemRepository;

	}

	// **************************************************************************************
	@Override
	public Orders placeOrder(OrdersDTO orderDTO) throws OrdersException {
		User existingUser = userRepository.findById(orderDTO.getUserId())
				.orElseThrow(() -> new UserException("User Not Found In Database"));

		Cart usercart = existingUser.getCart();
		Integer cartId = usercart.getCartId();

		// Create the order entity and save it to the database
		Orders newOrder = new Orders();

		newOrder.setOrderDate(LocalDateTime.now());
		newOrder.setStaus(OrderStatus.PENDING);

		existingUser.getOrders().add(newOrder);
		newOrder.setUser(existingUser);
		userRepository.save(existingUser);
		// till now order save to user and database
		System.out.println("1");
		// Create order items entities and save them to the database

		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem itemDTO : usercart.getCartItems()) {
			System.out.println("inside the loop");
			if (itemDTO.getCart().getCartId() == cartId) {

				OrderItem orderItem = new OrderItem();// creating New orderItem;

				orderItem.setQuantity(itemDTO.getQuantity());
				orderItem.setProduct(itemDTO.getProduct());
				orderItem.setOrders(newOrder);
				orderItems.add(orderItem);
				System.out.println("inside the loop and if");
			}
		}
		newOrder.setOrderItem(orderItems);
		newOrder.setTotalAmount(usercart.getTotalAmount());
		orderRepository.save(newOrder);

		// removing all product from cart
		usercart.setTotalAmount(usercart.getTotalAmount() - newOrder.getTotalAmount());
		cartItemRepository.removeAllProductFromCart(cartId);
		cartRepository.save(usercart);
		System.out.println("exit");
		return newOrder;

	}

	@Transactional
	public Orders getOrdersDetails(Integer orderId) throws OrdersException {

		Orders order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrdersException("Order not found in the database."));

		return order;
	}

	@Override
	public List<Orders> getAllUserOrder(Integer userId) throws OrdersException {
		try {
			List<Orders> orders = orderRepository.getAllOrderByUserId(userId);
			if (orders.isEmpty()) {
				throw new OrdersException("No orders found for the user in the database.");
			}
			return orders;
		} catch (Exception e) {
			throw new OrdersException("Failed to fetch orders for the user: " + e.getMessage());
		}
	}

	@Override
	public List<Orders> viewAllOrders() throws OrdersException {

		List<Orders> orders = orderRepository.findAll();

		if (orders.isEmpty()) {
			throw new OrdersException("No orders found in the database.");
		}
		return orders;
	}

	@Override
	public List<Orders> viewAllOrderByDate(Date date) throws OrdersException {

		List<Orders> orders = orderRepository.findByOrderDateGreaterThanEqual(date);

		if (orders.isEmpty()) {
			throw new OrdersException("No orders found for the given date.");
		}

		return orders;

	}

	@Override
	public void deleteOrders(Integer userId, Integer Orderid) throws OrdersException {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User Not Found In Database"));
		Orders existingOrder = orderRepository.findById(Orderid)
				.orElseThrow(() -> new UserException("order Not Found In Database"));

		orderRepository.delete(existingOrder);
	}

	@Override
	public Orders updateOrders(Integer ordersid, OrdersDTO orderDTo) throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

}
