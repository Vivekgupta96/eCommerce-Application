package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecom.Model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
   
}
