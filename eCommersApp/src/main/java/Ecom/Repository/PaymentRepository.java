package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ecom.Model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
}
