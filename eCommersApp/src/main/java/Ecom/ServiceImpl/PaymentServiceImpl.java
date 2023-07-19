package Ecom.ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Enum.OrderStatus;
import Ecom.Enum.PaymentMethod;
import Ecom.Enum.PaymentStatus;
import Ecom.Exception.PaymentException;
import Ecom.Exception.UserException;
import Ecom.Model.Orders;
import Ecom.Model.Payment;
import Ecom.Model.User;
import Ecom.Repository.OrderRepository;
import Ecom.Repository.PaymentRepository;
import Ecom.Repository.UserRepository;
import Ecom.Service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Payment makePayment(Integer orderId, Integer userId, BigDecimal amount) throws PaymentException {

		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User Not Found In Database"));
		Orders order = orderRepository.findByIdAndCustomerId(orderId, userId);
		if (order == null) {
			throw new PaymentException("Order not found for the given customer.");
		}

		LocalDateTime paymentDateTime = LocalDateTime.now();
		Payment payment = new Payment();

		payment.setOrder(order);
		payment.setPaymentAmount(amount);
		payment.setPaymentDate(paymentDateTime);
		payment.setPaymentMethod(PaymentMethod.UPI);
		payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
		order.setPayment(payment);
		order.setStaus(OrderStatus.SHIPPED);

		orderRepository.save(order);
		existingUser.getPayments().add(payment);
		userRepository.save(existingUser);
		return paymentRepository.save(payment);
	}

}
