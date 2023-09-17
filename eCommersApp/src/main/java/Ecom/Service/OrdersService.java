package Ecom.Service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import Ecom.Exception.OrdersException;
import Ecom.Model.Orders;
import Ecom.ModelDTO.OrdersDTO;

@Service 
public interface OrdersService {
	
	public OrdersDTO placeOrder(Integer orderId) throws OrdersException;
	
	public Orders updateOrders(Integer ordersid,OrdersDTO orderDTo)throws OrdersException;
	
	public Orders getOrdersDetails(Integer orderid)throws OrdersException;
	
	public List<Orders> getAllUserOrder(Integer userId)throws OrdersException;
	
	public List<Orders> viewAllOrders()throws OrdersException;
	
	public List<Orders> viewAllOrderByDate(Date date)throws OrdersException;
	
	public void deleteOrders(Integer userId,Integer Orderid)throws OrdersException;

}
