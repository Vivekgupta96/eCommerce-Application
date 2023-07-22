package Ecom.Service;

import java.math.BigDecimal;

import Ecom.Exception.PaymentException;
import Ecom.Model.Payment;

public interface PaymentService {
	
	 Payment makePayment(Integer orderId,Integer userId) throws PaymentException;
}
