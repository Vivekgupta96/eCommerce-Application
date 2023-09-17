package Ecom.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    @Override
    public OrdersDTO placeOrder(Integer userId) throws OrdersException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User Not Found In Database"));

        Cart usercart = existingUser.getCart();
        if(usercart.getTotalAmount()==0){
            throw new OrdersException("Add item To the cart first.......");
        }
        Integer cartId = usercart.getCartId();

        Orders newOrder = new Orders();

        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus(OrderStatus.PENDING);

        existingUser.getOrders().add(newOrder);
        newOrder.setUser(existingUser);
        userRepository.save(existingUser);
        orderRepository.save(newOrder);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem itemDTO : usercart.getCartItems()) {
            System.out.println("inside the loop");
            if (itemDTO.getCart().getCartId() == cartId) {

                OrderItem orderItem = new OrderItem();// creating New orderItem;

                orderItem.setQuantity(itemDTO.getQuantity());
                orderItem.setProduct(itemDTO.getProduct());
                orderItem.setOrderId(newOrder.getOrderId());
                orderItems.add(orderItem);
                System.out.println("inside the loop and if");
            }
        }

        newOrder.setOrderItem(orderItems);
        newOrder.setTotalAmount(usercart.getTotalAmount());
        orderRepository.save(newOrder);


        usercart.setTotalAmount(usercart.getTotalAmount() - newOrder.getTotalAmount());
        cartItemRepository.removeAllProductFromCart(cartId);
        cartRepository.save(usercart);

        OrdersDTO orderdata=new OrdersDTO();
        orderdata.setOrderId(newOrder.getOrderId());
        orderdata.setOrderAmount(newOrder.getTotalAmount());
        orderdata.setStatus("Pending");
        orderdata.setPaymentStatus("Pending");
        orderdata.setOrderDate("Currebt Date");
        return orderdata;

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

        return null;
    }

}
