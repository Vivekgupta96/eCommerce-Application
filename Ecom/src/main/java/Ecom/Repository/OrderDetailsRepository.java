package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ecom.Model.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
