package Ecom.ServiceImpl;

import java.math.BigDecimal;
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
import Ecom.Repository.OrderItemRepository;
import Ecom.Repository.OrderRepository;
import Ecom.Repository.ProductRepository;
import Ecom.Repository.UserRepository;
import Ecom.Service.OrdersService;

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
	public OrdersServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
			OrderItemRepository orderItemRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	public Orders placeOrder(OrdersDTO orderDTO) throws OrdersException {
		User existingUser = userRepository.findById(orderDTO.getUserId())
				.orElseThrow(() -> new UserException("User Not Found In Database"));

		Integer cartId = existingUser.getCart().getCartId();

		// Create the order entity and save it to the database
		Orders order = new Orders();
		order.setStaus(OrderStatus.PENDING);
		order.setOrderDate(new Date());
		order.setUser(existingUser);
		existingUser.getOrders().add(order);
		userRepository.save(existingUser);

		// Create order items entities and save them to the database
		int sum = 0;
		List<OrderItem> orderItems = new ArrayList<>();
		
		for (CartItem itemDTO : existingUser.getCart().getCartItems()) {

			if (itemDTO.getCart().getCartId() == cartId) {

				OrderItem orderItem = new OrderItem();// creating New orderItem;

				orderItem.setOrders(order);
				orderItem.setProduct(itemDTO.getProduct());
				sum += itemDTO.getProduct().getPrice();
	
				orderItem.setQuantity(itemDTO.getQuantity());
				orderItems.add(orderItem);
			}
		}

		orderItemRepository.saveAll(orderItems);
		order.setTotalAmount(sum);
		cartItemRepository.removeAllProductFromCart(cartId);
	
		return orderRepository.save(order);

	}

//******************************************************************************
	@Override
	public Orders updateOrders(Integer ordersid, OrdersDTO orderDTo) throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders getOrdersDetails(Integer orderid) throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllUserOrder(Integer userId) throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> viewAllOrders() throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> viewAllOrderByDate(Date date) throws OrdersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrders(Integer userId, Integer Orderid) throws OrdersException {
		// TODO Auto-generated method stub

	}

}
