package Ecom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ecom.Exception.CartException;
import Ecom.Exception.ProductException;
import Ecom.Exception.UserException;
import Ecom.Model.Orders;
import Ecom.ModelDTO.OrdersDTO;
import Ecom.Service.OrdersService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrdersService ordersService;
	
	@PostMapping("/placed")
    public ResponseEntity<Orders> addProductToCart(@RequestBody  OrdersDTO orderDto) {
        try {
          Orders placeOrder = ordersService.placeOrder(orderDto);
            return ResponseEntity.ok(placeOrder);
        } catch (CartException | ProductException | UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
