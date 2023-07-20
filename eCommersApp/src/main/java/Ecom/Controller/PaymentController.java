package Ecom.Controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Ecom.Exception.PaymentException;
import Ecom.Exception.UserException;
import Ecom.Model.Payment;
import Ecom.Service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping("/makePayment")
	public ResponseEntity<Payment> makePayment(@RequestParam Integer orderId, @RequestParam Integer userId,
			@RequestParam BigDecimal amount) {
		try {
			Payment payment = paymentService.makePayment(orderId, userId, amount);
			return ResponseEntity.ok(payment);
		} catch (PaymentException e) {

			return ResponseEntity.badRequest().body(null);
		} catch (UserException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
}