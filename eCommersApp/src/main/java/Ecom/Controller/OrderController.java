package Ecom.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ecom.Exception.CartException;
import Ecom.Exception.OrdersException;
import Ecom.Exception.ProductException;
import Ecom.Exception.UserException;
import Ecom.Model.Orders;
import Ecom.ModelDTO.OrdersDTO;
import Ecom.Service.OrdersService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrdersService ordersService;

	@Autowired
	public OrderController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	@PostMapping("/placed")
	public ResponseEntity<Orders> addProductToCart(@RequestBody OrdersDTO orderDto) {
		try {
			Orders placeOrder = ordersService.placeOrder(orderDto);
			return ResponseEntity.ok(placeOrder);
		} catch (CartException | ProductException | UserException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Orders> getOrdersDetails(@PathVariable Integer orderId) {
		try {
			Orders order = ordersService.getOrdersDetails(orderId);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (OrdersException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/orders/{userId}")
	public ResponseEntity<List<Orders>> getAllUserOrder(@PathVariable Integer userId) {
		try {
			List<Orders> orders = ordersService.getAllUserOrder(userId);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (OrdersException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<Orders>> viewAllOrders() {
		try {
			List<Orders> orders = ordersService.viewAllOrders();
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (OrdersException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/date/{date}")
	public ResponseEntity<List<Orders>> viewAllOrderByDate(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		try {
			List<Orders> orders = ordersService.viewAllOrderByDate(date);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (OrdersException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/users/{userId}/orders/{orderId}")
	public ResponseEntity<String> deleteOrders(@PathVariable Integer userId, @PathVariable Integer orderId) {
		try {
			ordersService.deleteOrders(userId, orderId);
			return new ResponseEntity<>("Order successfully deleted.", HttpStatus.OK);
		} catch (UserException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (OrdersException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
