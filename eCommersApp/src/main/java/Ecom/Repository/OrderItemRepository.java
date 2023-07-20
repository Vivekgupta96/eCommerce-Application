package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ecom.Model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
