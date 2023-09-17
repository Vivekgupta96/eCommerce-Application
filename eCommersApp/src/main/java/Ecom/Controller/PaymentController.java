package Ecom.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Ecom.Model.Payment;
import Ecom.Service.PaymentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ecom/order-payments")
public class PaymentController {

	private final PaymentService paymentService;

	@PostMapping("/makePayment")
	public ResponseEntity<Payment> makePayment(@RequestParam Integer orderId, @RequestParam Integer userId
			) {
		Payment payment = paymentService.makePayment(orderId, userId);
		return ResponseEntity.ok(payment);
	}
}
