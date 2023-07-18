package Ecom.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Exception.OrdersException;
import Ecom.Exception.UserException;
import Ecom.Model.CartItem;
import Ecom.Model.OrderDetails;
import Ecom.Model.Orders;
import Ecom.Model.Product;
import Ecom.Model.User;
import Ecom.ModelDTO.OrdersDTO;
import Ecom.Repository.OrderDetailsRepository;
import Ecom.Repository.OrderRepository;
import Ecom.Repository.UserRepository;
import Ecom.Service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	private final OrderRepository orderRepository;

	private final UserRepository userRepository;

	private final OrderDetailsRepository ordrDetailsRepository;

	@Autowired
	public OrdersServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
			OrderDetailsRepository ordrDetailsRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.ordrDetailsRepository = ordrDetailsRepository;
	}

	@Override
	public Orders placeOrder(Integer userId, Integer cartId) throws OrdersException {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User Not Found In Database"));

		// Step-1,create order
		Orders order = createOrder(existingUser);

		// step-2
		List<CartItem> cartItems = existingUser.getCart().getCartItems();
		for (CartItem cartItem : cartItems) {

			Product product = cartItem.getProduct();

			int quantity = cartItem.getQuantity();

			createOrderDetail(order, product, quantity);
		}
		//step-3
		double totalAmount = calculateTotalOrderAmount(order);
        order.setTotalAmount(totalAmount);
        updateOrder(order);
		return order;
	}

	private void updateOrder(Orders order) {
		orderRepository.save(order);
	}

	private double calculateTotalOrderAmount(Orders order) {
        double totalAmount = 0.0;
        for (OrderDetails orderDetail : order.getOrderDetails()) {
            totalAmount += (orderDetail.getQuantity()*orderDetail.getQuantity());
        }
        return totalAmount;
	}

	private Orders createOrder(User existingUser) {
		Orders order = new Orders();
		order.setUser(existingUser);
		order.setOrderDate(new Date());
		orderRepository.save(order);
		return order;
	}

	private void createOrderDetail(Orders order, Product product, int quantity) {

		OrderDetails orderDetails= new OrderDetails();
		orderDetails.setProduct(product);
		orderDetails.setOrders(order);
		orderDetails.setQuantity(quantity);
		ordrDetailsRepository.save(orderDetails);
		
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
