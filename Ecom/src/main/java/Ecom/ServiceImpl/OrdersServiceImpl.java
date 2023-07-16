package Ecom.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Exception.OrdersException;
import Ecom.Model.OrderItem;
import Ecom.Model.Orders;
import Ecom.Model.User;
import Ecom.ModelDTO.OrdersDTO;
import Ecom.Repository.OrderItemRepository;
import Ecom.Repository.OrderRepository;
import Ecom.Repository.UserRepository;
import Ecom.Service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	

	private final OrderRepository orderRepository;
	
	private final UserRepository userRepository;
	
	private final OrderItemRepository orderItemRepository;
	
	@Autowired
	public OrdersServiceImpl(OrderRepository orderRepository,UserRepository userRepository,OrderItemRepository orderItemRepository) {
		this.orderRepository=orderRepository;
		this.userRepository=userRepository;
		this.orderItemRepository=orderItemRepository;
	}
	

	@Override
	public Orders addOrders(Integer userId,Orders orders) throws OrdersException {
		
        User existingUser=userRepository.findById(userId)
                .orElseThrow(() -> new OrdersException("User Not Found"));

        // Set the user for the order
        orders.setUser(existingUser);

        Orders savedOrder = orderRepository.save(orders);

        // Save the order items
        List<OrderItem> orderItems = orders.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            //orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
        }

        return savedOrder;
	}

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
